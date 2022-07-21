package edu.mentorship.votes.application.usecase;

import edu.mentorship.votes.application.dto.InputStartSessionDto;
import edu.mentorship.votes.core.session.entity.Session;
import edu.mentorship.votes.core.session.entity.SessionState;
import edu.mentorship.votes.core.session.service.StartedSessionCommand;
import edu.mentorship.votes.core.stave.service.ChangeSessionState;
import edu.mentorship.votes.infra.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartSession {

    private final ChangeSessionState changeSessionState;

    private final StaveRepository staveRepository;

    private final StartedSessionCommand startedSessionCommand;

    public void startSession(String id, InputStartSessionDto inputStartSessionDto) {
        var stave = staveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stave not found"));

        if (Boolean.FALSE.equals(stave.verifyState())) {
            throw new RuntimeException();
        }

        changeSessionState.changeSessionInProgressState(stave.getIdentify());

        Session session = new Session();
        session.setSessionState(SessionState.IN_PROGRESS);
        session.setIdentify(stave.getIdentify());
        session.setTimeToLive(inputStartSessionDto.getTimeToLive());

        startedSessionCommand.execute(session);
    }
}
