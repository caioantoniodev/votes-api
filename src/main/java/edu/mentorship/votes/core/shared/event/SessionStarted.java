package edu.mentorship.votes.core.shared.event;

import edu.mentorship.votes.core.session.SessionRepresentation;

public class SessionStarted extends EventApplication<SessionRepresentation> {

    private final SessionRepresentation sessionRepresentation;

    public SessionStarted(SessionRepresentation sessionRepresentation) {
        this.sessionRepresentation = sessionRepresentation;
    }

    @Override
    public SessionRepresentation getSessionRepresentation() {
        return sessionRepresentation;
    }
}
