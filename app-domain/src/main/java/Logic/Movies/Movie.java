package Logic.Movies;

import java.math.BigDecimal;

import Logic.Common.Entity;
import Logic.Customers.CustomerStatus;
import Logic.Customers.Dollars;
import Logic.Customers.ExpirationDate;
import Logic.Customers.LicensingModel;

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

	public abstract ExpirationDate GetExpirationDate();

	public Dollars CalculatePrice(CustomerStatus status) {
		BigDecimal modifier = new BigDecimal(1).subtract(status.GetDiscount());
		return Logic.Customers.Dollars.OpMultiply(GetBasePrice(), modifier);
	}

	protected abstract Dollars GetBasePrice();
}