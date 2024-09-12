package rs.raf.student.uniart.dto.user_role;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleUpdateDto {

    @NotBlank
    @Size(max = 32)
    private String name;

    @NotBlank
    @Size(max = 32)
    @JsonProperty("old_name")
    private String oldName;

}
