package Logic.Customers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import Logic.Common.ValueObject;

public class CustomerStatus extends ValueObject<CustomerStatus> {
	public static final CustomerStatus Regular = new CustomerStatus(CustomerStatusType.Regular,
			ExpirationDate.Infinite);

	private CustomerStatusType Type = CustomerStatusType.values()[0];

	public final CustomerStatusType getType() {
		return Type;
	}

	private ExpirationDate _expirationDate;

	public final ExpirationDate getExpirationDate() {
		return _expirationDate;
	}

	public final boolean getIsAdvanced() {
		return getType() == CustomerStatusType.Advanced && !getExpirationDate().getIsExpired();
	}

	private CustomerStatus() {
	}

	private CustomerStatus(CustomerStatusType type, ExpirationDate expirationDate) {
		this();
		Type = type;

		if (expirationDate == null) {
			throw new NullPointerException("expirationDate");
		}

		_expirationDate = expirationDate;
	}

	public final BigDecimal GetDiscount() {
		return getIsAdvanced() ? new BigDecimal(0.25) : java.math.BigDecimal.ZERO;
	}

	public final CustomerStatus Promote() {

		return new CustomerStatus(CustomerStatusType.Advanced, ExpirationDate.Create(LocalDateTime.now().plusYears(1)));
	}

	@Override
	protected boolean EqualsCore(CustomerStatus other) {
		return getType() == other.getType() && getExpirationDate() == other.getExpirationDate();
	}

	@Override
	protected int GetHashCodeCore() {
		return getType().hashCode() ^ getExpirationDate().hashCode();
	}
}