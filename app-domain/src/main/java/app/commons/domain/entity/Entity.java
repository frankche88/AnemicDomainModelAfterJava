package app.commons.domain.entity;

public abstract class Entity {
	private long Id;

	public long getId() {
		return Id;
	}

	protected void setId(long value) {
		Id = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

	public static boolean OpEquality(Entity a, Entity b) {
		if ((a == null) && (b == null)) {
			return true;
		}

		if ((a == null) || (b == null)) {
			return false;
		}

		return a.equals(b);
	}

	public static boolean OpInequality(Entity a, Entity b) {
		return !app.commons.domain.entity.Entity.OpEquality(a, b);
	}

}