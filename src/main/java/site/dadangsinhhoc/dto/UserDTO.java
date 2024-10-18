package site.dadangsinhhoc.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    private String name;
    private String password;
    private String email;
    private String phone;
    private String gender;
    private LocalDate dob;
    private String address;
    private String role;
    private Boolean status;

}
