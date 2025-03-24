package mk.ukim.finki.wp.emtlab.model.dto;

import lombok.Data;

@Data
public class CountryDto {
    private Long id;
    private String name;
    private String continent;

    public CountryDto() {
    }

    public CountryDto(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }
}
