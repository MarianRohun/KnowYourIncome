package KYI.Entits;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private int workedHours;
    private String password;
    private int position;
    private int loginStatus;

    public User(){
    }

    public User(int id, String name, String surname, String email, int workedHours, String password, int position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.workedHours = workedHours;
        this.password = password;
        this.position = position;
    }

    public User(String name, String surname, String email, int workedHours, int position) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.workedHours = workedHours;
        this.position = position;
    }


    public int getId() { return id; }

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

    public int getloginStatus() {
        return loginStatus;
    }

    public void setloginStatus(int loginStatus) { this.loginStatus = loginStatus; }

    public int getposition() {
        return position;
    }

    public void setPossition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }
}








