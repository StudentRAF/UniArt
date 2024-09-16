package rs.raf.student.uniart.dto.user_role;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class UserRoleUpdateDto {

    @NotBlank
    @Size(max = 32)
    @JsonProperty("name")
    private String name;

    @NotBlank
    @Size(max = 32)
    @JsonProperty("old_name")
    private String oldName;

}
