package entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "laptops")
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laptop_id")
    private Long laptopId;

    @Column(name = "brand")
    private String brand;

    @OneToMany(mappedBy = "laptop", cascade = CascadeType.ALL)
    private Set<IPAddress> ipAddresses = new HashSet<>();

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

    public Set<IPAddress> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(Set<IPAddress> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    public void addIPAddress(IPAddress ipAddress) {
        ipAddresses.add(ipAddress);
        ipAddress.setLaptop(this);
    }

    public void removeIPAddress(IPAddress ipAddress) {
        ipAddresses.remove(ipAddress);
        ipAddress.setLaptop(null);
    }
}
