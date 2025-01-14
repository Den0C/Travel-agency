package com.denys.travel_agency.controller;


import com.denys.travel_agency.dto.MyApiResponse;
import com.denys.travel_agency.dto.UserDTO;
import com.denys.travel_agency.exeption.EntityNotFoundException;
import com.denys.travel_agency.service.serviceinterface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/agency/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PreAuthorize("hasAnyAuthority('ADMIN_READ','USER_READ','MANAGER_READ')")
    @GetMapping("/get/{name}")
    public ResponseEntity<MyApiResponse<UserDTO>> getUserByUsername(@PathVariable String name) throws EntityNotFoundException {
        return ResponseEntity.ok(MyApiResponse.<UserDTO>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("User was obtained successfully")
                .data(userService.getUserByUsername(name))
                .build());
    }
    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<MyApiResponse<UserDTO>> deleteById(@PathVariable String username)
            throws IllegalArgumentException, EntityNotFoundException {
        return ResponseEntity.ok(
                MyApiResponse.<UserDTO>builder()
                        .statusMessage("User is successfully deleted!")
                        .statusCode(HttpStatus.OK.name())
                        .data(userService.deleteByUsername(username))
                        .build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE','USER_UPDATE','MANAGER_UPDATE')")
    @PatchMapping("/update/{name}")
    public ResponseEntity<MyApiResponse<UserDTO>> updateUser(@PathVariable String name, @RequestBody UserDTO userDTO) throws EntityNotFoundException {
        return ResponseEntity.ok(MyApiResponse.<UserDTO>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("User was updated successfully")
                .data(userService.updateUser(name, userDTO))
                .build());
    }
    @PreAuthorize("hasAnyAuthority('ADMIN_READ','MANAGER_READ')")
    @GetMapping("/get-all")
    public ResponseEntity<MyApiResponse<Page<UserDTO>>> getUserAllUsers(
            Pageable pageable,
            @RequestParam(defaultValue = "") String nameFilter

    ) throws IllegalArgumentException {
        return ResponseEntity.ok(MyApiResponse.<Page<UserDTO>>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("User was obtained successfully")
                .data(userService.getAllUsers(pageable,nameFilter))
                .build());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_READ','MANAGER_READ')")
    @GetMapping("/get-all-customers")
    public ResponseEntity<MyApiResponse<Page<UserDTO>>> getUserAllUsersWhereRoleIsUser(
            Pageable pageable,
            @RequestParam(defaultValue = "") String nameFilter

    ) throws IllegalArgumentException {
        return ResponseEntity.ok(MyApiResponse.<Page<UserDTO>>builder()
                .statusCode(HttpStatus.OK.name())
                .statusMessage("User was obtained successfully")
                .data(userService.getAllUsersWhereRoleIsUser(pageable,nameFilter))
                .build());
    }
}
