package tech_task.user_story.repositories;

import org.springframework.data.repository.CrudRepository;
import tech_task.user_story.domain.model.Session;

public interface SessionRepository extends CrudRepository<Session, String> {
}
