package br.com.sicredi.simulacao.cenarios.simulacoes;

import br.com.sicredi.simulacao.base.Properties;
import br.com.sicredi.simulacao.utils.GeradorDeDados;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AlterarSimulacoesTest implements Properties {
    GeradorDeDados geradorDeDados;
    public static String nomeCompleto = null;
    public static String cpf = null;
    public static String email = null;
    public static int valor = 0;
    public static Integer parcelas = null;
    public static Boolean seguro = true;
    private static String CPF;

    @Test
    public void t01_criarSimulacao() {
        try {
            cadastrar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CPF = given()
                .header("Content-Type", "application/json")
                .body(
                        "{\n" +
                                "  \"nome\": \"" + nomeCompleto + "\",\n" +
                                "  \"cpf\": \"" + cpf + "\",\n" +
                                "  \"email\": \"" + email + "\",\n" +
                                "  \"valor\": " + valor + ",\n" +
                                "  \"parcelas\": " + parcelas + ",\n" +
                                "  \"seguro\": " + seguro + "\n" +
                                "}")
                .when()
                .post("/api/v1/simulacoes")
                .then()
                .statusCode(201)
                .extract().path("cpf");
        System.out.println(CPF);
    }
    @Test
    public void tc02_deveAlterarSimulacaoPeloCPF() {
        try {
            cadastrar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        given()
                .header("Content-Type", "application/json")
                .pathParam("cpf", CPF)
                .body(
                        "{\n" +
                                "  \"nome\": \"" + nomeCompleto +"alterada\",\n" +
                                "  \"cpf\": \"" + cpf + "\",\n" +
                                "  \"email\": \"" + email + "\",\n" +
                                "  \"valor\": " + valor + ",\n" +
                                "  \"parcelas\": " + parcelas + ",\n" +
                                "  \"seguro\": " + seguro + "\n" +
                                "}")
                .when()
                .put("/api/v1/simulacoes/{cpf}")
                .then()
                .statusCode(200);
    }
    public void cadastrar() {
        geradorDeDados = new GeradorDeDados();
        nomeCompleto = geradorDeDados.getNome();
        cpf = geradorDeDados.getCpf();
        email = geradorDeDados.getEmail();
        valor = geradorDeDados.getValor();
        parcelas = geradorDeDados.getParcela();
    }
}
