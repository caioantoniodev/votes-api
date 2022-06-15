package edu.mentorship.votes.core.shared.command;

public interface ServiceCommand<In, Out> {

    Out execute(In input);
}
