package edu.brown.cs.student.geocodetests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.brown.cs.student.main.Server.Exceptions.DatasourceException;
import edu.brown.cs.student.main.Server.SafeTravels.GeocodingAPIClient;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeocodingAPIClientTest {
  @Test
  public void testGetCoordinates() throws DatasourceException {
    GeocodingAPIClient client = new GeocodingAPIClient();
    List<Double> coordinates = client.getCoordinates("24 Sussex Dr, Ottawa, ON K1M 1M4, Canada");

    assertNotNull(coordinates, "Coordinates should not be null");
    assertEquals(2, coordinates.size(), "There should be two elements in the coordinates list");

    // Assuming you know the expected latitude and longitude
    double expectedLat = 45.4444101;
    double expectedLng = -75.69387789999999;

    assertEquals(
        expectedLat, coordinates.get(0), 0.0001, "Latitude should match the expected value");
    assertEquals(
        expectedLng, coordinates.get(1), 0.0001, "Longitude should match the expected value");
  }

  private GeocodingAPIClient client;
  private HttpURLConnection mockConnection;

  @BeforeEach
  public void setUp() throws DatasourceException {
    client = new GeocodingAPIClient();
    mockConnection = mock(HttpURLConnection.class);
    // Set up other necessary mock behaviors
  }

  @Test
  public void testSuccessfulApiCall() throws Exception {
    String sampleResponse = "{\"data\": [{\"safetyScores\": {\"overall\": 45}}]}";

    Map<String, Object> result = client.getSafetyRatings(41.397158, 2.160873, 2);
    assertNotNull(result);
    assertTrue(result.containsKey("data"));
    // Additional assertions to check the structure and values of the response
  }

  //  @Test
  //  public void testApiCallWithInvalidCoordinates() {
  //    // Mock behavior for invalid coordinates
  //    // Assertions to check for correct exception or error handling
  //  }
  //
  //  @Test
  //  public void testApiCallWithInvalidToken() {
  //    // Mock behavior for invalid token
  //    // Assertions to check for correct exception or error handling
  //  }
  //
  //  @Test(expected = DatasourceException.class)
  //  public void testIOExceptionHandling() throws Exception {
  //    when(mockConnection.getInputStream()).thenThrow(new IOException());
  //    client.getSafetyRatings(40.7128, 40.706, -74.0060, -74.010, "validToken");
  //  }
}