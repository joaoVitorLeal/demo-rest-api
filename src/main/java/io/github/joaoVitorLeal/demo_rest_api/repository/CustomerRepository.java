package io.github.joaoVitorLeal.demo_rest_api.repository;

import io.github.joaoVitorLeal.demo_rest_api.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class CustomerRepository {

    public void save(Customer customer) {
        if (customer.getId() == null) {
            System.out.println("SAVE - Recebendo o usuário na camada de repositório.");
        } else {
            System.out.println("UPDATE - Recebendo usuário na camada de repositório.");
        }
        System.out.println(customer);
    }

    public void deleteById(Integer id) {
        System.out.printf("DELETE/id - Recebendo o id: %d para deleção de usuário.%n", id);
    }

    public Customer findById(Integer id) {
        System.out.println("FIND/id - Recebendo o id: " + id + "para localizar um usuário.");
        return new Customer("joaoleal_c", "********");
    }

    public List<Customer> findAll() {
        System.out.println("FIND/LIST - Listando os usuários do sistema.");
        return Arrays.asList(
                new Customer("joaoleal_c", "********"),
                new Customer("carlealstro", "password")
        );
    }

    public Customer findByUsername(String username) {
        System.out.printf("FIND/username - Recebendo o username: %s para localizar um usuário.%n", username);
        return new Customer("joaoleal_c", "********");
    }
}
