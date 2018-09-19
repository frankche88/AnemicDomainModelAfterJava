package app.movies.infrastructure.hibernate.repository;

import FluentNHibernate.*;
import FluentNHibernate.Mapping.*;

public class MovieMap extends ClassMap<Movie>
{
	public MovieMap()
	{
		Id(x -> x.Id);

		DiscriminateSubClassesOnColumn("LicensingModel");

		Map(x -> x.Name);
		Map(Reveal.<Movie>Member("LicensingModel")).<Integer>CustomType();
	}
}