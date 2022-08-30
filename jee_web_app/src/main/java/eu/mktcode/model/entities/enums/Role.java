package eu.mktcode.model.entities.enums;

public enum Role {
    ADMINISTRATOR(1),
    MODERATOR(2),
    SPEAKER(3),
    USER(4);

    final int ordinal;

    Role(int ordinal) {
        this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return ordinal;
    }
}
