package life.majiang.community.provider;


import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    @Value("${github.client.accesstoken}")
    private String accessurl;

//    通过accessATO得到返回的accesstoken
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url(accessurl)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String [] split = string.split("&");
            String [] split2 = split[0].split("=");
            return split2[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

// 通过accesstoken 得到user信息
    public GithubUser getuser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
//            将放回的报文转换为githubuser格式json数据
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            System.out.println(githubUser.toString());
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
