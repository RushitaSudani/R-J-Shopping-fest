package org.technous.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
