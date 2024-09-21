package rs.raf.student.uniart.dto.user.editor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Objects;

public class EditorUpdateDto {

    @NotBlank
    @Size(max = 32)
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @Size(max = 32)
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("username")
    private String username;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("old_username")
    private String oldUsername;

    @NotBlank
    @Size(min = 8, max = 64)
    @JsonProperty("password")
    private String password;

    @Email
    @NotBlank
    @Size(min = 1, max = 256)
    @JsonProperty("email")
    private String email;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private LocalDate dateOfBirth;

    //region Constructors

    public EditorUpdateDto() { }

    public EditorUpdateDto(String firstName, String lastName, String username, String oldUsername, String password, String email, LocalDate dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setOldUsername(oldUsername);
        setPassword(password);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
    }

    //endregion Constructors

    //region Data

    public EditorUpdateDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public EditorUpdateDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public EditorUpdateDto setUsername(String username) {
        this.username = username;

        return this;
    }

    public String username() {
        return username;
    }

    public EditorUpdateDto setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;

        return this;
    }

    public String oldUsername() {
        return oldUsername;
    }

    public EditorUpdateDto setPassword(String password) {
        this.password = password;

        return this;
    }

    public String password() {
        return password;
    }

    public EditorUpdateDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public EditorUpdateDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

        return this;
    }

    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof EditorUpdateDto dto)
            return Objects.equals(dto.email, email)             &&
                   Objects.equals(dto.lastName, lastName)       &&
                   Objects.equals(dto.password, password)       &&
                   Objects.equals(dto.username, username)       &&
                   Objects.equals(dto.firstName, firstName)     &&
                   Objects.equals(dto.dateOfBirth, dateOfBirth) &&
                   Objects.equals(dto.oldUsername, oldUsername);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, lastName, password, username, firstName, dateOfBirth, oldUsername);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' firstName = {1} | lastName = {2} | username = {3} | oldUsername = {4} | password = {5} | email = {6} | \
                                    dateOfBirth = {7} '}'\
                                    """,
                                    EditorUpdateDto.class.getSimpleName(),  firstName, lastName, username, oldUsername, password, email, dateOfBirth);
    }

    //endregion Object

}
