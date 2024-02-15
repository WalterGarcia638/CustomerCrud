package quarkus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.Entities.Client;
import Repository.ClientRepository;
import Service.CountryInfoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/Client")
@Transactional
public class ClientResource {

	
	@Inject
	private ClientRepository repo;
	
	 @Inject
	    private CountryInfoService countryInfoService;
	
	@GET
	public List<Client> index(){
		return repo.listAll();
	}
	
	/*@POST
	public Client insert(Client insertClient) {
		repo.persist(insertClient);
		return insertClient;
	}*/
	
	 public Client insert(Client insertClient) {
	        // Obtener el país del cliente
	        String countryCode = insertClient.getCountry();
	        // Obtener el gentilicio para el país
	        String demonym = countryInfoService.getDemonymByCountryCode(countryCode);
	        // Asignar el gentilicio como nacionalidad
	        if (demonym != null) {
	            insertClient.setNationality(demonym);
	        } else {
	            insertClient.setNationality("Desconocido");
	        }
	        // Persistir el cliente
	        repo.persist(insertClient);
	        return insertClient;
	    }
	
	@GET
	@Path("{id}")
	public Client retrieve(@PathParam("id") Long id) throws Exception 
	{
		var client = repo.findById(id);
		if(client !=null) 
		{
			return client;
		}
		
		throw new Exception("no hay cliente con el Id " + id + ".");
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id)
	{
		repo.deleteById(id);
	}	
	
	 @GET
	    @Path("/byCountry")
	    public List<Client> getClientsByCountry(@QueryParam("country") String country) {
	        return repo.find("country", country).list();
	    }
	 
	 @GET
	    @Path("/demonym")
	    @Produces("application/json")
	    public Map<String, String> getDemonymByCountryCode(@QueryParam("countryCode") String countryCode) {
	        Map<String, String> response = new HashMap<>();

	        try {
	            String demonym = countryInfoService.getDemonymByCountryCode(countryCode);
	            if (demonym != null) {
	                response.put("countryCode", countryCode);
	                response.put("demonym", demonym);
	            } else {
	                response.put("error", "No se pudo obtener el gentilicio para el país con código " + countryCode);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.put("error", "Se produjo un error al procesar la solicitud");
	        }

	        return response;
	    }
	
	/*@GET
	public Response createClient() { // Se ha removido el parámetro Client ya que no se usa
	    Client client = clientService.getClient(); // Obteniendo el Client del servicio
	    return Response.status(Response.Status.CREATED).entity(client).build();
	}*/
	

    /*@POST
    public Response createClient(Client client) {
        clientService.createClient(client);
        return Response.status(Response.Status.CREATED).entity(client).build();
    }

    @GET
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GET
    @Path("/country/{countryCode}")
    public List<Client> getClientsByCountry(@PathParam("countryCode") String countryCode) {
        return clientService.getClientsByCountry(countryCode);
    }

    @GET
    @Path("/{id}")
    public Client getClientById(@PathParam("id") Long id) {
        return clientService.getClientById(id);
    }

    @PUT
    @Path("/{id}")
    public Client updateClient(@PathParam("id") Long id, Client client) {
        return clientService.updateClient(id, client);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClient(@PathParam("id") Long id) {
        clientService.deleteClient(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }*/
	
	/*@GET
	public Client getClient() 
	{
		 return new Client(
			        "John", // Suponiendo que este es el nombre, aunque no se menciona en tu fragmento original
			        "Doe", // Apellido (lastName) - REQUERIDO
			        "Smith", // Segundo apellido (secondLastName) - OPCIONAL
			        "johndoe@example.com", // Email - REQUERIDO
			        "123 Example Street, Example City", // Dirección (address) - REQUERIDO
			        "+1234567890", // Teléfono (phone) - REQUERIDO
			        "US", // País (country), usando código ISO 3166 - REQUERIDO
			        "American" // Nacionalidad (nationality) - Asumiendo que es opcional ya que no se especifica
			        , null
			    );
	}*/
	
}
