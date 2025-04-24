package mk.ukim.finki.wp.emtlab.service.application;

import mk.ukim.finki.wp.emtlab.dto.CreateUserDto;
import mk.ukim.finki.wp.emtlab.dto.DisplayUserDto;
import mk.ukim.finki.wp.emtlab.dto.LoginUserDto;
import mk.ukim.finki.wp.emtlab.model.domain.Accommodation;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

    public Accommodation addToTemporaryReservations(String username, Long accommodationId);

    public List<Accommodation> confirmReservations(String username);

    public List<Accommodation> getTemporaryReservations(String username);

}