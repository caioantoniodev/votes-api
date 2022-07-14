package edu.mentorship.votes.application.usecase;

import edu.mentorship.votes.application.dto.InputStartSessionDto;
import edu.mentorship.votes.core.stave.service.ChangeSessionState;
import edu.mentorship.votes.infra.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartSession {

    private final ChangeSessionState changeSessionState;

    private final StaveRepository staveRepository;

    public void startSession(String id, InputStartSessionDto inputStartSessionDto) {
        var stave = staveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stave not found"));

        if (Boolean.FALSE.equals(stave.verifyState())) {
            throw new RuntimeException();
        }

        changeSessionState.changeSessionInProgressState(stave.getIdentify());
    }
}
