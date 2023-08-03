package tech_task.user_story.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session_player")
public class SessionPlayer {

    @EmbeddedId
    private SessionPlayerId sessionPlayerId;
}
