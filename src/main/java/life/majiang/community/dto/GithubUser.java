package life.majiang.community.dto;

public class GithubUser {
    private String id;
    private String name;
    private String bio;
    private String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avator_url) {
        this.avatar_url = avator_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}