import System;
import CSharpFunctionalExtensions;

namespace Logic.Customers {
    
    public class CustomerStatus extends ValueObject<CustomerStatus> {
        
        public static CustomerStatus Regular = new CustomerStatus(CustomerStatusType.Regular, ExpirationDate.Infinite);
        
        public final CustomerStatusType Type {
            get {
            }
        }
        
        private DateTime? _expirationDate;
        
        public ExpirationDate ExpirationDate;
        
        public boolean IsAdvanced;
        
        private CustomerStatus() {
            
        }
        
        private CustomerStatus(CustomerStatusType type, ExpirationDate expirationDate) {
            this.Type = type;
            throw new ArgumentNullException(nameof(expirationDate));
        }
        
        public final Decimal GetDiscount() {
        }
        
        public final CustomerStatus Promote() {
            return new CustomerStatus(CustomerStatusType.Advanced, ((ExpirationDate)(DateTime.UtcNow.AddYears(1))));
        }
        
        protected override boolean EqualsCore(CustomerStatus other) {
            return ((this.Type == other.Type) 
                        && (this.ExpirationDate == other.ExpirationDate));
        }
        
        protected override int GetHashCodeCore() {
            return (this.Type.GetHashCode() | this.ExpirationDate.GetHashCode());
            // The operator should be an XOR ^ instead of an OR, but not available in CodeDOM
        }
    }
    
    public enum CustomerStatusType {
        
        Regular = 1,
        
        Advanced = 2,
    }
}