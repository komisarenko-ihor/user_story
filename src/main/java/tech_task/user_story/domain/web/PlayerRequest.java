package tech_task.user_story.domain.web;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PlayerRequest {
    @NotNull
    @Size(min = 36, max = 36)
    private String id;
    @NotNull
    @Size(min = 3, max = 60)
    private String name;
}
