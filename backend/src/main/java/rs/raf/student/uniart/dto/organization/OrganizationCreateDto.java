package rs.raf.student.uniart.dto.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.util.Objects;

public class OrganizationCreateDto {

    @NotBlank
    @Size(max = 64)
    @JsonProperty("name")
    private String name;

    //region Constructors

    public OrganizationCreateDto() { }

    public OrganizationCreateDto(String name) {
        setName(name);
    }

    //endregion Constructors

    //region Data

    public OrganizationCreateDto setName(String name) {
        this.name = name;

        return this;
    }

    public String name() {
        return name;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof OrganizationCreateDto dto)
            return Objects.equals(dto.name, name);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' name = {1} '}'\
                                    """,
                                    OrganizationCreateDto.class.getSimpleName(), name);
    }

    //endregion Object

}
