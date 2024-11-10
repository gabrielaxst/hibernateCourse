package listenner;

import java.util.Scanner;

import entity.Student;
import entity.Teacher;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;

public class TeacherListenner {
	
	Scanner input = new Scanner(System.in);
	public static Boolean allowRemove;
	
	@PostPersist
	@PostUpdate
	public void logPersistUpdate(Teacher teacher) {
		System.out.println("A entidade foi salva ou atualizada no banco.");
	}
	
	@PreRemove
	public void validarRemove(Teacher teacher) {
		System.out.println("Deseja realmente remover a entidade? SIM (1) / NÃO (O)");
		int opt = input.nextInt();
		
		if (opt == 1) {
			allowRemove = true;
			System.out.println("A entidade será removida no banco.");
		} else {
			allowRemove = false;
		}	
	}
}
