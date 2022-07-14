package edu.mentorship.votes.core.stave.service;

import edu.mentorship.votes.core.session.entity.Session;
import edu.mentorship.votes.core.session.entity.SessionState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class ChangeSessionState {

    private final MongoTemplate mongoTemplate;

    public void changeSessionFinishState(String identify) {
        var query = Query.query(Criteria.where("identify").is(identify));

        var updateDefinition = Update.update("sessionState", SessionState.FINISH.name())
                .set("finishedAt", LocalDateTime.now(ZoneId.of("UTC")));

        mongoTemplate.findAndModify(query, updateDefinition, Session.class);
    }

    public void changeSessionInProgressState(String identify) {
        var query = Query.query(Criteria.where("identify").is(identify));

        var updateDefinition = Update.update("session_state", SessionState.IN_PROGRESS.name());

        mongoTemplate.findAndModify(query, updateDefinition, Session.class);
    }
}
