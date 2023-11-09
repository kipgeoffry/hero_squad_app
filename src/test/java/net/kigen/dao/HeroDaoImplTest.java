package net.kigen.dao;

import net.kigen.models.Hero;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import static org.junit.Assert.*;

public class HeroDaoImplTest {
    private static final Logger logger = LoggerFactory.getLogger(HeroDaoImplTest.class);
    private static Connection conn;
    private static HeroDaoImpl heroDao;

    @BeforeClass  //(run once before running any tests in this file)
    public static void setUp() throws Exception {
        logger.info("*** Setting up database for Testing ***");
        Sql2o db = new Sql2o("jdbc:mysql://192.168.10.93:3360/hero_squad_test" ,"demouser", "pw123456");
        try(Connection con = db.open()){
            logger.info("*** Test Database connection Success ");
        }catch (Sql2oException ex){
            logger.error("*** Failed to connect to Test Database ");
            System.out.println(ex);
            System.exit(0);
        }
        heroDao = new HeroDaoImpl(db);
        conn = db.open();      // open connection once before this test file is run
    }
    @After // run after every test
    public void tearDown() throws Exception {
        logger.info("** clearing database **");
        heroDao.clearAllHeroes(); // clear all Heroes after every test
    }
    @AfterClass //
    public static void shutDown() throws Exception{
        conn.close();
        logger.info("** Db connection closed **");
    }

    @Test
    public void addingHeroSetsId() throws Exception {
        Hero hero = setupNewHero();
        int originalId = hero.getId();
        heroDao.addHero(hero);
        int idFromDb = hero.getId();
        assertNotEquals(originalId,idFromDb);
    }

    @Test
    public void addingWeaknessReturnsId() throws Exception{
        Hero hero = setupNewHero();
        int id = heroDao.addWeakness(hero.getWeakness());
        assertNotEquals(0,id);
    }
    @Test
    public void addingPowerReturnsId() throws Exception {
        Hero hero = setupNewHero();
        int id = heroDao.addPower(hero.getPower(),0);
        assertNotEquals(0,id);
    }

    @Test
    public void existingHeroCanBeFoundById() throws Exception {
        Hero hero = setupNewHero();
        heroDao.addHero(hero);
        Hero foundHero =heroDao.findHeroById(hero.getId());
        assertTrue(hero.getName().equals(foundHero.getName()));
    }

    @Test
    public void addedHeroesCanBeReturned() throws Exception{
        Hero hero1 = setupNewHero();
        Hero hero2 = new Hero("Micah Maritim",60,"Womaniser","Talented Singer");
        Hero hero3 = new Hero("Junior Kotestes",35,"Uncontrolled spender","Comedian");
        heroDao.addHero(hero1);
        heroDao.addHero(hero2);
        heroDao.addHero(hero3);
        int heroes = heroDao.getAllHeroes().size();
        assertEquals(3,heroes);
    }

    @Test
    public void deletesAllHeroes() {
        Hero hero = setupNewHero();
        heroDao.addHero(hero);
        heroDao.clearAllHeroes();
        int heroes = heroDao.getAllHeroes().size();
        assertNotEquals(1,heroes);

    }

    //define the following once and then call it as above in your tests.
    public Hero setupNewHero(){
        return new Hero("Kiptindinyo",30,"Alcoholism","Super Farmer");
    }
}
