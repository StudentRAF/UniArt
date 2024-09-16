package rs.raf.student.uniart.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.raf.student.uniart.dto.user.UserLoginDto;
import rs.raf.student.uniart.exception.UniArtException;

public interface IUserService {

    Page<Object> findAll(Pageable pageable) throws UniArtException;

    Object findByUsername(String username) throws UniArtException;

    Object login(UserLoginDto loginDto) throws UniArtException;

    String authorizationToken() throws UniArtException;

}
