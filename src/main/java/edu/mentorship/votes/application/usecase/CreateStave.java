package edu.mentorship.votes.application.usecase;

import edu.mentorship.votes.application.dto.InputNewStaveDto;
import edu.mentorship.votes.application.dto.StaveDto;
import edu.mentorship.votes.structure.repository.StaveRepository;
import edu.mentorship.votes.core.stave.domain.Stave;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateStave {

    private final ModelMapper modelMapper;

    private final StaveRepository staveRepository;

    @Transactional
    public StaveDto create(InputNewStaveDto inputNewStaveDto) {

        var staveExists = staveRepository.existsStaveByThemeAndStateNot(inputNewStaveDto.getTheme(),"SESSION_VOTES_DONE");

        if (staveExists) {
            throw new RuntimeException("");
        }

        var entity = modelMapper.map(inputNewStaveDto, Stave.class);

        entity.createAt();

        staveRepository.save(entity);

        return modelMapper.map(entity, StaveDto.class);
    }
}
