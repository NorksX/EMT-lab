package mk.ukim.finki.wp.emtlab.service.application;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.wp.emtlab.dto.CreateCountryDto;
import mk.ukim.finki.wp.emtlab.dto.DisplayCountryDto;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> save(CreateCountryDto country);

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto country);

    void deleteById(long id);
}