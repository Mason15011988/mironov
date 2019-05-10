package by.task.mironov.controller;

import by.task.mironov.entity.Voting;
import by.task.mironov.entity.VotingItem;
import by.task.mironov.service.VotingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/votings")
public class VotingController {
    @Resource
    private VotingService votingService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public Voting getVoting(@PathVariable("id") UUID id) {
        return votingService.getVoting(id);
    }

    @GetMapping(path = "/{id}/items/{itemId}", produces = "application/json")
    public VotingItem getVotingItems(@PathVariable("id") UUID id, @PathVariable("itemId") UUID itemId) {
        return votingService.getVotingItem(id, itemId);
    }

    @PutMapping(path = "/{id}/items/{itemId}")
    public void doVote(@PathVariable("id") UUID id, @PathVariable("itemId") UUID itemId) {
        votingService.doVote(id, itemId);
    }

    @GetMapping(path = "/{id}/start")
    public void startVoting(@PathVariable("id") UUID id) {
        votingService.startVoting(id);
    }

    @GetMapping(path = "/{id}/stop")
    public void stopVoting(@PathVariable("id") UUID id) {
        votingService.stopVoting(id);
    }

    /**
     * Создаем тему для голосования и генерируем ссылку
     **/
    @PostMapping(path = "/{id}/items", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createVotingItem(@RequestBody String name, @PathVariable("id") UUID id) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(votingService.createVotingItem(name, id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Создаем вариант для голосования и генерируем ссылку
     **/
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createVoting(@RequestBody String name) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(votingService.createVoting(name))
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
