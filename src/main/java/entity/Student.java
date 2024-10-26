package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String curso;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "guide_id")
	private Guide guide;

	private Integer flagAtivo;
	
	public Student() {
	}

	public Student(String nome, Guide guide) {
		this.nome = nome;
		this.guide = guide;
	}	

	public Student(String nome, String curso, Guide guide, Integer flagAtivo) {
		this.nome = nome;
		this.curso = curso;
		this.guide = guide;
		this.flagAtivo = flagAtivo;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Guide getGuide() {
		return guide;
	}
	
	public String getGuideName() {
		return guide != null ? guide.getName() : "------";
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}


	public Integer getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Integer flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", nome=" + nome + ", curso=" + curso + ", guide=" + getGuideName()+ ", merito=" + "]";
	}

}
