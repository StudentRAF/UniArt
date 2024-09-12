package rs.raf.student.uniart.dto.user.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerCreateDto {

    @NotBlank
    @Size(max = 32)
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @Size(max = 32)
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    @Size(max = 64)
    private String username;

    //NOTE: Should be removed in the future
    @NotBlank
    @Size(min = 8, max = 64)
    private String password;

    @NotBlank
    @Email
    @Size(min = 1, max = 256)
    private String email;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;

    @JsonProperty("user_role")
    private String userRole;

}
