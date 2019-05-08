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

    @GetMapping(path = "/{id}/items/{idItem}", produces = "application/json")
    public VotingItem getVotingItems(@PathVariable("id") UUID id, @PathVariable("idItem") UUID idItem) {
        return votingService.getVotingItems(id, idItem);
    }

    @GetMapping(path = "/{id}/items/{idItem}/voting")
    public String voting(@PathVariable("id") UUID id, @PathVariable("idItem") UUID idItem) {
        return votingService.voting(id, idItem);
    }

    @GetMapping(path = "/{id}/start")
    public String startVoting(@PathVariable("id") UUID id) {
        votingService.startVoting(id);
        return "start voting";
    }

    @GetMapping(path = "/{id}/stop")
    public String stopVoting(@PathVariable("id") UUID id) {
        votingService.stopVoting(id);
        return "stop voting";
    }

    @PostMapping(path = "/{id}/items", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createVotingItem(@RequestBody String name, @PathVariable("id") UUID id) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(votingService.createVotingItem(name, id))
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createVoting(@RequestBody String name) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(votingService.createVoting(name))
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
