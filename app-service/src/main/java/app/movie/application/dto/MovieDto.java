package app.movie.application.dto;

public class MovieDto
{
	private long Id;
	public final long getId()
	{
		return Id;
	}
	public final void setId(long value)
	{
		Id = value;
	}
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
}