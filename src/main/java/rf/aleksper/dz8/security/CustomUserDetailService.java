package rf.aleksper.dz8.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import rf.aleksper.dz8.entity.Reader;
import rf.aleksper.dz8.repository.ReaderRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final ReaderRepository readerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Reader reader = readerRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь " + username + " не найден."));
        return new User(reader.getLogin(), reader.getPassword(), List.of(
                new SimpleGrantedAuthority(reader.getRole())
        ));
    }
}
