package entity;

import java.util.ArrayList;
import java.util.List;

import dto.StudentDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import mapper.EntityMapper;

@Entity
@NamedQuery(name = "Guide.findById", query = "SELECT g FROM Guide g WHERE g.id = :id")
public class Guide {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "guide", cascade = CascadeType.PERSIST, orphanRemoval = true)
	@OrderBy("flagAtivo DESC, nome ASC")
	private List<Student> students = new ArrayList<Student>();

	public Guide() {
	}

	public Guide(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudents() {
		return students;
	}
	
	public List<StudentDto> getStudentDtos() {
		return EntityMapper.studentToDto(students);
	}
	
	public void setStudents(Student student) {
		getStudents().add(student);
	}

	@Override
	public String toString() {
		return "Guide [id=" + id + ", name=" + name + "students="+ getStudentDtos() +"]";
	}

}
