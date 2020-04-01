package com.millysapp.dtos;

import com.millysapp.enums.DatabaseEnum;

public class SkunkDto {

    private Long skunkId;
    private String name;
    private Double size;
    private Double weight;
    private String color;
    private boolean isOmnivorous;
    private String familyName;
    private DatabaseEnum chosenDatabase;

    public Long getSkunkId() {
        return skunkId;
    }

    public void setSkunkId(Long skunkId) {
        this.skunkId = skunkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isOmnivorous() {
        return isOmnivorous;
    }

    public void setOmnivorous(boolean omnivorous) {
        isOmnivorous = omnivorous;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public DatabaseEnum getChosenDatabase() {
        return chosenDatabase;
    }

    public void setChosenDatabase(DatabaseEnum chosenDatabase) {
        this.chosenDatabase = chosenDatabase;
    }
}
