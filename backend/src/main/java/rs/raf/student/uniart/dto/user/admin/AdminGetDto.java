package rs.raf.student.uniart.dto.user.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import rs.raf.student.uniart.dto.user_role.UserRoleGetDto;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Objects;

public class AdminGetDto {

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

    @JsonProperty("access")
    private boolean access;

    @JsonProperty("activated")
    private boolean activated;

    //region Constructors

    public AdminGetDto() { }

    public AdminGetDto(String firstName, String lastName, String username, String email, LocalDate dateOfBirth, UserRoleGetDto userRole, boolean access, boolean activated) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setEmail(email);
        setDateOfBirth(dateOfBirth);
        setUserRole(userRole);
        setAccess(access);
        setActivated(activated);
    }

    //endregion Constructors

    //region Data

    public AdminGetDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public AdminGetDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public AdminGetDto setUsername(String username) {
        this.username = username;

        return this;
    }

    public String username() {
        return username;
    }

    public AdminGetDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    public AdminGetDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

        return this;
    }

    public LocalDate dateOfBirth() {
        return dateOfBirth;
    }

    public AdminGetDto setUserRole(UserRoleGetDto userRole) {
        this.userRole = userRole;

        return this;
    }

    public UserRoleGetDto userRole() {
        return userRole;
    }

    public AdminGetDto setAccess(boolean access) {
        this.access = access;

        return this;
    }

    public boolean access() {
        return access;
    }

    public AdminGetDto setActivated(boolean activated) {
        this.activated = activated;

        return this;
    }

    public boolean activated() {
        return activated;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof AdminGetDto dto)
            return Objects.equals(dto.email, email)         &&
                   Objects.equals(dto.access, access)       &&
                   Objects.equals(dto.lastName, lastName)   &&
                   Objects.equals(dto.username, username)   &&
                   Objects.equals(dto.userRole, userRole)   &&
                   Objects.equals(dto.activated, activated) &&
                   Objects.equals(dto.firstName, firstName) &&
                   Objects.equals(dto.dateOfBirth, dateOfBirth);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, access, lastName, username, userRole, firstName, dateOfBirth);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' firstName = {1} | lastName = {2} | username = {3} | email = {4} | dateOfBirth = {5} | userRole = {6} | \
                                    access = {7} | activated = {8} '}'\
                                    """,
                                    AdminGetDto.class.getSimpleName(), firstName, lastName, username, email, dateOfBirth, userRole, access, activated);
    }

    //endregion Object

}
