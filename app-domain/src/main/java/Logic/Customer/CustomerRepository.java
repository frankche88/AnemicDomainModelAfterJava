import System.Collections.Generic;
import System.Linq;
import Logic.Common;
import Logic.Utils;

namespace Logic.Customers {
    
    public class CustomerRepository extends Repository<Customer> {
        
        public CustomerRepository(UnitOfWork unitOfWork) {
            super(unitOfWork);
            
            
        }
        
        public final IReadOnlyList<Customer> GetList() {
            return _unitOfWork.Query<Customer>().ToList();
        }
        
        public final Customer GetByEmail(Email email) {
            return _unitOfWork.Query<Customer>().SingleOrDefault(() => {  }, (x.Email == email.Value));
        }
    }
}