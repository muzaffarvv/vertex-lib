package uz.vv.vertexlib.dtos.responses;

public record MovieShortResponse(
        String id,
        String title,
        String author,
        Integer availableCopies
) {}
