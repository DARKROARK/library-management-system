public class Staff extends User {
    private String employeeId;
    private String department;
    private String position;

    public Staff(int id, String name, String email, String phone, String employeeId, String department, String position) {
        super(id, name, email, phone);
        this.employeeId = employeeId;
        this.department = department;
        this.position = position;
    }

    public String getEmployeeId() { return employeeId; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }

    public void setDepartment(String department) {
        if (!department.trim().isEmpty()) this.department = department;
    }

    public void setPosition(String position) {
        if (!position.trim().isEmpty()) this.position = position;
    }

    @Override
    public String getRole() { return "Staff"; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Employee ID: " + employeeId + " | Dept: " + department + " | Position: " + position);
    }
}