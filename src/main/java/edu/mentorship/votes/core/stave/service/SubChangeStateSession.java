package edu.mentorship.votes.core.stave.service;

import edu.mentorship.votes.core.session.entity.SessionEventRepresentationAdapter;
import edu.mentorship.votes.core.shared.event.SessionStarted;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.infra.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SubChangeStateSession {

    private final ChangeState changeState;
    private final StaveRepository staveRepository;

    @Async
    @EventListener
    public void subChangeStateSession(SessionStarted message) {
        SessionEventRepresentationAdapter sessionRepresentation = message.getSessionRepresentation();

        Stave stave = staveRepository.findById(sessionRepresentation.identify()).orElseThrow();

        changeState.processNextState(stave);

        log.info("retrieve message {} to queue ", message.getTimeStamp());
    }
}
