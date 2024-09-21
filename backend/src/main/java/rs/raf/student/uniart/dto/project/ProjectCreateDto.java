package rs.raf.student.uniart.dto.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.util.Objects;

public class ProjectCreateDto {

    @NotBlank
    @Size(max = 64)
    @JsonProperty("name")
    private String name;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("organization")
    private String organization;

    //region Constructors

    public ProjectCreateDto() { }

    public ProjectCreateDto(String name, String organization) {
        setName(name);
        setOrganization(organization);
    }

    //endregion Constructors

    //region Data

    public ProjectCreateDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public ProjectCreateDto setOrganization(String organization) {
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

        if (object instanceof ProjectCreateDto dto)
            return Objects.equals(dto.name, name) &&
                   Objects.equals(dto.organization, organization);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, organization);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' name = {1} | organization = {2} '}'\
                                    """,
                                    ProjectCreateDto.class.getSimpleName(), name, organization);
    }

    //endregion Object

}
