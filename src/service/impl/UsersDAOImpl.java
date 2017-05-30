package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.UsersDAO;
import db.MyHibernateSessionFactory;
import entity.Users;

public class UsersDAOImpl implements UsersDAO {

	public boolean usersLogin(Users u) {
		// 创建事务对象
		Transaction ts = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory()
					.getCurrentSession();
			ts = session.beginTransaction();
			hql = "from Users where username =? and password =?";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getUsername());
			query.setParameter(1, u.getPassword());
			List list = query.list();
			ts.commit();
			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

}
