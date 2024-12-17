package com.medium.music.model;

public class Contact {
// Mỗi phương thức liên lạc được gán một giá trị số nguyên khác nhau
    public static final int FACEBOOK = 0;
    public static final int HOTLINE = 1;
    public static final int GMAIL = 2;
    public static final int SKYPE = 3;
    public static final int YOUTUBE = 4;
    public static final int ZALO = 5;

    private int id;
    private int image;

//Khởi tạo một đối tượng Contact với hai tham số id, image
    public Contact(int id, int image) {
        this.id = id;
        this.image = image;
    }
//Trả về giá trị id của đối tượng
    public int getId() {
        return id;
    }
//Thiết lập giá trị mới cho id
    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
