package br.com.sicredi.simulacao.utils;

import io.restassured.RestAssured;

public class SimulacaoUtils {
    public static Integer getIdSimulacaoPeloNome(String nome) {
        return RestAssured.get("/api/v1/simulacoes?nome="+nome).then().extract().path("id[0]");
    }
}
