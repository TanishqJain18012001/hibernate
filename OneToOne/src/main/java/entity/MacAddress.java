package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mac_addresses")
public class MacAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mac_id")
    private Long macId;

    @Column(name = "address")
    private String address;

	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

    // Constructors, setters, and getters
    public MacAddress() {
    }

    public MacAddress(String address) {
        this.address = address;
    }

    public Long getMacId() {
        return macId;
    }

    public void setMacId(Long macId) {
        this.macId = macId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

}