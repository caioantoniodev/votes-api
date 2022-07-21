package edu.mentorship.votes.core.stave.service;

import edu.mentorship.votes.core.stave.domain.IStaveState;
import edu.mentorship.votes.core.stave.domain.Stave;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeState {

    private final BeanFactory beanFactory;

    public void processNextState(Stave stave) {
        IStaveState state = beanFactory.getBean(stave.getState(), IStaveState.class);
        state.nextState(stave);
    }

    public void processCancel(Stave stave) {
        IStaveState state = beanFactory.getBean(stave.getState(), IStaveState.class);
        state.cancel(stave);
    }
}
