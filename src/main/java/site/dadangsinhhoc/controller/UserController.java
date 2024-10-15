package site.dadangsinhhoc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.services.user.IUserService;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@CrossOrigin
public class UserController {
    private final IUserService userService;

    @GetMapping("/findUserById/{id}")
    public ResponseObject findUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/findUserByEmail/{email}")
    public ResponseObject findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/checkUserExists/{identifier}")
    public ResponseObject checkUserExists(@PathVariable String identifier) {
        return userService.checkUserExists(identifier);
    }

    @PostMapping("/createUser")
    public ResponseObject createUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String pw,
            @RequestParam String repw,
            @RequestParam String phone,
            @RequestParam String gender,
            @RequestParam LocalDate dob,
            @RequestParam String address
    ) {
        return userService.createNewUser(name, email, pw, repw, phone, gender, dob, address);
    }

    @PostMapping("/signIn")
    public ResponseObject signIn(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }

    @GetMapping("/current")
    public ResponseEntity<ResponseObject> getCurrentUser() {
        ResponseObject response = userService.getCurrentUser();
        return ResponseEntity.status(response.code()).body(response);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseObject deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable Integer id, @RequestBody UserModel updatedUser) {
        ResponseObject response = userService.updateUser(id, updatedUser);
        return ResponseEntity.status(response.code()).body(response);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ResponseObject> getAllUsers() {
        ResponseObject response = userService.getAllUsers();
        return ResponseEntity.status(response.code()).body(response);
    }

    @GetMapping("/countAllUsers")
    public ResponseEntity<ResponseObject> countAllUsers() {
        ResponseObject response = userService.countAllUsers();
        return ResponseEntity.status(response.code()).body(response);
    }

    @GetMapping("/countUsersByRole")
    public ResponseEntity<ResponseObject> countUsersByRole() {
        ResponseObject response = userService.countUsersByRole();
        return ResponseEntity.status(response.code()).body(response);
    }

    @GetMapping("/getUsersByRole/{role}")
    public ResponseEntity<ResponseObject> getUsersByRole(@PathVariable String role) {
        ResponseObject response = userService.getUsersByRole(role);
        return ResponseEntity.status(response.code()).body(response);
    }
}
