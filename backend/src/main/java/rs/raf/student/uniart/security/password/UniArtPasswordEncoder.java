package rs.raf.student.uniart.security.password;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.raf.student.uniart.utils.PasswordUtilities;

import java.util.Objects;

@Data
@RequiredArgsConstructor
public class UniArtPasswordEncoder implements PasswordEncoder {

    private String salt;

    @Override
    public String encode(CharSequence rawPassword) {
        return PasswordUtilities.encodeBase64(DigestUtils.sha256(rawPassword + salt));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(encode(rawPassword), encodedPassword);
    }

}
