package edu.mentorship.votes.infra.config.bean;

import edu.mentorship.votes.core.shared.command.ServiceCommand;
import edu.mentorship.votes.core.stave.StaveRepresentation;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.core.stave.service.CreatedServiceCommand;
import edu.mentorship.votes.core.stave.service.DeletedServiceCommand;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static edu.mentorship.votes.core.shared.command.CommandNameConstants.CREATED_COMMAND;
import static edu.mentorship.votes.core.shared.command.CommandNameConstants.DELETED_COMMAND;

@Configuration
public class ServiceCommandBean {

    @Bean(CREATED_COMMAND)
    public ServiceCommand<Stave, StaveRepresentation> createdCommandConfig(ApplicationEventPublisher applicationEventPublisher) {
        return new CreatedServiceCommand(applicationEventPublisher);
    }

    @Bean(DELETED_COMMAND)
    public ServiceCommand<Stave, Stave> deletedCommandConfig() {
        return new DeletedServiceCommand();
    }
}
