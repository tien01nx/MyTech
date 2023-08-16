package com.example.example_btl_androidnc.students.model;

public class ChangePass {
    private String oldpassword;
    private String newpassword;
    private String retypepassword;

    public ChangePass(String oldpassword, String newpassword, String retypepassword) {
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.retypepassword = retypepassword;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getRetypepassword() {
        return retypepassword;
    }

    public void setRetypepassword(String retypepassword) {
        this.retypepassword = retypepassword;
    }
}
