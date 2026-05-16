package Controlleur;

import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import Model.produits;
import DAO.DAOFactory;
import DAO.IProduitDao;
import DAO.userDAO;
import jakarta.servlet.http.HttpSession;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Hello.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String L = request.getParameter("login");
        String p = request.getParameter("password");

        User user = userDAO.authenticate(L, p);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getLogin());
            session.setAttribute("role", user.getRole()); // "admin" ou "user"

            IProduitDao dao = DAOFactory.getProduitDao();
            List<produits> produits = dao.FindAll();
            session.setAttribute("produits", produits);

            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("error", "Login ou mot de passe incorrect");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
    }
}
