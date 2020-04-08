package com.millysapp.dtos;

import com.millysapp.enums.DatabaseEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

public class SkunkDto {

    private UUID skunkId;
    @Size(min = 3)
    @NotBlank
    private String name;
    private Double size;
    private Double weight;
    @NotBlank
    private String color;
    private Boolean isOmnivorous;
    @Size(min = 2)
    @NotBlank
    private String familyName;
    private DatabaseEnum chosenDatabase;

    public UUID getSkunkId() {
        return skunkId;
    }

    public void setSkunkId(UUID skunkId) {
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

    public Boolean getOmnivorous() {
        return isOmnivorous;
    }

    public void setOmnivorous(Boolean omnivorous) {
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
