package Logic.Movies;

import Logic.Common.*;
import Logic.Customers.*;
import java.time.*;
import java.math.*;

public abstract class Movie extends Entity
{
	private String Name;
	public String getName()
	{
		return Name;
	}
	protected void setName(String value)
	{
		Name = value;
	}
	private LicensingModel LicensingModel = getLicensingModel().values()[0];
	protected LicensingModel getLicensingModel()
	{
		return LicensingModel;
	}
	protected void setLicensingModel(LicensingModel value)
	{
		LicensingModel = value;
	}

	public abstract ExpirationDate GetExpirationDate();

	public Dollars CalculatePrice(CustomerStatus status)
	{
		BigDecimal modifier = 1 - status.GetDiscount();
		return Logic.Customers.Dollars.OpMultiply(GetBasePrice(), modifier);
	}

	protected abstract Dollars GetBasePrice();
}