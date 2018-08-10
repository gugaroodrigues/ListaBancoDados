/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import bean.AlunosBean;
import conexao.ConexaoFactory;
import dao.AlunosDAO;
import intefaces.BaseInterfaceListaBanco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Gus
 */
public class AlunosCadastro implements BaseInterfaceListaBanco {

    private JFrame telaAlunos;
    private JLabel jLabelNomeAluno, jLabelCodigoMatricula, jLabelNota1,
            jLabelNota2, jLabelNota3, jLabelFrequenciaAluno;
    private JTextField jTextFieldNomeAluno, jTextFieldNota1,
            jTextFieldNota2, jTextFieldNota3, jTextFieldFrequenciaAluno;
    private JTable jTableAlunos;
    private DefaultTableModel dtm;
    private JScrollPane jScrollPaneAlunos;
    private JButton jButtonCadastrar, jButtonAlterar, jButtonExcluir;
    JFormattedTextField jFormattedTextFieldMatricula;
    private ArrayList<AlunosBean> dados = new ArrayList<>();

    public AlunosCadastro() {
        gerarTela();
        ConfigurandoLookAndFeel();
        instanciarComponentes();
        adicionarComponentes();
        gerarLocalizacoes();
        gerarDimensoes();
        configurarJFormattedTextField();
        configurarJTable();
        acaoCadastrar();
        popularTabela();

        telaAlunos.setVisible(true);

    }

    @Override
    public void gerarTela() {
        telaAlunos = new JFrame("ENTRA - 21 ");
        telaAlunos.setLayout(null);
        telaAlunos.setLocationRelativeTo(null);
        //telaAlunos.setResizable(false);
        telaAlunos.setSize(455, 400);
        telaAlunos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void instanciarComponentes() {
        jLabelNomeAluno = new JLabel("Nome");
        jLabelCodigoMatricula = new JLabel("C° da matricula");
        jLabelNota1 = new JLabel("Nota 1");
        jLabelNota2 = new JLabel("Nota 2");
        jLabelNota3 = new JLabel("Nota 3");
        jLabelFrequenciaAluno = new JLabel("Faltas");

        jTextFieldNomeAluno = new JTextField();
        jFormattedTextFieldMatricula = new JFormattedTextField();
        jTextFieldNota1 = new JTextField();
        jTextFieldNota2 = new JTextField();
        jTextFieldNota3 = new JTextField();
        jTextFieldFrequenciaAluno = new JTextField();

        jButtonCadastrar = new JButton("SALVAR");
        jButtonAlterar = new JButton("EDITAR");
        jButtonExcluir = new JButton("EXCLUIR");

        jTableAlunos = new JTable();
        jScrollPaneAlunos = new JScrollPane(jTableAlunos);

    }

    @Override
    public void adicionarComponentes() {
        telaAlunos.add(jLabelNomeAluno);
        telaAlunos.add(jLabelCodigoMatricula);
        telaAlunos.add(jLabelNota1);
        telaAlunos.add(jLabelNota2);
        telaAlunos.add(jLabelNota3);
        telaAlunos.add(jLabelFrequenciaAluno);

        telaAlunos.add(jTextFieldNomeAluno);
        telaAlunos.add(jFormattedTextFieldMatricula);
        telaAlunos.add(jTextFieldNota1);
        telaAlunos.add(jTextFieldNota2);
        telaAlunos.add(jTextFieldNota3);
        telaAlunos.add(jTextFieldFrequenciaAluno);

        telaAlunos.add(jButtonCadastrar);
        telaAlunos.add(jButtonAlterar);
        telaAlunos.add(jButtonExcluir);

        telaAlunos.add(jScrollPaneAlunos);
        //telaAlunos.add(jTableAlunos);   

    }

    @Override
    public void gerarLocalizacoes() {
        jLabelNomeAluno.setLocation(6, 5);
        jLabelCodigoMatricula.setLocation(6, 60);
        jLabelNota1.setLocation(6, 105);
        jLabelNota2.setLocation(146, 105);
        jLabelNota3.setLocation(286, 105);
        jLabelFrequenciaAluno.setLocation(220, 60);

        jTextFieldNomeAluno.setLocation(5, 30);
        jFormattedTextFieldMatricula.setLocation(5, 80);
        jTextFieldNota1.setLocation(5, 130);
        jTextFieldNota2.setLocation(145, 130);
        jTextFieldNota3.setLocation(285, 130);
        jTextFieldFrequenciaAluno.setLocation(220, 80);

        jButtonCadastrar.setLocation(5, 320);
        jButtonAlterar.setLocation(160, 320);
        jButtonExcluir.setLocation(315, 320);

        jScrollPaneAlunos.setLocation(5, 170);

    }

    @Override
    public void gerarDimensoes() {
        jLabelNomeAluno.setSize(100, 20);
        jLabelCodigoMatricula.setSize(100, 20);
        jLabelNota1.setSize(100, 20);
        jLabelNota2.setSize(100, 20);
        jLabelNota3.setSize(100, 20);
        jLabelFrequenciaAluno.setSize(100, 20);

        jTextFieldNomeAluno.setSize(410, 25);
        jFormattedTextFieldMatricula.setSize(170, 25);
        jTextFieldNota1.setSize(130, 25);
        jTextFieldNota2.setSize(130, 25);
        jTextFieldNota3.setSize(130, 25);
        jTextFieldFrequenciaAluno.setSize(195, 25);

        jButtonCadastrar.setSize(120, 30);
        jButtonAlterar.setSize(120, 30);
        jButtonExcluir.setSize(120, 30);

        jScrollPaneAlunos.setSize(430, 150);

    }

    private void popularTabela() {
        AlunosDAO alunosDAO = new AlunosDAO();
        List<AlunosBean> alunos = alunosDAO.obterAlunos();

        //foreach
        for (AlunosBean aluno : alunos) {
            dtm.addRow(new Object[]{
                aluno.getNome(),
                aluno.getMatricula(),
                aluno.getNota1(),
                aluno.getNota2(),
                aluno.getNota3(),
                aluno.getFrequencia()

            });
        }
    }

    private void configurarJTable() {
        dtm = new DefaultTableModel();
        dtm.addColumn("Nome");
        dtm.addColumn("Matricula");
        dtm.addColumn("nota 1");
        dtm.addColumn("nota 2");
        dtm.addColumn("nota 3");
        dtm.addColumn("Medía");
        dtm.addColumn("Fequencia");
        dtm.addColumn("Situação");
        jTableAlunos.setModel(dtm);
    }

    @Override
    public void acaoCadastrar() {
        jButtonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (jTextFieldNomeAluno.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Favor digitar o nome"
                            + " do aluno");
                    jTextFieldNomeAluno.requestFocus();
                }
                if (jFormattedTextFieldMatricula.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite o codigo de matricula"
                            + " corretamente");
                    jFormattedTextFieldMatricula.requestFocus();
                }

                if (jTextFieldFrequenciaAluno.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Favor digitar as faltas"
                            + " do aluno");
                    jTextFieldNomeAluno.requestFocus();
                }
                if (jTextFieldNota1.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Favor digitar a nota 1°"
                            + " do aluno");
                    jTextFieldNomeAluno.requestFocus();
                }
                if (jTextFieldNota2.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Favor digitar a nota 2°"
                            + " do aluno");
                    jTextFieldNomeAluno.requestFocus();
                }
                if (jTextFieldNota3.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Favor digitar a nota 3°"
                            + " do aluno");
                    jTextFieldNomeAluno.requestFocus();
                }
                validacaoNotas();

                float nota1 = Float.parseFloat(jTextFieldNota1.getText().trim());
                float nota2 = Float.parseFloat(jTextFieldNota2.getText().trim());
                float nota3 = Float.parseFloat(jTextFieldNota3.getText().trim());
                Byte frequenciaAluno
                        = Byte.parseByte(jTextFieldFrequenciaAluno.getText());
                float mediaAluno = (nota1 + nota2 + nota3) / 3;

                AlunosBean aluno = new AlunosBean();
                aluno.setNome(jTextFieldNomeAluno.getText());
                aluno.setMatricula(jFormattedTextFieldMatricula.getText());
                aluno.setNota1(nota1);
                aluno.setNota2(nota2);
                aluno.setNota3(nota3);
                aluno.setFrequencia(frequenciaAluno);
                aluno.setMedia(mediaAluno);  
                new AlunosDAO().adicionar(aluno);
                JOptionPane.showMessageDialog(null, "O(a) aluno(a) foi cadastrado"
                        + " Sua média é: " + mediaAluno);

            }
        });
    }

    public void validacaoNotas() {
        try {
            float nota1 = Float.parseFloat(jTextFieldNota1.getText().trim());
            float nota2 = Float.parseFloat(jTextFieldNota2.getText().trim());
            float nota3 = Float.parseFloat(jTextFieldNota3.getText().trim());
            if (nota1 < 0 || nota1 > 10) {
                JOptionPane.showMessageDialog(null, "Nota 1° invalida");
            }
            if (nota2 < 0 || nota2 > 10) {
                JOptionPane.showMessageDialog(null, "Nota 2° invalida");
            }
            if (nota3 < 0 || nota3 > 10) {
                JOptionPane.showMessageDialog(null, "Nota 3° invalida");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite apenas numeros");

            return;
        }
    }

    @Override
    public void acaoAlterar() {
    }

    @Override
    public void acaoExcluir() {
    }

    @Override
    public void acaoListarAlunos() {
    }

    @Override
    public void acaoGerarMedia() {
    }

    public void ConfigurandoLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {

        } catch (ClassNotFoundException e) {

        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        }
    }

    private void configurarJFormattedTextField() {
        try {
            MaskFormatter maskFormatter = new MaskFormatter("##.###");

            maskFormatter.install(jFormattedTextFieldMatricula);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Chame o Pastor");
        }
    }

}
