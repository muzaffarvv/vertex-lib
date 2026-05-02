package uz.vv.vertexlib.dtos.responses;

import uz.vv.vertexlib.enums.UserRole;

public record UserShortResponse(
        String id,
        String fullName,
        UserRole role) {}
