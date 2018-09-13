import System;
import System.Data;
import System.Linq;
import NHibernate;
import NHibernate.Linq;

namespace Logic.Utils {
    
    public class UnitOfWork extends IDisposable {
        
        private ISession _session;
        
        private ITransaction _transaction;
        
        private boolean _isAlive = true;
        
        private boolean _isCommitted;
        
        public UnitOfWork(SessionFactory sessionFactory) {
            this._session = sessionFactory.OpenSession();
            this._transaction = this._session.BeginTransaction(IsolationLevel.ReadCommitted);
        }
        
        public final void Dispose() {
            if (!this._isAlive) {
                return;
            }
            
            this._isAlive = false;
            try {
                if (this._isCommitted) {
                    this._transaction.Commit();
                }
                
            }
            finally {
                this._transaction.Dispose();
                this._session.Dispose();
            }
            
        }
        
        public final void Commit() {
            if (!this._isAlive) {
                return;
            }
            
            this._isCommitted = true;
        }
        
        internal final <T> T Get(long id) {
            return this._session.Get<T>(id);
        }
        
        internal final <T> void SaveOrUpdate(T entity) {
            this._session.SaveOrUpdate(entity);
        }
        
        internal final <T> void Delete(T entity) {
            this._session.Delete(entity);
        }
        
        public final <T> IQueryable<T> Query() {
            return this._session.Query<T>();
        }
        
        public final ISQLQuery CreateSQLQuery(string q) {
            return this._session.CreateSQLQuery(q);
        }
    }
}