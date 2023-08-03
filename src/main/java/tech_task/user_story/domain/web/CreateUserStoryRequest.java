package tech_task.user_story.domain.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateUserStoryRequest {
    @NotEmpty
    @Size(min = 3, max = 60)
    private String description;
}
