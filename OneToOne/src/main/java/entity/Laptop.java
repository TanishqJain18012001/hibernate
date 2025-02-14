package entity;

import jakarta.persistence.*;


@Entity
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private Long laptopId;

    @Column(name = "brand")
    private String brand;

    @OneToOne(mappedBy = "laptop", cascade = CascadeType.ALL)
    private MacAddress macAddress;

    // Constructors, setters, and getters
    public Laptop() {
    }

    public Laptop(String brand) {
        this.brand = brand;
    }

    public Long getLaptopId() {
        return laptopId;
    }
    
    public void setLaptopId(Long laptopId) {
        this.laptopId = laptopId;
    }

   

	public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public MacAddress getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(MacAddress macAddress) {
        this.macAddress = macAddress;
    }
}