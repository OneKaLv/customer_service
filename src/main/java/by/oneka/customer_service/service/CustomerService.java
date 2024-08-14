package by.oneka.customer_service.service;

import by.oneka.customer_service.dto.CustomerDTO;
import by.oneka.customer_service.entity.Customer;
import by.oneka.customer_service.mapper.CustomerMapper;
import by.oneka.customer_service.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final CustomerMapper customerMapper;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toCustomer(customerDTO);
        return customerMapper.toDTO(customerRepository.save(customer));
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toDTO).collect(Collectors.toList());
    }


    public CustomerDTO getCustomer(UUID id) {
        return customerRepository.findById(id)
                .map(customerMapper::toDTO)
                .orElse(null);
    }

    public CustomerDTO updateCustomer(UUID id, CustomerDTO customerDTO) {
        if (!customerRepository.existsById(id))
            return null;
        Customer customer = customerMapper.toCustomer(customerDTO);
        customer.setId(id);
        return customerMapper.toDTO(customerRepository.save(customer));
    }

    public void deleteCustomer(UUID id){
        customerRepository.deleteById(id);
    }
}
