package edu.mentorship.votes.core.shared.event;

import java.time.LocalDateTime;

public abstract class EventApplication<M> {

    public abstract M getMessage();
    public abstract LocalDateTime getTimeStamp();
}
