package tech_task.user_story.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_story_vote")
public class UserStoryVote {

    @EmbeddedId
    private UserStoryVoteId userStoryVoteId;

    private Integer vote;
}
