package edu.mentorship.votes.core.session.service;

import edu.mentorship.votes.core.session.entity.Session;
import edu.mentorship.votes.core.stave.StaveRepresentation;
import edu.mentorship.votes.infra.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatedSession {

    private final SessionRepository sessionRepository;

    public void createdSession(StaveRepresentation message) {
        var session = new Session();

        session.setIdentify(message.identify());

        sessionRepository.save(session);
    }
}
