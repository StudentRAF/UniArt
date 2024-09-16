package rs.raf.student.uniart.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.raf.student.uniart.repository.IUserRepository;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements ISecurityUserService {

    private final IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                         .orElseThrow(() -> new UsernameNotFoundException("Could not find User. User with username: " + username + " does not exist."));
    }

}
