package edu.mentorship.votes.infra.repository;

import edu.mentorship.votes.core.stave.domain.Stave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaveRepository extends MongoRepository<Stave, String> {

    Boolean existsStaveByThemeAndStateNot(String theme, String state);
}
