package app.customers.domain.entity;

import app.commons.domain.entity.ValueObject;

public class CustomerName extends ValueObject<CustomerName> {
	private String Value;

	public final String getValue() {
		return Value;
	}

	private CustomerName(String value) {
		Value = value;
	}

	public static CustomerName Create(String customerName) {
		customerName = ((customerName != null) ? customerName : "").trim();

		if (customerName.length() == 0) {
			throw new IllegalArgumentException("Customer name should not be empty");
		}

		if (customerName.length() > 100) {
			throw new IllegalArgumentException("Customer name is too long");
		}

		return new CustomerName(customerName);
	}

	@Override
	protected boolean EqualsCore(CustomerName other) {
		return getValue().toLowerCase().equals(other.getValue().toLowerCase());
	}

	@Override
	protected int GetHashCodeCore() {
		return getValue().hashCode();
	}

}