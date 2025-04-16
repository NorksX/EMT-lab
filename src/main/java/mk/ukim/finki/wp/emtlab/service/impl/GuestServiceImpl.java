package mk.ukim.finki.wp.emtlab.service.impl;

import mk.ukim.finki.wp.emtlab.model.Guest;
import mk.ukim.finki.wp.emtlab.model.Host;
import mk.ukim.finki.wp.emtlab.model.dto.GuestDto;
import mk.ukim.finki.wp.emtlab.repository.GuestRepository;
import mk.ukim.finki.wp.emtlab.repository.HostRepository;
import mk.ukim.finki.wp.emtlab.service.CountryService;
import mk.ukim.finki.wp.emtlab.service.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final CountryService countryService;

    private final HostRepository hostRepository;

    public GuestServiceImpl(GuestRepository guestRepository, CountryService countryService, HostRepository hostRepository) {
        this.guestRepository = guestRepository;
        this.countryService = countryService;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Optional<Guest> save(GuestDto guest) {
        if(guest.getCountry() != null && countryService.findById(guest.getCountry()).isPresent())
        {
            return Optional.of(guestRepository.save(new Guest(guest.getName(), guest.getSurname(), countryService.findById(guest.getCountry()).get())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Guest> findById(Long id) {
        return guestRepository.findById(id);
    }

    @Override
    public Optional<Guest> update(Long id, GuestDto guest) {
        return guestRepository.findById(id).map(existingHost -> {
            if (guest.getName() != null)
            {
                existingHost.setName(guest.getName());
            }
            if(guest.getSurname() != null)
            {
                existingHost.setSurname(guest.getSurname());
            }
            if(guest.getCountry() != null && countryService.findById(guest.getCountry()).isPresent())
            {
                existingHost.setCountry(countryService.findById(guest.getCountry()).get());
            }
            return guestRepository.save(existingHost);
        });
    }

    @Override
    public void deleteById(long id) {
        guestRepository.deleteById(id);
    }

    @Override
    public Optional<Guest> addHost(Long guestId, Long hostId) {
        Optional<Guest> guest = guestRepository.findById(guestId);
        Optional<Host> host = hostRepository.findById(hostId);

        if (guest.isPresent() && host.isPresent()) {
            guest.get().addHost(host.get());
            return Optional.of(guestRepository.save(guest.get()));
        }
        return Optional.empty();
    }
}
