package com.petclinic.dao.mem;

import com.petclinic.dao.OwnerRepository;
import com.petclinic.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OwnerRepositoryInMemoryImpl implements OwnerRepository {
    private Map<Long, Owner> ownersMap = new HashMap<>();

    public OwnerRepositoryInMemoryImpl() {
        Owner owner1 = new Owner();
        owner1.setFirstName("Kenan");
        owner1.setLastName("Sevindik");
        owner1.setId(1L);

        Owner owner2 = new Owner();
        owner2.setFirstName("Benan");
        owner2.setLastName("Den");
        owner2.setId(2L);

        Owner owner3 = new Owner();
        owner3.setFirstName("Cenan");
        owner3.setLastName("Izli");
        owner3.setId(3L);

        Owner owner4 = new Owner();
        owner4.setFirstName("Denan");
        owner4.setLastName("Bilgehan");
        owner4.setId(4L);

        ownersMap.put(owner1.getId(),owner1);
        ownersMap.put(owner2.getId(),owner2);
        ownersMap.put(owner3.getId(),owner3);
        ownersMap.put(owner4.getId(),owner4);
    }

    @Override
    public List<Owner> findAll() {
        return new ArrayList<>(ownersMap.values());
    }

    @Override
    public Owner findById(Long id) {
        return ownersMap.get(id);
    }

    @Override
    public List<Owner> findByLastName(String lastName) {
        return ownersMap.values().stream().filter(owner -> owner.getLastName().equals(lastName)).collect(Collectors.toList());
    }

    @Override
    public void create(Owner owner) {
        owner.setId(new Date().getTime());
        ownersMap.put(owner.getId(),owner);
    }

    @Override
    public Owner update(Owner owner) {
        ownersMap.replace(owner.getId(),owner);
        return owner;
    }

    @Override
    public void delete(Long id) {
        ownersMap.remove(id);
    }
}
