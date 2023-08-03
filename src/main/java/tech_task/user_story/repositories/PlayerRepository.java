package tech_task.user_story.repositories;

import org.springframework.data.repository.CrudRepository;
import tech_task.user_story.domain.model.Player;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, String> {
    @Override
    List<Player> findAllById(Iterable<String> strings);
}
