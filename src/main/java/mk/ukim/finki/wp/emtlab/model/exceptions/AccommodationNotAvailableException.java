package mk.ukim.finki.wp.emtlab.model.exceptions;

public class AccommodationNotAvailableException extends RuntimeException {
    public AccommodationNotAvailableException(Long id) {
        super("Accommodation not available: " + id);
    }
}