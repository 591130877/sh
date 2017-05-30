package service;

import java.util.List;

import entity.Students;

//学生业务逻辑接口
public interface StudentsDAO {

	// 查询所有学生资料
	public List<Students> queryAllStudents();

	// 根据学生编号查询学生资料
	public Students queryStudentBySid(String sid);

	// 添加学生资料
	public boolean addStudent(Students s);

	// 修改学生资料
	public boolean updateStudent(Students s);

	// 删除学生资料
	public boolean deleteStudent(String sid);
}
