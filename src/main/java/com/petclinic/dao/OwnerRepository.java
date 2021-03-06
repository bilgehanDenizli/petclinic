package com.petclinic.dao;

import com.petclinic.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository {
    List<Owner> findAll();
    Owner findById(Long id);
    List<Owner> findByLastName(String lastName);
    void create(Owner owner);
    Owner update (Owner owner);
    void delete (Long id);
}
