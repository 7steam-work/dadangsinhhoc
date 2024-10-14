# Quy trình đăng nhập
```mermaid
sequenceDiagram
    actor Client
    participant AC as AuthController
    participant AS as AuthService
    participant AM as AuthenticationManager
    participant SCH as SecurityContextHolder
    participant UR as UserRepository
    participant TR as TokenRepository
    participant JS as JwtService

    Client->>AC: POST /login (email, password)
    AC->>AS: login(LoginDto)
    
    AS->>AM: authenticate(UsernamePasswordAuthenticationToken)
    Note over AM: Validates credentials
    
    AM-->>AS: Authentication object
    
    AS->>SCH: setAuthentication(authentication)
    
    AS->>UR: findByEmail(email)
    UR-->>AS: UserModel
    
    AS->>TR: findLastTokenByUser(user)
    TR-->>AS: TokenModel
    
    alt Valid existing token
        AS->>JS: isTokenValidWithData(user)
        JS-->>AS: true
        Note over AS: Use existing token
    else No valid token
        AS->>AS: revokeAllUserTokens(user)
        AS->>JS: generateToken(user)
        JS-->>AS: new JWT token
        AS->>AS: saveToken(user, jwtToken)
        AS->>TR: save(TokenModel)
    end
    
    AS->>UR: save(user with updated lastSigninedTime)
    
    AS-->>AC: ResponseObject with JWT token
    AC-->>Client: JWT token in response
```

# Quy trình xác thực
```mermaid
sequenceDiagram
    actor Client
    participant JF as JwtAuthenticationFilter
    participant JS as JwtService
    participant UDS as UserDetailsService
    participant UR as UserRepository
    participant SCH as SecurityContextHolder
    participant EC as EmployeeController

    Client->>JF: GET /api/employees
    
    Note over JF: Extract JWT from header
    
    JF->>JS: extractUsername(token)
    JS-->>JF: userEmail
    
    alt userEmail != null && SecurityContext empty
        JF->>UDS: loadUserByUsername(userEmail)
        UDS->>UR: findByEmail(userEmail)
        UR-->>UDS: UserModel
        UDS-->>JF: UserDetails
        
        JF->>JS: isTokenValid(token, userDetails)
        JS-->>JF: boolean
        
        alt token is valid
            JF->>JF: Create UsernamePasswordAuthenticationToken
            JF->>JF: Set WebAuthenticationDetails
            JF->>SCH: setAuthentication(authentication)
        end
    end
    
    JF->>EC: Forward request
    EC->>Client: List<Employees>
```

Tóm tắt sự hoạt động:
- Khi một yêu cầu bất kì được gửi tới máy chủ, các `JwtTokenFilter` sẽ chặn trước khi nó được quyền truy cập vào endpoint employees.
- Lấy thông báo từ header Authorization của yêu cầu sử dụng `JwtTokenProvider.resolveToken()`.
- Nếu mã thông báo được tìm thấy, nó sẽ được xác thực bằng cách sử dụng `validateToken()`.
- Nếu mã thông báo hợp lệ, *Filter Chain* sẽ tải chi tiết người dùng được liên kết với mã thông báo bằng cách sử dụng `loadUserByUsername` tại phương thức `UserDetailsService`.
- Tạo một đối tượng xác thực kiểu `UsernamePasswordAuthenticationToken` và đặt nó trong `SecurityContextHolder`.
- Từ đó, Filter Chain được gọi để chuyển yêu cầu và phản hồi đến bộ lọc tiếp theo hoặc tới endpoint.

# Ý nghĩa các hàm và sự cần thiết:

## 1. Security Configuration:
`SecurityConfig` định nghĩa các *@Bean* quan trọng như `AuthenticationManager`, `AuthenticationProvider`, `UserDetailsService` và `SecurityFilterChain` rất cần thiết để thực hiện xác thực và ủy quyền.

## 2. 










