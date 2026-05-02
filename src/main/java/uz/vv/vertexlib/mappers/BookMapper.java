package uz.vv.vertexlib.mappers;

import org.mapstruct.*;
import uz.vv.vertexlib.dtos.requests.BookCreateRequest;
import uz.vv.vertexlib.dtos.requests.BookUpdateRequest;
import uz.vv.vertexlib.dtos.responses.BookResponse;
import uz.vv.vertexlib.dtos.responses.BookShortResponse;
import uz.vv.vertexlib.entities.Book;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    @Mapping(target = "genre.id", source = "genreId")
    @Mapping(target = "availableCopies", source = "totalCopies") // Yangi kitobda barcha nusxalar mavjud deb olinadi
    Book toEntity(BookCreateRequest request);

    BookResponse toResponse(Book book);

    BookShortResponse toShortResponse(Book book);

    @Mapping(target = "genre.id", source = "genreId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(BookUpdateRequest request, @MappingTarget Book book);
}