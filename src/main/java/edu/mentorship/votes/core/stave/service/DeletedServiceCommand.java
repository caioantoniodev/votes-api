package edu.mentorship.votes.core.stave.service;

import edu.mentorship.votes.core.shared.command.ServiceCommand;
import edu.mentorship.votes.core.stave.domain.Stave;

public class DeletedServiceCommand implements ServiceCommand<Stave, Stave> {
    @Override
    public Stave execute(Stave input) {
        return null;
    }
}
