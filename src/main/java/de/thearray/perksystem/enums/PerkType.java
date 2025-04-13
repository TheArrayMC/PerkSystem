package de.thearray.perksystem.enums;

public enum PerkType {

    //Name = Debug name
    //DisplayName = Name for Items in Inventory or something else
    FAST_BRAKE("Schnelles Abbauen", "§8» §eSchnelles Abbauen", "§e"),
    NO_HUNGER("Kein Hunger", "§8» §aKein Hunger", "§a"),
    SPEED("Schnelligkeit", "§8» §6Schnelligkeit", "§6"),
    KEEP_INVENTORY("Inventar behalten", "§8» §2Inventar behalten", "§2");

    private final String name, displayName, color;

    PerkType(String name, String displayName, String color) {
        this.name = name;
        this.displayName = displayName;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getDisplayName() {
        return displayName;
    }
}
