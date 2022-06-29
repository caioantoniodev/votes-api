package edu.mentorship.votes.core.shared.event;

import edu.mentorship.votes.core.stave.StaveRepresentation;

public class StaveRegistered extends EventApplication<StaveRepresentation> {

    private final StaveRepresentation staveRepresentation;

    public StaveRegistered(StaveRepresentation staveRepresentation) {
        this.staveRepresentation = staveRepresentation;
    }


    @Override
    public StaveRepresentation getSessionRepresentation() {
        return staveRepresentation;
    }
}
