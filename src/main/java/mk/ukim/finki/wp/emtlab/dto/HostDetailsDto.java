package mk.ukim.finki.wp.emtlab.dto;

public record HostDetailsDto(
        Long id,
        String name,
        String surname,
        DisplayCountryDto country
) {}