package rs.raf.student.uniart.service.user;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.raf.student.uniart.dto.user.UserLoginDto;
import rs.raf.student.uniart.entity.User;
import rs.raf.student.uniart.exception.UniArtException;
import rs.raf.student.uniart.extension.AuthenticationExtension;
import rs.raf.student.uniart.mapper.UserMapper;
import rs.raf.student.uniart.repository.IUserRepository;
import rs.raf.student.uniart.security.authentication.SecurityAuthenticationManager;
import rs.raf.student.uniart.type.AuthenticationSchemeType;
import rs.raf.student.uniart.utils.JwtUtilities;

import static rs.raf.student.uniart.exception.ExceptionType.FIND_USER_NOT_FOUND_USERNAME;
import static rs.raf.student.uniart.exception.ExceptionType.GENERATE_AUTHORIZATION_TOKEN_NOT_FOUND_AUTHENTICATED_USER;

@Service
@RequiredArgsConstructor
@ExtensionMethod({UserMapper.class, AuthenticationExtension.class})
public class UserService implements IUserService {

    private final IUserRepository               repository;
    private final SecurityAuthenticationManager authenticationManager;

    @Override
    public Page<Object> findAll(Pageable pageable) throws UniArtException {
        Page<User> users = repository.findAll(pageable);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return users.map(user -> user.mapDto(authentication.permissionType(user)));
    }

    @Override
    public Object findByUsername(String username) throws UniArtException {
        User user = repository.findByUsername(username).orElseThrow(() -> new UniArtException(FIND_USER_NOT_FOUND_USERNAME, username));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return user.mapDto(authentication.permissionType(user));
    }

    @Override
    public Object login(UserLoginDto loginDto) throws UniArtException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ((User) authentication.getPrincipal()).mapDto();
    }

    @Override
    public String authorizationToken() throws UniArtException {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User authenticatedUser)
            return AuthenticationSchemeType.BEARER.addCredentials(JwtUtilities.generateToken(authenticatedUser));

        throw new UniArtException(GENERATE_AUTHORIZATION_TOKEN_NOT_FOUND_AUTHENTICATED_USER);
    }

}
