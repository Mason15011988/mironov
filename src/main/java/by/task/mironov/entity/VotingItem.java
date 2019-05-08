package by.task.mironov.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class VotingItem implements Serializable {
    private String description;
    private UUID id;
    private Integer itemsCount = 0;

    public VotingItem() {
        this.id = UUID.randomUUID();
    }
}
