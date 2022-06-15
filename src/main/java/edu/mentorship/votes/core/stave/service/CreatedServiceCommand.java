package edu.mentorship.votes.core.stave.service;

import edu.mentorship.votes.core.shared.command.ServiceCommand;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.structure.repository.StaveRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatedServiceCommand implements ServiceCommand<Stave, Stave> {

    private final StaveRepository staveRepository;

    @Override
    public Stave execute(Stave input) {
        return null;
    }

    // To Do
    /* Create session */
}
