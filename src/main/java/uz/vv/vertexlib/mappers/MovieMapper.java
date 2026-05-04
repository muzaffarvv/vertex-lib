package uz.vv.vertexlib.mappers;

import org.mapstruct.*;
import uz.vv.vertexlib.dtos.requests.MovieCreateRequest;
import uz.vv.vertexlib.dtos.requests.MovieUpdateRequest;
import uz.vv.vertexlib.dtos.responses.MovieResponse;
import uz.vv.vertexlib.dtos.responses.MovieShortResponse;
import uz.vv.vertexlib.entities.Movie;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {

    @Mapping(target = "genre.id", source = "genreId")
    @Mapping(target = "availableCopies", source = "totalCopies")
    Movie toEntity(MovieCreateRequest request);

    MovieResponse toResponse(Movie movie);

    MovieShortResponse toShortResponse(Movie movie);

    @Mapping(target = "genre.id", source = "genreId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(MovieUpdateRequest request, @MappingTarget Movie movie);
}