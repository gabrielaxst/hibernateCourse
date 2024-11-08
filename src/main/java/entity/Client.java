package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tb_client")
	@SequenceGenerator(name = "seq_tb_client", allocationSize = 1)
	private Long id;
	
	private String name;

	public Client() {
	}

	public Client(Long id, String name) {
		this.id = id;
		this.name = name;
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
	
	/*
	 * @PrePersist public void executarAoPersistir() {
	 * System.out.println("o método prepersist da minha acc foi executado."); }
	 */
	
	/*
	 * não podemos ter mais de uma mesma anotação de lifecycle callbacks em um entidade
	 * @PrePersist public void executarAoPersistir2() {
	 * System.out.println("o método prepersist2 da minha acc foi executado."); }
	 */
	
	

}
