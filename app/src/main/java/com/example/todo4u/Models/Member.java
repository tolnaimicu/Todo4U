package com.example.todo4u.Models;

public class Member {
    public String memberId;
    public String nickName;

    public Member(){

    }

    public Member(String memberId, String nickName)
    {
        this.memberId = memberId;
        this.nickName = nickName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
