package edu.mentorship.votes.core.session.service;

import edu.mentorship.votes.core.shared.command.ServiceCommand;

public class StartedSessionCommand implements ServiceCommand<Object, Void> {
    @Override
    public Void execute(Object input) {
        return null;
    }
}
