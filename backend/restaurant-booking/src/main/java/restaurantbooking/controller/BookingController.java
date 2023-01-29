package restaurantbooking.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restaurantbooking.model.TableInfo;
import restaurantbooking.repository.TableRepository;
import restaurantbooking.service.ConfirmationNumberService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    TableRepository tableRepository;

    @Autowired
    ConfirmationNumberService confirmationNumberService;

    private final Logger log = LogManager.getLogger(BookingController.class);

    @PostMapping("/initialize")
    public void  init() {
        for (long i = 1; i <= 10; i++) {
            TableInfo tableInfo = new TableInfo(i, false);
            tableRepository.save(tableInfo);
        }

    }

    @Transactional
    @DeleteMapping("/delete")
    public void deleteAll() {
        tableRepository.deleteAll();

    }

    @PutMapping("/book")
    public TableInfo book(HttpServletRequest request) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining());

        ObjectMapper mapper = new JsonMapper();
        JsonNode json = mapper.readTree(body);

        Long id = json.get("id").asLong();


        TableInfo tableInfo = tableRepository.findById(id).orElse(null);
        if (tableInfo == null) return null; //return table not found exception
        if (tableInfo.isBooked()) {
            return null; //return table is already booked
        }
        tableInfo.setBooked(true);
        tableInfo.setConfirmationNumber(confirmationNumberService.generate());
        log.info("Table " + id + " booked.");
        return tableRepository.save(tableInfo);
    }

    @GetMapping("/getAvailable")
    public List<TableInfo> getAvailableTables() {
        return tableRepository.findByBooked(false);
    }

}
