package tech_task.user_story.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech_task.user_story.domain.web.*;
import tech_task.user_story.services.SessionService;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
@Tag(name = "Session", description = "Session management APIs")
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a session")
    public SessionResponse create(@RequestParam String playerId, @RequestBody @Valid CreateSessionRequest createSessionRequest) {
        return sessionService.create(playerId, createSessionRequest);
    }

    @PostMapping("/{sessionId}")
    @Operation(summary = "Add a player to the session")
    public SessionPlayerResponse addPlayerToSession(@PathVariable String sessionId, @RequestBody @Valid AddPlayerToSessionRequest request) {
        return sessionService.addPlayerToSession(sessionId, request);
    }

    @DeleteMapping("/{sessionId}")
    @Operation(summary = "Delete a session")
    public void deleteSession(@PathVariable String sessionId, @RequestParam String playerId) {
        sessionService.deleteSession(sessionId, playerId);
    }

    @PostMapping("/{sessionId}/user-story")
    @Operation(summary = "Add a user story")
    public SessionPlayerResponse addUserStory(@PathVariable String sessionId, @RequestParam String playerId, @RequestBody @Valid CreateUserStoryRequest request) {
        return sessionService.addUserStory(sessionId, playerId, request);
    }

    @DeleteMapping("/{sessionId}/user-story/{userStoryId}")
    @Operation(summary = "Delete a user story")
    public void deleteUserStory(@PathVariable String sessionId, @PathVariable String userStoryId, @RequestParam String playerId) {
        sessionService.deleteUserStory(sessionId, userStoryId, playerId);
    }

    @PutMapping("/{sessionId}/user-story/{userStoryId}/start-voting")
    @Operation(summary = "Start voting a user story")
    public void startVotingUserStory(@PathVariable String sessionId, @PathVariable String userStoryId, @RequestParam String playerId) {
        sessionService.startVotingUserStory(sessionId, userStoryId, playerId);
    }

    @PutMapping("/{sessionId}/user-story/{userStoryId}/finish-voting")
    @Operation(summary = "Finish voting a user story")
    public void finishVotingUserStory(@PathVariable String sessionId, @PathVariable String userStoryId, @RequestParam String playerId) {
        sessionService.finishVotingUserStory(sessionId, userStoryId, playerId);
    }

    @PostMapping("/{sessionId}/user-story/{userStoryId}/vote")
    @Operation(summary = "Vote a user story")
    public void vote(@PathVariable String sessionId, @PathVariable String userStoryId, @RequestParam String playerId, @RequestBody VoteRequest request) {
        sessionService.vote(sessionId, userStoryId, playerId, request);
    }

    @GetMapping("/{sessionId}/user-story")
    @Operation(summary = "Get user stories")
    public List<UserStoryResponse> getUserStories(@PathVariable String sessionId) {
        return sessionService.getUserStories(sessionId);
    }

    @GetMapping("/desk-types")
    @Operation(summary = "Get desk's types")
    public List<DeskTypeResponse> getDeskTypes() {
        return sessionService.getDeskTypes();
    }
}
