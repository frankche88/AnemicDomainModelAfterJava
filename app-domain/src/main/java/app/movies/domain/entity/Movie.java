package app.movies.domain.entity;

import java.math.BigDecimal;

import app.commons.domain.entity.Entity;
import app.customers.domain.entity.CustomerStatus;
import app.customers.domain.entity.Dollars;
import app.customers.domain.entity.ExpirationDate;
import app.customers.domain.entity.LicensingModel;

public abstract class Movie extends Entity {
	private String Name;

	public String getName() {
		return Name;
	}

	protected void setName(String value) {
		Name = value;
	}

	private LicensingModel LicensingModel = getLicensingModel().values()[0];

	protected LicensingModel getLicensingModel() {
		return LicensingModel;
	}

	protected void setLicensingModel(LicensingModel value) {
		LicensingModel = value;
	}

	public abstract ExpirationDate getExpirationDate();

	public Dollars calculatePrice(CustomerStatus status) {
		BigDecimal modifier = new BigDecimal(1).subtract(status.GetDiscount());
		return app.customers.domain.entity.Dollars.OpMultiply(getBasePrice(), modifier);
	}

	protected abstract Dollars getBasePrice();
}