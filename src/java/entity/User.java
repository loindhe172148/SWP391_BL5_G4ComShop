package entity;

import java.util.Date;

public class User {
    private int id;
    private String email;
    private Date dob; // Chỉnh sửa từ bod thành dob
    private String address;
    private Boolean gender; // Sử dụng Boolean thay vì boolean nếu cần xử lý giá trị null
    private String phone;
    private Boolean status; // Sử dụng Boolean thay vì boolean nếu cần xử lý giá trị null
    private Account account;
    private String ava;
    private String name;

    // Getters và Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email; // Chỉnh sửa từ gmail thành email
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob; // Chỉnh sửa từ bod thành dob
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

