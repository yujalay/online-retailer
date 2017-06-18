package com.cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.web.db.DBUtils;
import com.cn.web.entity.GoodsEntity;
import com.cn.web.entity.User;

public class UserDao {
	Connection conn= DBUtils.getConnection();	
	public int addUser(User user){
		int count = 0;
		String insertSql = "insert into tb_user(user_name,pwd,pwd_confirm) values(?,?,?)";		
		try {
			PreparedStatement psmt = conn.prepareStatement(insertSql);
			psmt.setString(1, user.getUserName());
			psmt.setString(2, user.getPwd());
			psmt.setString(3, user.getPwd2());
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return count;
	}
	
	public ResultSet select(String userName){
		ResultSet ret = null;
		String selSQL = "select * from tb_user where user_name=?";
		
		try {
			//通过连接创建对象
			PreparedStatement psmt = conn.prepareStatement(selSQL);
			//通过对象传入参数
			psmt.setString(1, userName);
			ret = psmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ret;
	}	
	public ResultSet findUserByNameAndPwd(String userName,String pwd){
		ResultSet ret = null;
		String sql = "select * from tb_user where user_name=? and pwd=?";
		PreparedStatement psmt;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userName);
			psmt.setString(2, pwd);
			ret = psmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return ret;
	}
	
	

}
