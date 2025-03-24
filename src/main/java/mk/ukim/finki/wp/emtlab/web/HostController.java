package mk.ukim.finki.wp.emtlab.web;

import mk.ukim.finki.wp.emtlab.model.Host;
import mk.ukim.finki.wp.emtlab.model.dto.HostDto;
import mk.ukim.finki.wp.emtlab.service.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public List<Host> findAll() {
        return hostService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Host> findById(@PathVariable Long id){
        return hostService.findById(id)
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Host> save(@RequestBody HostDto host)
    {
        return hostService.save(new HostDto(host.getName(), host.getSurname(), host.getCountry()))
                .map(c -> ResponseEntity.ok().body(c))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Host> update(@PathVariable Long id, @RequestBody HostDto host)
    {
        return hostService.update(id, new HostDto(host.getName(), host.getSurname(), host.getCountry()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id)
    {
        if (hostService.findById(id).isPresent())
        {
            hostService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }


}
