package com.br.pdvpostocombustivel.util;

public class validaCnpj {

    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("\\d{14}")) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int sum = 0;
        int weight = 2;
        for (int i = 11; i >= 0; i--) {
            sum += (cnpj.charAt(i) - '0') * weight;
            weight++;
            if (weight == 10) {
                weight = 2;
            }
        }
        int verificacaoDo1Dig = 11 - (sum % 11);
        if (verificacaoDo1Dig > 9) {
            verificacaoDo1Dig = 0;
        }

        if ((cnpj.charAt(12) - '0') != verificacaoDo1Dig) {
            return false;
        }

        sum = 0;
        weight = 2;
        for (int i = 12; i >= 0; i--) {
            sum += (cnpj.charAt(i) - '0') * weight;
            weight++;
            if (weight == 10) {
                weight = 2;
            }
        }
        int verificacaoDo2Dig = 11 - (sum % 11);
        if (verificacaoDo2Dig > 9) {
            verificacaoDo2Dig = 0;
        }

        return (cnpj.charAt(13) - '0') == verificacaoDo2Dig;
    }
}
