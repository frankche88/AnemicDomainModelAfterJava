import System.Collections.Generic;
import System.Linq;
import Logic.Entities;
import Logic.Utils;

namespace Logic.Repositories {
    
    public class MovieRepository extends Repository<Movie> {
        
        public MovieRepository(UnitOfWork unitOfWork) {
            super(unitOfWork);
            
            
        }
        
        public final IReadOnlyList<Movie> GetList() {
            return _unitOfWork.Query<Movie>().ToList();
        }
    }
}