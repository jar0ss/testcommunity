package life.majiang.community.mapper;


import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QusetionMapper {

    @Insert("insert into question (title,description,gmt_creat,gmt_modified,creator,comment_count,view_count,like_count,tags) values (#{title},#{description},#{gmt_creat},#{gmt_modified},#{creator},#{comment_count},#{view_count},#{like_count},#{tags}) ")
    public void creatQuestion(Question question);

    @Select("select * from question")
    List<Question> list();
}
