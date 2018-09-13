import System;
import Logic.Common;
import Logic.Customers;

namespace Logic.Movies {
    
    public abstract class Movie extends Entity {
        
        public virtual string Name {
            get {
            }
            set {
            }
        }
        
        protected virtual LicensingModel LicensingModel {
            get {
            }
            set {
            }
        }
        
        public abstract ExpirationDate GetExpirationDate();
        
        public virtual Dollars CalculatePrice(CustomerStatus status) {
            Decimal modifier = (1 - status.GetDiscount());
            return (this.GetBasePrice() * modifier);
        }
        
        protected abstract Dollars GetBasePrice();
    }
    
    public class TwoDaysMovie extends Movie {
        
        public override ExpirationDate GetExpirationDate() {
            return ((ExpirationDate)(DateTime.UtcNow.AddDays(2)));
        }
        
        protected override Dollars GetBasePrice() {
            return Dollars.Of(4);
        }
    }
    
    public class LifeLongMovie extends Movie {
        
        public override ExpirationDate GetExpirationDate() {
            return ExpirationDate.Infinite;
        }
        
        protected override Dollars GetBasePrice() {
            return Dollars.Of(8);
        }
    }
}