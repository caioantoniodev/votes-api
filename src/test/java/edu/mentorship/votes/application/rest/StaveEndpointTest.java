package edu.mentorship.votes.application.rest;

import edu.mentorship.votes.AbstractContextTest;
import edu.mentorship.votes.application.dto.InputNewStaveDto;
import edu.mentorship.votes.structure.repository.StaveRepository;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.structure.mapper.MessageMapper;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapperType;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Locale;
import java.util.UUID;

@RequiredArgsConstructor
class StaveEndpointTest extends AbstractContextTest {

    @Autowired
    private StaveRepository staveRepository;

    @Test
    void shouldReturnBadRequestWhenThemeInvalid() {
        var staveDto = new InputNewStaveDto().description("battle");

        RestAssured
                .given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusCode", Matchers.is(HttpStatus.BAD_REQUEST.value()))
                .body("details.findAll {it}.field", Matchers.hasItems("theme"))
                .when()
                    .post("/api/staves")
                .prettyPrint();
    }

    @Test
    void shouldReturnBadRequestWhenDescriptionInvalid() {
        var staveDto = new InputNewStaveDto().theme("battle");

        RestAssured
                .given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusCode", Matchers.is(HttpStatus.BAD_REQUEST.value()))
                .body("details.findAll {it}.field", Matchers.hasItems("description"))
                .when()
                .post("/api/staves")
                .prettyPrint();
    }

    @Test
    void shouldReturnBadRequestWhenThemeLeesThanFive() {
        var staveDto = new InputNewStaveDto()
                .theme("also")
                .description("creature");

        var locale = Locale.US;

        var message = messageSource.getMessage(MessageMapper.ARGUMENT_INVALID.getCode(), null, locale);

        RestAssured
                .given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, locale)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusCode", Matchers.is(HttpStatus.BAD_REQUEST.value()))
                .body("details.findAll {it}.descriptionError", Matchers.hasItems("size must be between 5 and 100"))
                .body("message", Matchers.is(message))
                .when()
                .post("/api/staves")
                .prettyPrint();
    }

    @Test
    void shouldReturnBadRequestWhenDescriptionLeesThanFive() {
        var staveDto = new InputNewStaveDto()
                .theme("noise")
                .description("dust");

        RestAssured
                .given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("statusCode", Matchers.is(HttpStatus.BAD_REQUEST.value()))
                .body("details.findAll {it}.descriptionError", Matchers.hasItems("tamanho deve ser entre 5 e 255"))
                .when()
                .post("/api/staves")
                .prettyPrint();
    }

    @Test
    void shouldReturnConflictWhenTwoStavesIsEqual() {

        var entity = new Stave();

        entity.setTheme("Rest APIs");
        entity.setId(UUID.randomUUID().toString());
        entity.setDescription("Good Practices; Modern architecture");
        entity.createAt();

        staveRepository.save(entity);

        var staveDto = new InputNewStaveDto()
                .theme(entity.getTheme())
                .description(entity.getDescription());

        var locale = Locale.US;

        var message = messageSource.getMessage(MessageMapper.THEME_CONFLICT.getCode(), null, locale);

        RestAssured
                .given()
                .port(port)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, locale)
                .body(staveDto, ObjectMapperType.JACKSON_2)
                .expect()
                .statusCode(HttpStatus.CREATED.value())
                .body("statusCode", Matchers.is(HttpStatus.CONFLICT.value()))
                .body("message", Matchers.is(message))
                .when()
                .post("/api/staves")
                .prettyPrint();
    }
}