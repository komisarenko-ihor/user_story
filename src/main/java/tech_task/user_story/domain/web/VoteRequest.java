package tech_task.user_story.domain.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoteRequest {
    @NotNull
    @Min(1)
    private Integer vote;
}
