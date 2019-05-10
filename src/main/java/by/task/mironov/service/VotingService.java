package by.task.mironov.service;

import by.task.mironov.entity.Voting;
import by.task.mironov.entity.VotingItem;
import by.task.mironov.role.Role;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class VotingService {

    @Resource
    public VotingStorageService votingStorageService;

    public UUID createVoting(String name) {
        Voting voting = new Voting(name);
        votingStorageService.save(voting);
        return voting.getId();
    }

    public void startVoting(UUID id) {
        Voting voting = votingStorageService.findById(id);
        if (!voting.getFlag().equals(Role.START)) {
            voting.setFlag(Role.START);
            votingStorageService.save(voting);
        }
    }

    public void stopVoting(UUID id) {
        Voting voting = votingStorageService.findById(id);
        if (!voting.getFlag().equals(Role.STOP)) {
            voting.setFlag(Role.STOP);
            votingStorageService.save(voting);
        }
    }

    public UUID createVotingItem(String name, UUID id) {
        VotingItem votingItem = new VotingItem(name);
        Voting voting = votingStorageService.findById(id);
        voting.getVotingItemMap().put(votingItem.getId(), votingItem);
        votingStorageService.save(voting);
        return votingItem.getId();
    }

    public Voting getVoting(UUID id) {
        return votingStorageService.findById(id);
    }

    public VotingItem getVotingItem(UUID id, UUID itemId) {
        Voting voting = votingStorageService.findById(id);
        return voting.getVotingItemMap().get(itemId);
    }

    public void doVote(UUID id, UUID itemId) {
        Voting voting = votingStorageService.findById(id);
        if (voting.getFlag().equals(Role.STOP)) {
            throw new IllegalArgumentException("you cannot vote because voting is stopped");
        }
        voting.getVotingItemMap().get(itemId).setItemsCount(voting.getVotingItemMap().get(itemId).getItemsCount() + 1);
        votingStorageService.save(voting);
    }
}
