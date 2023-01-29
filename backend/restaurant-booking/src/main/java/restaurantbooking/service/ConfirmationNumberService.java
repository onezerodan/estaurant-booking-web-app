package restaurantbooking.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationNumberService {
    public String generate() {
        return RandomStringUtils.randomAlphanumeric(8);
    }
}
