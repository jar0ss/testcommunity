package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_creat,gmt_modified,description,avatar_url,account) values (#{account_id},#{name},#{token},#{gmt_creat},#{gmt_modified},#{description},#{avatar_url},#{account})")
    void insert(User user);

    @Insert("insert into user (account,name,token,password,gmt_creat,gmt_modified) values (#{account},#{name},#{token},#{password},#{gmt_creat},#{gmt_modified})")
    void insertUser(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") int id);


    @Select("select * from user where account_id = #{account_id}")
    User findByAccount_id(@Param("account_id") String account_id);

    @Select("select * from user where account = #{account}")
    User findByAccount(@Param("account") String account);
}
