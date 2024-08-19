
package entity;

public class RAM {


    private int id;  //key not null
    private String name; //not null
    private String brand; //not null
    private int memory;  //not null
    private int speed; //not null
    private String description; // alow nulls


    public RAM() {
    }

    public RAM(int id, String name, String brand, int memory, int speed, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.memory = memory;
        this.speed = speed;
        this.description = description;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
