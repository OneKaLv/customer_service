package by.oneka.customer_service.controller;

import by.oneka.customer_service.dto.CustomerDTO;
import by.oneka.customer_service.service.CustomerService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;


    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Пользователь создан")
    })
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        customerDTO = customerService.createCustomer(customerDTO);
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("/all")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список всех пользователей")
    })
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable UUID id) {
        CustomerDTO customerDTO = customerService.getCustomer(id);
        return customerDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(customerDTO);
    }

    @PutMapping("/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "203", description = "Пользователь изменён"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable UUID id, @RequestBody CustomerDTO customerDTO) {
        customerDTO = customerService.updateCustomer(id, customerDTO);
        return customerDTO == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(customerDTO);
    }

    @DeleteMapping("{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Пользователь удален")
    })
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
