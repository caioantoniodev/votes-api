package edu.mentorship.votes.core.shared;

import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleExecutor {

    private final RqueueMessageEnqueuer rqueueMessageEnqueuer;

    public void includeSession(String identify, Long aLong) {

        // Nome da File, Mensagem(ID DA SEÇÃO), Fila, Delay (End Session)
        // rqueueMessageEnqueuer.enqueueIn()

    }
}
