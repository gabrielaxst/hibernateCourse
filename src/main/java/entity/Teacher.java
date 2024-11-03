package entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_professor")
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "cd_professor")
	private Long id;
	
	@Column(name = "ds_nome")
	private String name;
	
	@ElementCollection
	@CollectionTable(
				name = "tb_professor_materias",
				joinColumns = @JoinColumn(name="cd_professo")
			)
	@Column(name = "ds_materia")
	private List<String> subjects;

	public Teacher() {
	}

	public Teacher(Long id, String name, List<String> subjects) {
		this.id = id;
		this.name = name;
		this.subjects = subjects;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", subjects=" + subjects + "]";
	}

}
