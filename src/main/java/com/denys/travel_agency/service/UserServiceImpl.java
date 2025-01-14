package com.denys.travel_agency.service;


import com.denys.travel_agency.dto.UserDTO;
import com.denys.travel_agency.enums.Role;
import com.denys.travel_agency.exeption.EntityAlreadyExistsException;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.mapper.UserMapper;
import com.denys.travel_agency.model.User;
import com.denys.travel_agency.repository.UserRepository;
import com.denys.travel_agency.service.serviceinterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(UserDTO userDTO) throws EntityAlreadyExistsException {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new EntityAlreadyExistsException();
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userRepository.save(userMapper.toUser(userDTO));
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO updateUser(String username, UserDTO userDTO) throws EntityNotFoundException {
        if (userRepository.existsByUsername(username)) {
            User userFoInsert = userMapper.toUser(userDTO);
            userFoInsert.setVouchers(userRepository.findUserByUsername(username).get().getVouchers());

            if (userDTO.getPassword() == null && userRepository.existsByUsername(username))
                userFoInsert.setPassword(userRepository.findUserByUsername(username).get().getPassword());
            else
                userFoInsert.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            return userMapper.toUserDTO(userRepository.save(userFoInsert));
        }
        throw new EntityNotFoundException("User with current username is absent");
    }

    @Override
    public UserDTO getUserByUsername(String username) throws EntityNotFoundException {
        try {
            return userMapper.toUserDTO(userRepository.findUserByUsername(username).orElseThrow());
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("User with current username is absent");
        }

    }

    @Override
    public UserDTO changeAccountStatus(UserDTO userDTO) throws EntityNotFoundException {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            User userFoInsert = userMapper.toUser(userDTO);
            userFoInsert.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            return userMapper.toUserDTO(userRepository.save(userFoInsert));
        }
        throw new EntityNotFoundException("User with current username is absent");
    }

    @Override
    public UserDTO getUserById(UUID id) throws EntityNotFoundException {
        try {
            return userMapper.toUserDTO(userRepository.findById(id).orElseThrow());
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Entity with current id is absent");
        }
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable, String nameFilter) {
        try {
            return userRepository.findByUsernameStartingWith(nameFilter, pageable).map(userMapper::toUserDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameters!");
        }
    }

    @Transactional
    @Override
    public UserDTO deleteByUsername(String username) throws EntityNotFoundException {
        if (userRepository.existsByUsername(username)) {
            UserDTO deletedUserDTO = userMapper.toUserDTO(userRepository.findUserByUsername(username).get());
            userRepository.deleteByUsername(username);
            return deletedUserDTO;
        }
        throw new EntityNotFoundException("User with current username is absent");
    }

    @Override
    public Page<UserDTO> getAllUsersWhereRoleIsUser(Pageable pageable, String nameFilter) {
        try {
            return userRepository.findByUsernameStartingWithAndRole(nameFilter, pageable, Role.USER.name())
                    .map(userMapper::toUserDTO);
        } catch (Exception e) {
            throw new IllegalArgumentException("Bad parameters!");
        }
    }
}
