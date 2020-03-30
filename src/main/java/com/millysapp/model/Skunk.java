package com.millysapp.model;

import javax.persistence.*;

@Entity
@Table(name = "skunks")
public class Skunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long skunkId;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private Double size;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "color")
    //later to become enum
    private String color;

    @Column(name = "is_omnivorous")
    private boolean isOmnivorous;

    @Column(name = "family_name")
    private String familyName;


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
}
