package com.denys.travel_agency.mapper;

import com.denys.travel_agency.dto.UserDTO;
import com.denys.travel_agency.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toUserDTO(User any);
    @Mapping(target = "vouchers", ignore = true)
    User toUser(UserDTO any);
}
