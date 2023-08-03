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
public class SessionPlayerResponse {
    private Session session;
    private List<Player> players;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Session {
        private String id;
        private String title;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Player {
        private String id;
        private String name;
        private List<Story> stories;

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Story {
            private String id;
            private String description;
            private String status;
        }
    }
}
