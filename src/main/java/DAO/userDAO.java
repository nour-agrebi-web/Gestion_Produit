package DAO;

import Model.User;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class userDAO {

    /**
     * Vérifie login/password et retourne l'objet User (ou null si invalide)
     */
    public static User authenticate(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            User user = session.createQuery(
                "FROM User u WHERE u.login = :l AND u.password = :p", User.class)
                .setParameter("l", login)
                .setParameter("p", password)
                .uniqueResult();
            return user;
        } finally {
            session.close();
        }
    }

    /**
     * Ancien verify (gardé pour compatibilité)
     */
    public static boolean verify(String login, String password) {
        return authenticate(login, password) != null;
    }

    /**
     * Inscription d'un nouvel utilisateur avec rôle "user"
     */
    public static boolean register(String login, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            User existing = session.get(User.class, login);
            if (existing != null) {
                return false;
            }
            tx = session.beginTransaction();
            User newUser = new User(login, password, "user");
            session.persist(newUser);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }
}
