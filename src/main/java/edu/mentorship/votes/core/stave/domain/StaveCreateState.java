package edu.mentorship.votes.core.stave.domain;

import edu.mentorship.votes.infra.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("CREATE")
@RequiredArgsConstructor
public final class StaveCreateState implements IStaveState {

    private final StaveRepository staveRepository;

    @Override
    public void nextState(Stave stave) {
        log.info("Change State to stave");
        stave.setState(StateName.VOTING_IN_PROGRESS.name());
        staveRepository.save(stave);
    }

    @Override
    public void cancel(Stave stave) {
        stave.setState(StateName.CANCEL.name());
    }

    @Override
    public String getName() {
        return StateName.CREATE.name();
    }
}
