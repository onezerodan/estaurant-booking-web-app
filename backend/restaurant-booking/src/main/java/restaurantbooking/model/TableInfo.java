package restaurantbooking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tables")
public class TableInfo {
    @Id
    private Long id;
    private boolean booked;

    private String confirmationNumber;

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public TableInfo(long id, boolean booked) {
        this.id = id;
        this.booked = booked;
    }

    public Long getId() {
        return id;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public TableInfo() {
    }
}
