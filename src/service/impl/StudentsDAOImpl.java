package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import service.StudentsDAO;
import db.MyHibernateSessionFactory;
import entity.Students;

public class StudentsDAOImpl implements StudentsDAO {

	@Override
	public List<Students> queryAllStudents() {
		Transaction ts = null;
		List<Students> list = null;
		String hql = "";
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory()
					.getCurrentSession();
			ts = session.beginTransaction();
			hql = "from Students";
			Query query = session.createQuery(hql);
			list = query.list();
			ts.commit();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.commit();
			return list;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	@Override
	public Students queryStudentBySid(String sid) {
		Transaction ts = null;
		Students s = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory()
					.getCurrentSession();
			ts = session.beginTransaction();
			s = (Students) session.get(Students.class, sid);
			ts.commit();
			return s;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.commit();
			return s;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	@Override
	public boolean addStudent(Students s) {
		s.setSid(getNewSid());
		Transaction ts = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory()
					.getCurrentSession();
			ts = session.beginTransaction();
			session.save(s);
			ts.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.commit();
			return false;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	@Override
	public boolean updateStudent(Students s) {
		Transaction ts = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory()
					.getCurrentSession();
			ts = session.beginTransaction();
			session.update(s);;
			ts.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.commit();
			return false;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	@Override
	public boolean deleteStudent(String sid) {
		Transaction ts = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory()
					.getCurrentSession();
			ts = session.beginTransaction();
			Students s = (Students) session.get(Students.class, sid);
			session.delete(s);
			ts.commit();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.commit();
			return false;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}

	// 生成学生学号
	private String getNewSid() {
		Transaction ts = null;
		String hql = "";
		String sid = null;
		try {
			Session session = MyHibernateSessionFactory.getSessionFactory()
					.getCurrentSession();
			ts = session.beginTransaction();
			// 获取当前学生的最大编号
			hql = "select max(sid) from Students";
			Query query = session.createQuery(hql);
			sid = (String) query.uniqueResult();
			if (sid == null || "".equals(sid)) {
				sid = "S0000001";
			} else {
				String temp = sid.substring(1);// 去掉首字母
				int i = Integer.parseInt(temp);
				i++;
				temp = String.valueOf(i);
				int len = temp.length();
				// 凑够七位
				for (int l = 0; l < 7 - len; l++) {
					temp = "0" + temp;
				}
				sid = "S" + temp;
			}
			ts.commit();
			return sid;
		} catch (Exception ex) {
			ex.printStackTrace();
			ts.commit();
			return null;
		} finally {
			if (ts != null) {
				ts = null;
			}
		}
	}
}
