package com.lick.test;

import com.lick.mapper.IUserMapper;
import com.lick.pojo.User;
import com.lick.sqlSession.SqlSession;
import com.lick.sqlSession.SqlSessionFactory;
import com.lick.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.util.List;

/**
 * @Auther: lick
 * @Description:
 * @Date:2020/12/7 21:34
 */
public class UserTest {
    @Test
    public void findAll() throws PropertyVetoException, DocumentException {
        IUserMapper mapper = getSqlSession().getMapper(IUserMapper.class);
        List<User> users = mapper.findAll();
        users.forEach(System.out::println);
    }
    @Test
    public void findOne() throws PropertyVetoException, DocumentException {
        IUserMapper mapper = getSqlSession().getMapper(IUserMapper.class);
        User user = mapper.findOne(1);
        System.out.println(user);
    }
    @Test
    public void saveUser() throws PropertyVetoException, DocumentException {
        IUserMapper mapper = getSqlSession().getMapper(IUserMapper.class);
        User user = new User();
        user.setId(3);
        user.setUsername("lick11");
        user.setBirthday("1996-09-08");
        user.setPassword("123456");
        System.out.println(mapper.insertUser(user));
    }
    @Test
    public void updateUser() throws PropertyVetoException, DocumentException {
        IUserMapper mapper = getSqlSession().getMapper(IUserMapper.class);
        User user = new User();
        user.setId(1);
        user.setUsername("lick11");
        mapper.updateUser(user);
    }
    @Test
    public void deleteUser() throws PropertyVetoException, DocumentException {
        IUserMapper mapper = getSqlSession().getMapper(IUserMapper.class);
        User user = new User();
        user.setId(1);
        mapper.deleteUser(user);
    }
    @Test
    public void deleteUserById() throws PropertyVetoException, DocumentException {
        IUserMapper mapper = getSqlSession().getMapper(IUserMapper.class);
        mapper.deleteUserById(1);
    }
//    @Test
//    public void findAllByProxy(){
//        try (SqlSession sqlSession = getSqlSession(false)){
//            IUserMapper userDao = sqlSession.getMapper(IUserMapper.class);
//            List<User> users = userDao.findAll();
//            users.forEach(System.out::println);
//        }
//    }
//    @Test
//    public void findByProxyIf(){
//        try (SqlSession sqlSession = getSqlSession(false)){
//            IUserMapper userDao = sqlSession.getMapper(IUserMapper.class);
//            User user = new User();
//            user.setUsername("lick3");
//            user.setId(3);
//            List<User> users = userDao.findByCondition(user);
//            users.forEach(System.out::println);
//        }
//    }
//    @Test
//    public void findByForeach(){
//        try (SqlSession sqlSession = getSqlSession(false)){
//            IUserMapper userDao = sqlSession.getMapper(IUserMapper.class);
//            List<User> users = userDao.findByForeach(new int[]{1, 2});
//            users.forEach(System.out::println);
//        }
//    }
    private SqlSession getSqlSession() throws PropertyVetoException, DocumentException {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(this.getClass().getClassLoader().getResourceAsStream("sqlMapConfig.xml"));
        return sqlSessionFactory.openSession();//自动提交事务
    }
}
