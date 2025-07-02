package io.github.joaoVitorLeal.demo_rest_api.controller;

import io.github.joaoVitorLeal.demo_rest_api.model.Customer;
import io.github.joaoVitorLeal.demo_rest_api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping
    public void save(@RequestBody Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Cadastro inválido!");
        }
        repository.save(customer);
    }

    @GetMapping("/{id}")
    public Customer retrieveById( @PathVariable(name = "id") Integer id) {
        return repository.findById(id);
    }

    @GetMapping("/username/{username}")
    public Customer retrieveByUsername( @PathVariable(name = "username") String username) {
        return repository.findByUsername(username);
    }

    @GetMapping
    public List<Customer> retrieveAll() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        repository.deleteById(id);
    }
}