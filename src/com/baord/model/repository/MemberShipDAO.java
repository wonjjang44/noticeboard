package com.baord.model.repository;

import org.apache.ibatis.session.SqlSession;

import com.baord.mybatis.config.MybatisManager;
import com.board.model.domain.MemberShip;

public class MemberShipDAO {
	MybatisManager manager=MybatisManager.getInstance();
	
	public int insert(MemberShip memberShip) {
		int result=0;
		SqlSession sqlSession=manager.getSqlSession();
		result=sqlSession.insert("MemberShip.insert",memberShip);
		sqlSession.commit();
		manager.release(sqlSession);
		return result;
	}
	
	public MemberShip select(MemberShip memberShip) {
		SqlSession sqlSession=manager.getSqlSession();
		MemberShip member=sqlSession.selectOne("MemberShip.select", memberShip);
		return member;
	}
}
