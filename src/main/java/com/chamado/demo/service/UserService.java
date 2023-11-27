package com.chamado.demo.service;

import com.chamado.demo.model.Login;
import com.chamado.demo.model.User;
import com.chamado.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(String login) {
        return this.userRepository.findByEmail(login);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).get();
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}
