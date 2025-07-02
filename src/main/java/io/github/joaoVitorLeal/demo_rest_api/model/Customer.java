package io.github.joaoVitorLeal.demo_rest_api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String login;
    private String password;

    public Customer() {}

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(login, customer.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }
}
