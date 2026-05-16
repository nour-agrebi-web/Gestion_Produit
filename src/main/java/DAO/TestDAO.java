package DAO;
import java.util.List;
import Model.produits;
public class TestDAO {
	
	public static void main(String[] args) {
	/*ProduitDaoImpl pdao= new ProduitDaoImpl();
	produits prod= pdao.save(new produits("iphone 8 plus",2800));
	//System.out.println(prod);
	produits p1= pdao.getProduit(prod.getIdProduit());
	List<produits> prods =pdao.produitsParMC("HP");
	for (produits p : prods)
	System.out.println(p.toString());*/

	ProduitDaoImpl pdao= new ProduitDaoImpl();
	List<produits> prods = pdao.FindAll();
	for(produits p:prods) {
		System.out.println(p);
	}

		}}
