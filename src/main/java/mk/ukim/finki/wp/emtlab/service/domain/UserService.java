package mk.ukim.finki.wp.emtlab.service.domain;


import mk.ukim.finki.wp.emtlab.model.domain.Accommodation;
import mk.ukim.finki.wp.emtlab.model.enums.Role;
import mk.ukim.finki.wp.emtlab.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);

    public Accommodation addToTemporaryReservations(String username, Long accommodationId);

    public void confirmReservations(String username);

    public List<Accommodation> getTemporaryReservations(String username);
    }