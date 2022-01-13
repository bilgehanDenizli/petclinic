package com.petclinic.web;

import com.petclinic.exception.InternalServerException;
import com.petclinic.exception.OwnerNotFoundException;
import com.petclinic.model.Owner;
import com.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {
    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(method = RequestMethod.GET, value = "/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        List<Owner> owners = petClinicService.findOwners();
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner")
    public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastName) {
        List<Owner> owners = petClinicService.findOwnersByLastName(lastName);
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id) {
        try {
            Owner owner = petClinicService.findOwner(id);
            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/owner")
    public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
        try {
            petClinicService.createOwner(owner);
            Long id = owner.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody Owner ownerRequest) {
        try {
            Owner owner = petClinicService.findOwner(id);
            owner.setFirstName(ownerRequest.getFirstName());
            owner.setLastName(ownerRequest.getLastName());
            petClinicService.update(owner);

            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
        try {
            petClinicService.deleteOwner(id);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }
    }

   /* @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}", produces = "application/json")
    public ResponseEntity<?> getOwnerAsHateoasResource(@PathVariable("id") Long id) {
        try {
            Owner owner = petClinicService.findOwner(id);
            Link self = WebMvcLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withSelfRel();
            Link create = WebMvcLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/").withRel("create");
            Link update = WebMvcLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("update");
            Link delete = WebMvcLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("delete");

            EntityModel<Owner> ownerEntityModel = new EntityModel<Owner>(owner,self,create,update,delete);

            return ResponseEntity.ok(ownerEntityModel);
        } catch (OwnerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }

    }*/
}
