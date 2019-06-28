package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthoriziController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientid;

    @Value("${github.client.secret}")
    private String clientsecret;

    @Value("${github.redirect.uri}")
    private String redirecturi;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientid);
        accessTokenDTO.setClient_secret(clientsecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirecturi);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
//        System.out.println(accessToken);
        GithubUser githubUser = githubProvider.getuser(accessToken);
//        System.out.println(user);
        if (githubUser!=null && githubUser.getId()!=null){
            User user = new User();
            user.setAccount_id(githubUser.getId());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmt_creat(System.currentTimeMillis());
            user.setName(githubUser.getName());
            user.setGmt_modified(user.getGmt_creat());
            user.setDiscreption(githubUser.getBio());
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
//            写cookies和session返回登录成功状态
            return "redirect:/";
        }else{
//            返回登录失败
            return "redirect:/";

        }
    }
}
