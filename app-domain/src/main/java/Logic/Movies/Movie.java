import Newtonsoft.Json;

namespace Logic.Entities {
    
    public class Movie extends Entity {
        
        public virtual string Name {
            get {
            }
            set {
            }
        }
        
        @JsonIgnore()
        public virtual LicensingModel LicensingModel {
            get {
            }
            set {
            }
        }
    }
}