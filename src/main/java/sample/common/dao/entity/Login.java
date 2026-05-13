package sample.common.dao.entity;

public class Login {

    private Integer id;
    private String username;
    private String password;

    // getter
    public Integer getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // setter
    public void setId(Integer id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}