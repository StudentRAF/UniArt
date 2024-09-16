package rs.raf.student.uniart.security.authentication;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;
import rs.raf.student.uniart.entity.User;
import rs.raf.student.uniart.security.password.UniArtPasswordEncoder;

public class UsernamePasswordSaltAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getPrincipal() != null) {
            String      username    = authentication.getName();
            UserDetails userDetails = getUserCache().getUserFromCache(username);

            if (userDetails == null) {
                try {
                    userDetails = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
                    getUserCache().putUserInCache(userDetails);
                }
                catch (UsernameNotFoundException exception) { //Note: original catch from AbstractUserDetailsAuthenticationProvider
                    logger.debug("Failed to find user '" + username + "'");

                    if (!this.hideUserNotFoundExceptions)
                        throw exception;

                    throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                }
            }

            Assert.notNull(userDetails, "retrieveUser returned null - a violation of the interface contract");

            ((UniArtPasswordEncoder)getPasswordEncoder()).setSalt(((User)userDetails).salt());
        }

        return super.authenticate(authentication);
    }

}
