package edu.mentorship.votes.core.session.service;

import com.github.sonus21.rqueue.annotation.RqueueListener;
import edu.mentorship.votes.core.session.SessionRepresentation;
import edu.mentorship.votes.core.session.entity.Session;
import edu.mentorship.votes.core.session.entity.SessionEventRepresentationAdapter;
import edu.mentorship.votes.core.shared.command.ServiceCommand;
import edu.mentorship.votes.core.stave.StaveRepresentation;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.infra.repository.SessionRepository;
import edu.mentorship.votes.infra.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static edu.mentorship.votes.core.shared.command.CommandNameConstants.CREATED_COMMAND;
import static edu.mentorship.votes.core.shared.command.CommandNameConstants.SESSION_COMMAND;

@Slf4j
@Service
public class EndSessionListener {

    private final SessionRepository sessionRepository;
    private final ServiceCommand<Session, SessionRepresentation> serviceCommand;

    public EndSessionListener(@Qualifier(SESSION_COMMAND) ServiceCommand<Session, SessionRepresentation> serviceCommand, SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
        this.serviceCommand = serviceCommand;
    }

    @RqueueListener(value = "end-session")
    public void receivedEndSession(String identify) {
        log.debug("Receive message -> {} from queue {}", identify, "end-session");

        Optional<Session> optionalSession = sessionRepository.findById(identify);

        optionalSession.ifPresent(serviceCommand::execute);
    }
}
