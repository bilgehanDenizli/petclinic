package com.petclinic.service.implementation;

import com.petclinic.dao.OwnerRepository;
import com.petclinic.exception.OwnerNotFoundException;
import com.petclinic.model.Owner;
import com.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class PetClinicServiceImpl implements PetClinicService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Owner> findOwnersByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Owner findOwner(Long id) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(id);
        if (owner == null) throw new OwnerNotFoundException("Owner not found with id: " + id);
        return owner;
    }

    @Override
    public void createOwner(Owner owner) {
        ownerRepository.create(owner);
    }

    @Override
    public void update(Owner owner) {
        ownerRepository.update(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        ownerRepository.delete(id);
    }
}
