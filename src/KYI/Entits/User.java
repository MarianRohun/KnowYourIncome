package KYI.Entits;

public class User {
    private int id;
    private String surname;
    private String email;
    private String password;
    private int position;

    public User(){

    }
    public User(int id, String surname, String email, String password, int position) {
        this.id = id;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.position = position;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getposition() {
        return position;
    }

    public void setPossition(int position) {
        this.position = position;
    }
}








