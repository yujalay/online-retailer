package com.cn.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cn.web.db.DBUtils;
import com.cn.web.entity.GoodsEntity;

public class GoodsDao {
	Connection conn = DBUtils.getConnection();	
	public List<GoodsEntity> queryAll(){
		List<GoodsEntity> goodsList = new ArrayList<>();
		String sql = "select good_name,good_code,good_stock,good_property,"
		+"good_price,good_market_price,good_issue from tb_good";		
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet ret = psmt.executeQuery();
			while (ret.next()) {
				GoodsEntity good  = new GoodsEntity();
				good.setGoodsName(ret.getString(1));
				good.setGoodsCode(ret.getString(2));
				good.setGoodsStock(ret.getInt(3));
				good.setGoodsProperty(ret.getString(4));
				good.setGoodsPrice(ret.getDouble(5));
				good.setGoodsMarketPrice(ret.getDouble(6));
				good.setGoodsIssue(ret.getDate(7));
				goodsList.add(good);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodsList;
	}
	public int addGoods(GoodsEntity goodEntity){
		int count = 0;
		String addSql = "insert into tb_good (good_name,good_code,"
				+"good_stock,good_property,good_price,good_market_price,"
				+"good_issue) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(addSql);
			psmt.setString(1, goodEntity.getGoodsName());
			psmt.setString(2, goodEntity.getGoodsCode());
			psmt.setInt(3, goodEntity.getGoodsStock());
			psmt.setString(4, goodEntity.getGoodsProperty());
			psmt.setDouble(5, goodEntity.getGoodsPrice());
			psmt.setDouble(6, goodEntity.getGoodsMarketPrice());
			psmt.setDate(7, goodEntity.getGoodsIssue());
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public int EditGoods(GoodsEntity goodEntity){
		int count = 0;
		String addSql = "update tb_good set good_name=?,good_code=?,"
				+"good_stock=?,good_property=?,good_price=?,good_market_price=?,"
				+"good_issue=? where good_name=?";
		try {
			PreparedStatement psmt = conn.prepareStatement(addSql);
			psmt.setString(1, goodEntity.getGoodsName());
			psmt.setString(2, goodEntity.getGoodsCode());
			psmt.setInt(3, goodEntity.getGoodsStock());
			psmt.setString(4, goodEntity.getGoodsProperty());
			psmt.setDouble(5, goodEntity.getGoodsPrice());
			psmt.setDouble(6, goodEntity.getGoodsMarketPrice());
			psmt.setDate(7, goodEntity.getGoodsIssue());
			psmt.setString(8, goodEntity.getGoodsName());
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public int del(String goodName){
		int ret=0;
		String sql = "delete  from tb_good where good_name= ?";
		;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			 psmt.setString(1, goodName);
			 ret = psmt.executeUpdate();
		
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public GoodsEntity findGoodByName(String goodName){
		ResultSet ret = null;
		String sql = "select good_name,good_code,good_stock,good_property,"
		+"good_price,good_market_price,good_issue from tb_good where good_name= ?";
		PreparedStatement psmt;
		GoodsEntity good  = null;
		try {
			 psmt = conn.prepareStatement(sql);
			 psmt.setString(1, goodName);
			 ret = psmt.executeQuery();
			while(ret.next()){
				good=new GoodsEntity();
				good.setGoodsName(ret.getString(1));
				good.setGoodsCode(ret.getString(2));
				good.setGoodsStock(ret.getInt(3));
				good.setGoodsProperty(ret.getString(4));
				good.setGoodsPrice(ret.getDouble(5));
				good.setGoodsMarketPrice(ret.getDouble(6));
				good.setGoodsIssue(ret.getDate(7));				
			}
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return good;
	}
	public List<GoodsEntity> QueryByNameOrCode(String name,String code){
		List<GoodsEntity> goodsList = new ArrayList<>();
		String querySql = "select good_name,good_code,good_stock,good_property,"
				+ "good_price,good_market_price,good_issue from tb_good where good_name like ? or good_code = ?";
		try{
			PreparedStatement psmt = conn.prepareStatement(querySql);
			psmt.setString(1, "%"+name+"%");
			psmt.setString(2, code);
		    ResultSet rs = psmt.executeQuery();
			while(rs.next()){
				GoodsEntity good = new GoodsEntity();
				good.setGoodsName(rs.getString(1));
				good.setGoodsCode(rs.getString(2));
				good.setGoodsStock(rs.getInt(3));
				good.setGoodsProperty(rs.getString(4));
				good.setGoodsPrice(rs.getDouble(5));
				good.setGoodsMarketPrice(rs.getDouble(6));
				good.setGoodsIssue(rs.getDate(7));
				goodsList.add(good);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return goodsList;
	}	
}
