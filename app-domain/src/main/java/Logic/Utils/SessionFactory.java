import System.Reflection;
import FluentNHibernate.Cfg;
import FluentNHibernate.Cfg.Db;
import FluentNHibernate.Conventions;
import FluentNHibernate.Conventions.AcceptanceCriteria;
import FluentNHibernate.Conventions.Helpers;
import FluentNHibernate.Conventions.Instances;
import FluentNHibernate.Mapping;
import NHibernate;

namespace Logic.Utils {
    
    public class SessionFactory {
        
        private ISessionFactory _factory;
        
        public SessionFactory(string connectionString) {
            this._factory = BuildSessionFactory(connectionString);
        }
        
        internal final ISession OpenSession() {
            return this._factory.OpenSession();
        }
        
        private static ISessionFactory BuildSessionFactory(string connectionString) {
            FluentConfiguration configuration = Fluently.Configure().Database(MsSqlConfiguration.MsSql2012.ConnectionString(connectionString)).Mappings(() => {  }, m.FluentMappings.AddFromAssembly(Assembly.GetExecutingAssembly()).Conventions.Add(ForeignKey.EndsWith("ID"), ConventionBuilder.Property.When(() => {  }, criteria.Expect(() => {  }, x.Nullable, Is.Not.Set), () => {  }, x.Not.Nullable())).Conventions.Add().Conventions.Add().Conventions.Add());
            return configuration.BuildSessionFactory();
        }
        
        private class OtherConversions extends IHasManyConvention, IReferenceConvention {
            
            public final void Apply(IOneToManyCollectionInstance instance) {
                instance.LazyLoad();
                instance.AsBag();
                instance.Cascade.SaveUpdate();
                instance.Inverse();
            }
            
            public final void Apply(IManyToOneInstance instance) {
                instance.LazyLoad(Laziness.Proxy);
                instance.Cascade.None();
                instance.Not.Nullable();
            }
        }
        
        public class TableNameConvention extends IClassConvention {
            
            public final void Apply(IClassInstance instance) {
                instance.Table(instance.EntityType.Name);
            }
        }
        
        public class HiLoConvention extends IIdConvention {
            
            public final void Apply(IIdentityInstance instance) {
                instance.Column((instance.EntityType.Name + "ID"));
                instance.GeneratedBy.Native();
            }
        }
    }
}