package Service;

import Domain.Entities.Client;
import Repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClientService {
	
	@Inject
	private ClientRepository repo;
	
	@Inject
	CountryInfoService countryInfoService;
	
	public Client updateClient(Long id, String email, String address, String phone, String country) {
	    Client client = repo.findById(id);
	    if (client != null) {
	        client.setEmail(email);
	        client.setAddress(address);
	        client.setPhone(phone);
	        client.setCountry(country);

	        // Utilizar CountryInfoService para obtener la nacionalidad basada en el código de país
	        String demonym = countryInfoService.getDemonymByCountryCode(country);
	        if (demonym != null && !demonym.equals("No disponible")) {
	            client.setNationality(demonym);
	        } else {
	            // Manejar el caso cuando el gentilicio no está disponible
	            client.setNationality("Desconocido");
	        }

	        repo.persist(client);
	    }
	    return client;
	}
}
