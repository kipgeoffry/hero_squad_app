package net.kigen.dao;

import net.kigen.models.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class HeroDaoImpl implements HeroDao {
    private static final Logger logger = LoggerFactory.getLogger(HeroDaoImpl.class);
    private final Sql2o db;

    public HeroDaoImpl(Sql2o db){
        this.db = db;
    }

    @Override
    public int addWeakness(String weakness) {
        String sql = "INSERT INTO weaknesses (weakness, created_at) VALUES (:weakness,:createdAt)";
        int id = 0;
        try(Connection con = db.open()){
             id= ((BigInteger)
                    con.createQuery(sql)
                    .addParameter("weakness",weakness)
                    .addParameter("createdAt",LocalDateTime.now())
                    .executeUpdate()
                    .getKey()).intValue();
        }catch (Sql2oException ex){
            logger.error("*** There was an error executing query");
        }
        return id;
    }

    @Override
    public int addPower(String power,int points) {
        String sql = "INSERT INTO powers (power_name, points,created_at) VALUES (:power_name, :points, :created_at)";
        int id = 0;
        try(Connection con = db.open()){
            id= ((BigInteger)
                    con.createQuery(sql)
                            .addParameter("power_name",power)
                            .addParameter("points",points)
                            .addParameter("createdAt",LocalDateTime.now())
                            .executeUpdate()
                            .getKey()).intValue();
        }catch (Sql2oException ex){
            logger.error("*** There was an error executing query");
        }
        return id;
    }

    @Override
    public void addHero(Hero hero) {
        String sql = "INSERT INTO heroes (hero_name, age,weakness_id,power_id, created_at)" +
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
