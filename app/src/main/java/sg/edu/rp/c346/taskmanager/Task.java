package sg.edu.rp.c346.taskmanager;

import java.io.Serializable;

public class Task implements Serializable{
    private int id;
    private String name, desc;

    public Task(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Task(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
