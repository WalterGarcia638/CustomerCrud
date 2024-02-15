package quarkus;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/saludar")
public class EcoResource {

	@GET
	public String saludar() 
	{
		return "Hola";
	}
	/*
	@GET
	@Path("/dias")
	public String dias() 
	{
		return "Hola, muy buenos dias";
	}
	
	@GET
	@Path("/noche")
	public String noche() 
	{
		return "Hola, muy buenas noches";
	}
	@GET
	@Path("/dias")
	public String dias(@queryParam("mensaje") String mensaje) 
	{
		return "Hola, muy buenos dias";
	}
	
	*/
}
