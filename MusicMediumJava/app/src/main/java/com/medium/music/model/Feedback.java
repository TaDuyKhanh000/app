package com.medium.music.model;

public class Feedback {

    private String name;
    private String phone;
    private String email;
    private String comment;


    public Feedback() {
    }

// khởi tạo một đối tượng Feedback với thông tin đầy đủ (tên, SĐT, email, comment
// các giá trị sẽ được gán ngay lập tức cho các thuộc tính tương ứng
    public Feedback(String name, String phone, String email, String comment) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.comment = comment;
    }
// Trả về giá trị của thuộc tính
    public String getName() {
        return name;
    }
// Thiết lập giá trị cho thuộc tính
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
