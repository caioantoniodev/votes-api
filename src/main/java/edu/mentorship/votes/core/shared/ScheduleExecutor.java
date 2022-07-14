package edu.mentorship.votes.core.shared;

import com.github.sonus21.rqueue.core.RqueueMessageEnqueuer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScheduleExecutor {

    private final RqueueMessageEnqueuer rqueueMessageEnqueuer;

    public void includeSession() {

        // Nome da File, Mensagem(ID DA SEÇÃO), Fila, Delay (End Session)
        // rqueueMessageEnqueuer.enqueueIn()

    }
}
