package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    public void createUser(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            logger.info("Пользователь создан: " + user);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.log(Level.SEVERE, "Ошибка при создании пользователя", e);
        }
    }

    public User getUser(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            if (user != null) {
                logger.info("Пользователь найден: " + user);
            } else {
                logger.warning("Пользователь с ID " + id + " не найден.");
            }
            return user;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ошибка при получении пользователя с ID " + id, e);
            return null;
        }
    }

    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<User> users = session.createQuery("from User", User.class).list();
            logger.info("Получено пользователей: " + users.size());
            return users;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Ошибка при получении списка пользователей", e);
            return null;
        }
    }

    public void updateUser(User user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            logger.info("Пользователь обновлён: " + user);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.log(Level.SEVERE, "Ошибка при обновлении пользователя: " + user, e);
        }
    }

    public void deleteUser(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                logger.info("Пользователь удалён: " + user);
            } else {
                logger.warning("Не удалось удалить. Пользователь с ID " + id + " не найден.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.log(Level.SEVERE, "Ошибка при удалении пользователя с ID " + id, e);
        }
    }
}