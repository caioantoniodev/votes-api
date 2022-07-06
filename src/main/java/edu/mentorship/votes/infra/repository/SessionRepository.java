package edu.mentorship.votes.infra.repository;

import edu.mentorship.votes.core.session.entity.Session;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<Session, String> {


}
