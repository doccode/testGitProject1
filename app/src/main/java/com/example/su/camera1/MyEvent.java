package com.example.su.camera1;

/**
 * Created by su on 2016/7/9.
 */
public class MyEvent{
    MyEvent(int i){
        name  = "name is " + i;
        id  = "id is " + i;
    }

    @Override
    public String toString() {
        return name + ", " + id + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;


}
