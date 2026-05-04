package uz.vv.vertexlib.mappers;

import org.mapstruct.*;
import uz.vv.vertexlib.dtos.requests.LoanCreateRequest;
import uz.vv.vertexlib.dtos.requests.LoanUpdateRequest;
import uz.vv.vertexlib.dtos.responses.LoanResponse;
import uz.vv.vertexlib.entities.Loans;

@Mapper(componentModel = "spring",
        uses = {MovieMapper.class, UserMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface LoanMapper {

    @Mapping(target = "movie.id", source = "movieId")
    @Mapping(target = "member.id", source = "memberId")
    @Mapping(target = "staff.id", source = "staffId")
    @Mapping(target = "loanDate", expression = "java(java.time.Instant.now())")
    Loans toEntity(LoanCreateRequest request);

    LoanResponse toResponse(Loans loan);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(LoanUpdateRequest request, @MappingTarget Loans loan);
}