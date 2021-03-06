package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import service.UsersDAO;
import service.impl.UsersDAOImpl;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

public class UsersAction extends SuperAction implements ModelDriven<Users> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Users user = new Users();

	// 用户登录方法
	public String login() {
		UsersDAO udao = new UsersDAOImpl();
		if (udao.usersLogin(user)) {
			//用session记录登录成功用户名
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		} else {
			return "login_failure";
		}
	}

	@SkipValidation
	//用户注销方法
	public String logout() {
		if(session.getAttribute("loginUserName") != null){
			session.removeAttribute("loginUserName");
		}
		return "logout_success";
	}
	
	@Override
	public void validate() {
		//用户名不能为空
		if("".equals(user.getUsername().trim())){
			this.addFieldError("usernameError", "用户名不能为空");
		}
		if(user.getPassword().length() < 6){
			this.addFieldError("passwordError", "密码长度不少于6位");
		}
	}

	@Override
	public Users getModel() {
		return user;
	}

}
