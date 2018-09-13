package Logic.Movies;

import FluentNHibernate.*;
import FluentNHibernate.Mapping.*;

public class LifeLongMovieMap extends SubclassMap<LifeLongMovie>
{
	public LifeLongMovieMap()
	{
		DiscriminatorValue(2);
	}
}