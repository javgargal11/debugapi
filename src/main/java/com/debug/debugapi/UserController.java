package com.debug.debugapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AddressRepository addressRepo;

    @PostMapping("/register")
    public User register (@RequestBody User user){
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User u = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return (u != null) ? ResponseEntity.ok(u) : ResponseEntity.status(401).body("Credenciales incorrectas");
    }

    @PostMapping("/add-address")
    public Address addAddress(@RequestBody Address address) {
        return addressRepo.save(address);
    }
    @GetMapping("/")
    public String home() {
        return "API Debug & Repair funcionando 👋";
    }

}
