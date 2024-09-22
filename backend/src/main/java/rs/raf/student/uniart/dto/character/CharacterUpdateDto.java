package rs.raf.student.uniart.dto.character;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import rs.raf.student.uniart.adapter.document.DocumentAdapter;
import rs.raf.student.uniart.model.document.Document;

import java.text.MessageFormat;
import java.util.Objects;

public class CharacterUpdateDto {

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
    @JsonProperty("project")
    private String project;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("organization")
    private String organization;

    @JsonProperty("document")
    @JsonDeserialize(using = DocumentAdapter.Deserializer.class)
    private Document document;

    //region Constructors

    public CharacterUpdateDto() { }

    public CharacterUpdateDto(String name, String oldName, String project, String organization, Document document) {
        setName(name);
        setOldName(oldName);
        setProject(project);
        setOrganization(organization);
        setDocument(document);
    }

    //endregion Constructors

    //region Data


    public CharacterUpdateDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    public CharacterUpdateDto setOldName(String oldName) {
        this.oldName = oldName;

        return this;
    }

    public String oldName() {
        return oldName;
    }

    public CharacterUpdateDto setProject(String project) {
        this.project = project;

        return this;
    }

    public String project() {
        return project;
    }

    public CharacterUpdateDto setOrganization(String organization) {
        this.organization = organization;

        return this;
    }

    public String organization() {
        return organization;
    }

    public CharacterUpdateDto setDocument(Document document) {
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

        if (object instanceof CharacterUpdateDto dto)
            return Objects.equals(dto.name, name)       &&
                   Objects.equals(dto.oldName, oldName) &&
                   Objects.equals(dto.project, project) &&
                   Objects.equals(dto.organization, organization);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, oldName, project, organization);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' name = {1} | oldName = {2} | project = {3} | organization = {4} | document = {5} '}'\
                                    """,
                                    CharacterUpdateDto.class.getSimpleName(), name, oldName, project, organization, document);
    }

    //endregion Object

}
