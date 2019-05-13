package com.baord.model.repository;



import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.baord.mybatis.config.MybatisManager;
import com.board.model.domain.MyBoard;

public class MyBoardDAO {
	MybatisManager manager = MybatisManager.getInstance();
	
	public List<MyBoard> selectAll() {
		SqlSession sqlSession = manager.getSqlSession();
		List list = sqlSession.selectList("MyBoard.selectAll");
		return list;
	}
	
	public int insert(MyBoard myBoard) {
		int result=0;
		SqlSession sqlSession=manager.getSqlSession();
		result=sqlSession.insert("MyBoard.insert",myBoard);
		sqlSession.commit();
		manager.release(sqlSession);
		return result;
	}
	
	public MyBoard select(int myboard_id) {
		SqlSession sqlSession = manager.getSqlSession();
		MyBoard myBoard =sqlSession.selectOne("MyBoard.select",myboard_id);
		manager.release(sqlSession);
		return myBoard;
	}
	
	public int update(MyBoard myBoard) {
		int result=0;
		SqlSession sqlSession=manager.getSqlSession();
		result=sqlSession.update("MyBoard.update",myBoard);
		sqlSession.commit();
		manager.release(sqlSession);
		
		return result;
	}
	
	public int delete(int myboard_id) {
		int result=0;
		SqlSession sqlSession=manager.getSqlSession();
		result=sqlSession.delete("MyBoard.delete",myboard_id);
		sqlSession.commit();
		manager.release(sqlSession);
		
		return result;
	}
	
	
}
