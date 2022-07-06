package edu.mentorship.votes.core.session.service;

import edu.mentorship.votes.core.shared.event.StaveRegistered;
import edu.mentorship.votes.core.stave.StaveRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StaveRegisteredListener {

    private final CreatedSession createSession;

    @Async
    @EventListener
    public void handleEvent(StaveRegistered staveRegistered) {
        StaveRepresentation message = staveRegistered.getSessionRepresentation();

        createSession.createdSession(message);

        log.info(message.identify());
    }
}
