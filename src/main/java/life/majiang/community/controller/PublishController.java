package life.majiang.community.controller;

import life.majiang.community.mapper.QusetionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QusetionMapper qusetionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title")String title,
                            @RequestParam("description")String description,
                            @RequestParam("tag")String tags,
                            HttpServletRequest request,
                            Model model
                            ){
        User user=null ;
//        System.out.println("-------------------");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findToken(token);
                if (user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTags(tags);
        question.setTitle(title);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setGmt_creat(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_creat());
//        System.out.println("-------------------");

        qusetionMapper.creatQuestion(question);
        return "redirect:/";
    }
}
