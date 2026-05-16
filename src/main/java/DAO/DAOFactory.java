package DAO;
import DAO.ProduitDAOImpH;
public class DAOFactory {
   public static IProduitDao getProduitDao() {

// JDBC
//return new ProduitDaoImpl();

// Hibernate
return new ProduitDAOImpH();
}
}