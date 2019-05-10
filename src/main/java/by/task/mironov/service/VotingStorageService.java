package by.task.mironov.service;

import by.task.mironov.entity.Voting;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class VotingStorageService {

    private Map<UUID, Voting> votingStorage = new HashMap<>();

    public void save(Voting voting) {
        votingStorage.put(voting.getId(), voting);
    }

    public Voting findById(UUID id) {
        return votingStorage.get(id);
    }
}
