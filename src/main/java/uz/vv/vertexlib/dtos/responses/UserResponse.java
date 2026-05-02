package uz.vv.vertexlib.dtos.responses;

import uz.vv.vertexlib.enums.UserRole;

public record UserResponse(
        String id,
        String fullName,
        String phoneNumber,
        UserRole role
) {}
