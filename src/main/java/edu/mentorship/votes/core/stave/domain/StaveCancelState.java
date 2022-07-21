package edu.mentorship.votes.core.stave.domain;

import edu.mentorship.votes.infra.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("CANCEL")
@RequiredArgsConstructor
public final class StaveCancelState implements IStaveState {

    private final StaveRepository staveRepository;

    @Override
    public void nextState(Stave stave) {
        stave.setState(StateStave.CANCEL.name());
        staveRepository.save(stave);
        log.info("cancel stave by id {}", stave.getId());
    }

    @Override
    public void cancel(Stave stave) {
        log.info("cancel stave by id {}", stave.getId());
    }

    @Override
    public String getName() {
        return StateName.CANCEL.name();
    }
}
