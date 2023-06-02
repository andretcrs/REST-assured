package br.com.sicredi.simulacao.base;
import io.restassured.http.ContentType;

public interface Properties {
    String APP_BASE_URL = "http://localhost";
    Integer APP_PORT = 8080; 
    String APP_BASE_PATH = "";
    ContentType APP_CONTENT_TYPE =  ContentType.JSON;
    Long MAX_TIME_OUT = 6000L;
}
