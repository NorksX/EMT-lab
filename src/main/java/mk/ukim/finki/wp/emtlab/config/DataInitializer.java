package mk.ukim.finki.wp.emtlab.config;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.emtlab.model.Accommodation;
import mk.ukim.finki.wp.emtlab.model.Country;
import mk.ukim.finki.wp.emtlab.model.Host;
import mk.ukim.finki.wp.emtlab.repository.AccommodationRepository;
import mk.ukim.finki.wp.emtlab.repository.CountryRepository;
import mk.ukim.finki.wp.emtlab.repository.HostRepository;
import mk.ukim.finki.wp.emtlab.web.AccommodationController;
import org.springframework.stereotype.Component;
import mk.ukim.finki.wp.emtlab.model.enums.Category;


@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, CountryRepository countryRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
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
    }
}
