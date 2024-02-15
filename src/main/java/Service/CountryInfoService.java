

package Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.h2.util.json.JSONObject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CountryInfoService {
	
	 private static final String API_BASE_URL = "https://restcountries.com/v3.1";
	    private static final HttpClient httpClient = HttpClient.newHttpClient();

	    public String getDemonymByCountryCode(String countryCode) {
	        String url = API_BASE_URL + "/alpha/" + countryCode;

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .build();

	        try {
	            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	            if (response.statusCode() == 200) {
	                ObjectMapper objectMapper = new ObjectMapper();
	                JsonNode countryData = objectMapper.readTree(response.body());
	                
	                JsonNode demonyms = countryData.at("/0/demonyms/eng/f");
	                if (demonyms != null && !demonyms.isMissingNode() && !demonyms.asText().isEmpty()) {
	                    return demonyms.asText();
	                } else {
	                    demonyms = countryData.at("/0/demonyms/fra/f");
	                    if (demonyms != null && !demonyms.isMissingNode() && !demonyms.asText().isEmpty()) {
	                        return demonyms.asText();
	                    } else {
	                        return "No disponible";
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
	
	 /*private static final String API_BASE_URL = "https://restcountries.com/v3.1";
	    private static final HttpClient httpClient = HttpClient.newHttpClient();

	    public String getDemonymByCountryCode(String countryCode) {
	        String url = API_BASE_URL + "/alpha/" + countryCode;

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .build();

	        try {
	            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	            if (response.statusCode() == 200) {
	                ObjectMapper objectMapper = new ObjectMapper();
	                JsonNode countryData = objectMapper.readTree(response.body());
	                
	                JsonNode demonyms = countryData.at("/0/demonyms/eng");
	                if (!demonyms.isMissingNode() && !demonyms.asText().isEmpty()) {
	                    return demonyms.asText();
	                } else {
	                    demonyms = countryData.at("/0/demonyms/fra");
	                    if (!demonyms.isMissingNode() && !demonyms.asText().isEmpty()) {
	                        return demonyms.asText();
	                    } else {
	                        return "No disponible";
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }*/
	
	 /*private static final String API_BASE_URL = "https://restcountries.com/v3.1";
	    private static final HttpClient httpClient = HttpClient.newHttpClient();

	    public String getDemonymByCountryCode(String countryCode) {
	        String url = API_BASE_URL + "/alpha/" + countryCode;

	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .build();

	        try {
	            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

	            if (response.statusCode() == 200) {
	                ObjectMapper objectMapper = new ObjectMapper();
	                JsonNode countryData = objectMapper.readTree(response.body());
	                
	                JsonNode demonyms = countryData.at("/0/demonyms/eng");
	                if (!demonyms.isMissingNode()) {
	                    return demonyms.asText();
	                } else {
	                    demonyms = countryData.at("/0/demonyms/fra");
	                    if (!demonyms.isMissingNode()) {
	                        return demonyms.asText();
	                    } else {
	                        return null;
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }*/
    
}
