package edu.mentorship.votes.core.session.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;


@Data
@Document(collection = "session")
public class Session {

    @Id
    private String identify;
    private SessionState sessionState;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    public Session() {
        this.sessionState = SessionState.CREATED;
        this.createdAt = LocalDateTime.now(ZoneId.of("UTC"));
    }
}
