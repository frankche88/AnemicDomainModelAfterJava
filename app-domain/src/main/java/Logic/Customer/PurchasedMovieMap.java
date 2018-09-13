import System;
import FluentNHibernate.Mapping;

namespace Logic.Customers {
    
    public class PurchasedMovieMap extends ClassMap<PurchasedMovie> {
        
        public PurchasedMovieMap() {
            Id(() => {  }, x.Id);
            Map(() => {  }, x.Price).CustomType().Access.CamelCaseField(Prefix.Underscore);
            Map(() => {  }, x.PurchaseDate);
            Map(() => {  }, x.ExpirationDate).CustomType().Access.CamelCaseField(Prefix.Underscore).Nullable();
            References(() => {  }, x.Movie);
            References(() => {  }, x.Customer);
        }
    }
}