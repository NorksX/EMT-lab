package mk.ukim.finki.wp.emtlab.service;

import mk.ukim.finki.wp.emtlab.model.Host;
import mk.ukim.finki.wp.emtlab.model.dto.HostDto;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> save (HostDto host);

    Optional<Host> findById(Long id);

    Optional<Host> update (Long id, HostDto host);

    void deleteById(Long id);
}
