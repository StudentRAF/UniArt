package rs.raf.student.uniart.dto.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.util.Objects;

public class OrganizationUpdateDto {

    @NotBlank
    @Size(max = 64)
    @JsonProperty("name")
    private String name;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("old_name")
    private String oldName;

    //region Constructors

    public OrganizationUpdateDto() { }

    public OrganizationUpdateDto(String name, String oldName) {
        setName(name);
        setOldName(oldName);
    }

    //endregion Constructors

    //region Data

    public OrganizationUpdateDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public OrganizationUpdateDto setOldName(String oldName) {
        this.oldName = oldName;

        return this;
    }

    public String oldName() {
        return oldName;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof OrganizationUpdateDto dto)
            return Objects.equals(dto.name, name) &&
                   Objects.equals(dto.oldName, oldName);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, oldName);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' name = {1} | oldName = {2} '}'\
                                    """,
                                    OrganizationUpdateDto.class.getSimpleName(), name, oldName);
    }

    //endregion Object

}
