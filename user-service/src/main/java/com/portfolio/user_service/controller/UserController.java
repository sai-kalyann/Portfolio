package com.portfolio.user_service.controller;

import com.portfolio.user_service.dto.UserMapper;
import com.portfolio.user_service.dto.UserRequestDTO;
import com.portfolio.user_service.dto.UserResponseDTO;
import com.portfolio.user_service.model.User;
import com.portfolio.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto) {
        User created=userService.createUser(UserMapper.toEntity(dto));
        return new ResponseEntity<>(UserMapper.toResponseDTO(created), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users=userService.getAllUsers().stream().map(UserMapper::toResponseDTO).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user=userService.getUserById(id).map(UserMapper::toResponseDTO).orElse(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
    return userService.getUserByEmail(email)
            .map(user->ResponseEntity.ok(UserMapper.toResponseDTO(user)))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable long id, @RequestBody UserRequestDTO dto) {
        User updated=userService.updateUser(id,UserMapper.toEntity(dto));
        return new ResponseEntity<>(UserMapper.toResponseDTO(updated), HttpStatus.OK);
    }
}
