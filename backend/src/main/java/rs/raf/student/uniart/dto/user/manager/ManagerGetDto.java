package rs.raf.student.uniart.dto.user.manager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import rs.raf.student.uniart.dto.user_role.UserRoleGetDto;

import java.time.LocalDate;

@Data
public class ManagerGetDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String username;

    private String email;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;

    @JsonProperty("user_role")
    private UserRoleGetDto userRole;

}
