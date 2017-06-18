package com.cn.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.web.dao.UserDao;
import com.cn.web.entity.User;

import sun.rmi.server.Dispatcher;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    String userName = request.getParameter("userName");
			String pwd = request.getParameter("pwd");
			String pwd2 = request.getParameter("pwd2");		
			User user = new User();
			user.setUserName(userName);
			user.setPwd(pwd);
			user.setPwd2(pwd2);		
			UserDao userDao = new UserDao();
			ResultSet ret = userDao.select(userName);
			try {
				if(ret.next()){
					System.out.println("此用户名已被注册，请重新输入！");
					request.getRequestDispatcher("/MessRegester.jsp").forward(request, response);
				}
				else{
					if(pwd.equals(pwd2)){						
						int count = userDao.addUser(user);
						if(count != 0){
							request.getRequestDispatcher("/Login.jsp").forward(request, response);
						}
					}else{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/Register.jsp");
					dispatcher.forward(request, response);				
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
}
