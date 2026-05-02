package uz.vv.vertexlib.mappers;

import org.mapstruct.*;
import uz.vv.vertexlib.dtos.requests.UserRequest;
import uz.vv.vertexlib.dtos.responses.UserResponse;
import uz.vv.vertexlib.dtos.responses.UserShortResponse;
import uz.vv.vertexlib.entities.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(UserRequest request);

    @Mapping(target = "fullName", source = "fullName")
    UserResponse toResponse(User user);

    UserShortResponse toShortResponse(User user);
}