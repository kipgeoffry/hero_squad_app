package net.kigen.dao;

import org.sql2o.Sql2o;

public class HeroDAOImpl implements HeroDAO {
    private final Sql2o db;

    public HeroDAOImpl(Sql2o db){
        this.db = db;
    }


}
