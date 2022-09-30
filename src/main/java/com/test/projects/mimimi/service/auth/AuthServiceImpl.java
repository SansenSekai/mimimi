package com.test.projects.mimimi.service.auth;

import com.test.projects.mimimi.dto.auth.AuthRequest;
import com.test.projects.mimimi.model.auth.User;
import com.test.projects.mimimi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(AuthRequest dto) {
        User user = new User(dto.getUsername(), new BCryptPasswordEncoder().encode(dto.getPassword()));
        userRepository.save(user);
    }
}
