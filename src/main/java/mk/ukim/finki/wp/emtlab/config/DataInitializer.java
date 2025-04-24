package mk.ukim.finki.wp.emtlab.config;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.emtlab.model.domain.*;
import mk.ukim.finki.wp.emtlab.model.enums.Role;
import mk.ukim.finki.wp.emtlab.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import mk.ukim.finki.wp.emtlab.model.enums.Category;


@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    private final GuestRepository guestRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository, GuestRepository guestRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init()
    {
        Country country1 = countryRepository.save(new Country("Macedonia", "Europe"));
        Country country2 = countryRepository.save(new Country("Japan","Asia"));
        Country country3 = countryRepository.save(new Country("Argentina", "South America"));

        Host host1 = hostRepository.save(new Host("Boris","Manev", country1));
        Host host2 = hostRepository.save(new Host("John", "Doe", country2));
        Host host3 = hostRepository.save(new Host("Jane","Doe", country3));

        Accommodation accommodation1 = accommodationRepository.save(new Accommodation("Ohrid Apartmani",Category.APARTMENT , host1, 8));
        Accommodation accommodation2 = accommodationRepository.save(new Accommodation("Tokyo Room Special", Category.ROOM,host2,1));
        Accommodation accommodation3 = accommodationRepository.save(new Accommodation("Messi childhood House",Category.HOUSE , host3, 3));

        Guest guest1 = guestRepository.save(new Guest("Trajce", "Trajkovski", country2));
        Guest guest2 = guestRepository.save(new Guest("Stefan", "Novkovski", country1));
        Guest guest3 = guestRepository.save(new Guest("Donald", "Trump", country3));

        userRepository.save(new User(
                "bm",
                passwordEncoder.encode("bm"),
                "Boris",
                "Manev",
                Role.ROLE_HOST
        ));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("user"),
                "user",
                "user",
                Role.ROLE_USER
        ));



    }
}
