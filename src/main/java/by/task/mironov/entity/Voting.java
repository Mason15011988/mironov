package by.task.mironov.entity;

import by.task.mironov.role.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class Voting implements Serializable {
    private UUID id;
    private String name;
    private Role flag = Role.STOP;
    private Map<UUID,VotingItem> votingItemMap = new HashMap<>();

    public Voting(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}