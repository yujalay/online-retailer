package com.cn.web.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.web.dao.GoodsDao;
import com.cn.web.dao.UserDao;
import com.cn.web.entity.GoodsEntity;
import com.cn.web.tool.StringToDate;

/**
 * Servlet implementation class GoodsController
 */
@WebServlet("/GoodsController")
public class GoodsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GoodsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String btnName = request.getParameter("btnName");
		GoodsDao dao = new GoodsDao();
		if (btnName != null) {
			if ("edit".equals(btnName)) {
				String goodName = request.getParameter("goodName");
				GoodsEntity goodEntity = dao.findGoodByName(goodName);
				request.setAttribute("goodEntity", goodEntity);
				request.getRequestDispatcher("/GoodsEdit.jsp").forward(request,
						response);
			} 
			if ("del".equals(btnName)) {
				String goodName = request.getParameter("goodName");
				int res=dao.del(goodName);
				System.out.println(res);				
				List<GoodsEntity> goodList = dao.queryAll();
				request.setAttribute("goodList", goodList);
				request.getRequestDispatcher("/GoodsView.jsp").forward(
						request, response);
			
			} 
			else if("query".equals(btnName)){
				System.out.println("查询请求");
				String name = request.getParameter("goodName");
				String code = request.getParameter("goodCode");
				System.out.print(name);
				System.out.print(code);				
				GoodsDao goodsDao = new GoodsDao();
				List<GoodsEntity> goodList = goodsDao.QueryByNameOrCode(name,code);
				request.setAttribute("goodList", goodList);
				request.getRequestDispatcher("/GoodsView.jsp").forward(request, response);	
			}
		} 
		else {
			String goodName = request.getParameter("goodName");
			String goodCode = request.getParameter("goodCode");
			String goodStock = request.getParameter("goodStock");
			String goodProperty = request.getParameter("goodProperty");
			String goodPrice = request.getParameter("goodPrice");
			String goodMarketPrice = request.getParameter("goodMarketPrice");
			String goodIssue = request.getParameter("goodIssue");
			GoodsEntity goodsEntity = new GoodsEntity();
			goodsEntity.setGoodsName(goodName);
			goodsEntity.setGoodsCode(goodCode);
			goodsEntity.setGoodsStock(Integer.parseInt(goodStock));
			goodsEntity.setGoodsProperty(goodProperty);
			goodsEntity.setGoodsPrice(Double.parseDouble(goodPrice));
			goodsEntity.setGoodsMarketPrice(Double.parseDouble(goodMarketPrice));
			goodsEntity.setGoodsIssue(StringToDate.getString(goodIssue));
			// 添加商品
			if (dao.findGoodByName(goodName) == null) {
				if (dao.addGoods(goodsEntity) > 0) {
					System.out.println("goods insert success");
					List<GoodsEntity> goodList = dao.queryAll();
					request.setAttribute("goodList", goodList);
					request.getRequestDispatcher("/GoodsView.jsp").forward(
							request, response);
				}
			} 
			else {
				if (dao.EditGoods(goodsEntity) > 0) {

					System.out.println("goods insert success");
					List<GoodsEntity> goodList = dao.queryAll();
					request.setAttribute("goodList", goodList);
					request.getRequestDispatcher("/GoodsView.jsp").forward(
							request, response);
				}
			}
		}
	}
	private void alert(String string) {
		// TODO Auto-generated method stub		
	}
}
