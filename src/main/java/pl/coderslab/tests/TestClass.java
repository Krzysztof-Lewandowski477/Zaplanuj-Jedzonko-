package pl.coderslab.tests;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import java.util.ArrayList;
import java.util.List;

public class TestClass {

    public static void main(String[] args) {

        Admin admin = new Admin("Dupa", "Wołowa", "email@email.com", "admin", 1, 1);
        admin.setId(1);
        new AdminDao().update(admin);

    }

}
