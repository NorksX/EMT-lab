package mk.ukim.finki.wp.emtlab.service.domain;

import mk.ukim.finki.wp.emtlab.model.domain.Host;
import mk.ukim.finki.wp.emtlab.model.projections.HostProjection;
import mk.ukim.finki.wp.emtlab.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostService {
    List<Host> findAll();

    Optional<Host> save (Host host);

    Optional<Host> findById(Long id);

    Optional<Host> update (Long id, Host host);


    List<HostProjection> takeNameAndSurnameByProjection();

    void refreshMaterializedView();

    List<HostsPerCountryView> findAllPerCountry();
    void deleteById(Long id);
}