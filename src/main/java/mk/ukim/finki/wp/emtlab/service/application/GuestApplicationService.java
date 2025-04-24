package mk.ukim.finki.wp.emtlab.service.application;

import mk.ukim.finki.wp.emtlab.dto.CreateGuestDto;
import mk.ukim.finki.wp.emtlab.dto.DisplayGuestDto;

import java.util.List;
import java.util.Optional;

public interface GuestApplicationService {
    List<DisplayGuestDto> findAll();

    Optional<DisplayGuestDto> save(CreateGuestDto guest);

    Optional<DisplayGuestDto> addHost(Long guest, Long host);

    Optional<DisplayGuestDto> findById(Long id);

    Optional<DisplayGuestDto> update(Long id, CreateGuestDto guest);

    void deleteById(long id);
}