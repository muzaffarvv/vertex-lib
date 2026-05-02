package uz.vv.vertexlib.mappers;

import org.mapstruct.*;
import uz.vv.vertexlib.dtos.requests.GenreRequest;
import uz.vv.vertexlib.dtos.responses.GenreResponse;
import uz.vv.vertexlib.entities.Genres;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GenreMapper {

    Genres toEntity(GenreRequest request);

    GenreResponse toResponse(Genres genre);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(GenreRequest request, @MappingTarget Genres genre);
}