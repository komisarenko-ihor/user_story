package tech_task.user_story.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech_task.user_story.domain.web.CreatePlayerRequest;
import tech_task.user_story.domain.web.PlayerResponse;
import tech_task.user_story.services.PlayerService;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
@Tag(name = "Player", description = "Player management APIs")
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a player")
    public PlayerResponse create(@RequestBody @Valid CreatePlayerRequest playerRequest) {
        return playerService.create(playerRequest);
    }
}
