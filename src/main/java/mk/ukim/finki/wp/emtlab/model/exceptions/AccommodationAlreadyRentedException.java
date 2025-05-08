package mk.ukim.finki.wp.emtlab.model.exceptions;

public class AccommodationAlreadyRentedException extends RuntimeException{
    public AccommodationAlreadyRentedException() {
        super("Accommodation is already rented exception");
    }
}