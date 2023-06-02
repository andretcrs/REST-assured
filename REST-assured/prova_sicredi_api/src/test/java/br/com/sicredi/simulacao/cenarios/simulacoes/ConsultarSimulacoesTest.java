package br.com.sicredi.simulacao.cenarios.simulacoes;

import br.com.sicredi.simulacao.base.Properties;
import br.com.sicredi.simulacao.utils.SimulacaoUtils;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ConsultarSimulacoesTest implements Properties {
    public static String cpfSemSimulcaoCadastrada = "01124056321";

    @Test
    public void naoDeveEncontrarSimulacaoCpfInvalido(){
        given()
            .pathParam("cpf", cpfSemSimulcaoCadastrada)
        .when()
            .get("/api/v1/simulacoes/{cpf}")
        .then()
            .statusCode(404)
            .body("mensagem", is("CPF 01124056321 não encontrado"));

    }
    @Test
    public void deveEncontrarSimulacaoPorID(){
        Integer SIM_ID = SimulacaoUtils.getIdSimulacaoPeloNome("");
        given()
            .pathParam("id", SIM_ID  )
        .when()
            .get("/api/v1/simulacoes/?{id}")
        .then()
            .statusCode(200);
    }
}
