package rs.raf.student.uniart.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import rs.raf.student.uniart.security.password.UniArtPasswordEncoder;
import rs.raf.student.uniart.service.user.ISecurityUserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityAuthenticationManager implements AuthenticationManager {

    private       ProviderManager      providerManager;
    private final ISecurityUserService securityUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return getProviderManager().authenticate(authentication);
    }

    private ProviderManager getProviderManager() { //TODO: it might need to always return new ProviderManager
        if (providerManager == null)
            providerManager = new ProviderManager(List.of(usernamePasswordSaltAuthenticationProvider()));

        return providerManager;
    }

    private AuthenticationProvider usernamePasswordSaltAuthenticationProvider() {
        UsernamePasswordSaltAuthenticationProvider authenticationProvider = new UsernamePasswordSaltAuthenticationProvider();

        authenticationProvider.setUserDetailsService(securityUserService);
        authenticationProvider.setPasswordEncoder(new UniArtPasswordEncoder());

        return authenticationProvider;
    }

}
