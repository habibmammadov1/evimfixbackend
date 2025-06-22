package com.evimfix.evimfix.mapper.admin;

import com.evimfix.evimfix.dao.entites.concretes.user.User;
import com.evimfix.evimfix.dao.model.request.CreateAdminRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AdminMapper {

    @Mapping(target = "password", ignore = true)
    User createAdminRequestToUser(CreateAdminRequest createAdminRequest);
}
