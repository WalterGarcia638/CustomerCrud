package quarkus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.Entities.Client;
import Repository.ClientRepository;
import Service.ClientService;
import Service.CountryInfoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
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
	 
	 @Inject
	    ClientService clientService;
	
	@GET
	public List<Client> index(){
		return repo.listAll();
	}
	
	@POST
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
	 
	 @PATCH
	    @Path("/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response updateClient(@PathParam("id") Long id, Client clientData) {
	        Client updatedClient = clientService.updateClient(id, clientData.getEmail(), clientData.getAddress(), clientData.getPhone(), clientData.getCountry());
	        if (updatedClient != null) {
	            return Response.ok(updatedClient).build();
	        } else {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }
	    }
	 
}
