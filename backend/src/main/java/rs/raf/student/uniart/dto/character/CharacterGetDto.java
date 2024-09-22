package rs.raf.student.uniart.dto.character;

import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.dto.organization.OrganizationGetDto;
import rs.raf.student.uniart.dto.project.ProjectGetDto;
import rs.raf.student.uniart.model.document.Document;

import java.text.MessageFormat;
import java.util.Objects;

public class CharacterGetDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("project")
    private ProjectGetDto project;

    @JsonProperty("organization")
    private OrganizationGetDto organization;

    @JsonProperty("document")
    private Document document;

    //region Constructors

    public CharacterGetDto() { }

    public CharacterGetDto(String name, ProjectGetDto project, OrganizationGetDto organization, Document document) {
        setName(name);
        setProject(project);
        setOrganization(organization);
        setDocument(document);
    }

    //endregion Constructors

    //region Data

    public CharacterGetDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public CharacterGetDto setProject(ProjectGetDto project) {
        this.project = project;

        return this;
    }

    public ProjectGetDto project() {
        return project;
    }

    public CharacterGetDto setOrganization(OrganizationGetDto organization) {
        this.organization = organization;

        return this;
    }

    public OrganizationGetDto organization() {
        return organization;
    }

    public CharacterGetDto setDocument(Document document) {
        this.document = document;

        return this;
    }

    public Document document() {
        return document;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof CharacterGetDto dto)
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
                                    {0}: '{' name = {1} | project = {2} | organization = {3} | document = {4} '}'\
                                    """,
                                    CharacterGetDto.class.getSimpleName(), name, project, organization, document);
    }

    //endregion Object

}
