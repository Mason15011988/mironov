package by.task.mironov.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class Voting implements Serializable {
    private String description;
    private UUID id;
    private String flag = "stop";
    private Map<UUID,VotingItem> votingItemMap = new HashMap<>();

    public Voting() {
        this.id = UUID.randomUUID();
    }
}