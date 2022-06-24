package edu.mentorship.votes.rest;

import edu.mentorship.votes.application.api.StaveApi;
import edu.mentorship.votes.application.dto.InputNewStaveDto;
import edu.mentorship.votes.application.dto.InputUpdateStaveDto;
import edu.mentorship.votes.application.dto.StaveDto;
import edu.mentorship.votes.application.usecase.CancelStave;
import edu.mentorship.votes.application.usecase.CreateStave;
import edu.mentorship.votes.application.usecase.UpdateStave;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/staves")
@RequiredArgsConstructor
public class StaveEndpoints implements StaveApi {

    private final CreateStave createStave;
    private final UpdateStave updateStave;
    private final CancelStave cancelStave;

    @Override
    @PostMapping
    public ResponseEntity<StaveDto> create(InputNewStaveDto inputNewStaveDto) {
        var payload = createStave.create(inputNewStaveDto);

        return ResponseEntity
                .status(CREATED)
                .body(payload);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<StaveDto> update(
            @PathVariable("id") String id,
            InputUpdateStaveDto inputUpdateStaveDto) {

        var payload = updateStave.update(id, inputUpdateStaveDto);

        return ResponseEntity.status(OK).body(payload);
    }

    @Override
    public ResponseEntity<Void> cancel(String id) {
        cancelStave.cancelStave(id);
        return ResponseEntity.noContent().build();
    }
}
