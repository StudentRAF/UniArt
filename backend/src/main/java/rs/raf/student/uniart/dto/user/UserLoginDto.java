package rs.raf.student.uniart.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.text.MessageFormat;
import java.util.Objects;

public class UserLoginDto {

    @NotBlank
    @Size(max = 64)
    @JsonProperty("username")
    private String username;

    @NotBlank
    @Size(max = 64)
    @JsonProperty("password")
    private String password;

    //region Constructors

    public UserLoginDto() { }

    public UserLoginDto(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    //endregion Constructors

    //region Data

    public UserLoginDto setUsername(String username) {
        this.username = username;

        return this;
    }

    public String username() {
        return username;
    }

    public UserLoginDto setPassword(String password) {
        this.password = password;

        return this;
    }

    public String password() {
        return password;
    }

    //endregion Data

    //region Object

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        if (object instanceof UserLoginDto dto)
            return Objects.equals(dto.username, username) &&
                   Objects.equals(dto.password, password);

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    public String toString() {
        return MessageFormat.format("""
                                    {0}: '{' username = {1} | password = {2} '}'\
                                    """,
                                    UserLoginDto.class.getSimpleName(), username, password);
    }

    //endregion Object

}
