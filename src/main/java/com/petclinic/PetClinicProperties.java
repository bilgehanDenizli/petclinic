package com.petclinic;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "petclinic")
@Component
public class PetClinicProperties {
    private boolean displayOwnersWithPets = false;

    public boolean isDisplayOwnersWithPets() {
        return displayOwnersWithPets;
    }

    public void setDisplayOwnersWithPets(boolean displayOwnersWithPets) {
        this.displayOwnersWithPets = displayOwnersWithPets;
    }
}
