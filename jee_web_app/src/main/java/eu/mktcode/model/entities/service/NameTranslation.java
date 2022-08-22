package eu.mktcode.model.entities.service;

import eu.mktcode.model.entities.DBEntity;

public class NameTranslation extends DBEntity {
    private String translation;
    private int languageId;


    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}
