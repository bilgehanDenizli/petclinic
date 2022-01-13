package com.petclinic.dao;

import com.petclinic.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository {
    Pet findByid(Long id);
    List<Pet> findByOwner(Long ownerId);
    void create (Pet pet);
    Pet update (Pet pet);
    void delete(Long id);
    void deleteByOwnerId (Long ownerId);
}
