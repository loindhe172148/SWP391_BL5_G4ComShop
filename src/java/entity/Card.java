
package entity;

public class Card {
    private int id; // key not null
    private String name; // not null
    private String brand; // not null
    private int memory; // not null
    private String chipset; // not null
    private String description; // allow nulls
    

    public Card() {
    }

    public Card(int id, String name, String brand, int memory, String chipset, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.memory = memory;
        this.chipset = chipset;
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

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}

