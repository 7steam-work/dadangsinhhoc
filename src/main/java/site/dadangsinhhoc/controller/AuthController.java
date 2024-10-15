package site.dadangsinhhoc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.dto.UserDTO;
import site.dadangsinhhoc.services.auth.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseObject register(@RequestBody UserDTO dto) throws Exception {
        log.info("Registering user: {}", dto);
        return authService.register(dto);
    }

    @PostMapping("/login/")
    public ResponseObject login(@PathVariable String email, @PathVariable String password) throws Exception {
        return authService.login(email, password);
    }
}
