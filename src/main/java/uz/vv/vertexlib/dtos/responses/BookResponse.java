package uz.vv.vertexlib.dtos.responses;

public record BookResponse(
        String id,
        String title,
        String isbn,
        String author,
        GenreResponse genre,
        Integer publishedYear,
        Integer totalCopies,
        Integer availableCopies
) {}
