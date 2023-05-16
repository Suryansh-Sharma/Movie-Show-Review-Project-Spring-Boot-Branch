package com.suryansh.Service.impl;

import com.suryansh.Entity.Role;
import com.suryansh.Entity.User;
import com.suryansh.Exception.SpringShowException;
import com.suryansh.Model.LoginModel;
import com.suryansh.Model.UserSignUpModel;
import com.suryansh.Repository.UserRepository;
import com.suryansh.dto.UserLoginDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testRegisterNewUser_Success() {
        // Arrange
        UserSignUpModel userModel = new
                UserSignUpModel("Suryansh","suryansh@gmail.com","1234");
        String verificationToken = UUID.randomUUID().toString();
        String jwtToken = "";
        UserDetails userDetails = mock(UserDetails.class);
        var user = User.builder()
                .username(userModel.getUsername())
                .email(userModel.getEmail())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .userPic(null)
                .isVerified(false)
                .verifyToken(verificationToken)
                .role(Role.USER)
                .build();
        // Mock
        lenient().when(userRepository.findByUsername("Not_Present")).thenReturn(Optional.empty());
        when(jwtService.getJwtTokenForUser(any())).thenReturn(userDetails);
        when(jwtUtils.generateToken(any(UserDetails.class))).thenReturn(jwtToken);
        // Act
        UserLoginDto res = userService.registerNewUser(userModel);
        // Assert
        verify(jwtService).getJwtTokenForUser(any());
        assertThat(res.User().email()).isEqualTo(user.getEmail());
        assertThat(res.jwt_token()).isEqualTo(jwtToken);
    }
    @Test
    void testRegisterNewUser_UserAlreadyPresent(){
        // Arrange
        UserSignUpModel userModel = new
                UserSignUpModel("Suryansh","suryansh@gmail.com","1234");
        var user = User.builder()
                .username(userModel.getUsername())
                .email(userModel.getEmail())
                .password(passwordEncoder.encode(userModel.getPassword()))
                .userPic(null)
                .isVerified(false)
                .verifyToken("verificationToken")
                .role(Role.USER)
                .build();
        // Mock
        when(userRepository.findByUsername(userModel.getUsername())).thenReturn(Optional.of(user));
        // Assert
        assertThatThrownBy(()->userService.registerNewUser(userModel))
                .isInstanceOf(SpringShowException.class);
    }
    @Test
    void testLoginUser_Success() {
        // Arrange
        LoginModel loginModel = new LoginModel("suryansh@gmail.com","1234");
        String jwtToken = "jwt_token";
        var user = User.builder()
                .username(loginModel.getUsername())
                .email("suryansh@gmail.com")
                .password(passwordEncoder.encode(loginModel.getPassword()))
                .userPic(null)
                .isVerified(true)
                .verifyToken("verificationToken")
                .role(Role.USER)
                .build();
        UserDetails userDetails = mock(UserDetails.class);
        // Mock
        when(userRepository.findByUsername(loginModel.getUsername()))
                .thenReturn(Optional.of(user));
        when(jwtService.getJwtTokenForUser(user))
                .thenReturn(userDetails);
        when(jwtUtils.generateToken(userDetails))
                .thenReturn(jwtToken);
        // Act
        UserLoginDto res = userService.loginUser(loginModel);

        // Assert
        assertThat(res.User().username())
                .isEqualTo(user.getUsername());
        assertThat(res.jwt_token())
                .isEqualTo(jwtToken);
    }
    @Test
    void testLoginUser_InvalidCredentials(){
        // Arrange
        LoginModel loginModel = new LoginModel("suryansh@gmail.com","1234");
        // Mock
        doThrow(new SpringShowException("Invalid login credentials"))
                .when(jwtService).authenticate(loginModel);
        // Act and Assert
        assertThatThrownBy(()->userService.loginUser(loginModel))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Invalid login credentials");
    }
}