package rs.raf.student.uniart.dto.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.dto.organization.OrganizationGetDto;

import java.text.MessageFormat;
import java.util.Objects;

public class ProjectGetDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("organization")
    private OrganizationGetDto organization;

    //region Constructors

    public ProjectGetDto() { }

    public ProjectGetDto(String name, OrganizationGetDto organization) {
        setName(name);
        setOrganization(organization);
    }

    //endregion Constructors

    //region Data

    public ProjectGetDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public ProjectGetDto setOrganization(OrganizationGetDto organization) {
        this.organization = organization;

        return this;
    }

    public OrganizationGetDto organization() {
        return organization;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof ProjectGetDto dto)
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
                                    ProjectGetDto.class.getSimpleName(), name, organization);
    }

    //endregion Object

}
