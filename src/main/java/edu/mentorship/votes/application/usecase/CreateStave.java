package edu.mentorship.votes.application.usecase;

import edu.mentorship.votes.application.dto.InputNewStaveDto;
import edu.mentorship.votes.application.dto.StaveDto;
import edu.mentorship.votes.core.stave.domain.Stave;
import edu.mentorship.votes.structure.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateStave {

    private final ModelMapper modelMapper;

    private final StaveRepository staveRepository;

    public StaveDto create(InputNewStaveDto inputNewStaveDto) {

        var staveExists = staveRepository.existsStaveByThemeAndStateNot(inputNewStaveDto.getTheme(), "SESSION_VOTES_DONE");

        if (staveExists) {
            throw new RuntimeException("");
        }

        var entity = modelMapper.map(inputNewStaveDto, Stave.class);

        staveRepository.save(entity);

        return modelMapper.map(entity, StaveDto.class);
    }
}
