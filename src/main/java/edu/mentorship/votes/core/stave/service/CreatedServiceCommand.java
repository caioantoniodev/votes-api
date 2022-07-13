package edu.mentorship.votes.core.stave.service;

import edu.mentorship.votes.core.shared.command.ServiceCommand;
import edu.mentorship.votes.core.shared.event.StaveRegistered;
import edu.mentorship.votes.core.stave.StaveRepresentation;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.core.stave.domain.StaveEventRepresentationAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class CreatedServiceCommand implements ServiceCommand<Stave, StaveRepresentation> {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public StaveRepresentation execute(Stave input) {
        StaveRepresentation representationAdapter = StaveEventRepresentationAdapter.of(input.getIdentify(),
                input.getTheme(), input.getDescription());

        StaveRegistered staveRegistered = new StaveRegistered(representationAdapter);

        applicationEventPublisher.publishEvent(staveRegistered);

        return representationAdapter;
    }
}
