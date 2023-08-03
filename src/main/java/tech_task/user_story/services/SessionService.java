package tech_task.user_story.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech_task.user_story.domain.UserStoryStatus;
import tech_task.user_story.domain.dto.DeskTypeDto;
import tech_task.user_story.domain.model.Player;
import tech_task.user_story.domain.model.Session;
import tech_task.user_story.domain.model.UserStory;
import tech_task.user_story.domain.model.UserStoryVote;
import tech_task.user_story.domain.web.*;
import tech_task.user_story.mappers.*;
import tech_task.user_story.repositories.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final SessionPlayerRepository sessionPlayerRepository;
    private final PlayerRepository playerRepository;
    private final UserStoryRepository userStoryRepository;
    private final UserStoryVoteRepository userStoryVoteRepository;
    private final DeskTypeRepository deskTypeRepository;
    private final SessionMapper sessionMapper;
    private final SessionPlayerMapper sessionPlayerMapper;
    private final UserStoryMapper userStoryMapper;
    private final UserStoryVoteMapper userStoryVoteMapper;
    private final DeskTypeMapper deskTypeMapper;

    @Transactional
    public SessionResponse create(String playerId, CreateSessionRequest createSessionRequest) {
        SessionResponse sessionResponse = sessionMapper.sessionToSessionResponse(
                sessionRepository.save(sessionMapper.createSessionRequestToSession(playerId, createSessionRequest)));
        sessionPlayerRepository.save(sessionPlayerMapper.toSessionPlayer(playerId, sessionResponse.getId()));
        return sessionResponse;
    }

    public SessionPlayerResponse addPlayerToSession(String sessionId, AddPlayerToSessionRequest request) {
        sessionPlayerRepository.save(sessionPlayerMapper.toSessionPlayer(request.getPlayerId(), sessionId));
        return toSessionPlayerResponse(sessionId);
    }

    public SessionPlayerResponse addUserStory(String sessionId, String playerId, CreateUserStoryRequest request) {
        userStoryRepository.save(userStoryMapper.toUserStory(sessionId, playerId, request));
        return toSessionPlayerResponse(sessionId);
    }

    @Transactional
    public void deleteSession(String sessionId, String playerId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow();
        if (playerId.equals(session.getPlayer().getId())) {
            userStoryVoteRepository.deleteAllByUserStoryVoteId_SessionId(sessionId);
            userStoryRepository.deleteBySessionId(sessionId);
            sessionPlayerRepository.deleteAllBySessionPlayerId_SessionId(sessionId);
            sessionRepository.deleteById(sessionId);
        } else {
            throw new RuntimeException("Player is not the owner of the session");
        }
    }

    @Transactional
    public void deleteUserStory(String sessionId, String userStoryId, String playerId) {
        UserStory userStory = userStoryRepository.findById(userStoryId).orElseThrow();
        if (sessionId.equals(userStory.getSession().getId()) && playerId.equals(userStory.getPlayer().getId())) {
            userStoryVoteRepository.deleteAllByUserStoryVoteId_UserStoryId(userStoryId);
            userStoryRepository.deleteBySessionIdAndPlayerId(sessionId, playerId);
        } else {
            throw new RuntimeException("Wrong session or player");
        }
    }

    public void startVotingUserStory(String sessionId, String userStoryId, String playerId) {
        changeUserStoryStatus(sessionId, userStoryId, playerId, UserStoryStatus.VOTING.name());
    }

    public void finishVotingUserStory(String sessionId, String userStoryId, String playerId) {
        changeUserStoryStatus(sessionId, userStoryId, playerId, UserStoryStatus.VOTED.name());
    }

    public void vote(String sessionId, String userStoryId, String playerId, VoteRequest request) {
        UserStory userStory = userStoryRepository.findById(userStoryId).orElseThrow();
        if (!UserStoryStatus.VOTING.name().equals(userStory.getStatus())) {
            throw new RuntimeException("User story has wrong voting status");
        }
        if (sessionId.equals(userStory.getSession().getId())) {
            Set<String> players = sessionPlayerRepository.findAllBySessionPlayerId_SessionId(sessionId)
                    .stream()
                    .map(s -> s.getSessionPlayerId().getPlayerId())
                    .collect(Collectors.toSet());
            if (players.contains(playerId)) {
                userStoryVoteRepository.save(userStoryVoteMapper.toUserStoryVote(sessionId, userStoryId, playerId, request));
            } else {
                throw new RuntimeException("Player doesnt belong to the session");
            }
        } else {
            throw new RuntimeException("Wrong sessionId");
        }
    }

    public List<UserStoryResponse> getUserStories(String sessionId) {
        List<UserStory> userStories = userStoryRepository.findAllBySession_IdAndStatus(sessionId, UserStoryStatus.VOTING.name());
        List<UserStoryVote> userStoriesVotes = userStoryVoteRepository.findByUserStoryVoteId_UserStoryIdIn(userStories.stream().map(UserStory::getId).toList());
        Map<String, Player> playersIdMap = playerRepository.findAllById(userStoriesVotes.stream().map(v -> v.getUserStoryVoteId().getPlayerId()).toList())
                .stream().collect(Collectors.toMap(Player::getId, Function.identity()));

        Map<String, List<UserStoryVote>> userStoryVotesMap =
                userStoryVoteRepository.findByUserStoryVoteId_UserStoryIdIn(userStories.stream().map(UserStory::getId).toList())
                .stream()
                .collect(Collectors.groupingBy(v -> v.getUserStoryVoteId().getUserStoryId()));

        return userStories
                .stream()
                .map(u -> new UserStoryResponse(
                        u.getId(),
                        u.getDescription(),
                        toPlayersNames(userStoryVotesMap.getOrDefault(u.getId(), Collections.emptyList()), playersIdMap),
                        userStoryVotesMap.getOrDefault(u.getId(), Collections.emptyList())
                                .stream()
                                .map(UserStoryVote::getVote)
                                .reduce(0, Integer::sum)))
                .collect(Collectors.toList());
    }

    public List<DeskTypeResponse> getDeskTypes() {
        return deskTypeMapper.deskTypesToDeskTypeResponses(deskTypeRepository.findAll());
    }

    public DeskTypeResponse createDeskType(DeskTypeDto deskTypeDto) {
        return deskTypeMapper.deskTypeToDeskTypeResponse(deskTypeRepository.save(deskTypeMapper.deskTypeDtoToDeskType(deskTypeDto)));
    }

    private void changeUserStoryStatus(String sessionId, String userStoryId, String playerId, String status) {
        UserStory userStory = userStoryRepository.findById(userStoryId).orElseThrow();
        if (sessionId.equals(userStory.getSession().getId()) && playerId.equals(userStory.getPlayer().getId())) {
            userStory.setStatus(status);
            userStoryRepository.save(userStory);
        }
    }

    private SessionPlayerResponse toSessionPlayerResponse(String sessionId) {
        Session session = sessionRepository.findById(sessionId).orElseThrow();
        List<String> playersIds = sessionPlayerRepository.findAllBySessionPlayerId_SessionId(sessionId)
                .stream().map(s -> s.getSessionPlayerId().getPlayerId()).toList();
        List<Player> players = playerRepository.findAllById(playersIds);

        Map<String, List<UserStory>> userIdStoryMap = userStoryRepository.findAllBySession_Id(sessionId)
                .stream()
                .collect(Collectors.groupingBy(s -> s.getPlayer().getId()));
        return new SessionPlayerResponse(
                new SessionPlayerResponse.Session(session.getId(), session.getTitle()),
                players
                        .stream()
                        .map(p -> new SessionPlayerResponse.Player(
                                p.getId(),
                                p.getName(),
                                userIdStoryMap.getOrDefault(p.getId(), Collections.emptyList())
                                        .stream()
                                        .map(s -> new SessionPlayerResponse.Player.Story(
                                                s.getId(),
                                                s.getDescription(),
                                                s.getStatus()
                                        ))
                                        .collect(Collectors.toList())))
                        .collect(Collectors.toList()));
    }

    private static List<String> toPlayersNames(List<UserStoryVote> votes, Map<String, Player> playersIdMap) {
        return votes.stream().map(v -> playersIdMap.get(v.getUserStoryVoteId().getPlayerId()).getName()).toList();
    }
}
