package DAO;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.produits;

public class ProduitDaoImpl implements IProduitDao {
@Override
public produits save(produits p) {
Connection conn=DBConnection.getConnection();
try {
PreparedStatement ps= conn.prepareStatement("INSERT INTO PRODUITS(NOM_PRODUIT,PRIX) VALUES(?,?)");
ps.setString(1, p.getNomProduit());
ps.setDouble(2, p.getPrix());
ps.executeUpdate();
PreparedStatement ps2= conn.prepareStatement
("SELECT MAX(ID_PRODUIT) as MAX_ID FROM PRODUITS");

ResultSet rs =ps2.executeQuery();
if (rs.next()) {
p.setIdProduit(rs.getLong("MAX_ID"));
}
ps.close();
ps2.close();
} catch (SQLException e) {
e.printStackTrace();
}
return p;}
@Override
public List<produits> produitsParMC(String mc) {
List<produits> prods= new ArrayList<produits>();
Connection conn=DBConnection.getConnection();
try {
PreparedStatement ps= conn.prepareStatement("select * from PRODUITS where NOM_PRODUIT LIKE ?");
ps.setString(1, "%"+mc+"%");
ResultSet rs = ps.executeQuery();
while (rs.next()) {
produits p = new produits();
p.setIdProduit(rs.getLong("ID_PRODUIT"));
p.setNomProduit(rs.getString("NOM_PRODUIT"));
p.setPrix(rs.getDouble("PRIX"));
prods.add(p);
}
} 
catch (SQLException e) {
e.printStackTrace();
}
return prods;

}
@Override
public produits getProduit(Long id) {
    produits p = null;
    Connection conn = DBConnection.getConnection();
    try {
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM PRODUITS WHERE ID_PRODUIT = ?"
        );
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            p = new produits();
            p.setIdProduit(rs.getLong("ID_PRODUIT"));
            p.setNomProduit(rs.getString("NOM_PRODUIT"));
            p.setPrix(rs.getDouble("PRIX"));
        }
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return p;
}
@Override
public int updateProduit(produits p) {

    Connection conn = DBConnection.getConnection();

    int rowsAffected = 0;

    try {

        PreparedStatement ps = conn.prepareStatement(
            "UPDATE PRODUITS SET NOM_PRODUIT = ?, PRIX = ? WHERE ID_PRODUIT = ?"
        );

        ps.setString(1, p.getNomProduit());
        ps.setDouble(2, p.getPrix());
        ps.setLong(3, p.getIdProduit());

        rowsAffected = ps.executeUpdate();

        if (rowsAffected == 0) {
            System.out.println(
                "No product found with id: " + p.getIdProduit()
            );
        }

        ps.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return rowsAffected;
}
@Override
public int deleteProduit(Long id) {
    Connection conn = DBConnection.getConnection();
    
    try {
        PreparedStatement ps = conn.prepareStatement(
            "DELETE FROM PRODUITS WHERE ID_PRODUIT = ?"
        );
        ps.setLong(1, id);
        ps.executeUpdate();
        ps.close();
        return 1;
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    }
}
@Override
public List<produits> FindAll() {
    List<produits> prods = new ArrayList<>();
    Connection conn = DBConnection.getConnection();
    try {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM PRODUITS");
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            produits p = new produits();
            p.setIdProduit(rs.getLong("ID_PRODUIT"));
            p.setNomProduit(rs.getString("NOM_PRODUIT"));
            p.setPrix(rs.getDouble("PRIX"));
            prods.add(p);
        }
        
        rs.close();
        ps.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return prods;
}

}