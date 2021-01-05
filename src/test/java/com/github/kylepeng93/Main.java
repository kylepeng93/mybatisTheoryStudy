package com.github.kylepeng93;

import com.github.kylepeng93.mapper.BlogMapper;
import com.github.kylepeng93.pojo.Blog;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    private static SqlSessionFactory sqlSessionFactory;
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    @Before
    public void setup () {
        try(InputStream in = Resources.getResourceAsStream("mybatis-config.xml")) {
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            sqlSessionFactory  = builder.build(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Test
    public void insertBlog() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            String insertTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS, Locale.CHINA));
            Blog blog = new Blog();
            blog.setAuthor("彭凯");
            blog.setInsertTime(insertTime);
            blog.setTitle("mybatis学习笔记");
            mapper.insertBlog(blog);
            sqlSession.commit();
            System.out.println(blog);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void selectBlogById() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
            Blog blog = mapper.selectBlog(null);
            System.out.println(blog);
        }
    }
}
