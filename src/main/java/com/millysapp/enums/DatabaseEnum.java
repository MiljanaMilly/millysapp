package com.millysapp.enums;

public enum DatabaseEnum {

    POSTGRES_DB("Postgres"),
    MARIA_DB("MariaDB"),
    BOTH("Both");

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    DatabaseEnum(String displayName) {
        this.displayName = displayName;
    }

    public static DatabaseEnum getByName(String name) {
        for (DatabaseEnum databaseEnum : DatabaseEnum.values()) {
            if (databaseEnum.displayName.equalsIgnoreCase(name)) {
                return databaseEnum;
            }
        }
        return null;
    }
}
