package DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.produits;
import Util.HibernateUtil;

public class ProduitDAOImpH implements IProduitDao {

    /*
     * Ajouter un produit
     */
    @Override
    public produits save(produits p) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        session.persist(p);

        tx.commit();

        session.close();

        return p;
    }

    /*
     * Recherche par mot clé
     */
    @Override
    public List<produits> produitsParMC(String mc) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        List<produits> produits =
                session.createQuery(
                        "FROM produits p WHERE p.nomProduit LIKE :x",
                        produits.class)

                .setParameter("x", "%" + mc + "%")
                .list();

        session.close();

        return produits;
    }

    /*
     * Afficher tous les produits
     */
    @Override
    public List<produits> FindAll() {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        List<produits> produits =
                session.createQuery(
                        "FROM produits",
                        produits.class)
                .list();

        session.close();

        return produits;
    }

    /*
     * Récupérer un produit par ID
     */
    @Override
    public produits getProduit(Long id) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        produits p = session.get(produits.class, id);

        session.close();

        return p;
    }

    /*
     * Modifier un produit
     */
    @Override
    public int updateProduit(produits p) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        session.merge(p);

        tx.commit();

        session.close();

        return 1;
    }

    /*
     * Supprimer un produit
     */
    @Override
    public int deleteProduit(Long id) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        produits p = session.get(produits.class, id);

        if (p != null) {
            session.remove(p);
        }

        tx.commit();

        session.close();

        return 1;
    }
}