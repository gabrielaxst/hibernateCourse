package entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import dto.StudentDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import listenner.EntityListenner;
import mapper.EntityMapper;

@Entity
@NamedQueries({
	@NamedQuery(name = "Guide.findById", query = "SELECT g FROM Guide g WHERE g.id = :id"),
	//Named query com o mesmo valor para name lançam uma exception
	//@NamedQuery(name = "findById", query = "SELECT g FROM Guide g WHERE g.id = :id"),
	@NamedQuery(name = "findAllGuides", query = "SELECT g from Guide g")
})
@EntityListeners(EntityListenner.class)
@BatchSize(size = 3)
public class Guide {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guideGenerator")
	@SequenceGenerator(name = "guideGenerator",allocationSize = 1)
	private Integer id;

	private String name;

	@OneToMany(mappedBy = "guide", orphanRemoval = true, cascade = CascadeType.MERGE)
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
		student.setGuide(this);
		getStudents().add(student);
	}

	@Override
	public String toString() {
		return "Guide [id=" + id + ", name=" + name + "students="+ getStudentDtos() +"]";
	}

}
