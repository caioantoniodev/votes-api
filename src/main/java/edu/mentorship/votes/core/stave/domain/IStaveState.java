package edu.mentorship.votes.core.stave.domain;


import edu.mentorship.votes.infra.exception.IllegalStateStaveException;

public interface IStaveState {

    String getName();

    default void nextState(Stave stave) {
        throw new IllegalStateStaveException(stave.getState(), getName());
    }

    default void cancel(Stave stave) {
        throw new IllegalStateStaveException(stave.getState(), StateName.CANCEL.name());
    }

    enum StateName {
        CREATE, VOTING_IN_PROGRESS, CANCEL, CALCULATING_VOTES, SESSION_VOTES_DONE
    }
}
