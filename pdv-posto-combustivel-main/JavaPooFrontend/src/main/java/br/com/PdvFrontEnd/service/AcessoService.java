package br.com.PdvFrontEnd.service;

import br.com.PdvFrontEnd.dto.AcessoRequest;
import br.com.PdvFrontEnd.dto.AcessoResponse;
import br.com.PdvFrontEnd.model.Acesso;
import br.com.PdvFrontEnd.util.HttpClient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AcessoService {

    public AcessoService() {
    }

    public void addAcesso(Acesso acesso) {
        try {
            AcessoRequest request = new AcessoRequest(
                acesso.getUsuario(),
                acesso.getSenha()
            );

            HttpClient.post("/acessos", request, AcessoResponse.class);
            // Mensagem removida para não aparecer no login automático
        } catch (Exception e) {
            throw new RuntimeException("Erro ao adicionar acesso: " + e.getMessage(), e);
        }
    }

    public List<Acesso> getAllAcessos() {
        try {
            AcessoResponse[] responses = HttpClient.getArray("/acessos", AcessoResponse[].class);
            List<Acesso> acessos = new ArrayList<>();
            for (AcessoResponse response : responses) {
                Acesso acesso = new Acesso(
                    response.getUsuario(),
                    response.getSenha()
                );
                acesso.setId(response.getId()); // ← ADICIONAR ID
                acessos.add(acesso);
            }
            return acessos;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao listar acessos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    public void removeAcesso(Long id) {
        try {
            HttpClient.delete("/acessos/" + id);
            JOptionPane.showMessageDialog(null,
                "Acesso removido com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao remover acesso: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateAcesso(Long id, Acesso acesso) {
        try {
            AcessoRequest request = new AcessoRequest(
                acesso.getUsuario(),
                acesso.getSenha()
            );

            HttpClient.put("/acessos/" + id, request, AcessoResponse.class);
            JOptionPane.showMessageDialog(null,
                "Acesso atualizado com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Erro ao atualizar acesso: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Verifica se já existe um acesso com o usuário especificado
     */
    public boolean usuarioJaExiste(String usuario) {
        try {
            List<Acesso> acessos = getAllAcessos();
            return acessos.stream()
                .anyMatch(acesso -> acesso.getUsuario().equals(usuario));
        } catch (Exception e) {
            System.err.println("Erro ao verificar usuário: " + e.getMessage());
            return false;
        }
    }
}
