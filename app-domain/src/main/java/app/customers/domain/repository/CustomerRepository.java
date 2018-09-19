package app.customers.domain.repository;

import java.util.List;

import app.customers.domain.entity.Customer;
import app.customers.domain.entity.Email;

public interface CustomerRepository {

	List<Customer> getList();

	Customer getByEmail(Email email);

	Customer getById(long id);

}