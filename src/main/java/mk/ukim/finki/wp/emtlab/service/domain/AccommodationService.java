package mk.ukim.finki.wp.emtlab.service.domain;

import mk.ukim.finki.wp.emtlab.dto.AccommodationDetailsDto;
import mk.ukim.finki.wp.emtlab.dto.AccommodationTypeCountDTO;
import mk.ukim.finki.wp.emtlab.model.domain.Accommodation;
import mk.ukim.finki.wp.emtlab.model.views.AccommodationsPerHostView;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    List<Accommodation> findAll();

    Optional<Accommodation> save(Accommodation accommodation);

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    List<AccommodationTypeCountDTO> getAccommodationCountsByType();

    void refreshMaterializedView();

    List<AccommodationsPerHostView> findAllPerHost();
    void deleteById(long id);

}