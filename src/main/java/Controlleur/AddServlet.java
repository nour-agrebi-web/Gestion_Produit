package Controlleur;

import DAO.*;
import Model.produits;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import DAO.IProduitDao;
import DAO.ProduitDAOImpH;
@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String P = request.getParameter("nom");
        String prix = request.getParameter("prix");

        System.out.println("Produit = " + P);
        System.out.println("Prix = " + prix);

        double pr = Double.parseDouble(prix);

        produits p = new produits();
        p.setNomProduit(P);
        p.setPrix(pr);

        //ProduitDaoImpl dao = new ProduitDaoImpl();
        IProduitDao dao = DAOFactory.getProduitDao();

        dao.save(p);

        List<produits> prods = dao.FindAll();

        HttpSession session = request.getSession();
        session.setAttribute("produits", prods);

        response.sendRedirect("home.jsp");
    }
}