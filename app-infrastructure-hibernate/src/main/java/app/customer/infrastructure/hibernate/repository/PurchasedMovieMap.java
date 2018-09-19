package app.customer.infrastructure.hibernate.repository;

import FluentNHibernate.Mapping.*;
import java.util.*;
import java.time.*;
import java.math.*;

public class PurchasedMovieMap extends ClassMap<PurchasedMovie>
{
	public PurchasedMovieMap()
	{
		Id(x -> x.Id);

		Map(x -> x.Price).<BigDecimal>CustomType().Access.CamelCaseField(Prefix.Underscore);
		Map(x -> x.PurchaseDate);
		Map(x -> x.ExpirationDate).<Optional<LocalDateTime>>CustomType().Access.CamelCaseField(Prefix.Underscore).Nullable();

		References(x -> x.Movie);
		References(x -> x.Customer);
	}
}