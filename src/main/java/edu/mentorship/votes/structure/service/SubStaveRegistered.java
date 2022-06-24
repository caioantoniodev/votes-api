package edu.mentorship.votes.structure.service;

import edu.mentorship.votes.core.shared.event.StaveRegistered;
import edu.mentorship.votes.core.stave.StaveRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SubStaveRegistered {

    @Async
    @EventListener
    public void handleEvent(StaveRegistered staveRegistered) {
        StaveRepresentation message = staveRegistered.getMessage();

        log.info("subscribe message {} to queue ", message.identify());
    }
}