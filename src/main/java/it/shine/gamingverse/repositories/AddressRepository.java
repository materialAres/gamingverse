package it.shine.gamingverse.repositories;

import it.shine.gamingverse.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
