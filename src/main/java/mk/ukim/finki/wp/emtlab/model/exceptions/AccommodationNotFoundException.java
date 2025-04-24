package mk.ukim.finki.wp.emtlab.model.exceptions;

public class AccommodationNotFoundException extends RuntimeException {
    public AccommodationNotFoundException(Long id) {
        super("Accommodation not found: " + id);
    }
}