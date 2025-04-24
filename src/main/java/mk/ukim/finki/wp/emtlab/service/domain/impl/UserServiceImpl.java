package mk.ukim.finki.wp.emtlab.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.emtlab.model.domain.Accommodation;
import mk.ukim.finki.wp.emtlab.model.domain.User;
import mk.ukim.finki.wp.emtlab.model.enums.Role;
import mk.ukim.finki.wp.emtlab.model.exceptions.*;
import mk.ukim.finki.wp.emtlab.repository.AccommodationRepository;
import mk.ukim.finki.wp.emtlab.repository.UserRepository;
import mk.ukim.finki.wp.emtlab.service.domain.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AccommodationRepository accommodationRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AccommodationRepository accommodationRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User register(
            String username,
            String password,
            String repeatPassword,
            String name,
            String surname,
            Role userRole
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword)) throw new PasswordsDoNotMatchException();
        if (userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname, userRole);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        Object InvalidUserCredentialsException;
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(
                InvalidUserCredentialsException::new);
    }
    @Override
    @Transactional
    public Accommodation addToTemporaryReservations(String username, Long accommodationId) {
        User user = this.findByUsername(username);
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));

        if(accommodation.getRented() || accommodation.getUser() != null) {
            throw new AccommodationNotAvailableException(accommodationId);
        }

        accommodation.setUser(user);
        return accommodationRepository.save(accommodation);
    }

    @Override
    @Transactional
    public void confirmReservations(String username) {
        User user = this.findByUsername(username);
        List<Accommodation> temporaryReservations = accommodationRepository.findAllByUser(user);

        temporaryReservations.forEach(acc -> {
            acc.setRented(true);
            acc.setUser(user);
            accommodationRepository.save(acc);
        });
    }
    @Override
    public List<Accommodation> getTemporaryReservations(String username) {
        User user = findByUsername(username);
        if(accommodationRepository.findAllByUser(user) != null)
            return accommodationRepository.findAllByUser(user);
        else
            return Collections.emptyList();
    }

}