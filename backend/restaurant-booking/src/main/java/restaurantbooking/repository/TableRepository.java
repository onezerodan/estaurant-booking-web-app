package restaurantbooking.repository;

import org.springframework.data.repository.CrudRepository;
import restaurantbooking.model.TableInfo;

import java.util.List;

public interface TableRepository extends CrudRepository<TableInfo, Long> {
    List<TableInfo> findByBooked(boolean booked);
}
