package edu.mentorship.votes.core.session.service;

import edu.mentorship.votes.core.session.SessionRepresentation;
import edu.mentorship.votes.core.session.entity.Session;
import edu.mentorship.votes.core.session.entity.SessionEventRepresentationAdapter;
import edu.mentorship.votes.core.shared.command.ServiceCommand;
import edu.mentorship.votes.core.shared.event.SessionFinished;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FinishedSessionCommand implements ServiceCommand<Session, SessionRepresentation> {

    @Autowired
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public SessionRepresentation execute(Session input) {

        SessionRepresentation sessionRepresentation = SessionEventRepresentationAdapter
                .of(input.getIdentify(), input.getTimeToLive(), input.getSessionState().name(), input.getCreatedAt(), input.getFinishedAt());

        SessionFinished sessionFinished = new SessionFinished(sessionRepresentation);

        applicationEventPublisher.publishEvent(sessionFinished);

        return sessionRepresentation;
    }
}
