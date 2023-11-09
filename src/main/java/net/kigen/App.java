package net.kigen;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        staticFileLocation("/public");
        //initialize database
        Sql2o db = new Sql2o(System.getenv("DB_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        try(Connection con = db.open()){
            logger.info("*** Database connection Success ");
        }catch (Sql2oException ex){
            logger.error("*** Failed to connect to Database ");
            System.out.println(ex);
            System.exit(0);
        }
        port(2200);

        //get: show all heroes
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new HandlebarsTemplateEngine().render(
                    new ModelAndView(model, "index.hbs")
            );
        });
        //get: show a hero
        //get: show a form to add new hero
        //post: add new hero
        //put: update a hero
        //delete: delete a hero
        //delete:delete all heroes-be carefull

        //post: add a user to a squad
        //delete: remove a user from a squad

        //get: show all squads
        //get: show a squad
        //get: show a form to add a squad
        //post: add a squad
        //put: update a squad
        //delete: delete a squad
        //delete: delete all squads - be carefull




        logger.info("*** App is running and listening on port {} ***",port());
    }
}