
package entity;

public class ROM {
    private int id; //key not null
    private int capacity; // not null
    private String description; // allow nulls

    public ROM() {
    }

    public ROM(int id, int capacity, String description) {
        this.id = id;
        this.capacity = capacity;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
