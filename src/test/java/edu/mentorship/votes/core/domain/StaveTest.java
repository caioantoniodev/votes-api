package edu.mentorship.votes.core.domain;

import edu.mentorship.votes.core.stave.domain.Stave;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StaveTest {

    @Test
    void shouldReturnStateCreateAndCreatedAt() {
        var stave = new Stave();

        Assertions.assertThat(stave.getCreateAt()).isNotNull();
        Assertions.assertThat(stave.getUpdateAt()).isNotNull();
        Assertions.assertThat(stave.getTotalVotesYes()).isEqualTo(0);
        Assertions.assertThat(stave.getTotalVotesNo()).isEqualTo(0);
        Assertions.assertThat(stave.getTotalVoteInvalid()).isEqualTo(0);
    }
}
