package br.com.sicredi.simulacao.cenarios.restricoes;

import br.com.sicredi.simulacao.base.Properties;

import br.com.sicredi.simulacao.utils.ObjectMapperFactory;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RestricoesTest implements Properties {
    public static String cpfSemRestricoes = "34942140854";
    public static String cpfComRestricoes = "97093236014";
    @ObjectMapperFactory
    @Test
    public void informarCPFComRestricao(){
        given()
            .pathParam("cpf", cpfComRestricoes)
        .when()
            .get("/api/v1/restricoes/{cpf}")
        .then()
            .statusCode(200)
            .body("mensagem", is("O CPF "+cpfComRestricoes+" tem problema"));
    }
    @Test
    public void informarCPFSemRestricao(){
        given()
            .pathParam("cpf", cpfSemRestricoes)
        .when()
            .get("/api/v1/restricoes/{cpf}")
        .then()
            .statusCode(204);
    }

}
