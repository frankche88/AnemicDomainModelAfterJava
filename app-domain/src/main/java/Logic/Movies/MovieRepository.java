import System.Collections.Generic;
import System.Linq;
import Logic.Common;
import Logic.Utils;

namespace Logic.Movies {
    
    public class MovieRepository extends Repository<Movie> {
        
        public MovieRepository(UnitOfWork unitOfWork) {
            super(unitOfWork);
            
            
        }
        
        public final IReadOnlyList<Movie> GetList() {
            return _unitOfWork.Query<Movie>().ToList();
        }
    }
}