package br.com.sicredi.simulacao.utils;

import com.github.javafaker.Faker;

public class GeradorDeDados {

    private final Faker faker = new Faker();
    private final String CPF = geraCPF();
    private final String nome = faker.name().firstName().replace("'", " ");
    private final String email = nome + "@email.com";
    private final Integer parcela = faker.number().numberBetween(2, 48);
    private final int valor = faker.number().numberBetween(1000,40000);

    /* ------------------------------------------------------------------------------------------- */
    /* Geração de CPF */
    private static String calcDigVerif(String num) {
        int primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) { soma += Integer.parseInt(num.substring(i, i + 1)) * peso--; }

        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = 0; }
        else {
            primDig = 11 - (soma % 11); }

        soma = 0;peso = 11;

        for (int i = 0; i < num.length(); i++) { soma += Integer.parseInt(num.substring(i, i + 1)) * peso--; }
        soma += primDig * 2;

        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = 0; }
        else {
            segDig = 11 - (soma % 11); }

        return Integer.toString(primDig) + segDig;
    }

    public static String geraCPF() {
        String iniciais = "";
        int numero;
        numero = (int) (Math.random() * 10);
        iniciais += Integer.toString(numero);
        numero = (int) (Math.random() * 10);
        iniciais += Integer.toString(numero);
        numero = (int) (Math.random() * 10);
        iniciais += Integer.toString(numero);
        numero = (int) (Math.random() * 10);
        iniciais += Integer.toString(numero);
        numero = (int) (Math.random() * 10);
        iniciais += Integer.toString(numero);
        numero = (int) (Math.random() * 10);
        iniciais += Integer.toString(numero);
        numero = (int) (Math.random() * 10);
        iniciais += Integer.toString(numero);
        numero = (int) (Math.random() * 10);
        iniciais += Integer.toString(numero);
        numero = (int) (Math.random() * 10);
        iniciais += Integer.toString(numero);
        return iniciais + calcDigVerif(iniciais);
    }


    /* ------------------------------------------------------------------------------------------- */
    /* Construtores */
    public String getCpf() { return CPF; }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public int getValor() { return valor; }

    public Integer getParcela() { return parcela; }
}