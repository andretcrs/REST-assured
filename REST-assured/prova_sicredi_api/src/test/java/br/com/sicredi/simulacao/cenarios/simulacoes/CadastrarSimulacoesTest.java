package br.com.sicredi.simulacao.cenarios.simulacoes;

import br.com.sicredi.simulacao.base.Properties;
import br.com.sicredi.simulacao.utils.GeradorDeDados;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class CadastrarSimulacoesTest implements Properties {

    GeradorDeDados geradorDeDados;
    public static String nomeCompleto = null;
    public static String cpf = null;
    public static String email = null;
    public static int valor = 0;
    public static Integer parcelas = null;
    public static Boolean seguro = true;

    @Test
    public void deveCadastrarSimulacaoComDadosCorretos() {
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
    public void naoDeveAceitarSimulacaoSemNome() {
        try {
            cadastrar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        given()
            .header("Content-Type", "application/json")
            .body(
                "{\n" +
                        "  \"cpf\": \"" + cpf + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}")
        .when()
            .post("/api/v1/simulacoes")
        .then()
            .statusCode(400)
            .body("erros.nome", is("Nome não pode ser vazio"));

    }
    @Test
    public void naoDeveCadastrarSimulacaoSemInformarCpf() {
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
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}")
        .when()
            .post("/api/v1/simulacoes")
        .then()
            .statusCode(400)
            .body("erros.cpf", is("CPF não pode ser vazio"));
    }
    @Test
    public void naoDeveCadastrarSimulacaoSemInformarEmail() {
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
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}")
        .when()
            .post("/api/v1/simulacoes")
        .then()
            .statusCode(400)
            .body("erros.email", is("E-mail não deve ser vazio"));
    }
    @Test
    public void naoDeveCadastrarSimulacaoSemInformarValor() {
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
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}")
        .when()
            .post("/api/v1/simulacoes")
        .then()
            .statusCode(400)
            .body("erros.valor", is("Valor não pode ser vazio"));
    }
    @Test
    public void naoDeveCadastrarSimulacaoSemInformarParcelas() {
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
                        "  \"seguro\": " + seguro + "\n" +
                        "}")
        .when()
            .post("/api/v1/simulacoes")
        .then()
            .statusCode(400)
            .body("erros.parcelas", is("Parcelas não pode ser vazio"));
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
