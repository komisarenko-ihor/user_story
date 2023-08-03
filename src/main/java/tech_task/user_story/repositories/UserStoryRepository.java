package tech_task.user_story.repositories;

import org.springframework.data.repository.CrudRepository;
import tech_task.user_story.domain.model.UserStory;

import java.util.List;

public interface UserStoryRepository extends CrudRepository<UserStory, String> {
    List<UserStory> findAllBySession_Id(String sessionId);
    List<UserStory> findAllBySession_IdAndStatus(String sessionId, String status);
    void deleteBySessionIdAndPlayerId(String sessionId, String playerId);
    void deleteBySessionId(String sessionId);
}
