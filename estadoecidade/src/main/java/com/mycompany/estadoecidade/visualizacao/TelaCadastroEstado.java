package com.mycompany.estadoecidade.visualizacao;


import com.mycompany.estadoecidade.DAO.EstadoDAO;
import com.mycompany.estadoecidade.model.Estado;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class TelaCadastroEstado extends JFrame {

    private JTextField txtNome, txtUf;
    private JButton btnSalvar, btnDeletar;
    private JList<Estado> listaEstados;
    private DefaultListModel<Estado> modeloLista;
    private EstadoDAO estadoDAO;

    public TelaCadastroEstado() {
        setTitle("Cadastro de Estado");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        estadoDAO = new EstadoDAO();

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 200, 25);
        add(txtNome);

        JLabel lblUf = new JLabel("UF:");
        lblUf.setBounds(20, 60, 80, 25);
        add(lblUf);

        txtUf = new JTextField();
        txtUf.setBounds(100, 60, 50, 25);
        add(txtUf);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(100, 100, 100, 30);
        add(btnSalvar);

        btnDeletar = new JButton("Excluir");
        btnDeletar.setBounds(210, 100, 100, 30);
        add(btnDeletar);

        
        modeloLista = new DefaultListModel<>();
        listaEstados = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaEstados);
        scroll.setBounds(20, 150, 340, 180);
        add(scroll);

        carregarEstados();

        
        btnSalvar.addActionListener(e -> salvarEstado());
        btnDeletar.addActionListener(e -> deletarEstado());

        setVisible(true);
    }

    private void carregarEstados() {
        modeloLista.clear(); 

        List<Estado> estados = estadoDAO.listar();
        for (Estado estado : estados) {
            modeloLista.addElement(estado);
        }
    }

    private void salvarEstado() {
        String nome = txtNome.getText();
        String uf = txtUf.getText();

        if (nome.isEmpty() || uf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        Estado estado = new Estado();
        estado.setNome(nome);
        estado.setUf(uf);

        estadoDAO.add(estado);

        JOptionPane.showMessageDialog(this, "Estado salvo com sucesso!");
        txtNome.setText("");
        txtUf.setText("");
        carregarEstados();
    }

    private void deletarEstado() {
        Estado estadoSelecionado = listaEstados.getSelectedValue();

        if (estadoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um estado para excluir.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir o estado?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            estadoDAO.apagar(estadoSelecionado.getId());
            carregarEstados();
        }
    }
}