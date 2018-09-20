package app.customer.infrastructure.hibernate.repository;

import java.util.List;

import app.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import app.customers.domain.entity.Customer;
import app.customers.domain.entity.Email;
import app.customers.domain.repository.CustomerRepository;

public class CustomerHibernateRepository extends BaseHibernateRepository<Customer> implements CustomerRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * app.customer.infrastructure.hibernate.repository.CustomerRepository#getList()
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public final List<Customer> getList() {

		return (List<Customer>) getSession().createCriteria(Customer.class).list();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see app.customer.infrastructure.hibernate.repository.CustomerRepository#
	 * getByEmail(app.customers.domain.entity.Email)
	 */
	@Override
	public final Customer getByEmail(Email email) {
		// return _unitOfWork.<Customer>Query().SingleOrDefault(x =
		// email.getValue().equals(> x.Email));
		// find customer by email
		
		org.hibernate.query.Query<Customer> query = getSession().createQuery ("from Customer where email.value = :email ", Customer.class);
		query.setParameter("email", email.getValue());
		List<Customer> list = query.list();
		
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		
		
		return null;// (Customer)getSession().get(Email.class, email.getValue());

	}

	@Override
	public Customer getById(long id) {

		return getSession().get(Customer.class, id);
	}
}