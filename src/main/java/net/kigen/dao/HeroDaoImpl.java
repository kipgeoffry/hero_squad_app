package net.kigen.dao;

import net.kigen.models.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.math.BigInteger;

public class HeroDaoImpl implements HeroDao {
    private static final Logger logger = LoggerFactory.getLogger(HeroDaoImpl.class);
    private final Sql2o db;

    public HeroDaoImpl(Sql2o db){
        this.db = db;
    }

    @Override
    public void add(Hero hero) {
        String sql = "INSERT INTO heroes (hero_name, age, created_at)" +
                "VALUES ( :name, :age, :createdAt)";
        try(Connection con = db.open()){
           int id = ((BigInteger)
            con.createQuery(sql,true)
                    .addParameter("name",hero.getName())
                    .addParameter("age",hero.getAge())
                    .addParameter("createdAt",hero.getCreatedAt())
                    .executeUpdate()
                    .getKey()).intValue();
           hero.setId(id);
        }catch (Sql2oException ex){
            logger.error("*** There was an error adding hero");
            System.out.println(ex);
        }

    }
}
