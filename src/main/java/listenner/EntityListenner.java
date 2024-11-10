package listenner;

import entity.Guide;
import entity.Student;
import jakarta.persistence.PostPersist;

public class EntityListenner {
	
	@PostPersist
	public void logEntityPersist(Object object) {
		if (object instanceof Student) {
			System.out.println("entidade Student persistida.");
		} else if (object instanceof Guide) {
			System.out.println("entidade Guide persistida.");
		}
	}
	
}
