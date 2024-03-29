package life.majiang.community.service;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QusetionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QusetionMapper qusetionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = qusetionMapper.list();
        List<QuestionDTO> list = new ArrayList<>();
        for (Question question : questions ){
           User user = userMapper.findById(question.getCreator());
           QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            list.add(questionDTO);
        }
        return list;
    }
}
