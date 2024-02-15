package Domain.Entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Client  {

	@Id
	@GeneratedValue
	private Long id;
	  private String firstName; // REQUIRED
	    private String middleName; // OPTIONAL
	    private String lastName; // REQUIRED
	    private String secondLastName; // OPTIONAL
	    private String email; // REQUIRED
	    private String address; // REQUIRED
	    private String phone; // REQUIRED
	    private String country; // REQUIRED, ISO 3166 code
	    private String nationality;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getSecondLastName() {
			return secondLastName;
		}
		public void setSecondLastName(String secondLastName) {
			this.secondLastName = secondLastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getNationality() {
			return nationality;
		}
		public void setNationality(String nationality) {
			this.nationality = nationality;
		}
		@Override
		public String toString() {
			return "Client [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
					+ lastName + ", secondLastName=" + secondLastName + ", email=" + email + ", address=" + address
					+ ", phone=" + phone + ", country=" + country + ", nationality=" + nationality + "]";
		}
		@Override
		public int hashCode() {
			return Objects.hash(address, country, email, firstName, id, lastName, middleName, nationality, phone,
					secondLastName);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Client other = (Client) obj;
			return Objects.equals(address, other.address) && Objects.equals(country, other.country)
					&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
					&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
					&& Objects.equals(middleName, other.middleName) && Objects.equals(nationality, other.nationality)
					&& Objects.equals(phone, other.phone) && Objects.equals(secondLastName, other.secondLastName);
		}
	 
	    
}
