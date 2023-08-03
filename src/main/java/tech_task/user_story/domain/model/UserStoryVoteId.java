package tech_task.user_story.domain.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class UserStoryVoteId implements Serializable {

    private String playerId;
    private String sessionId;
    private String userStoryId;
}
