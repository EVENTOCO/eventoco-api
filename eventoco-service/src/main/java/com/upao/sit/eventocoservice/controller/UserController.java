package com.upao.sit.eventocoservice.controller;

import com.upao.sit.eventocoservice.exception.BadRequestException;
import com.upao.sit.eventocoservice.exception.ResourceNotFoundException;
import com.upao.sit.eventocoservice.model.dto.UserRequestDTO;
import com.upao.sit.eventocoservice.model.dto.UserResponseDTO;
import com.upao.sit.eventocoservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/api/v1/users

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> user = userService.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/users/12345
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        UserResponseDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/users/tester
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username){
        UserResponseDTO user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/users/test@outlook.com
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        UserResponseDTO user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Validated @RequestBody UserRequestDTO userDTO){
        UserResponseDTO user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/users/12345
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                      @Validated @RequestBody UserRequestDTO userDTO){
        UserResponseDTO updatedUser = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/users/12345
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //http://localhost:8080/api/v1/login
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@Validated @RequestParam String email,
                                                     @RequestParam String password) {
        UserResponseDTO user = userService.loginUser(email, password);
        return ResponseEntity.ok(user);
    }
}
