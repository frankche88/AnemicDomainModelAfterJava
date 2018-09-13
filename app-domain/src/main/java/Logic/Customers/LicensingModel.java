package Logic.Customers;

public enum LicensingModel
{
	TwoDays(1),
	LifeLong(2);

	public static final int SIZE = java.lang.Integer.SIZE;

	private int intValue;
	private static java.util.HashMap<Integer, LicensingModel> mappings;
	private static java.util.HashMap<Integer, LicensingModel> getMappings()
	{
		if (mappings == null)
		{
			synchronized (LicensingModel.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, LicensingModel>();
				}
			}
		}
		return mappings;
	}

	private LicensingModel(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static LicensingModel forValue(int value)
	{
		return getMappings().get(value);
	}
}