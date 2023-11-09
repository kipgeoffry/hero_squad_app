package net.kigen.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Hero {
    private int id;
    private String name;
    private int age;
    private String weakness;
    private String power;
    private LocalDateTime createdAt;
    public Hero(String name, int age, String weakness, String power) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weakness = weakness;
        this.power = power;
        this.createdAt = LocalDateTime.now();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return id == hero.id && age == hero.age && Objects.equals(name, hero.name) && Objects.equals(weakness, hero.weakness) && Objects.equals(power, hero.power) && Objects.equals(createdAt, hero.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, weakness, power, createdAt);
    }

}

