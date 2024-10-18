package site.dadangsinhhoc.services.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.UserDTO;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.exception.ErrorCode;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.repositories.TokenRepository;
import site.dadangsinhhoc.repositories.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
//    private final BCryptPasswordEncoder pwEncoder;

    @Override
    public ResponseObject login(String email, String password) {
        System.out.println("In Service");
        UserModel user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "User not found");
        }
//        if (!pwEncoder.matches(password, user.getPassword())) {
//            return ResponseObject.error(ErrorCode.UNAUTHORIZED.getCode(), "Invalid password");
//        }

        return ResponseObject.success("Login successful");
    }

    @Override
    public ResponseObject findById(Integer id) {
        var authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}; Role: {}", authenticatedUser.getName(), authenticatedUser.getAuthorities());
        Optional<UserModel> user = userRepository.findById(id);
        return ResponseObject.success(user);
    }

    @Override
    public ResponseObject findByEmail(String email) {
        UserModel user = userRepository.findByEmail(email);
        return ResponseObject.success(user);
    }

    @Override
    public ResponseObject checkUserExists(String identifier) {
        boolean exists = userRepository.existsByEmail(identifier) ||
                userRepository.existsByPhone(identifier);
        if (exists) {
            return ResponseObject.success("User exists", true);
        } else {
            return ResponseObject.success("User does not exist", false);
        }
    }

    @Override
    public ResponseObject createNewUser(UserDTO input) {
        try {
            if (userRepository.existsByEmail(input.getEmail())) {
                return ResponseObject.error(ErrorCode.CONFLICT.getCode(), "Email already exists");
            }
            if (userRepository.existsByPhone(input.getPhone())) {
                return ResponseObject.error(ErrorCode.CONFLICT.getCode(), "Phone number already exists");
            }
            UserModel model = new UserModel();
            LocalDateTime now = LocalDateTime.now();
            model.setCreatedAt(now);
            model.setUpdatedAt(now);
            model.setStatus(true);

            if (model.getRole() == null) {
                model.setRole("ROLE_USER");
            }
            model.setId(null);
            UserModel savedUser = UserModel.builder()
                    .name(input.getName())
                    .email(input.getEmail())
//                  .password(pwEncoder.encode(pw))
                    .password(input.getPassword())
                    .phone(input.getPhone())
                    .gender(input.getGender())
                    .dob(input.getDob())
                    .address(input.getAddress())
                    .createdAt(now)
                    .updatedAt(now)
                    .lastSigninedTime(model.getLastSigninedTime())
                    .status(input.getStatus())
                    .role(input.getRole())
                    .build();
            UserModel savedUserModel = userRepository.save(savedUser);

            return ResponseObject.success("User created successfully", savedUserModel);
        } catch (Exception e) {
            log.info("An error occurred while creating the user: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while creating the user: " + e.getMessage());
        }
    }

    @Override
    public ResponseObject getCurrentUser() {
        try {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return ResponseObject.error(ErrorCode.UNAUTHORIZED.getCode(), "User not authenticated");
            }

            String email = authentication.getName();
            UserModel currentUser = userRepository.findByEmail(email);

            if (currentUser == null) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "User not found");
            }

            currentUser.setPassword(null);

            return ResponseObject.success("Current user retrieved successfully", currentUser);
        } catch (Exception e) {
            log.error("Error retrieving current user: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while retrieving the current user");
        }
    }

    @Override
    @Transactional
    public ResponseObject deleteUser(int id) {
        Optional<UserModel> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            UserModel user = userOptional.get();

            // Xóa tất cả token của người dùng trước khi xóa người dùng
            tokenRepository.deleteByUser(user);

            // Xóa người dùng
            userRepository.deleteById(id);

            return ResponseObject.success("Successfully deleted user and associated tokens", null);
        } else {
            return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage());
        }
    }

    @Override
    public ResponseObject updateUser(Integer id, UserModel updatedUser) {
        try {
            Optional<UserModel> existingUserOptional = userRepository.findById(id);
            if (existingUserOptional.isEmpty()) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "User not found");
            }

            UserModel existingUser = existingUserOptional.get();

            if (updatedUser.getName() != null) existingUser.setName(updatedUser.getName());
            if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPhone() != null) existingUser.setPhone(updatedUser.getPhone());
            if (updatedUser.getGender() != null) existingUser.setGender(updatedUser.getGender());
            if (updatedUser.getDob() != null) existingUser.setDob(updatedUser.getDob());
            if (updatedUser.getAddress() != null) existingUser.setAddress(updatedUser.getAddress());
            if (updatedUser.getStatus() != null) existingUser.setStatus(updatedUser.getStatus());
            if (updatedUser.getRole() != null) existingUser.setRole(updatedUser.getRole());

            existingUser.setUpdatedAt(LocalDateTime.now());

            UserModel savedUser = userRepository.save(existingUser);
            return ResponseObject.success("User updated successfully", savedUser);
        } catch (Exception e) {
            log.error("An error occurred while updating the user: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while updating the user: " + e.getMessage());
        }
    }

    @Override
    public ResponseObject updateUserStatus(int id, Boolean status) {
        try {
            UserModel existingUserOptional = userRepository.findUserModelById(id);
            if (existingUserOptional == null) {
                return ResponseObject.error(ErrorCode.NOT_FOUND.getCode(), "User not found");
            }
            existingUserOptional.setStatus(status);

            UserModel savedUser = userRepository.save(existingUserOptional);
            return ResponseObject.success("User status updated successfully", savedUser);
        } catch (Exception e) {
            log.error("An error occurred while updating the user status: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while updating the user status: " + e.getMessage());
        }
    }

    @Override
    public ResponseObject getAllUsers() {
        try {
            List<UserModel> users = userRepository.findAll();
            users.forEach(user -> user.setPassword(null));
            return ResponseObject.success("All users retrieved successfully", users);
        } catch (Exception e) {
            log.error("An error occurred while retrieving all users: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while retrieving all users: " + e.getMessage());
        }
    }

    @Override
    public ResponseObject countAllUsers() {
        try {
            long count = userRepository.count();
            return ResponseObject.success("Total number of users retrieved successfully", count);
        } catch (Exception e) {
            log.error("An error occurred while counting users: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while counting users: " + e.getMessage());
        }
    }

    @Override
    public ResponseObject countUsersByRole() {
        try {
            Map<String, Long> roleCounts = new HashMap<>();
            List<UserModel> users = userRepository.findAll();
            for (UserModel user : users) {
                String role = user.getRole();
                roleCounts.put(role, roleCounts.getOrDefault(role, 0L) + 1);
            }
            return ResponseObject.success("User counts by role retrieved successfully", roleCounts);
        } catch (Exception e) {
            log.error("An error occurred while counting users by role: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while counting users by role: " + e.getMessage());
        }
    }

    @Override
    public ResponseObject getUsersByRole(String role) {
        try {
            List<UserModel> users = userRepository.findByRole(role);
            users.forEach(user -> user.setPassword(null));
            return ResponseObject.success("Users with role '" + role + "' retrieved successfully", users);
        } catch (Exception e) {
            log.error("An error occurred while retrieving users by role: {}", e.getMessage());
            return ResponseObject.error(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), "An error occurred while retrieving users by role: " + e.getMessage());
        }
    }

}
