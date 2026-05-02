package uz.vv.vertexlib.dtos.responses;

public record BookShortResponse(
        String id,
        String title,
        String author,
        Integer availableCopies
) {}
