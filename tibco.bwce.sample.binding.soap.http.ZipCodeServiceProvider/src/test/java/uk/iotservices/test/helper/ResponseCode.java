package uk.iotservices.test.helper;

import static org.junit.Assert.assertEquals;

import org.apache.http.HttpResponse;

public class ResponseCode {

	public static void assertResponseCodeIs( final HttpResponse httpResponse, final int expectedCode ){
		
		   final int statusCode = httpResponse.getStatusLine().getStatusCode();
		
		    assertEquals(expectedCode,statusCode );
		
	}
	
}
