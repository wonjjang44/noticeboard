package com.baord.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	private static MybatisManager instance;
	
	private MybatisManager() {
		
		String resource = "com/baord/mybatis/config/myboard-config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	public static MybatisManager getInstance() {
		return instance= new MybatisManager();
	}
	
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
	public void release(SqlSession sqlSession) {
		sqlSession.close();
	}

}
