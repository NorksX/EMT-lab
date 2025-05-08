package mk.ukim.finki.wp.emtlab.repository;

import mk.ukim.finki.wp.emtlab.dto.AccommodationDetailsDto;
import mk.ukim.finki.wp.emtlab.dto.AccommodationTypeCountDTO;
import mk.ukim.finki.wp.emtlab.model.domain.Accommodation;
import mk.ukim.finki.wp.emtlab.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findAllByUser(User user);

    @Query("SELECT new mk.ukim.finki.wp.emtlab.dto.AccommodationTypeCountDTO (a.category, COUNT(a)) FROM Accommodation a GROUP BY a.category")
    List<AccommodationTypeCountDTO> countByType();

}
