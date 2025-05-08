package mk.ukim.finki.wp.emtlab.dto;

import mk.ukim.finki.wp.emtlab.model.domain.Accommodation;
import mk.ukim.finki.wp.emtlab.model.domain.Host;
import mk.ukim.finki.wp.emtlab.model.enums.Category;

import java.util.List;

public record AccommodationDetailsDto(
        Long id,
        String name,
        Integer numRooms,
        Category category,
        String hostName,
        String hostSurname,
        String countryName


) {

    public static AccommodationDetailsDto from(Accommodation accommodation) {
        return new AccommodationDetailsDto(accommodation.getId(), accommodation.getName(), accommodation.getNumRooms(),accommodation.getCategory(), accommodation.getHost().getName(), accommodation.getHost().getSurname(), accommodation.getHost().getCountry().getName());
    }

  }
