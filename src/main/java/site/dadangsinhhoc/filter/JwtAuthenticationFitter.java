package site.dadangsinhhoc.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import site.dadangsinhhoc.services.auth.JwtService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFitter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Xác thực người dùng dựa trên Jwt, thừa kế lớp OncePerRequestFilter nhằm đảm bảo đoạn mã chỉ thực hiện 1 lần cho mỗi request
     *
     * @param request chứa thông tin về yêu cầu HTTP từ client, bao gồm các header, phương thức, URL, và các tham số.
     * @param response dùng để gửi phản hồi về client, cho phép bạn thiết lập mã trạng thái, header, và nội dung phản hồi.
     * @param filterChain cho phép bạn tiếp tục chuỗi các bộ lọc khác
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        log.debug("Processing authentication for '{}'", request.getRequestURL());

        // Kiểm tra xem request có chứa header "Authorization" không
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Kiểm tra định dạng token có bắt đầu bằng "Bearer" không
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (isBypassToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Trích xuất Token
        jwt = authHeader.substring(7);

        try {
            // Trích xuất email
            userEmail = jwtService.extractUsername(jwt);
        } catch (Exception e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        // SecurityContextHolder: hoạt động như một kho lưu trữ thông tin về người dùng hiện tại đang tương tác với ứng dụng.
        // Xác thực khi có email nhưng chưa có xác thực
        if (userEmail != null
            && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        // Chuyển tới bộ lọc tiếp theo
        filterChain.doFilter(request, response);

    }

    private boolean isBypassToken(@NonNull HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
                Pair.of("/api/auth/**", "GET"),
                Pair.of("/api/auth/**", "POST")

        );
        for(Pair<String, String> bypassToken: bypassTokens) {
            if(request.getServletPath().contains(bypassToken.getFirst()) &&
            request.getMethod().equals(bypassToken.getSecond())) {
                return true;
            }
        }

        return false;
    }
}
