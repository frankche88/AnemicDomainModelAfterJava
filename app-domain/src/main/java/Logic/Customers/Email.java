package Logic.Customers;

import java.util.regex.Pattern;

import Logic.Common.ValueObject;

public class Email extends ValueObject<Email> {
	private String Value;

	public final String getValue() {
		return Value;
	}

	private Email(String value) {
		Value = value;
	}

	public static Email Create(String email) {
		email = ((email != null) ? email : "").trim();

		if (email.length() == 0) {
			throw new IllegalArgumentException("Email should not be empty");
		}

		Pattern regex = Pattern.compile("^(.+)@(.+)$");
		if (!regex.matcher(email).find()) {
			throw new IllegalArgumentException("Email is invalid");
		}

		return new Email(email);
	}

	@Override
	protected boolean EqualsCore(Email other) {
		return getValue().equalsIgnoreCase(other.getValue());
	}

	@Override
	protected int GetHashCodeCore() {
		return getValue().hashCode();
	}

}