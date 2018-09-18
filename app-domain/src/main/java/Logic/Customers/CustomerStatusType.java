package Logic.Customers;

public enum CustomerStatusType
{
	Regular(1),
	Advanced(2);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, CustomerStatusType> mappings;
	private static java.util.HashMap<Integer, CustomerStatusType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (CustomerStatusType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, CustomerStatusType>();
				}
			}
		}
		return mappings;
	}

	private CustomerStatusType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static CustomerStatusType forValue(int value)
	{
		return getMappings().get(value);
	}
}