package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Domain.Entities.Client;
import jakarta.ws.rs.core.Response;
import quarkus.ClientResource;


public class ClientTest {
	
    private ClientResource clientResource;

    @BeforeEach
    public void setup() {
    	
        this.clientResource = new ClientResource(); 
    }

    @Test
    public void testListAllClients() {
        List<Client> clients = clientResource.index();
        assertNotNull(clients);
        assertFalse(clients.isEmpty()); 
    }
    
    @Test
    public void testInsertClientDirectly() {
        Client nuevoCliente = new Client();
        // Configura el nuevo cliente con datos de prueba
        nuevoCliente.setFirstName("Walter");
        nuevoCliente.setEmail("test@example.com");
        nuevoCliente.setAddress("San Cristobal");
        nuevoCliente.setPhone("123456789");
        nuevoCliente.setCountry("US");

        Client resultClient = clientResource.insert(nuevoCliente);

        assertNotNull(resultClient);
        assertEquals("Walter", resultClient.getFirstName());
        assertEquals("test@example.com", resultClient.getEmail());
        assertEquals("San Cristobal", resultClient.getAddress());
        assertEquals("123456789", resultClient.getPhone());
        assertEquals("US", resultClient.getCountry());
    }
    
    @Test
    public void testRetrieveClient() throws Exception {
        Long clientId = 1L; 
        Client client = clientResource.retrieve(clientId);
        assertNotNull(client);
        assertEquals(clientId, client.getId());
    }
    
    @Test
    public void testDeleteClient() {
        Long clientId = 1L; 
        clientResource.delete(clientId);
    }
    
    @Test
    public void testGetClientsByCountry() {
        String country = "US"; 
        List<Client> clients = clientResource.getClientsByCountry(country);
        assertNotNull(clients);
        assertFalse(clients.isEmpty()); 
    }
    
    @Test
    public void testGetDemonymByCountryCode() {
        String countryCode = "ES"; 
        Map<String, String> response = clientResource.getDemonymByCountryCode(countryCode);
        assertNotNull(response);
        assertTrue(response.containsKey("demonym"));
    }
    
    @Test
    public void testUpdateClient() {
        Long clientId = 1L; 
        Client clientToUpdate = new Client(); 
        clientToUpdate.setEmail("updated@example.com");
        clientToUpdate.setAddress("Updated Address");
        clientToUpdate.setPhone("987654321");
        clientToUpdate.setCountry("Updated Country");

        Response response = clientResource.updateClient(clientId, clientToUpdate);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
    }

	
}
