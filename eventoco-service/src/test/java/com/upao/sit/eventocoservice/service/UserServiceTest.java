package com.upao.sit.eventocoservice.service;

import com.upao.sit.eventocoservice.exception.BadRequestException;
import com.upao.sit.eventocoservice.exception.ResourceNotFoundException;
import com.upao.sit.eventocoservice.mapper.UserMapper;
import com.upao.sit.eventocoservice.model.dto.UserRequestDTO;
import com.upao.sit.eventocoservice.model.dto.UserResponseDTO;
import com.upao.sit.eventocoservice.model.entity.User;
import com.upao.sit.eventocoservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    //test/visualizar-datos-del-usuario
    @Test
    public void testGetAllUsers(){
        //Arrange
        User user_1 = new User();
        user_1.setId(1L);
        User user_2 = new User();
        user_2.setId(2L);
        User user_3 = new User();
        user_3.setId(3L);
        List<User> users = Arrays.asList(user_1, user_2, user_3);
        //Simular el comportamiento del userRepository ==> List<user> users = userRepository.findAll();
        when(userRepository.findAll()).thenReturn(users);

        UserResponseDTO responseDTO_01 = new UserResponseDTO();
        responseDTO_01.setId(1L);
        UserResponseDTO responseDTO_02 = new UserResponseDTO();
        responseDTO_02.setId(2L);
        UserResponseDTO responseDTO_03 = new UserResponseDTO();
        responseDTO_03.setId(3L);

        List<UserResponseDTO> expectedResponseDTOs = Arrays.asList(responseDTO_01,responseDTO_02, responseDTO_03);
        //Simular el comportamiento del  userMapper.convertToListDTO(users)
        when(userMapper.convertToListDTO(users)).thenReturn(expectedResponseDTOs);


        //Act: Aca se realiza la prueba del metodo getAllUserss()
        List<UserResponseDTO> usersResult = userService.getAllUsers();

        //Assert
        assertEquals(expectedResponseDTOs.size(), usersResult.size());
    }

    @Test
    public void testGetUserById_ExistingId(){
        //Arrange
        Long id = 1L;
        User user = new User();
        user.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        when(userMapper.convertToDTO(user)).thenReturn(responseDTO);

        //Act
        UserResponseDTO result = userService.getUserById(id);

        //Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetUserById_NonExistingId(){
        //Arrange
        Long id = 99999L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        //Act & Assert
        assertThrows(ResourceNotFoundException.class,
                ()->userService.getUserById(id));
    }

    //test/crear-usuario
    @Test
    public void testCreateUser_Successful() {
        // Arrange
        UserRequestDTO validUserRequest = new UserRequestDTO();
        validUserRequest.setUsername("newuser");
        validUserRequest.setPassword("password123");
        validUserRequest.setEmail("newuser@example.com");
        validUserRequest.setPhone("123456789");
        validUserRequest.setBirthday(LocalDate.of(2000, 1, 1));

        User user = new User();
        user.setId(1L);
        user.setUsername("newuser");
        user.setPassword("password123");
        user.setEmail("newuser@example.com");
        user.setPhone("123456789");
        user.setBirthday(LocalDate.of(2000, 1, 1));

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("newuser");
        savedUser.setPassword("password123");
        savedUser.setEmail("newuser@example.com");
        savedUser.setPhone("123456789");
        savedUser.setBirthday(LocalDate.of(2000, 1, 1));

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setUsername("newuser");
        responseDTO.setEmail("newuser@example.com");
        responseDTO.setPhone("123456789");
        responseDTO.setBirthday(LocalDate.of(2000, 1, 1));

        when(userMapper.convertToEntity(validUserRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.convertToDTO(savedUser)).thenReturn(responseDTO);

        // Act
        UserResponseDTO result = userService.createUser(validUserRequest);

        // Assert
        assertNotNull(result);
        assertEquals(responseDTO.getId(), result.getId());
        assertEquals(responseDTO.getUsername(), result.getUsername());
    }

    @Test
    public void testCreateUser_FailDueToMissingFields() {
        // Arrange
        UserRequestDTO validUserRequest = new UserRequestDTO();
        validUserRequest.setUsername(null);
        validUserRequest.setPassword("password123");
        validUserRequest.setEmail("newuser@example.com");
        validUserRequest.setPhone("123456789");
        validUserRequest.setBirthday(LocalDate.of(2000, 1, 1));

        // Act
        UserResponseDTO result = userService.createUser(validUserRequest);

        // Assert
        assertNull(result);
    }

    //test/actualizar-usuario
    @Test
    public void testUpdateUser_Successful() {
        // Arrange
        Long id = 1L;
        UserRequestDTO requestDTO = new UserRequestDTO("newusername", "newpassword", "newuser@example.com", "987654321", LocalDate.of(2000, 1, 1));
        User user = new User(id, "oldusername", "oldpassword", "olduser@example.com", "123456789", LocalDate.of(2000, 1, 1));
        User updatedUser = new User(id, "newusername", "newpassword", "newuser@example.com", "987654321", LocalDate.of(2000, 1, 1));
        UserResponseDTO responseDTO = new UserResponseDTO(id, "newusername", "newpassword", "newuser@example.com", "987654321", LocalDate.of(2000, 1, 1));

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.convertToDTO(updatedUser)).thenReturn(responseDTO);

        // Act
        UserResponseDTO result = userService.updateUser(id, requestDTO);

        // Assert
        assertNotNull(result);
        assertEquals(responseDTO.getId(), result.getId());
        assertEquals(responseDTO.getUsername(), result.getUsername());
        assertEquals(responseDTO.getPassword(), result.getPassword());
        assertEquals(responseDTO.getEmail(), result.getEmail());
        assertEquals(responseDTO.getPhone(), result.getPhone());
        assertEquals(responseDTO.getBirthday(), result.getBirthday());
    }

    //test/elimiar-usuario
    @Test
    public void testDeleteUser_Successful() {
        // Arrange
        Long userId = 10L;
        User user = new User(userId, "username", "password", "user@example.com", "123456789", LocalDate.of(2000, 1, 1));

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(user);
    }

    //test/iniciar-sesión
    @Test
    public void testLoginUser_Successful() {
        // Arrange
        String email = "user@example.com";
        String password = "password123";
        User user = new User(1L, "validuser", password, email, "123456789", LocalDate.of(2000, 1, 1));
        UserResponseDTO responseDTO = new UserResponseDTO(1L, "validuser","password123", email, "123456789", LocalDate.of(2000, 1, 1));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userMapper.convertToDTO(user)).thenReturn(responseDTO);

        // Act
        UserResponseDTO result = userService.loginUser(email, password);

        // Assert
        assertNotNull(result);
        assertEquals(responseDTO.getEmail(), result.getEmail());
        assertEquals(responseDTO.getPassword(), result.getPassword());
    }

    @Test
    public void testLoginUser_FailDueToInvalidCredentials() {
        // Arrange
        String email = "user@example.com";
        String password = "wrongpassword";

        User user = new User(1L, "validuser", "password123", email, "123456789", LocalDate.of(2000, 1, 1));
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        //Act & Assert
        assertThrows(BadRequestException.class,
                ()->userService.loginUser(email, password));
    }

    @Test
    public void testLoginUser_FailDueToMissingFields() {
        // Arrange
        String email = "";
        String password = "";

        //Act & Assert
        assertThrows(ResourceNotFoundException.class,
                ()->userService.loginUser(email, password));
    }
}
