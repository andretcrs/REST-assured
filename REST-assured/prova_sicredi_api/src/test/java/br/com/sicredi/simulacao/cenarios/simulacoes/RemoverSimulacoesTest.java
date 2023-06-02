package br.com.sicredi.simulacao.cenarios.simulacoes;

import br.com.sicredi.simulacao.base.Properties;
import br.com.sicredi.simulacao.utils.GeradorDeDados;
import br.com.sicredi.simulacao.utils.SimulacaoUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RemoverSimulacoesTest implements Properties {
    GeradorDeDados geradorDeDados;
    public static String nomeCompleto = null;
    public static String cpf = null;
    public static String email = null;
    public static int valor = 0;
    public static Integer parcelas = null;
    public static Boolean seguro = true;

    @Test
    public void t01_deveCadastrarSimulacao() {
        try {
            cadastrar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        given()
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
                .statusCode(201);
    }
    @Test
    public void t02_removerSimulacao(){
        Integer SIM_ID = SimulacaoUtils.getIdSimulacaoPeloNome("");
        given()
            .pathParam("id", SIM_ID)
        .when()
            .delete("/api/v1/simulacoes/{id}")
        .then()
            .statusCode(200);
            // Deveria Retornar o HTTP Status 204 se simulação for removida com sucesso - Retorna 200 "ok"
    }
    @Test
    public void t03_naoDeveRemoverSimulacaoIdInvalido(){
        Integer SIM_ID = 123456;
        given()
                .pathParam("id", SIM_ID)
        .when()
            .delete("/api/v1/simulacoes/{id}")
        .then()
            .statusCode(200);
            // Deveria retornar o HTTP Status 404 com a mensagem "Simulação não encontrada" Retorna 200 "ok"

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
