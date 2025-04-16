package mk.ukim.finki.wp.emtlab.web;


import mk.ukim.finki.wp.emtlab.model.Guest;
import mk.ukim.finki.wp.emtlab.model.Host;
import mk.ukim.finki.wp.emtlab.model.dto.GuestDto;
import mk.ukim.finki.wp.emtlab.model.dto.HostDto;
import mk.ukim.finki.wp.emtlab.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<Guest> findAll() {
        return guestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> findById(@PathVariable Long id){
        return guestService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Guest> save(@RequestBody GuestDto guest)
    {
        return guestService.save(new GuestDto(guest.getName(), guest.getSurname(), guest.getCountry()))
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Guest> update(@PathVariable Long id, @RequestBody GuestDto guest)
    {
        return guestService.update(id, new GuestDto(guest.getName(), guest.getSurname(), guest.getCountry()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add_host/{id}")
    public ResponseEntity<Guest> addHost(@PathVariable Long id, @RequestBody Long hostId)
    {
        return guestService.addHost(id,hostId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id)
    {
        if (guestService.findById(id).isPresent())
        {
            guestService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
