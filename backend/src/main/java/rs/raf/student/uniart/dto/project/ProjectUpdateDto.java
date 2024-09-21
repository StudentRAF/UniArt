package rs.raf.student.uniart.dto.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.util.Objects;

public class ProjectUpdateDto {

    @NotBlank
    @Size(max = 64)
    @JsonProperty("name")
    private String name;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("old_name")
    private String oldName;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("organization")
    private String organization;

    //region Constructors

    public ProjectUpdateDto() { }

    public ProjectUpdateDto(String name, String oldName, String organization) {
        setName(name);
        setOldName(oldName);
        setOrganization(organization);
    }

    //endregion Constructors

    //region Data

    public ProjectUpdateDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public ProjectUpdateDto setOldName(String oldName) {
        this.oldName = oldName;

        return this;
    }

    public String oldName() {
        return oldName;
    }

    public ProjectUpdateDto setOrganization(String organization) {
        this.organization = organization;

        return this;
    }

    public String organization() {
        return organization;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof ProjectUpdateDto dto)
            return Objects.equals(dto.name, name)             &&
                   Objects.equals(dto.oldName, oldName)       &&
                   Objects.equals(dto.organization, organization);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, oldName, organization);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' name = {1} | oldName = {2} | organization = {3} '}'\
                                    """,
                                    ProjectUpdateDto.class.getSimpleName(), name, oldName, organization);
    }

    //endregion Object

}
