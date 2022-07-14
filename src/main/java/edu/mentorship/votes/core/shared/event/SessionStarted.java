package edu.mentorship.votes.core.shared.event;

import edu.mentorship.votes.core.session.entity.SessionEventRepresentationAdapter;


public class SessionStarted extends EventApplication<SessionEventRepresentationAdapter> {

    private final SessionEventRepresentationAdapter sessionRepresentation;

    public SessionStarted(SessionEventRepresentationAdapter sessionRepresentation) {
        this.sessionRepresentation = sessionRepresentation;
    }

    @Override
    public SessionEventRepresentationAdapter getSessionRepresentation() {
        return sessionRepresentation;
    }
}
