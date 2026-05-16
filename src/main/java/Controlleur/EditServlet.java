package Controlleur;

import DAO.DAOFactory;

import DAO.IProduitDao;
import DAO.ProduitDaoImpl;
import Model.produits;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
@WebServlet("/edit") 
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect("home.jsp");
            return;
        }
        Long id = Long.parseLong(idParam);

        //ProduitDaoImpl dao = new ProduitDaoImpl();
        IProduitDao dao = DAOFactory.getProduitDao();
        produits p = dao.getProduit(id);

        request.setAttribute("produit", p);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String nom = request.getParameter("nomProduit");
        String prixParam = request.getParameter("prix");

        if (idParam == null || prixParam == null || nom == null) {
            response.sendRedirect("home.jsp");
            return;
        }

        Long id = Long.parseLong(idParam);
        double prix = Double.parseDouble(prixParam);
        ProduitDaoImpl dao = new ProduitDaoImpl();
        produits p = dao.getProduit(id);

        p.setNomProduit(nom);
        p.setPrix(prix);

        dao.updateProduit(p);

        HttpSession session = request.getSession();
        session.setAttribute("produits", dao.FindAll());
        session.setAttribute("message", "Produit modifier ");
        // pas d'appel de la couche  DAO dans jsp je doit passer par un controller 
        response.sendRedirect("home.jsp");
        //framework hibernat avec jdbc pour accéder a la base de donnés 
    }
}