package Logic.Customers;

import Logic.Common.*;
import Logic.Utils.*;

public class CustomerRepository extends Repository<Customer>
{
	public CustomerRepository(UnitOfWork unitOfWork)
	{
		super(unitOfWork);
	}

	public final IReadOnlyList<Customer> GetList()
	{
		return _unitOfWork.<Customer>Query().ToList();
	}

	public final Customer GetByEmail(Email email)
	{
		return _unitOfWork.<Customer>Query().SingleOrDefault(x = email.getValue().equals(> x.Email));
	}
}