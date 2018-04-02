package in.epaylater.testApp.entity;

public class TokenData {

    private final Long id;

    private final String phone;

    private final String token;

    public TokenData(Long id, String phone, String token) {
        this.id = id;
        this.phone = phone;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "TokenData{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
