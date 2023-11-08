package net.kigen;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        staticFileLocation("/public");
        port(2200);

//        //get: show all heroes
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new HandlebarsTemplateEngine().render(
                    new ModelAndView(model, "index.hbs")
            );
        });

        logger.info("**** App is running and listening on port {} *** ",port());
    }
}