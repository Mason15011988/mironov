package by.task.mironov.service;

import by.task.mironov.entity.Voting;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {

    private Map<UUID, Voting> votingStorage = new HashMap<>();

    public void saveVoting(Voting voting) {
        votingStorage.put(voting.getId(), voting);
    }

    public Voting findVotingById(UUID id) {
        return votingStorage.get(id);
    }
}
