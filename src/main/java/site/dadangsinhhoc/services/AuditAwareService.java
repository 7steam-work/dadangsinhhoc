package site.dadangsinhhoc.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import site.dadangsinhhoc.models.UserModel;

import java.util.Optional;

/**
 * Đoạn mã sử dụng nhằm xác định người dùng hiện tại
 *
 * @apiNote return về Id của người dùng khi đã xác thực
 */
@Slf4j
public class AuditAwareService implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        log.debug("Start Audit Aware Service");
        // Lấy thông tin xác thực từ SecurityContextHolder, nơi lưu trữ thông tin người dùng đã login
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();
        log.debug("1. Authentication: {}", authentication);
        // Kiểm tra tính hợp lệ, bao gồm thông tin xác thực (authtication == null), người dùng không xác thực (không đăng nhập) hay người dùng dùng xác thực
        if (authentication == null
                || !authentication.isAuthenticated()
                // || !(authentication instanceof AnonymousAuthenticationToken)
                ) {
            log.error("User authentication failed");
            return Optional.empty();
        }
        UserModel user = (UserModel) authentication.getPrincipal();
        log.debug("2. User: {}", user);
        return Optional.ofNullable(user.getId());
    }
}
