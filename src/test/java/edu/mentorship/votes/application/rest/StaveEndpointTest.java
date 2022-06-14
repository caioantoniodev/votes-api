package edu.mentorship.votes.application.rest;

import edu.mentorship.votes.AbstractContextTest;
import edu.mentorship.votes.application.dto.InputNewStaveDto;
import edu.mentorship.votes.core.stave.domain.StateStave;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.structure.mapper.MessageMapper;
import edu.mentorship.votes.structure.repository.StaveRepository;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Locale;
import java.util.stream.Stream;

@RequiredArgsConstructor
class StaveEndpointTest extends AbstractContextTest {

    @Autowired
    private StaveRepository staveRepository;

    @ParameterizedTest(name = "{index} - [{arguments}]")
    @ValueSource(strings = {
            "plea",
            "excellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellencee"
    })
    void shouldReturnBadRequestWhenThemeLengthInvalid(String value) {
        var staveDto = new InputNewStaveDto()
                .description("battle")
                .theme(value);

        var locale = Locale.US;

        var message = messageSource.getMessage(MessageMapper.ARGUMENT_INVALID.getCode(), null, locale);

        RestAssured
                .given()
                .port(port)
                .header(HttpHeaders.ACCEPT_LANGUAGE, locale)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusCode", Matchers.is(HttpStatus.BAD_REQUEST.value()))
                .body("details.findAll {it}.descriptionError", Matchers.hasItems("size must be between 5 and 100"))
                .body("message", Matchers.is(message))
                .when()
                    .post("/api/v1/staves")
                .prettyPrint();
    }

    @ParameterizedTest(name = "{index} - [{arguments}]")
    @ValueSource(strings = "        ")
    @NullSource
    @EmptySource
    void shouldReturnBadRequestWhenThemeIsEmpty(String value) {
        var staveDto = new InputNewStaveDto()
                .description("battle")
                .theme(value);

        var locale = Locale.US;

        var message = messageSource.getMessage(MessageMapper.ARGUMENT_INVALID.getCode(), null, locale);

        RestAssured
                .given()
                .port(port)
                .header(HttpHeaders.ACCEPT_LANGUAGE, locale)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusCode", Matchers.is(HttpStatus.BAD_REQUEST.value()))
                .body("details.findAll {it}.descriptionError", Matchers.hasItems("must not be blank"))
                .body("message", Matchers.is(message))
                .when()
                .post("/api/v1/staves")
                .prettyPrint();
    }

    @ParameterizedTest(name = "{index} - [{arguments}]")
    @ValueSource(strings = {
            "plea",
            "excellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcellenceexcell"
    })
    void shouldReturnBadRequestWhenDescriptionLengthInvalid(String value) {
        var staveDto = new InputNewStaveDto()
                .description(value)
                .theme("battle");

        var locale = Locale.US;

        var message = messageSource.getMessage(MessageMapper.ARGUMENT_INVALID.getCode(), null, locale);

        RestAssured
                .given()
                .port(port)
                .header(HttpHeaders.ACCEPT_LANGUAGE, locale)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusCode", Matchers.is(HttpStatus.BAD_REQUEST.value()))
                .body("details.findAll {it}.descriptionError", Matchers.hasItems("size must be between 5 and 255"))
                .body("message", Matchers.is(message))
                .when()
                .post("/api/v1/staves")
                .prettyPrint();
    }

    @ParameterizedTest(name = "{index} - [{arguments}]")
    @NullSource
    void shouldReturnBadRequestWhenDescriptionIsEmpty(String value) {
        var staveDto = new InputNewStaveDto()
                .description(value)
                .theme("battle");

        var locale = Locale.US;

        var message = messageSource.getMessage(MessageMapper.ARGUMENT_INVALID.getCode(), null, locale);

        RestAssured
                .given()
                .port(port)
                .header(HttpHeaders.ACCEPT_LANGUAGE, locale)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusCode", Matchers.is(HttpStatus.BAD_REQUEST.value()))
                .body("details.findAll {it}.descriptionError", Matchers.hasItems("must not be null"))
                .body("message", Matchers.is(message))
                .when()
                .post("/api/v1/staves")
                .prettyPrint();
    }

    @ParameterizedTest(name = "{index} - [{arguments}]")
    @MethodSource("returnArgumentsConflictThemeVotes")
    void shouldReturnConflictWhenThemeAndDescriptionIsEqual(Stave stave) {

        staveRepository.save(stave);

        var staveDto = new InputNewStaveDto()
                .description("description")
                .theme("theme");

        var locale = Locale.US;

        var message = messageSource.getMessage(MessageMapper.THEME_CONFLICT.getCode(), null, locale);

        RestAssured
                .given()
                .port(port)
                .header(HttpHeaders.ACCEPT_LANGUAGE, locale)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                .statusCode(HttpStatus.CONFLICT.value())
                .body("statusCode", Matchers.is(HttpStatus.CONFLICT.value()))
                .body("message", Matchers.is(message))
                .when()
                .post("/api/v1/staves")
                .prettyPrint();
    }

    private static Stream<Arguments> returnArgumentsConflictThemeVotes() {
        var staveStateCreate = new Stave();

        staveStateCreate.setState(StateStave.CREATE.name());
        staveStateCreate.setDescription("description");
        staveStateCreate.setTheme("theme");

        var staveStateVotingInProgress = new Stave();

        staveStateVotingInProgress.setState(StateStave.VOTING_IN_PROGRESS.name());
        staveStateVotingInProgress.setDescription("description");
        staveStateVotingInProgress.setTheme("theme");

        var staveStateCancel = new Stave();

        staveStateCancel.setState(StateStave.CANCEL.name());
        staveStateCancel.setDescription("description");
        staveStateCancel.setTheme("theme");

        var staveStateCalculatingVotes = new Stave();

        staveStateCalculatingVotes.setState(StateStave.CALCULATING_VOTES.name());
        staveStateCalculatingVotes.setDescription("description");
        staveStateCalculatingVotes.setTheme("theme");

        return Stream.of(
                Arguments.of(staveStateCreate),
                Arguments.of(staveStateVotingInProgress),
                Arguments.of(staveStateCancel),
                Arguments.of(staveStateCalculatingVotes)
        );
    }
}
