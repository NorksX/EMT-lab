package mk.ukim.finki.wp.emtlab.model.dto;

import lombok.Data;

@Data
public class HostDto {
    private Long id;

    private String name;
    private String surname;
    private Long country;

    public HostDto() {
    }

    public HostDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
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
