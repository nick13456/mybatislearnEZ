package mybatis.learn.dao;

import mybatis.learn.domain.QueryVo;
import mybatis.learn.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface UserDao {

    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into user(username,birthday,sex,address)values(#{username},#{birthday},#{sex},#{address})")
    //values或者value都可以。传递一个user对象，#{}中为实体类的属性名
    void save(User user);

    @Update("update user set username=#{username},birthday=#{birthday},sex=#{sex},address=#{address} where id=#{id}")
    void updateUser(User user);

    @Delete("delete from user where id=#{userId}")  // #{}中的内容可以随便写，有个占位符就可以了
    void deleteById(Integer userId);

    @Select("select * from user where id=#{userId}")
    User findById(Integer userId);

    @Select("select * from user where username like #{username}")  //#{username} 可以替换成 '%${value}%'同时"%nick%"，需要改成"nick"
    List<User> findByName(String username);

    @Select("select count(id) from user")
    Integer findTotal();

    @Select("select * from user where username like #{user.username}")
    List<User> findByVo(QueryVo vo);

    @Select({"<script>",
                "select * from user",
                "<where>",
                    "<if test='username!=null'>",
                        "and username like #{username}",
                    "</if>",
                    "<if test='sex != null'>",
                        "and sex like #{sex}",
                    "</if>",
                "</where>",
            "</script>"})
    List<User> findByCondition(User user);

    @Select("<script> select * from user <where><if test='idList != null and idList.size > 0'> " +
            "<foreach collection='idList' open='and id in (' close=')' item='id' separator=','>" +
                "#{id}" +
            "</foreach></if></where></script>")
    List<User> findByIdList(QueryVo vo);

}
