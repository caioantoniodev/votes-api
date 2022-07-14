package edu.mentorship.votes.core.session.service;

import edu.mentorship.votes.core.session.entity.SessionEventRepresentationAdapter;
import edu.mentorship.votes.core.shared.ScheduleExecutor;
import edu.mentorship.votes.core.shared.event.SessionStarted;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubSessionStarted {

    private final ScheduleExecutor scheduleExecutor;

    @Async
    @EventListener
    public void subSessionStarted(SessionStarted message) {
        SessionEventRepresentationAdapter sessionRepresentation = message.getSessionRepresentation();

        scheduleExecutor.includeSession(sessionRepresentation.identify(), sessionRepresentation.timeToLive());

        log.info("retrieve message {} to queue ", message.getTimeStamp());
    }
}
