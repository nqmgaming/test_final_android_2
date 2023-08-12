package com.example.myapplication.DTO;

public class TestDTO {
    private int id;
    private String date_test;
    private String test_shift;
    private String test_room;
    private String test_subject;

    public TestDTO() {
    }

    public TestDTO(int id, String date_test, String test_shift, String test_room, String test_subject) {
        this.id = id;
        this.date_test = date_test;
        this.test_shift = test_shift;
        this.test_room = test_room;
        this.test_subject = test_subject;
    }

    public TestDTO(String date_test, String test_shift, String test_room, String test_subject) {
        this.date_test = date_test;
        this.test_shift = test_shift;
        this.test_room = test_room;
        this.test_subject = test_subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_test() {
        return date_test;
    }

    public void setDate_test(String date_test) {
        this.date_test = date_test;
    }

    public String getTest_shift() {
        return test_shift;
    }

    public void setTest_shift(String test_shift) {
        this.test_shift = test_shift;
    }

    public String getTest_room() {
        return test_room;
    }

    public void setTest_room(String test_room) {
        this.test_room = test_room;
    }

    public String getTest_subject() {
        return test_subject;
    }

    public void setTest_subject(String test_subject) {
        this.test_subject = test_subject;
    }
}
