package edu.mentorship.votes.core.stave.domain;

import org.springframework.stereotype.Component;

@Component("SESSION_VOTES_DONE")
public final class StaveSessionVotesDoneState implements IStaveState {

    @Override
    public String getName() {
        return StateName.SESSION_VOTES_DONE.name();
    }
}
