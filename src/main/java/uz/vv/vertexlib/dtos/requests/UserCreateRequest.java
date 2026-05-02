package uz.vv.vertexlib.dtos.requests;

import uz.vv.vertexlib.enums.UserRole;

public record UserCreateRequest(
        String fullName,
        String phoneNumber,
        String password,
        UserRole role
) {}
