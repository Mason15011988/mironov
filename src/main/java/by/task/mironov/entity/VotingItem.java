package by.task.mironov.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class VotingItem implements Serializable {
    private UUID id;
    private String name;
    private Integer itemsCount = 0;

    public VotingItem(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
