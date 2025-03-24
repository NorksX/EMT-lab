package mk.ukim.finki.wp.emtlab.model.dto;

import mk.ukim.finki.wp.emtlab.model.Accommodation;
import mk.ukim.finki.wp.emtlab.model.Host;
import mk.ukim.finki.wp.emtlab.model.enums.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccommodationDto(
        Long id,
        String name,
        Category category,
        Long host,
        Integer numRooms
) {

    public static DisplayAccommodationDto from(Accommodation accommodation)
    {
        return new DisplayAccommodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()
        );
    }

    public static List<DisplayAccommodationDto> from (List<Accommodation> accommodations)
    {
        return accommodations.stream().map(DisplayAccommodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host host)
    {
        return new Accommodation(name,category,host, numRooms);
    }

}
