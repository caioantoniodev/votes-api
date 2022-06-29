package edu.mentorship.votes.core.shared.event;

import java.time.LocalDateTime;

public abstract class EventApplication<M> {

    private final LocalDateTime localDateTime;

    protected EventApplication() {
        localDateTime = LocalDateTime.now();
    }

    public abstract M getSessionRepresentation();
    public LocalDateTime getTimeStamp() {
        return localDateTime;
    }
}
