package edu.mentorship.votes.core.shared.event;

import edu.mentorship.votes.core.session.SessionRepresentation;
import edu.mentorship.votes.core.session.entity.SessionEventRepresentationAdapter;
import edu.mentorship.votes.core.stave.StaveRepresentation;

import java.time.LocalDateTime;

public class SessionStarted implements SessionRepresentation {

    private final SessionRepresentation sessionRepresentation;

    public SessionStarted(SessionRepresentation sessionRepresentation) {
        this.sessionRepresentation = sessionRepresentation;
    }

    @Override
    public String identify() {
        return null;
    }

    @Override
    public String state() {
        return null;
    }

    @Override
    public LocalDateTime createdAt() {
        return null;
    }

    @Override
    public LocalDateTime finishedAt() {
        return null;
    }
}
