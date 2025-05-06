package mk.ukim.finki.wp.emtlab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.emtlab.dto.*;
import mk.ukim.finki.wp.emtlab.model.domain.User;
import mk.ukim.finki.wp.emtlab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.emtlab.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.emtlab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.emtlab.service.application.UserApplicationService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mk.ukim.finki.wp.emtlab.model.domain.Accommodation;
import mk.ukim.finki.wp.emtlab.model.exceptions.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and generates JWT")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(InvalidUserCredentialsException::new);
        }
        catch (InvalidArgumentsException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

//    @Operation(summary = "User logout", description = "Ends the user's session")
//    @ApiResponse(responseCode = "200", description = "User logged out successfully")
//    @GetMapping("/logout")
//    public void logout(HttpServletRequest request) {
//        request.getSession().invalidate();
//    }

    @Operation(summary = "Add accommodation to temporary reservations")
    @PostMapping("/add-reservation")
    public ResponseEntity<DisplayAccommodationDto> addTemporaryReservation(
            @RequestBody Long id,
            HttpServletRequest request,
            Principal principal
    ) {
        String username = principal.getName();
        if (username == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        try {
            Accommodation accommodation = userApplicationService.addToTemporaryReservations(username, id);
            return ResponseEntity.ok(DisplayAccommodationDto.from(accommodation));
        } catch (AccommodationNotAvailableException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "List all temporary reservations for the user")
    @GetMapping("/list-reservations")
    public ResponseEntity<List<DisplayAccommodationDto>> getTemporaryReservations(HttpServletRequest request, Principal principal) {
        String username = principal.getName();
        if (username == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        List<Accommodation> accommodations = userApplicationService.getTemporaryReservations(username);
        return ResponseEntity.ok(DisplayAccommodationDto.from(accommodations));
    }

    @Operation(summary = "Confirm all temporary reservations and return confirmed list")
    @PostMapping("/confirm-reservation")
    public ResponseEntity<List<DisplayAccommodationDto>> confirmReservations(HttpServletRequest request, Principal principal) {
        String username = principal.getName();
        if (username == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        try {
            List<Accommodation> confirmed = userApplicationService.confirmReservations(username);
            return ResponseEntity.ok(DisplayAccommodationDto.from(confirmed));
        } catch (AccommodationNotAvailableException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
