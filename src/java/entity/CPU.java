
package entity;

public class CPU {
    private int id; //key not null
    private String name; // not null
    private String brand; // not null
    private String generation; // not null
    private String description; // alow null

    public CPU() {
    }

    public CPU(int id, String name, String brand, String generation, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.generation = generation;
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

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

