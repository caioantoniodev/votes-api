package edu.mentorship.votes.core.stave.domain;

import edu.mentorship.votes.infra.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("VOTING_IN_PROGRESS")
@RequiredArgsConstructor
public final class StaveVotingInProgressState implements IStaveState {

    private final StaveRepository  staveRepository;

    @Override
    public void nextState(Stave stave) {
        stave.setState(StateName.CALCULATING_VOTES.name());
        staveRepository.save(stave);
    }

    @Override
    public void cancel(Stave stave) {
        stave.setState(StateName.CANCEL.name());
    }

    @Override
    public String getName() {
        return StateName.VOTING_IN_PROGRESS.name();
    }
}
