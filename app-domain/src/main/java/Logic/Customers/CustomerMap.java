package Logic.Customers;

import FluentNHibernate.Mapping.*;
import java.util.*;
import java.time.*;
import java.math.*;

public class CustomerMap extends ClassMap<Customer>
{
	public CustomerMap()
	{
		Id(x -> x.Id);

		Map(x -> x.Name).<String>CustomType().Access.CamelCaseField(Prefix.Underscore);
		Map(x -> x.Email).<String>CustomType().Access.CamelCaseField(Prefix.Underscore);
		Map(x -> x.MoneySpent).<BigDecimal>CustomType().Access.CamelCaseField(Prefix.Underscore);

		Component(x -> x.Status, y ->
		{
				y.Map(x -> x.Type, "Status").<Integer>CustomType();
				y.Map(x -> x.ExpirationDate, "StatusExpirationDate").<Optional<LocalDateTime>>CustomType().Access.CamelCaseField(Prefix.Underscore).Nullable();
		});

		HasMany(x -> x.PurchasedMovies).Access.CamelCaseField(Prefix.Underscore);
		;
	}
}