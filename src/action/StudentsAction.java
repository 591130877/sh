package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import service.StudentsDAO;
import service.impl.StudentsDAOImpl;
import entity.Students;

//学生action类
public class StudentsAction extends SuperAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 查询所有学生的动作
	public String query() {
		StudentsDAO sdao = new StudentsDAOImpl();
		List<Students> list = sdao.queryAllStudents();
		// 将查询到的学生放入session中
		if (list != null && list.size() > 0) {
			session.setAttribute("students_list", list);
		}
		return "query_success";
	}

	// 删除学生动作
	public String delete() {
		StudentsDAO sdao = new StudentsDAOImpl();
		String sid = request.getParameter("sid");
		sdao.deleteStudent(sid);
		return "delete_success";
	}

	// 增加学生
	public String add() throws Exception {
		StudentsDAO sdao = new StudentsDAOImpl();
		Students s = new Students();
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(df.parse(request.getParameter("birthday")));
		s.setAddress(request.getParameter("address"));
		sdao.addStudent(s);
		return "add_success";
	}

	// 修改学生资料
	public String modify() {
		// 获取学生编号
		String sid = request.getParameter("sid");
		StudentsDAO sdao = new StudentsDAOImpl();
		Students s = sdao.queryStudentBySid(sid);
		// 将学生保存在会话中
		session.setAttribute("modify_students", s);
		return "modify_success";
	}

	// 保存修改后的学生资料
	public String save() throws ParseException {
		StudentsDAO sdao = new StudentsDAOImpl();
		Students s = new Students();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(df.parse(request.getParameter("birthday")));
		s.setAddress(request.getParameter("address"));
		sdao.updateStudent(s);
		return "save_success";
	}

	// 查询学生
	public String search() {
		String sid = request.getParameter("sid");
		StudentsDAO sdao = new StudentsDAOImpl();
		Students s = sdao.queryStudentBySid(sid);
		if (s != null) {
			// 将学生保存在会话中
			session.setAttribute("search_students", s);
			return "search_success";
		} else {
			session.setAttribute("sid", sid);
			return "search_failure";
		}
	}
}
