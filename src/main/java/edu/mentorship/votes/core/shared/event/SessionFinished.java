package edu.mentorship.votes.core.shared.event;

import edu.mentorship.votes.core.session.SessionRepresentation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SessionFinished extends EventApplication<SessionRepresentation>{

    private final SessionRepresentation sessionRepresentation;

    @Override
    public SessionRepresentation getSessionRepresentation() {
        return sessionRepresentation;
    }
}
