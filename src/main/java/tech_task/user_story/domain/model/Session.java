package tech_task.user_story.domain.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session")
public class Session {

    @Id
    private String id;

    private String title;

    @OneToOne
    @JoinColumn(name = "desk_type_id", referencedColumnName = "id")
    private DeskType deskType;

    @OneToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;
}
