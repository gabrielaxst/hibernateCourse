package entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class ClientAddressId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long clientId;
	private Long addressId;

	public ClientAddressId() {
	}

	public ClientAddressId(Long clientId, Long addressId) {
		this.clientId = clientId;
		this.addressId = addressId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

}
