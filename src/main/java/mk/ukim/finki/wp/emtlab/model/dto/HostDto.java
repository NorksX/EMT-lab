package mk.ukim.finki.wp.emtlab.model.dto;

import lombok.Data;
import mk.ukim.finki.wp.emtlab.model.Guest;

import java.util.ArrayList;
import java.util.List;

@Data
public class HostDto {
    private Long id;

    private String name;
    private String surname;
    private Long country;
    private List<Guest> guests;

    public HostDto() {
    }

    public HostDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        guests = new ArrayList<>();
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Long getCountry() {
        return country;
    }
}
