package uk.iotservices.test.unit;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import uk.iotservices.test.data.*;
import uk.iotservices.test.helper.*;

public class GetCityDistanceRESTTest {
	
	HttpResponse httpResponse;
	CloseableHttpClient httpClient;
	
	@Test	
	@Before
	public void testGetRequest() throws ClientProtocolException, IOException{
		
		String hostname = System.getProperty("hostname");
	    String portNumber = System.getProperty("portNumber");
	    String sslRequired = System.getProperty("sslRequired");
	    
	    System.out.println("hostname = " + hostname);
	    System.out.println("portNumber = " + portNumber);
	    System.out.println("sslRequired = " + sslRequired);
	    
	    HttpUriRequest request ;
	    
	    // if portnumber is not specified then set to 8080 or 443 if SSL is specified
	    if (portNumber==null) 
	    
	    {
	    	 if ("Y".equalsIgnoreCase(sslRequired))
	    	 { 
	    		 portNumber="443";
	    	 }else{
	    		 portNumber="8080";	 
	    	 }
	    	
	    }
	    
	    // if hostname is not set then set it to localhost
	    if (hostname==null){
	    	
	    	hostname="localhost";
	    }    
	    
	    if ("Y".equalsIgnoreCase(sslRequired))
	    {
	    	
	    	request = new HttpGet("https://" + hostname + ":" + portNumber +  "/citydata/city/nv"); 	
	    	
	    	httpClient = HttpClients.custom().setSSLSocketFactory(
	    			     SSLHelper.getSSLConnectionSocketFactory()).build();
	    	
	    }else{
	    	request = new HttpGet("http://" + hostname + ":" + portNumber + "/citydata/city/nv");
	    	httpClient = HttpClients.createDefault();
	    }
	    
	    
        // request = new HttpGet( "https://zipservice.dev.iot.pcfdemo.tibco.com/citydata/city/nv" );
	    // request = new HttpGet( "http://marks-mbp-2.home:8080/citydata/city/nv" );
	
		
		request.addHeader("Accept","application/json");
	    
	    httpResponse = httpClient.execute(request);

	}
	
	@Test
	public void testResponseCode(){
	    ResponseCode.assertResponseCodeIs(httpResponse,200 );
	}
	
	@Test
	public void testCityData() throws ClientProtocolException, IOException{
	    
	      City city = RetrieveUtil.retrieveResourceFromResponse(
	      httpResponse, City.class);
	      assertThat("Urbana",CoreMatchers.containsString(city.getCity()));
	}

	@Test
	public void testLatitudeData() throws ClientProtocolException, IOException{
	    
	      Latitude latitude = RetrieveUtil.retrieveResourceFromResponse(
	      httpResponse, Latitude.class);
	      assertThat("40.11", CoreMatchers.containsString(latitude.getLatitude()));
	}
	
	@Test
	public void testLocationData() throws ClientProtocolException, IOException{
	    
	      Location location = RetrieveUtil.retrieveResourceFromResponse(
	      httpResponse, Location.class);
	      assertThat("Urbana, Illinois, United States", CoreMatchers.containsString(location.getLocation()));
	}
	
	@Test
	public void testLongitudeData() throws ClientProtocolException, IOException{
	    
	      Longitude longitude = RetrieveUtil.retrieveResourceFromResponse(
	      httpResponse, Longitude.class);
	      assertThat("88.207", CoreMatchers.containsString(longitude.getLongitude()));
	}
	
	@Test
	public void testStateData() throws ClientProtocolException, IOException{
	    
	      State state = RetrieveUtil.retrieveResourceFromResponse(
	      httpResponse, State.class);
	      assertThat("Illinois", CoreMatchers.containsString(state.getState()));
	}
	
	@Test
	public void testZIPData() throws ClientProtocolException, IOException{
	    
	      ZIP zip = RetrieveUtil.retrieveResourceFromResponse(
	      httpResponse, ZIP.class);
	      //-- pass 
	      assertThat("61801", CoreMatchers.containsString(zip.getZip()));
	      //-- fail
	      //-- assertThat("62801", JUnitMatchers.containsString(zip.getZip()));  // Fail
	  	
	}
	
	@Test    
	public void testResponseTypeJSON() throws ClientProtocolException, IOException{
	   
	   String jsonMimeType = "application/json";
	 
	   String mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
	   assertEquals( jsonMimeType, mimeType );
	}
	
	@Test
	@After
	public void testCloseConnection() throws IOException{
	
		httpClient.close();
	
	}
}
