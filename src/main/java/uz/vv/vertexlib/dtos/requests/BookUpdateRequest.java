package uz.vv.vertexlib.dtos.requests;

public record BookUpdateRequest(
    String title,
    String author,
    String genreId,
    Integer publishedYear,
    Integer totalCopies
) {}

