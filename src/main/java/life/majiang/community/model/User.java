package life.majiang.community.model;

public class User {
    private int id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_creat;
    private Long gmt_modified;
    private String description;
    private String avatar_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmt_creat() {
        return gmt_creat;
    }

    public void setGmt_creat(Long gmt_creat) {
        this.gmt_creat = gmt_creat;
    }

    public Long getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Long gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avator_url) {
        this.avatar_url = avator_url;
    }
}
