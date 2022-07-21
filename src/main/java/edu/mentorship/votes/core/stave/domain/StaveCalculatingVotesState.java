package edu.mentorship.votes.core.stave.domain;

import edu.mentorship.votes.infra.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static edu.mentorship.votes.core.stave.domain.StateStave.CALCULATING_VOTES;
import static edu.mentorship.votes.core.stave.domain.StateStave.SESSION_VOTES_DONE;

@RequiredArgsConstructor
@Component("CALCULATING_VOTES")
public final class StaveCalculatingVotesState implements IStaveState {

    private final StaveRepository staveRepository;

    @Override
    public void nextState(Stave stave) {
        stave.setState(SESSION_VOTES_DONE.name());
        staveRepository.save(stave);
    }

    @Override
    public String getName() {
        return CALCULATING_VOTES.name();
    }
}
