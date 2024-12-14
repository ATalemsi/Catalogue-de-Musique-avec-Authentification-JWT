package com.youcode.Album_Management.controller;

import com.youcode.Album_Management.dto.request.LoginRequest;
import com.youcode.Album_Management.dto.request.UserRequestDTO;
import com.youcode.Album_Management.dto.response.AuthResponse;
import com.youcode.Album_Management.dto.response.UserResponseDTO;
import com.youcode.Album_Management.security.JwtTokenProvider;
import com.youcode.Album_Management.service.CustomUserDetails;
import com.youcode.Album_Management.service.Interface.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRequestDTO userDTO) {
        UserResponseDTO registeredUser = userService.register(userDTO);

        String token = jwtTokenProvider.generateToken(registeredUser.getUsername());

        List<SimpleGrantedAuthority> authorities = registeredUser.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthResponse(token, registeredUser.getUsername(), authorities));
    }
}