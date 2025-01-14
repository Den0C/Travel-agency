package com.denys.travel_agency.service.serviceinterface;

import com.denys.travel_agency.dto.UserDTO;
import com.denys.travel_agency.exeption.EntityAlreadyExistsException;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    UserDTO register(UserDTO userDTO) throws EntityAlreadyExistsException;

    UserDTO updateUser(String username, UserDTO userDTO) throws EntityNotFoundException;

    UserDTO getUserByUsername(String username) throws EntityNotFoundException;

    UserDTO changeAccountStatus(UserDTO userDTO) throws EntityNotFoundException;

    UserDTO getUserById(UUID id) throws EntityNotFoundException;

    Page<UserDTO> getAllUsers(Pageable pageable, String nameFilter) throws IllegalArgumentException;

    UserDTO deleteByUsername(String username) throws EntityNotFoundException;

    Page<UserDTO> getAllUsersWhereRoleIsUser(Pageable pageable, String nameFilter);
}
