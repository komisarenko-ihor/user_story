package tech_task.user_story.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech_task.user_story.domain.dto.DeskTypeDto;
import tech_task.user_story.services.SessionService;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final SessionService sessionService;

    @Override
    public void run(String... args) throws Exception {
        sessionService.createDeskType(new DeskTypeDto("Desk Type 1"));
        sessionService.createDeskType(new DeskTypeDto("Desk Type 2"));
        sessionService.createDeskType(new DeskTypeDto("Desk Type 3"));
    }
}

