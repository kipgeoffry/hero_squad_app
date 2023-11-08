package net.kigen.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class HeroDAOImplTest {
    private static final Logger logger = LoggerFactory.getLogger(HeroDAOImplTest.class);
    private static Connection conn;

    @BeforeClass  //(run once before running any tests in this file)
    public static void setUp() throws Exception {
        logger.info("*** Setting up database for Testing ***");
        Sql2o db = new Sql2o("DB_URL/DB_TEST_NAME", "DB_USER", "DB_PASSWORD");
//        taskDao = new Sql2oTaskDao(sql2o);
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




}
