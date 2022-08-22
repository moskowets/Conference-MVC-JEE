package eu.mktcode.model.entities.service;

import eu.mktcode.model.entities.DBEntity;

public class Language extends DBEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
