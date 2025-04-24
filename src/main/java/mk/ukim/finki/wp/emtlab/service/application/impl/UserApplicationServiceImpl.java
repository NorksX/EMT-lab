package mk.ukim.finki.wp.emtlab.service.application.impl;

import mk.ukim.finki.wp.emtlab.dto.CreateUserDto;
import mk.ukim.finki.wp.emtlab.dto.DisplayUserDto;
import mk.ukim.finki.wp.emtlab.dto.LoginUserDto;
import mk.ukim.finki.wp.emtlab.model.domain.User;
import mk.ukim.finki.wp.emtlab.model.domain.Accommodation;
import mk.ukim.finki.wp.emtlab.service.application.UserApplicationService;
import mk.ukim.finki.wp.emtlab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(DisplayUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public Accommodation addToTemporaryReservations(String username, Long accommodationId) {
        return userService.addToTemporaryReservations(username, accommodationId);
    }

    @Override
    public List<Accommodation> confirmReservations(String username) {
        userService.confirmReservations(username);
        return userService.getTemporaryReservations(username);
    }

    @Override
    public List<Accommodation> getTemporaryReservations(String username) {
        return userService.getTemporaryReservations(username);
    }
}