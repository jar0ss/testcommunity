package life.majiang.community.controller;


import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Controller
public class AccountController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/account")
    public String account(){

        return "account";
    }

    @PostMapping("/account")
    public String login(@RequestParam("account")String account,
                        @RequestParam("password")String password,
                        HttpServletResponse response,
                        Model model) {
        String token = UUID.randomUUID().toString();
        User user = new User();
        user.setAccount(account);
        user.setGmt_creat(System.currentTimeMillis());
        user.setGmt_modified(user.getGmt_creat());
        user.setName(account);
        user.setToken(token);
        user.setPassword(password);
        User accountuser = userMapper.findByAccount(account);
        if (accountuser == null) {
            userMapper.insertUser(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        }else if (accountuser.getPassword().equals(password)) {
            System.out.println(2+accountuser.getPassword()+password);
            String accounttoken = accountuser.getToken();
            response.addCookie(new Cookie("token", accounttoken));
            return "redirect:/";
        } else
            model.addAttribute("erro", "该用户名或密码已被使用！");
            System.out.println(1+accountuser.getPassword()+password);
            System.out.println(accountuser.getPassword() == password);
            return "account";
    }

}
