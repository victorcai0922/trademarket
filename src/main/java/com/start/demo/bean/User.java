package com.start.demo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class User implements Serializable{

    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, unique = true)
    private String name;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(nullable = false, unique = true)
    private String nickName;

    @Override
    public String toString(){
        return "User{" +
                "id="+Id+",name="+name+",nickName="+nickName+"}";
    }

}
