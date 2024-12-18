package ru.job4j.socialmedia.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.service.user.UserService;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> get(@PathVariable("userId") Integer id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById(@PathVariable("userId") Integer id) {
        userService.deleteUserById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
