package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_account")
public class AccountBank {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acc_generator")
	@SequenceGenerator(name = "seq_acc_generator", sequenceName = "seq_tb_account", allocationSize = 1)
	private Long id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cd_client")	
	private Client client;
	
	@Column(name = "nr_initial_mount")
	private Double initialAmount;
	
	@Column(name = "ds_account")
	private String accountType;

	public AccountBank() {
	}

	public AccountBank(Client client, Double initialAmount) {
		this.client = client;
		this.initialAmount = initialAmount;
	}

	public AccountBank(Double initialAmount) {
		this.initialAmount = initialAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Double getInitialAmount() {
		return initialAmount;
	}

	public void setInitialAmount(Double initialAmount) {
		this.initialAmount = initialAmount;
	}

	public String getAccountType() {
		return accountType;
	}

	//irá executar antes da entidade ser persistida ou mergeada
	@PrePersist
	public void setAccountType() {
		this.accountType = initialAmount >= 5000L ? "Especial" : "Comum";
	}

	@PostPersist
	public void accountInserted() {
		System.out.println("a conta com id: " + getId() + " foi gerada.");
	}
	
	@Override
	public String toString() {
		return "AccountBank [id=" + id + ", client=" + client.getName() + ", initialAmount=" + initialAmount + ", accountType="
				+ accountType + "]";
	}
	
}
