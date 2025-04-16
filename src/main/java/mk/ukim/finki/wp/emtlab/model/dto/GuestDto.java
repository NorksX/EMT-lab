package mk.ukim.finki.wp.emtlab.model.dto;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.wp.emtlab.model.Country;
import mk.ukim.finki.wp.emtlab.model.Host;

import java.util.ArrayList;
import java.util.List;

@Data
public class GuestDto {

    private Long id;

    private String name;

    private String surname;

    private Long country;


    public GuestDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public GuestDto() {

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