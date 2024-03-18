package com.mml.www.config;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AuthSpotifyAPIConfig {
	
  private static final String clientId = "";
  
  private static final String clientSecret = "";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setClientId(clientId)
    .setClientSecret(clientSecret)
    .build();
  
  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
    .build();

  public static String accessToken() {
    try {
      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

      // Set access token for further "spotifyApi" object usage
      spotifyApi.setAccessToken(clientCredentials.getAccessToken());
      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
      System.out.println("spotifyApi.getAccessToken(): " + spotifyApi.getAccessToken());
      return spotifyApi.getAccessToken();
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
      return "error";
    }
  }
}