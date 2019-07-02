
import mybatis.learn.dao.UserDao;
import mybatis.learn.domain.QueryVo;
import mybatis.learn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {
    private InputStream stream;
    private UserDao userDao;
    private SqlSession sqlSession;

    @Before
    public void init() throws IOException {
        stream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //创建sessionfactory工厂
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = factoryBuilder.build(stream);

        //使用工厂创建session
        sqlSession = factory.openSession();

        //使用sqlsession产生代理对象
        userDao = sqlSession.getMapper(UserDao.class);

    }

    @After
    public void release() throws IOException {
        sqlSession.commit();
        stream.close();
        sqlSession.close();
    }


    @Test
    public void findAll() {

        //使用代理对象调用方法
        List<User> list = userDao.findAll();

        for(User user:list){
            System.out.println(user);
        }


    }


    @Test
    public void saveUser(){

        Date date = new Date();
        User user = new User("李信",date,"男","台湾");

        userDao.save(user);

        List<User> list = userDao.findAll();

        for(User user1:list){
            System.out.println(user1);
        }


    }

    @Test
    public void updateUser(){

        Date date = new Date();
        User user = new User("凌柳",date,"男","上海");
        user.setId(41);
        userDao.updateUser(user);


    }

    @Test
    public void deleteById(){
        userDao.deleteById(41);
    }

    @Test
    public void findById(){
       User user = userDao.findById(48);
        System.out.println(user);
    }

    @Test
    public void findByName(){
        List<User> list = userDao.findByName("%nick%");
        System.out.println(list.get(0)+"\n"+list.get(1));
    }

    @Test
    public void findTotal(){
        Integer total = userDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void findByVo(){
        QueryVo vo = new QueryVo(new User("%王%",new Date(),"男","宁波"));
        List<User> list = userDao.findByVo(vo);

        for(User user:list){
            System.out.println(user);
        }

    }
    @Test
    public void findByCondition(){
        User user = new User();
        user.setName("%王%");
        user.setSex("女");
        List<User> list = userDao.findByCondition(user);

        for(User user1:list){
            System.out.println(user1);
        }

    }
    @Test
    public void findByIdList(){
        QueryVo vo = new QueryVo();
        List<Integer> idlist=new ArrayList<Integer>();
        idlist.add(42);
        idlist.add(43);
        idlist.add(52);
        idlist.add(53);
        vo.setIdList(idlist);
        List<User> list = userDao.findByIdList(vo);

        for(User user:list){
            System.out.println(user);
        }

    }
}
