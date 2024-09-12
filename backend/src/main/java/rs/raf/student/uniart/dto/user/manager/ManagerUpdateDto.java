package rs.raf.student.uniart.dto.user.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ManagerUpdateDto {

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

    @NotBlank
    @Size(max = 64)
    @JsonProperty("old_username")
    private String oldUsername;

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

}
