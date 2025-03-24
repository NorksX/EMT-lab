package mk.ukim.finki.wp.emtlab.service;

import mk.ukim.finki.wp.emtlab.model.Accommodation;
import mk.ukim.finki.wp.emtlab.model.Country;
import mk.ukim.finki.wp.emtlab.model.dto.AccommodationDto;

import java.util.AbstractCollection;
import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    List<Accommodation> findAll();

    Optional<Accommodation> save(AccommodationDto accommodation);

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, AccommodationDto accommodation);

    void deleteById(long id);
}
