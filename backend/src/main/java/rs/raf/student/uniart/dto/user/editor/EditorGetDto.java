package rs.raf.student.uniart.dto.user.editor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.dto.user_role.UserRoleGetDto;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Objects;

public class EditorGetDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;

    @JsonProperty("user_role")
    private UserRoleGetDto userRole;

    //region Constructors

    public EditorGetDto() { }

    public EditorGetDto(String firstName, String lastName, String username, String email, LocalDate dateOfBirth, UserRoleGetDto userRole) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
        setUserRole(userRole);
    }

    //endregion Constructors

    //region Data

    public EditorGetDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public EditorGetDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public EditorGetDto setUsername(String username) {
        this.username = username;

        return this;
    }

    public String username() {
        return username;
    }

    public EditorGetDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public EditorGetDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

        return this;
    }

    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    public EditorGetDto setUserRole(UserRoleGetDto userRole) {
        this.userRole = userRole;

        return this;
    }

    public UserRoleGetDto userRole() {
        return userRole;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof EditorGetDto dto)
            return Objects.equals(dto.email, email)         &&
                   Objects.equals(dto.lastName, lastName)   &&
                   Objects.equals(dto.username, username)   &&
                   Objects.equals(dto.userRole, userRole)   &&
                   Objects.equals(dto.firstName, firstName) &&
                   Objects.equals(dto.dateOfBirth, dateOfBirth);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, lastName, username, userRole, firstName, dateOfBirth);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' firstName = {1} | lastName = {2} | username = {3} | email = {4} | dateOfBirth = {5} | userRole = {6} '}'\
                                    """,
                                    EditorGetDto.class.getSimpleName(), firstName, lastName, username, email, dateOfBirth, userRole);
    }

    //endregion Object

}
