import Logic.Utils;

namespace Logic.Common {
    
    public abstract class Repository<T>  where T: Entity {
        
        protected UnitOfWork _unitOfWork;
        
        protected Repository(UnitOfWork unitOfWork) {
            this._unitOfWork = unitOfWork;
        }
        
        public final T GetById(long id) {
            return this._unitOfWork.Get<T>(id);
        }
        
        public final void Add(T entity) {
            this._unitOfWork.SaveOrUpdate(entity);
        }
    }
}