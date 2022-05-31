package edu.mentorship.votes.structure.config.bean;

import edu.mentorship.votes.application.dto.InputNewStaveDto;
import edu.mentorship.votes.core.stave.domain.Stave;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Configuration
public class ModelMapperBean {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(STRICT);

        var typeMap = modelMapper.createTypeMap(InputNewStaveDto.class, Stave.class);

        typeMap.addMappings(new PropertyMap<InputNewStaveDto, Stave>() {
            @Override
            protected void configure() {
                map().setState("CREATE");
                map().setCreateAt(LocalDateTime.now(ZoneId.of("UTC")));
            }
        });

        return modelMapper;
    }
}
