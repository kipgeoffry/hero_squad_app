package net.kigen.dao;

import net.kigen.models.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public class HeroDaoImpl implements HeroDao {
    private static final Logger logger = LoggerFactory.getLogger(HeroDaoImpl.class);
    private final Sql2o db;

    public HeroDaoImpl(Sql2o db){
        this.db = db;
    }

    @Override
    public int addWeakness(String weakness) {
        String sql = "INSERT INTO weaknesses (weakness, created_at) VALUES (:weakness,:created_at)";
        int id = 0;
        try(Connection con = db.open()){
             id= ((BigInteger)
                     con.createQuery(sql,true)
                             .addParameter("weakness",weakness)
                             .addParameter("created_at",LocalDateTime.now())
                             .executeUpdate()
                             .getKey()).intValue();
        }catch (Sql2oException ex){
            logger.error("*** There was an error adding weakness", ex);
        }
        return id;
    }

    @Override
    public int addPower(String power,int points) {
        String sql = "INSERT INTO powers (power_name,points,created_at) VALUES (:power_name, :points, :created_at)";
        int id = 0;
        try(Connection con = db.open()){
            id= ((BigInteger)
                    con.createQuery(sql,true)
                            .addParameter("power_name",power)
                            .addParameter("points",points)
                            .addParameter("created_at",LocalDateTime.now())
                            .executeUpdate()
                            .getKey()).intValue();
        }catch (Sql2oException ex){
            logger.error("*** There was an error adding power", ex);
        }
        return id;
    }

    @Override
    public Hero findHeroById(int id) {
        String sql = "SELECT hero_id as id,hero_name as name,age,wk.weakness as weakness,pw.power_name as power, h.created_at as createdAt \n" +
                "FROM heroes h\n" +
                "INNER JOIN weaknesses wk ON h.weakness_id = wk.weakness_id\n" +
                "INNER JOIN powers pw ON h.power_id = pw.power_id\n" +
                "WHERE h.hero_id = :id";
        try(Connection con = db.open()){
                    return con.createQuery(sql)
                            .addParameter("id",id)
                            .executeAndFetchFirst(Hero.class);
        }catch (Sql2oException ex){
            logger.error("*** There was an error fetching a hero", ex);
        }
        return null;
    }

    @Override
    public List<Hero> getAllHeroes() {
        String sql = "SELECT hero_id as id,hero_name as name,age,wk.weakness as weakness,pw.power_name as power, h.created_at as createdAt\n" +
                "FROM heroes h\n" +
                "INNER JOIN weaknesses wk ON h.weakness_id = wk.weakness_id\n" +
                "INNER JOIN powers pw ON h.power_id = pw.power_id";
        try(Connection con = db.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Hero.class);
        }catch (Sql2oException ex){
            logger.error("*** There was an error fetching heroes", ex);
        }
        return null;
    }

    @Override
    public void addHero(Hero hero) {
        String sql = "INSERT INTO heroes (hero_name, age,weakness_id,power_id, created_at)" +
                "VALUES ( :name, :age, :weakness_id, :power_id, :createdAt)";
        try(Connection con = db.open()){
           int id = ((BigInteger)
            con.createQuery(sql,true)
                    .addParameter("name",hero.getName())
                    .addParameter("age",hero.getAge())
                    .addParameter("weakness_id",addWeakness(hero.getWeakness()))
                    .addParameter("power_id",addPower(hero.getPower(),0)) //Todo:get points from user-fontend and add to db
                    .addParameter("createdAt",hero.getCreatedAt())
                    .executeUpdate()
                    .getKey()).intValue();
           hero.setId(id);
        }catch (Sql2oException ex){
            logger.error("*** There was an error adding hero", ex);
        }

    }

    @Override
    public void clearAllHeroes() {
        String sql ="DELETE FROM heroes";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .executeUpdate();
        }catch (Sql2oException ex){
            logger.error("*** There was an error clearing heroes", ex);
        }
    }

    @Override
    public void deleteHeroById(int id) {
        String sql ="DELETE FROM heroes\n" +
                "WHERE hero_id = :id";
        try(Connection con = db.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch (Sql2oException ex){
            logger.error("*** There was an error deleting hero", ex);
        }
    }
}
