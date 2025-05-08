package mk.ukim.finki.wp.emtlab.service.application;


import mk.ukim.finki.wp.emtlab.dto.CreateHostDto;
import mk.ukim.finki.wp.emtlab.dto.DisplayHostDto;
import mk.ukim.finki.wp.emtlab.model.projections.HostProjection;
import mk.ukim.finki.wp.emtlab.model.views.HostsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> save (CreateHostDto host);

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> update (Long id, CreateHostDto host);

    List<HostProjection> takeNameAndSurnameByProjection();

    List<HostsPerCountryView> findAllPerCountry();

    void deleteById(Long id);
}