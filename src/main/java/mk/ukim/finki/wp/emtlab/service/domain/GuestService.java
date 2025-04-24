package mk.ukim.finki.wp.emtlab.service.domain;


import mk.ukim.finki.wp.emtlab.model.domain.Guest;
import mk.ukim.finki.wp.emtlab.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    List<Guest> findAll();

    Optional<Guest> save(Guest guest);

    Optional<Guest> addHost(Guest guest, Host host);

    Optional<Guest> findById(Long id);

    Optional<Guest> update(Long id, Guest guest);

    void deleteById(long id);
}