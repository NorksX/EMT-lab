package mk.ukim.finki.wp.emtlab.service.domain.impl;

import mk.ukim.finki.wp.emtlab.dto.AccommodationDetailsDto;
import mk.ukim.finki.wp.emtlab.dto.AccommodationTypeCountDTO;
import mk.ukim.finki.wp.emtlab.model.domain.Accommodation;
import mk.ukim.finki.wp.emtlab.model.enums.Category;
import mk.ukim.finki.wp.emtlab.model.exceptions.AccommodationNotFoundException;
import mk.ukim.finki.wp.emtlab.model.views.AccommodationsPerHostView;
import mk.ukim.finki.wp.emtlab.repository.AccommodationRepository;
import mk.ukim.finki.wp.emtlab.repository.AccommodationsPerHostRepository;
import mk.ukim.finki.wp.emtlab.service.domain.AccommodationService;
import mk.ukim.finki.wp.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final AccommodationsPerHostRepository accommodationsPerHostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, AccommodationsPerHostRepository accommodationsPerHostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.accommodationsPerHostRepository = accommodationsPerHostRepository;
    }

    @Override
    public void refreshMaterializedView() {
        accommodationsPerHostRepository.refreshMaterializedView();
    }

    @Override
    public List<AccommodationsPerHostView> findAllPerHost() {
        return accommodationsPerHostRepository.findAll();
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getHost() != null &&
                hostService.findById(accommodation.getHost().getId()).isPresent()) {
            return Optional.of(
                    accommodationRepository.save(new Accommodation(
                            accommodation.getName(),
                            accommodation.getCategory(),
                            hostService.findById(accommodation.getHost().getId()).get(),
                            accommodation.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id).map(acc -> {

            if (accommodation.getName() != null)
                acc.setName(accommodation.getName());

            if (accommodation.getNumRooms() != null)
                acc.setNumRooms(accommodation.getNumRooms());

            if (accommodation.getHost() != null &&
                    hostService.findById(accommodation.getHost().getId()).isPresent()) {
                acc.setHost(hostService.findById(accommodation.getHost().getId()).get());
            }

            if (accommodation.getCategory() != null) {
                acc.setCategory(accommodation.getCategory());
            }

            return accommodationRepository.save(acc);
        });
    }
    @Override
    public List<AccommodationTypeCountDTO> getAccommodationCountsByType() {
        return accommodationRepository.countByType();

    }
    @Override
    public void deleteById(long id) {
        accommodationRepository.deleteById(id);
    }

}