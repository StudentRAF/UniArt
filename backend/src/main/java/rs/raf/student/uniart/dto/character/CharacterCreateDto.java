package rs.raf.student.uniart.dto.character;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.util.Objects;

public class CharacterCreateDto {

    @NotBlank
    @Size(max = 64)
    @JsonProperty("name")
    private String name;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("project")
    private String project;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("organization")
    private String organization;

    //region Constructors

    public CharacterCreateDto() { }

    public CharacterCreateDto(String name, String project, String organization) {
        setName(name);
        setProject(project);
        setOrganization(organization);
    }

    //endregion Constructors

    //region Data


    public CharacterCreateDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public CharacterCreateDto setProject(String project) {
        this.project = project;

        return this;
    }

    public String project() {
        return project;
    }

    public CharacterCreateDto setOrganization(String organization) {
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

        if (object instanceof CharacterCreateDto dto)
            return Objects.equals(dto.name, name)       &&
                   Objects.equals(dto.project, project) &&
                   Objects.equals(dto.organization, organization);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, project, organization);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' name = {1} | project = {2} | organization = {3} '}'\
                                    """,
                                    CharacterCreateDto.class.getSimpleName(), name, project, organization);
    }

    //endregion Object

}
