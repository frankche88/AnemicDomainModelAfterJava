package Logic.Movies;

import FluentNHibernate.*;
import FluentNHibernate.Mapping.*;

public class TwoDaysMovieMap extends SubclassMap<TwoDaysMovie>
{
	public TwoDaysMovieMap()
	{
		DiscriminatorValue(1);
	}
}