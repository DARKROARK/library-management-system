public abstract class User {
    private int id;
    private String name;
    private String email;
    private String phone;

    public User(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setName(String name) {
        if (!name.trim().isEmpty()) this.name = name;
    }

    public void setEmail(String email) {
        if (email.contains("@")) this.email = email;
    }

    public void setPhone(String phone) {
        if (phone.matches("\\d+") && phone.length() >= 10) this.phone = phone;
    }

    public abstract String getRole();

    public void displayInfo() {
        System.out.println("ID: " + id + " | Name: " + name + " | Email: " + email + " | Phone: " + phone + " | Role: " + getRole());
    }
}