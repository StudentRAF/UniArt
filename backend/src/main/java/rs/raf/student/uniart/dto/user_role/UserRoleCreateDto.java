package rs.raf.student.uniart.dto.user_role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRoleCreateDto {

    @NotBlank
    @Size(max = 32)
    private String name;

}
