package Controlleur;

import DAO.DAOFactory;
import DAO.IProduitDao;
import DAO.ProduitDaoImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        IProduitDao dao = DAOFactory.getProduitDao();

        dao.deleteProduit(id);

        HttpSession session = request.getSession();

        session.setAttribute("message", "Produit supprimé");

        session.setAttribute("produits", dao.FindAll());

        response.sendRedirect("home.jsp");
    }
}