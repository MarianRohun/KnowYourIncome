package KYI.Entits;

public class User {
    private Integer id;
    private String surname;
    private String email;
    private String password;
    private String position;

    public User(){

    }
    public User(Integer id, String surname, String email, String password, String position) {
        this.id = id;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.position = position;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getposition() {
        return position;
    }
    public void setPossition(String position) {
        this.position = position;
    }
}








