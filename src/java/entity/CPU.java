/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author xuant
 */
public class CPU {
    private int id;
    private String name;
    private String tech;
    private String type;
    private float speed;
    private int core;
    private int flow;

    // Constructor không tham số
    public CPU() {
    }

    // Constructor có tham số
    public CPU(int id, String name, String tech, String type, float speed, int core, int flow) {
        this.id = id;
        this.name = name;
        this.tech = tech;
        this.type = type;
        this.speed = speed;
        this.core = core;
        this.flow = flow;
    }

    // Getter và Setter cho id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter và Setter cho name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter và Setter cho tech
    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    // Getter và Setter cho type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter và Setter cho speed
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    // Getter và Setter cho core
    public int getCore() {
        return core;
    }

    public void setCore(int core) {
        this.core = core;
    }

    // Getter và Setter cho flow
    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    // Override phương thức toString() nếu cần thiết
    @Override
    public String toString() {
        return "CPU [id=" + id + ", name=" + name + ", tech=" + tech + ", type=" + type + ", speed=" + speed
                + ", core=" + core + ", flow=" + flow + "]";
    }
}

