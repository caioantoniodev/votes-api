package edu.mentorship.votes.infra.config.bean;

import edu.mentorship.votes.core.session.SessionRepresentation;
import edu.mentorship.votes.core.session.entity.Session;
import edu.mentorship.votes.core.session.service.EndSessionListener;
import edu.mentorship.votes.core.session.service.StartedSessionCommand;
import edu.mentorship.votes.core.shared.command.ServiceCommand;
import edu.mentorship.votes.core.stave.StaveRepresentation;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.core.stave.service.CreatedServiceCommand;
import edu.mentorship.votes.core.stave.service.DeletedServiceCommand;
import edu.mentorship.votes.infra.repository.SessionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static edu.mentorship.votes.core.shared.command.CommandNameConstants.*;

@Configuration
public class ServiceCommandBean {

    @Bean(CREATED_COMMAND)
    public ServiceCommand<Stave, StaveRepresentation> createdCommandConfig(ApplicationEventPublisher applicationEventPublisher) {
        return new CreatedServiceCommand(applicationEventPublisher);
    }

    @Bean(SESSION_COMMAND)
    public ServiceCommand<Session, SessionRepresentation> sessionCommandConfig(ApplicationEventPublisher applicationEventPublisher) {
        return new StartedSessionCommand(applicationEventPublisher);
    }

    @Bean(DELETED_COMMAND)
    public ServiceCommand<Stave, Stave> deletedCommandConfig() {
        return new DeletedServiceCommand();
    }
}
