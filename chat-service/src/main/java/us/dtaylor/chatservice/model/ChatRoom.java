package us.dtaylor.chatservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ChatRoom {
    @Id
    private String id;

    private String name;
}
