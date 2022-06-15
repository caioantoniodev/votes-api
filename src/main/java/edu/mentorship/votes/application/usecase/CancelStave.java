package edu.mentorship.votes.application.usecase;

import edu.mentorship.votes.core.shared.command.ServiceCommand;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.core.stave.service.DeletedServiceCommand;
import edu.mentorship.votes.structure.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static edu.mentorship.votes.core.shared.command.CommandNameConstants.DELETED_COMMAND;

@Component
public class CancelStave {

    private final ModelMapper modelMapper;

    private final StaveRepository staveRepository;

    private final ServiceCommand<Stave, Stave> serviceCommand;

    public CancelStave(ModelMapper modelMapper, StaveRepository staveRepository,
                       @Qualifier(DELETED_COMMAND) ServiceCommand<Stave, Stave> serviceCommand) {
        this.modelMapper = modelMapper;
        this.staveRepository = staveRepository;
        this.serviceCommand = serviceCommand;
    }

    public void cancelStave(String id) {
        // To Do
    }
}
