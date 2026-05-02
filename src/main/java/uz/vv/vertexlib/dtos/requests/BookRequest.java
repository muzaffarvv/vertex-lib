package uz.vv.vertexlib.dtos.requests;

public record BookRequest(
        String title,
        String isbn,
        String author,
        String genreId,
        Integer publishedYear,
        Integer totalCopies
) {}
