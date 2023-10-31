package com.example.pokedex;

import androidx.annotation.NonNull;

public class Pokemon {
    int natNum;
    String name;
    String species;
    String gender;
    float height;
    float weight;
    int level;
    int hp;
    int attack;
    int defense;

    public Pokemon(int natNum, String name, String species, String gender, float height, float weight, int level, int hp, int attack, int defense){
        this.natNum = natNum;
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public int getNatNum() {
        return natNum;
    }

    public void setNatNum(int natNum) {
        this.natNum = natNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @NonNull
    @Override
    public String toString(){
        String str = natNum + ": " + name + "/"  + species + " | " + gender + "\nHeight: "
                + height + " | Weight: " + weight + " | Level: " + level + "\nHP: "
                + hp + " | Attack: " + attack + " | Defense: " + defense;
        return str;
    }
}
