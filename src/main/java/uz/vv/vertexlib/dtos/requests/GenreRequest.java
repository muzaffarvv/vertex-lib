package uz.vv.vertexlib.dtos.requests;

import jakarta.validation.constraints.*;

public record GenreRequest(
        @NotBlank(message = "Janr nomi kiritilishi shart")
        @Size(max = 60)
        String name,

        @Size(max = 132)
        String description
) {}