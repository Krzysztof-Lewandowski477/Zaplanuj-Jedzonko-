package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/app/recipe/plan/add")
public class AddRecipeToPlan extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        int id = (Integer) session.getAttribute("id");
        req.setAttribute("listPlan", new PlanDao().findAllByUsersPlans(id));
        req.setAttribute("listRecipe", new RecipeDao().findAllUsersRecipes(id));
        req.setAttribute("dayNames", new DayNameDao().findAll());
        getServletContext().getRequestDispatcher("/addRecipeToPlan.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Integer planId = Integer.parseInt(req.getParameter("plan"));
        String mealName = req.getParameter("mealName").toLowerCase();
        Integer mealNumber = Integer.parseInt(req.getParameter("number"));
        Integer recipeId = Integer.parseInt(req.getParameter("recipe"));
        Integer dayId = Integer.parseInt(req.getParameter("day"));


        if ("".equals(mealName)) {
            req.setAttribute("badParameter", "Nazwa posiłku");
            getServletContext().getRequestDispatcher("/addRecipeToPlan.jsp").forward(req, resp);
        }
        if ("".equals(mealNumber)) {
            req.setAttribute("badParameter", "Numer posiłku");
            getServletContext().getRequestDispatcher("/addRecipeToPlan.jsp").forward(req, resp);
        }

        new RecipePlanDao().create(new RecipePlan(recipeId, mealName, mealNumber, dayId, planId));

        resp.sendRedirect("/app/recipe/plan/add");

    }
}
