package edu.mentorship.votes.application.usecase;

import edu.mentorship.votes.application.dto.InputUpdateStaveDto;
import edu.mentorship.votes.application.dto.StaveDto;
import edu.mentorship.votes.structure.repository.StaveRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStave {

    private final ModelMapper modelMapper;

    private final StaveRepository staveRepository;

    public StaveDto update(String id, InputUpdateStaveDto inputUpdateStaveDto) {

        var staveOptional = staveRepository.findById(id);

        if (staveOptional.isEmpty())
            throw new RuntimeException();

        var entity = staveOptional.get();

        if (!entity.getTheme().equals(inputUpdateStaveDto.getTheme())) {
            var staveExists = staveRepository.existsStaveByThemeAndStateNot(
                    inputUpdateStaveDto.getTheme(), "SESSION_VOTES_DONE");

            if (staveExists)
                throw new RuntimeException();
        }

        entity.setTheme(inputUpdateStaveDto.getTheme());
        entity.setDescription(inputUpdateStaveDto.getDescription());
        entity.updateAt();

        var staveUpdated = staveRepository.save(entity);

        return modelMapper.map(staveUpdated, StaveDto.class);
    }
}
