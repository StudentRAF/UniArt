package rs.raf.student.uniart.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.MessageFormat;
import java.util.Objects;

public class UserGetDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    //region Constructors

    public UserGetDto() { }

    public UserGetDto(String firstName, String lastName, String username, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setEmail(email);
    }

    //endregion Constructors

    //region Data

    public UserGetDto setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String firstName() {
        return firstName;
    }

    public UserGetDto setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }

    public String lastName() {
        return lastName;
    }

    public UserGetDto setUsername(String username) {
        this.username = username;

        return this;
    }

    public String username() {
        return username;
    }

    public UserGetDto setEmail(String email) {
        this.email = email;

        return this;
    }

    public String email() {
        return email;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof UserGetDto dto)
            return Objects.equals(dto.email, email)       &&
                   Objects.equals(dto.lastName, lastName) &&
                   Objects.equals(dto.username, username) &&
                   Objects.equals(dto.firstName, firstName);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, lastName, username, firstName);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' firstName = {1} | lastName = {2} | username = {3} | email = {4} '}'\
                                    """,
                                    UserGetDto.class.getSimpleName(), firstName, lastName, username, email);
    }

    //endregion Object

}
