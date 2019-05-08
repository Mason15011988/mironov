package by.task.mironov.service;

import by.task.mironov.entity.Voting;
import by.task.mironov.entity.VotingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VotingService {

    @Autowired
    public StorageService storageService;

    public UUID createVoting(String name) {
        Voting voting = new Voting();
        voting.setDescription(name);
        storageService.saveVoting(voting);
        return voting.getId();
    }

    public void startVoting(UUID id) {
        Voting voting = storageService.findVotingById(id);
        if (!voting.getFlag().equals("start")) {
            voting.setFlag("start");
            storageService.saveVoting(voting);
        }
    }

    public void stopVoting(UUID id) {
        Voting voting = storageService.findVotingById(id);
        if (!voting.getFlag().equals("stop")) {
            voting.setFlag("stop");
            storageService.saveVoting(voting);
        }
    }

    public UUID createVotingItem(String name, UUID id) {
        VotingItem votingItem = new VotingItem();
        votingItem.setDescription(name);
        Voting voting = storageService.findVotingById(id);
        voting.getVotingItemMap().put(votingItem.getId(), votingItem);
        storageService.saveVoting(voting);
        return votingItem.getId();
    }

    public Voting getVoting(UUID id) {
        return storageService.findVotingById(id);
    }

    public VotingItem getVotingItems(UUID id, UUID idItem) {
        Voting voting = storageService.findVotingById(id);
        return voting.getVotingItemMap().get(idItem);
    }

    public String voting(UUID id, UUID idItem) {
        Voting voting = storageService.findVotingById(id);
        if (voting.getFlag().equals("start")) {
            voting.getVotingItemMap().get(idItem).setItemsCount(voting.getVotingItemMap().get(idItem).getItemsCount() + 1);
            storageService.saveVoting(voting);
            return "voting is successful";
        }
        return "you cannot vote because voting is stopped";
    }
}
