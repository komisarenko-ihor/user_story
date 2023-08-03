package tech_task.user_story.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech_task.user_story.domain.dto.DeskTypeDto;
import tech_task.user_story.domain.model.DeskType;
import tech_task.user_story.domain.web.DeskTypeResponse;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeskTypeMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    DeskType deskTypeDtoToDeskType(DeskTypeDto deskTypeDto);

    DeskTypeResponse deskTypeToDeskTypeResponse(DeskType deskType);

    List<DeskTypeResponse> deskTypesToDeskTypeResponses(List<DeskType> deskTypes);
}
