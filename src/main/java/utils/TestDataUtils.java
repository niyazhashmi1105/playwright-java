package utils;

import com.creditdatamw.zerocell.annotation.Column;

public class TestDataUtils {

    @Column(name = "username", index = 0)
    private String username;

    @Column(name = "password", index = 1)
    private String password;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "TestDataUtils(username=" + this.getUsername() + ", password=" + this.getPassword() + ")";
    }
}
