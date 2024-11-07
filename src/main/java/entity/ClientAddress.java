package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

//@Entity
@Table(name = "tb_client_address")
public class ClientAddress {
	@EmbeddedId
	private ClientAddressId clientAddressPk;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tb_client_id")
	@MapsId("clientId")
	private Client client;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tb_address_id")
	@MapsId("addressId")
	private Address address;

	public ClientAddress() {
	}

	public ClientAddress(ClientAddressId clientAddressPk, Client client, Address address) {
		this.clientAddressPk = clientAddressPk;
		this.client = client;
		this.address = address;
	}

	public ClientAddressId getClientAddressPk() {
		return clientAddressPk;
	}

	public void setClientAddressPk(ClientAddressId clientAddressPk) {
		this.clientAddressPk = clientAddressPk;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
