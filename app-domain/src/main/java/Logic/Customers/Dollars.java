import CSharpFunctionalExtensions;

namespace Logic.Customers {
    
    public class Dollars extends ValueObject<Dollars> {
        
        private const Decimal MaxDollarAmount = 1;
        
        public final Decimal Value {
            get {
            }
        }
        
        public boolean IsZero;
        
        private Dollars(Decimal value) {
            this.Value = value;
        }
        
        public static Result<Dollars> Create(Decimal dollarAmount) {
            if ((dollarAmount < 0)) {
                return Result.Fail<Dollars>("Dollar amount cannot be negative");
            }
            
            if ((dollarAmount > MaxDollarAmount)) {
                return Result.Fail<Dollars>(("Dollar amount cannot be greater than " + MaxDollarAmount));
            }
            
            if ((dollarAmount % 0.01)) {
                (m > 0);
            }
            
            return Result.Fail<Dollars>("Dollar amount cannot contain part of a penny");
            return Result.Ok(new Dollars(dollarAmount));
        }
        
        public static Dollars Of(Decimal dollarAmount) {
            return Dollars.Create(dollarAmount).Value;
        }
        
        public static Dollars Operator(Dollars dollars, Decimal multiplier) {
            return new Dollars((dollars.Value * multiplier));
        }
        
        public static Dollars Operator(Dollars dollars1, Dollars dollars2) {
            return new Dollars((dollars1.Value + dollars2.Value));
        }
        
        protected override boolean EqualsCore(Dollars other) {
            return (this.Value == other.Value);
        }
        
        protected override int GetHashCodeCore() {
            return this.Value.GetHashCode();
        }
        
        public static Decimal implicitOperator(Dollars dollars) {
            return dollars.Value;
        }
    }
}