package com.mycompany.estadoecidade.visualizacao;

import com.mycompany.estadoecidade.DAO.CidadeDAO;
import com.mycompany.estadoecidade.DAO.EstadoDAO;
import com.mycompany.estadoecidade.model.Cidade;
import com.mycompany.estadoecidade.model.Estado;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class TelaCadastroCidade extends JFrame {

    private JTextField txtNome;
    private JComboBox<Estado> comboEstado;
    private JButton btnSalvar, btnDeletar;
    private JList<Cidade> listaCidades;
    private DefaultListModel<Cidade> modeloLista;
    private CidadeDAO cidadeDAO;

    public TelaCadastroCidade() {
        setTitle("Cadastro de Cidade");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        cidadeDAO = new CidadeDAO();

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 200, 25);
        add(txtNome);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(20, 60, 80, 25);
        add(lblEstado);

        comboEstado = new JComboBox<>();
        comboEstado.setBounds(100, 60, 200, 25);
        add(comboEstado);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(100, 100, 100, 30);
        add(btnSalvar);

        btnDeletar = new JButton("Excluir");
        btnDeletar.setBounds(210, 100, 100, 30);
        add(btnDeletar);

        // Lista de cidades
        modeloLista = new DefaultListModel<>();
        listaCidades = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaCidades);
        scroll.setBounds(20, 150, 340, 180);
        add(scroll);

        carregarEstados();
        carregarCidades();

        // Eventos
        btnSalvar.addActionListener(e -> salvarCidade());
        btnDeletar.addActionListener(e -> deletarCidade());

        setVisible(true);
    }

    private void carregarEstados() {
        EstadoDAO estadoDAO = new EstadoDAO();
        List<Estado> estados = estadoDAO.listar();
        comboEstado.removeAllItems(); 

        for (Estado estado : estados) {
            comboEstado.addItem(estado);
        }
    }

    private void carregarCidades() {
        modeloLista.clear(); 

        List<Cidade> cidades = cidadeDAO.listar();
        for (Cidade cidade : cidades) {
            modeloLista.addElement(cidade);
        }
    }

    private void salvarCidade() {
        String nomeCidade = txtNome.getText();
        Estado estadoSelecionado = (Estado) comboEstado.getSelectedItem();

        if (nomeCidade.isEmpty() || estadoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        Cidade cidade = new Cidade();
        cidade.setNome(nomeCidade);
        cidade.setEstado(estadoSelecionado);

        cidadeDAO.add(cidade);

        JOptionPane.showMessageDialog(this, "Cidade salva com sucesso!");
        txtNome.setText("");
        carregarCidades();
    }

    private void deletarCidade() {
        Cidade cidadeSelecionada = listaCidades.getSelectedValue();

        if (cidadeSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma cidade para excluir.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir a cidade?", "Confirmação", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            cidadeDAO.apagar(cidadeSelecionada.getId());
            carregarCidades();
        }
    }
}