package mk.ukim.finki.wp.emtlab.dto;

import mk.ukim.finki.wp.emtlab.model.domain.Country;
import mk.ukim.finki.wp.emtlab.model.domain.Guest;

import java.util.List;

public record DisplayGuestDto(
        Long id,
        String name,

        String surname,
        Long country
) {
    public Guest toGuest(Country country) {
        return new Guest(name,surname, country);
    }

    public static DisplayGuestDto from(Guest guest) {
        return new DisplayGuestDto(guest.getId(), guest.getName(), guest.getSurname(), guest.getCountry().getId());
    }

    public static List<DisplayGuestDto> from(List<Guest> guests) {
        return guests.stream().map(DisplayGuestDto::from).toList();
    }
}
