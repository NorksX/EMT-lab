package mk.ukim.finki.wp.emtlab.service.application;
import java.util.List;
import java.util.Optional;

import mk.ukim.finki.wp.emtlab.dto.AccommodationTypeCountDTO;
import mk.ukim.finki.wp.emtlab.dto.CreateAccommodationDto;
import mk.ukim.finki.wp.emtlab.dto.DisplayAccommodationDto;
public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto accommodation);

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto accommodation);
    List<AccommodationTypeCountDTO> getAccommodationCountsByType();


    void deleteById(long id);
}