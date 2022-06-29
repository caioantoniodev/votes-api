package edu.mentorship.votes.core.session;

import java.time.LocalDateTime;

public interface SessionRepresentation {

    String identify();
    String state();
    LocalDateTime createdAt();
    LocalDateTime finishedAt();
}
