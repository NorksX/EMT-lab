package mk.ukim.finki.wp.emtlab.service.impl;

import mk.ukim.finki.wp.emtlab.model.Accommodation;
import mk.ukim.finki.wp.emtlab.model.dto.AccommodationDto;
import mk.ukim.finki.wp.emtlab.repository.AccommodationRepository;
import mk.ukim.finki.wp.emtlab.service.AccommodationService;
import mk.ukim.finki.wp.emtlab.service.CountryService;
import mk.ukim.finki.wp.emtlab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final CountryService countryService;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, CountryService countryService, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.countryService = countryService;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> save(AccommodationDto accommodation) {
        if(accommodation.getHost() != null &&
        hostService.findById(accommodation.getHost()).isPresent()) {
            return Optional.of(
                    accommodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(), hostService.findById(accommodation.getHost()).get(), accommodation.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, AccommodationDto accommodation) {
        return accommodationRepository.findById(id).map(existingAccommodation -> {
            if (accommodation.getHost() != null && hostService.findById(accommodation.getHost()).isPresent()) {
                existingAccommodation.setHost(hostService.findById(accommodation.getHost()).get());
            }
            if (accommodation.getName() != null) {
                existingAccommodation.setName(accommodation.getName());
            }
            if (accommodation.getNumRooms() != null) {
                existingAccommodation.setNumRooms(accommodation.getNumRooms());
            }
            if (accommodation.getCategory() != null) {
                existingAccommodation.setCategory(accommodation.getCategory());
            }
            return accommodationRepository.save(existingAccommodation);
        });
    }

    @Override
    public void deleteById(long id) {
        accommodationRepository.deleteById(id);
    }
}
