package rs.raf.student.uniart.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginDto {

    @NotBlank
    @Size(max = 64)
    private String username;

    @NotBlank
    @Size(max = 64)
    private String password;

}
