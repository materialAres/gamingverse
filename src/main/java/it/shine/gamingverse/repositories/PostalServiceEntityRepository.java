package it.shine.gamingverse.repositories;

import it.shine.gamingverse.entities.PostalServiceInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalServiceEntityRepository extends JpaRepository<PostalServiceInformation, Integer> {
}
