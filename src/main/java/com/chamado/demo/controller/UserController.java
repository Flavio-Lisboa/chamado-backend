package com.chamado.demo.controller;

import com.chamado.demo.model.Login;
import com.chamado.demo.model.User;
import com.chamado.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @CrossOrigin
    @GetMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email) {
        User user = this.userService.findUser(email);
        return ResponseEntity.ok(user);
    }

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<User> getUserData(@RequestParam Long id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        this.userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
