package dto;

public class StudentDto {
	private Integer id;

	private String nome;

	private String curso;
	
	private Integer flagAtivo;

	public StudentDto() {
	}

	public StudentDto(Integer id, String nome, String curso, Integer flagAtivo) {
		this.id = id;
		this.nome = nome;
		this.curso = curso;
		this.flagAtivo = flagAtivo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", nome=" + nome + ", curso=" + curso + ", flagAtivo= "+ flagAtivo + "]";
	}
	
}
