package tech_task.user_story.domain.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "desk_type")
public class DeskType {

    @Id
    private String id;

    private String type;
}
