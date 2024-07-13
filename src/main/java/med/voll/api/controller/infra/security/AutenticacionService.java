package med.voll.api.controller.infra.security;

import med.voll.api.controller.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {



        return repository.findByLogin(username);
    }
}
