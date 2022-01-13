package com.petclinic.service;


import com.petclinic.exception.OwnerNotFoundException;
import com.petclinic.model.Owner;

import java.util.List;


public interface PetClinicService {
    List<Owner> findOwners();
    List<Owner> findOwnersByLastName(String lastName);
    Owner findOwner(Long id) throws OwnerNotFoundException;
    void createOwner(Owner owner);
    void update(Owner owner);
    void deleteOwner(Long id);
}
