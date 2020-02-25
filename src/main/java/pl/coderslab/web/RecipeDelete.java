package pl.coderslab.web;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
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
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/app/recipe/list/delete")
public class RecipeDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id", req.getParameter("id"));
        getServletContext().getRequestDispatcher("/recipeDelete.jsp").forward(req, resp);

        System.out.println(req.getParameter("id"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("confirm"));
        if (req.getParameter("confirm") != null) {
            new RecipeDao().delete(Integer.parseInt(req.getParameter("confirm")));
        }
        resp.sendRedirect("/app/recipe/list");

    }
}
