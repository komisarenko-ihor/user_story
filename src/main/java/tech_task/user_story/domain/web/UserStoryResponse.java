package tech_task.user_story.domain.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserStoryResponse {

    private String id;
    private String description;
    private List<String> members;
    private Integer vote;
}
