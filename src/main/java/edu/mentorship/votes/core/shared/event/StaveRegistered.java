package edu.mentorship.votes.core.shared.event;

import edu.mentorship.votes.core.stave.StaveRepresentation;

import java.time.LocalDateTime;

public class StaveRegistered extends EventApplication<StaveRepresentation> {

    private final StaveRepresentation message;
    private final LocalDateTime timeStamp;

    public StaveRegistered(StaveRepresentation message) {
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    @Override
    public StaveRepresentation getMessage() {
        return message;
    }

    @Override
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
