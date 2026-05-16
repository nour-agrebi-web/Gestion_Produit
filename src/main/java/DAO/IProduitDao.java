package DAO;
import Model.produits;
import java.util.List;
public interface IProduitDao {
public produits save(produits p);
public List<produits> produitsParMC(String mc);
public produits getProduit(Long id);
//public produits updateProduit(produits p);
public int updateProduit(produits p);
public int deleteProduit(Long id);
List<produits> FindAll();
}