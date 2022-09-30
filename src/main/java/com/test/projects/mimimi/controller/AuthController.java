package com.test.projects.mimimi.controller;

import com.test.projects.mimimi.dto.auth.AuthRequest;
import com.test.projects.mimimi.dto.auth.AuthResponse;
import com.test.projects.mimimi.model.auth.User;
import com.test.projects.mimimi.security.jwt.JwtTokenUtil;
import com.test.projects.mimimi.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = Path.AUTH)
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JwtTokenUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, AuthenticationManager authManager, JwtTokenUtil jwtUtil) {
        this.authService = authService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getUsername(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid AuthRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.ok().build();
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
