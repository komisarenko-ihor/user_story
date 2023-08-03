package tech_task.user_story.domain.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionResponse {
    private String id;
    private String title;
    private String deskTypeId;
    private String playerId;
}
