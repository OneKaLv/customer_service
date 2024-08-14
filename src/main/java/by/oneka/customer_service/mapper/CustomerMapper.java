package by.oneka.customer_service.mapper;

import by.oneka.customer_service.dto.CustomerDTO;
import by.oneka.customer_service.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO toDTO(Customer entity) {
        return new CustomerDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword()
        );
    }

    public Customer toCustomer(CustomerDTO dto) {
        return new Customer(
                dto.id(),
                dto.name(),
                dto.email(),
                dto.password()
        );
    }
}
