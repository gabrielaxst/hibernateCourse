package entity;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name= "findById", query = "SELECT s FROM Student s WHERE s.id = :id")
})
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String curso;
	
	private LocalDate dataNascimento;
	
	//faz com um atributo não seja mapeada para coluna no bd
	@Transient
	private Integer idade;

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

	public Student(Integer id, String nome, String curso, LocalDate dataNascimento, Guide guide,
			Integer flagAtivo) {
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.dataNascimento = dataNascimento;
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

	//método de callback, será excecutado quando a entidade é carregada do bd
	@PostLoad
	public void setIdade() {
		this.idade = Period.between(dataNascimento, LocalDate.now()).getYears();
		System.out.println(this.idade);
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", nome=" + nome + ", curso=" + curso + ", guide=" + getGuideName()+ ", idade=" + idade + "]";
	}

}
