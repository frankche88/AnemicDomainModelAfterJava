import System;
import CSharpFunctionalExtensions;

namespace Logic.Customers {
    
    public class ExpirationDate extends ValueObject<ExpirationDate> {
        
        public static ExpirationDate Infinite = new ExpirationDate(null);
        
        public final DateTime? Date {
            get {
            }
        }
        
        public boolean IsExpired;
        
        private ExpirationDate(DateTime? date) {
            this.Date = date;
        }
        
        public static Result<ExpirationDate> Create(DateTime date) {
            return Result.Ok(new ExpirationDate(date));
        }
        
        protected override boolean EqualsCore(ExpirationDate other) {
            return (this.Date == other.Date);
        }
        
        protected override int GetHashCodeCore() {
            return this.Date.GetHashCode();
        }
        
        public static ExpirationDate explicitOperator(DateTime? date) {
            if (date.HasValue) {
                return ExpirationDate.Create(date.Value).Value;
            }
            
            return Infinite;
        }
        
        public static DateTime? implicitOperator(ExpirationDate date) {
            return date.Date;
        }
    }
}