package rs.raf.student.uniart.dto.user.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Objects;

public class AdminCreateDto {

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

    //NOTE: Should be removed in the future
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

    @JsonProperty("user_role")
    private String userRole;

    //region Constructors

    public AdminCreateDto() { }

    public AdminCreateDto(String firstName, String lastName, String username, String password, String email, LocalDate dateOfBirth, String userRole) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
        setUserRole(userRole);
    }

    //endregion Constructors

    //region Data

    public AdminCreateDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public AdminCreateDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public AdminCreateDto setUsername(String username) {
        this.username = username;

        return this;
    }

    public String username() {
        return username;
    }

    public AdminCreateDto setPassword(String password) {
        this.password = password;

        return this;
    }

    public String password() {
        return password;
    }

    public AdminCreateDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public AdminCreateDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

        return this;
    }

    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    public AdminCreateDto setUserRole(String userRole) {
        this.userRole = userRole;

        return this;
    }

    public String userRole() {
        return userRole;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof AdminCreateDto dto)
            return Objects.equals(dto.email, email)         &&
                   Objects.equals(dto.lastName, lastName)   &&
                   Objects.equals(dto.username, username)   &&
                   Objects.equals(dto.userRole, userRole)   &&
                   Objects.equals(dto.password, password)   &&
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
                                    {0}: '{' firstName = {1} | lastName = {2} | username = {3} | password = {4} | email = {5} | dateOfBirth = {6} | \
                                    userRole = {7} '}'\
                                    """,
                                    AdminCreateDto.class.getSimpleName(), firstName, lastName, username, password, email, dateOfBirth, userRole);
    }

    //endregion Object

}
