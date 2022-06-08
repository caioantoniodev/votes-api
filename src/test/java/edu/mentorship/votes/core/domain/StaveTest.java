package edu.mentorship.votes.core.domain;

import edu.mentorship.votes.core.stave.domain.Stave;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StaveTest {

    @ParameterizedTest(name = "{index} - [{arguments}]")
    @ValueSource(ints = 0)
    void shouldReturnStateCreateAndCreatedAt(int value) {
        var stave = new Stave();

        Assertions.assertThat(stave.getCreateAt()).isNotNull();
        Assertions.assertThat(stave.getUpdateAt()).isNotNull();
        Assertions.assertThat(stave.getTotalVotesYes()).isEqualTo(value);
        Assertions.assertThat(stave.getTotalVotesNo()).isEqualTo(value);
        Assertions.assertThat(stave.getTotalVoteInvalid()).isEqualTo(value);
    }
}
