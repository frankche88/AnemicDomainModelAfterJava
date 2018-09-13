package Logic.Utils;

import FluentNHibernate.Cfg.*;
import FluentNHibernate.Cfg.Db.*;
import FluentNHibernate.Conventions.*;
import FluentNHibernate.Conventions.AcceptanceCriteria.*;
import FluentNHibernate.Conventions.Helpers.*;
import FluentNHibernate.Conventions.Instances.*;
import FluentNHibernate.Mapping.*;
import NHibernate.*;

public class SessionFactory
{
	private ISessionFactory _factory;

	public SessionFactory(String connectionString)
	{
		_factory = BuildSessionFactory(connectionString);
	}

	public final ISession OpenSession()
	{
		return _factory.OpenSession();
	}

	private static ISessionFactory BuildSessionFactory(String connectionString)
	{
		FluentConfiguration configuration = Fluently.Configure().Database(MsSqlConfiguration.MsSql2012.ConnectionString(connectionString)).Mappings(m -> m.FluentMappings.AddFromAssembly(Assembly.GetExecutingAssembly()).Conventions.Add(ForeignKey.endsWith("ID"), ConventionBuilder.Property.When(criteria -> criteria.Expect(x -> x.Nullable, Is.Not.Set), x -> x.Not.Nullable())).Conventions.<OtherConversions>Add().Conventions.<TableNameConvention>Add().Conventions.<HiLoConvention>Add());

		return configuration.BuildSessionFactory();
	}


	private static class OtherConversions implements IHasManyConvention, IReferenceConvention
	{
		public final void Apply(IOneToManyCollectionInstance instance)
		{
			instance.LazyLoad();
			instance.AsBag();
			instance.Cascade.SaveUpdate();
			instance.Inverse();
		}

		public final void Apply(IManyToOneInstance instance)
		{
			instance.LazyLoad(Laziness.Proxy);
			instance.Cascade.None();
			instance.Not.Nullable();
		}
	}


	public static class TableNameConvention implements IClassConvention
	{
		public final void Apply(IClassInstance instance)
		{
			instance.Table(instance.EntityType.Name);
		}
	}


	public static class HiLoConvention implements IIdConvention
	{
		public final void Apply(IIdentityInstance instance)
		{
			instance.Column(instance.EntityType.Name + "ID");
			instance.GeneratedBy.Native();
		}
	}
}