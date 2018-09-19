package app.customers.domain.entity;

import java.time.LocalDateTime;

import app.commons.domain.entity.ValueObject;

public class ExpirationDate extends ValueObject<ExpirationDate> {
	public static final ExpirationDate Infinite = new ExpirationDate(null);

	private LocalDateTime Date;

	public final LocalDateTime getDate() {
		return Date;
	}

	public final boolean getIsExpired() {
		return this != Infinite && getDate().compareTo(LocalDateTime.now()) < 0;
	}

	private ExpirationDate(LocalDateTime date) {
		Date = date;
	}

	public static ExpirationDate Create(LocalDateTime date) {
		return new ExpirationDate(date);
	}

	@Override
	protected boolean EqualsCore(ExpirationDate other) {
		return getDate().equals(other.getDate());
	}

	@Override
	protected int GetHashCodeCore() {
		return getDate().hashCode();
	}

}