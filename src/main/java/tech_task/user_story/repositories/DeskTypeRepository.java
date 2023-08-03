package tech_task.user_story.repositories;

import org.springframework.data.repository.CrudRepository;
import tech_task.user_story.domain.model.DeskType;

import java.util.List;

public interface DeskTypeRepository extends CrudRepository<DeskType, String> {
    @Override
    List<DeskType> findAll();
}
