package net.kigen.dao;

import net.kigen.models.Hero;

import java.time.LocalDateTime;
import java.util.List;

public interface HeroDao {
    int addWeakness(String weakness);
    int addPower(String power,int points);

    // LIST
    List<Hero> getAllHeroes();
   // CREATE
    void addHero(Hero hero);
//    // READ
    Hero findHeroById(int id);

//    //UPDATE
//    void update(int id, String name, int age, String weakness, String power);
//
//    //DELETE
//    void deleteById(int id);
    void clearAllHeroes();
//



}
