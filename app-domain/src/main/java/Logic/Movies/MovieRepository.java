package Logic.Movies;

import Logic.Common.*;
import Logic.Utils.*;

public class MovieRepository extends Repository<Movie>
{
	public MovieRepository(UnitOfWork unitOfWork)
	{
		super(unitOfWork);
	}

	public final IReadOnlyList<Movie> GetList()
	{
		return _unitOfWork.<Movie>Query().ToList();
	}
}