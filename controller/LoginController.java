package com.cn.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.web.dao.GoodsDao;
import com.cn.web.dao.UserDao;
import com.cn.web.entity.GoodsEntity;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		String userName = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		UserDao dao = new UserDao();
		ResultSet ret = dao.findUserByNameAndPwd(userName, pwd);		
			try {
				while (ret.next()) {
					GoodsDao  goodDao =new GoodsDao();
					List<GoodsEntity>goodList = goodDao.queryAll();
					request.setAttribute("goodList", goodList);
					request.getRequestDispatcher("/GoodsView.jsp").forward(request, response);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}		
		System.out.println(request.getParameter("username"));
		request.getRequestDispatcher("/Message.jsp").forward(request, response);
	}
}
