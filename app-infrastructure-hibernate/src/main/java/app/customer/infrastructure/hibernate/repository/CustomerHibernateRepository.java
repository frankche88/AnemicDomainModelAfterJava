package app.customer.infrastructure.hibernate.repository;

import java.util.List;

import app.common.infrastructure.hibernate.repository.BaseHibernateRepository;
import app.customers.domain.entity.Customer;
import app.customers.domain.entity.Email;
import app.customers.domain.repository.CustomerRepository;

public class CustomerHibernateRepository extends BaseHibernateRepository<Customer> implements CustomerRepository {

	/* (non-Javadoc)
	 * @see app.customer.infrastructure.hibernate.repository.CustomerRepository#getList()
	 */
	@Override
	public final List<Customer> getList() {

		return (List<Customer>) getSession().createCriteria(Customer.class).list();

	}

	/* (non-Javadoc)
	 * @see app.customer.infrastructure.hibernate.repository.CustomerRepository#getByEmail(app.customers.domain.entity.Email)
	 */
	@Override
	public final Customer getByEmail(Email email)
	{
		//return _unitOfWork.<Customer>Query().SingleOrDefault(x = email.getValue().equals(> x.Email));
		//find customer  by email
		return null;//(Customer)getSession().get(Email.class, email.getValue());
		
	}
}