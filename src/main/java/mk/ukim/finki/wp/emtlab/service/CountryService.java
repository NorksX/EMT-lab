package mk.ukim.finki.wp.emtlab.service;

import mk.ukim.finki.wp.emtlab.model.Country;
import mk.ukim.finki.wp.emtlab.model.dto.CountryDto;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> save(CountryDto country);

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, CountryDto country);

    void deleteById(long id);



}
