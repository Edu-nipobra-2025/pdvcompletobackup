package com.br.pdvpostocombustivel.util;

public class validaCpf {

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }
        int verificacaoDo1Dig = 11 - (sum % 11);
        if (verificacaoDo1Dig > 9) {
            verificacaoDo1Dig = 0;
        }

        if ((cpf.charAt(9) - '0') != verificacaoDo1Dig) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }
        int verificacaoDo2Dig = 11 - (sum % 11);
        if (verificacaoDo2Dig > 9) {
            verificacaoDo2Dig = 0;
        }

        return (cpf.charAt(10) - '0') == verificacaoDo2Dig;
    }
}