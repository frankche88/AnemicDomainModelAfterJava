import System;
import NHibernate.Proxy;

namespace Logic.Common {
    
    public abstract class Entity {
        
        public virtual long Id {
            get {
            }
            set {
            }
        }
        
        public override boolean Equals(object obj) {
            var other = ((Entity)(obj));
            if (ReferenceEquals(other, null)) {
                return false;
            }
            
            if (ReferenceEquals(this, other)) {
                return true;
            }
            
            if ((this.GetRealType() != other.GetRealType())) {
                return false;
            }
            
            if (((this.Id == 0) 
                        || (other.Id == 0))) {
                return false;
            }
            
            return (this.Id == other.Id);
        }
        
        public static boolean Operator(Entity a, Entity b) {
            if ((ReferenceEquals(a, null) && ReferenceEquals(b, null))) {
                return true;
            }
            
            if ((ReferenceEquals(a, null) || ReferenceEquals(b, null))) {
                return false;
            }
            
            return a.Equals(b);
        }
        
        public static boolean Operator(Entity a, Entity b) {
            return !(a == b);
        }
        
        public override int GetHashCode() {
            return ((this.GetRealType().ToString() + this.Id)).GetHashCode();
        }
        
        private final Type GetRealType() {
            return NHibernateProxyHelper.GetClassWithoutInitializingProxy(this);
        }
    }
}