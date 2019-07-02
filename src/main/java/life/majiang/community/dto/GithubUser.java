package life.majiang.community.dto;

public class GithubUser {
    private Long id;
    private String name;
    private String bio;
    private String avatar_url;

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avator_url) {
        this.avatar_url = avator_url;
    }

    public Long getAccount_id() {
        return id;
    }

    public void setAccount_id(Long account_id) {
        this.id = account_id;
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
