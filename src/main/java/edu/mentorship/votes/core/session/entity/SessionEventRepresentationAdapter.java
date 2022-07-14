package edu.mentorship.votes.core.session.entity;

import edu.mentorship.votes.core.session.SessionRepresentation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@RequiredArgsConstructor(staticName = "of")
@Getter
@Accessors(fluent = true)
public class SessionEventRepresentationAdapter implements SessionRepresentation {

    private final String identify;
    private final Long timeToLive;
    private final String state;
    private final LocalDateTime createdAt;
    private final LocalDateTime finishedAt;
}
