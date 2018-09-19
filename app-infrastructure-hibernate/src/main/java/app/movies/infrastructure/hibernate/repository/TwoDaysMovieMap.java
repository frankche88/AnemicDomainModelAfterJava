package app.movies.infrastructure.hibernate.repository;

import FluentNHibernate.*;
import FluentNHibernate.Mapping.*;

public class TwoDaysMovieMap extends SubclassMap<TwoDaysMovie>
{
	public TwoDaysMovieMap()
	{
		DiscriminatorValue(1);
	}
}