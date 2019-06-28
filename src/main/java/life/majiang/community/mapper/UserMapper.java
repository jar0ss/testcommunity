package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_creat,gmt_modified,discreption) values (#{account_id},#{name},#{token},#{gmt_creat},#{gmt_modified},#{discreption})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findToken(@Param("token") String token);
}
