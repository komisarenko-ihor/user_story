package tech_task.user_story.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_story")
public class UserStory {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @OneToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

    private String description;

    private String status;
}
