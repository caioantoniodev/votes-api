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
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
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
    @ValueSource(strings = {
            "        ",
            ""
    })
    @NullSource
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
    void shouldReturnConflict(Stave stave) {

        staveRepository.save(stave);

        var staveDto = new InputNewStaveDto()
                .description("description")
                .theme("theme1");

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
        var stave = new Stave();

        stave.setState(StateStave.CREATE.name());
        stave.setDescription("description");
        stave.setTheme("theme1");

        var stave2 = new Stave();

        stave2.setState(StateStave.VOTING_IN_PROGRESS.name());
        stave2.setDescription("description");
        stave2.setTheme("theme1");

        var stave3 = new Stave();

        stave3.setState(StateStave.CANCEL.name());
        stave3.setDescription("description");
        stave3.setTheme("theme1");

        var stave4 = new Stave();

        stave4.setState(StateStave.CALCULATING_VOTES.name());
        stave4.setDescription("description");
        stave4.setTheme("theme1");

        return Stream.of(
                Arguments.of(stave),
                Arguments.of(stave2),
                Arguments.of(stave3),
                Arguments.of(stave4)
        );
    }
}
