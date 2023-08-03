package tech_task.user_story.repositories;


import org.springframework.data.repository.CrudRepository;
import tech_task.user_story.domain.model.SessionPlayer;
import tech_task.user_story.domain.model.SessionPlayerId;

import java.util.List;

public interface SessionPlayerRepository extends CrudRepository<SessionPlayer, SessionPlayerId> {
    List<SessionPlayer> findAllBySessionPlayerId_SessionId(String sessionId);
    void deleteAllBySessionPlayerId_SessionId(String sessionId);
}
