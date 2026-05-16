package Controlleur;

import DAO.userDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");

        if (login == null || login.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirm)) {
            request.setAttribute("error", "Les mots de passe ne correspondent pas.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        boolean success = userDAO.register(login.trim(), password.trim());

        if (success) {
            request.setAttribute("success", "Compte créé avec succès ! Vous pouvez vous connecter.");
            request.getRequestDispatcher("/Hello.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Ce login est déjà utilisé. Choisissez-en un autre.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
