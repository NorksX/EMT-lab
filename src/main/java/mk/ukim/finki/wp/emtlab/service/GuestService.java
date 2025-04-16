package mk.ukim.finki.wp.emtlab.service;

import mk.ukim.finki.wp.emtlab.model.Guest;
import mk.ukim.finki.wp.emtlab.model.dto.GuestDto;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    List<Guest> findAll();

    Optional<Guest> save(GuestDto guest);

    Optional<Guest> findById(Long id);

    Optional<Guest> update(Long id, GuestDto guest);

    void deleteById(long id);

    Optional<Guest> addHost(Long guestId, Long hostId);
}
