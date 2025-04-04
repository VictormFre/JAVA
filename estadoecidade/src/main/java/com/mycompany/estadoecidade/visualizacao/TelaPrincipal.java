package com.mycompany.estadoecidade.visualizacao;

import javax.swing.*;
import java.awt.event.*;
import org.flywaydb.core.Flyway;

public class TelaPrincipal extends JFrame {

    private JButton btnCadastroEstado;
    private JButton btnCadastroCidade;

    public TelaPrincipal() {
        setTitle("Sistema de Cadastro");
        setSize(300, 200);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        btnCadastroEstado = new JButton("Cadastrar Estado");
        btnCadastroEstado.setBounds(45, 30, 200, 30);
        add(btnCadastroEstado);

        btnCadastroCidade = new JButton("Cadastrar Cidade");
        btnCadastroCidade.setBounds(45, 80, 200, 30);
        add(btnCadastroCidade);

        
        btnCadastroEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaCadastroEstado(); 
            }
        });

        btnCadastroCidade.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TelaCadastroCidade(); 
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:~/cidadeestado", "sa", "")
                .load();
        flyway.migrate();
        
        new TelaPrincipal();
    }
}