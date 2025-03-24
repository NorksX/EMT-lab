package mk.ukim.finki.wp.emtlab.model.dto;

import lombok.Data;
import mk.ukim.finki.wp.emtlab.model.enums.Category;

@Data
public class AccommodationDto {
    private Long id;


    private String name;

    private Category category;

    private Long host;

    private Integer numRooms;

    public AccommodationDto()
    {
    }

    public AccommodationDto(String name, Category category, Long host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public Long getHost() {
        return host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }


}
