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
        Sql2o db = new Sql2o("jdbc:mysql://192.168.10.93:3360/hero_squad" ,"demouser", "pw123456");
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
    public void tearDown() throws Exception { //I have changed
        logger.info("** clearing database **");
//        categoryDao.clearAllCategories(); // clear all categories after every test
//        taskDao.clearAllTasks(); // clear all tasks after every test
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

    //define the following once and then call it as above in your tests.
    public Hero setupNewHero(){
        return new Hero("Kiptindinyo",30,"Alcoholism","Super Farmer");
    }
}
