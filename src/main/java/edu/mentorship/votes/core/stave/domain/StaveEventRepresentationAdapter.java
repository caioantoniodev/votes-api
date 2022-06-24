package edu.mentorship.votes.core.stave.domain;

import edu.mentorship.votes.core.stave.StaveRepresentation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor(staticName = "of")
@Getter
@Accessors(fluent = true)
public class StaveEventRepresentationAdapter implements StaveRepresentation {

    private final String identify;
    private final String theme;
    private final String description;
}
