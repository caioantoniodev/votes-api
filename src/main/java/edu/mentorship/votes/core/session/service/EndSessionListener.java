package edu.mentorship.votes.core.session.service;

import com.github.sonus21.rqueue.annotation.RqueueListener;
import edu.mentorship.votes.core.session.entity.Session;
import edu.mentorship.votes.core.session.entity.SessionState;
import edu.mentorship.votes.core.stave.service.ChangeSessionState;
import edu.mentorship.votes.infra.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EndSessionListener {

    @Autowired
    private final SessionRepository sessionRepository;

    @Autowired
    private final ChangeSessionState changeSessionState;

    @Autowired
    private final FinishedSessionCommand finishedSessionCommand;

    @RqueueListener(value = "session-started")
    public void receivedEndSession(String identify) {
        log.debug("Receive message -> {} from queue {}", identify, "session-started");

        Optional<Session> optionalSession = sessionRepository.findById(identify);

        optionalSession.ifPresent(input -> changeSessionState.changeSessionFinishState(input.getIdentify()));

        finishedSessionCommand.execute(optionalSession.get());
    }
}
