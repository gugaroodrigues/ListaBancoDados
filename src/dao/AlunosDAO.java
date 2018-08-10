/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import bean.AlunosBean;
import conexao.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Gus
 */
public class AlunosDAO {

    public int adicionar(AlunosBean aluno) {
        Connection conexao = ConexaoFactory.obterConexao();

        if (conexao != null) {
            String sql = "INSERT INTO Alunos"
                    + "\n(nome, matricula, nota1, nota2, nota3, fequencia)"
                    + "\nVALUES(?,?,?,?,?,?)";

            try {
                PreparedStatement preparedStatement
                        = conexao.prepareStatement(sql,
                                PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, aluno.getNome());
                preparedStatement.setString(2, aluno.getMatricula());
                preparedStatement.setFloat(3, aluno.getNota1());
                preparedStatement.setFloat(4, aluno.getNota2());
                preparedStatement.setFloat(5, aluno.getNota3());
                preparedStatement.setInt(6, aluno.getFrequencia());
                preparedStatement.execute();

                ResultSet resultSet
                        = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    return resultSet.getInt(1);

                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                ConexaoFactory.fecharConexao();
            }
        }
        return 0;

    }

    public boolean alterar(AlunosBean aluno) {
        Connection conexao = ConexaoFactory.obterConexao();
        String sql = "UPDATE alunos SET nome = ?, matricula = ?, nota1 = ?, "
                + "\nnota2 = ?, nota3 = ?, frequencia = ? "
                + "\nWHERE id = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getMatricula());
            ps.setFloat(3, aluno.getNota1());
            ps.setFloat(4, aluno.getNota2());
            ps.setFloat(5, aluno.getNota3());
            ps.setInt(6, aluno.getFrequencia());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConexaoFactory.fecharConexao();
        }

        return false;
    }
    
    public boolean apagar(int id){
        String sql = "DELETE FROM clientes WHERE id = ?";
    Connection conexao = ConexaoFactory.obterConexao();
    if (conexao != null) {
      try {
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() == 1;
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        ConexaoFactory.fecharConexao();
      }
    }
        return false;
    }
 
    
    public List<AlunosBean> obterAlunos() {
    List<AlunosBean> alunos = new ArrayList<>();
    Connection conexao = ConexaoFactory.obterConexao();
    if (conexao != null) {
      String sql = "SELECT id, nome, matricula, nota1, nota2, nota3,"
              + "\nfrequencia  FROM clientes;";
      try {
        Statement statement = conexao.createStatement();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
          AlunosBean aluno = new AlunosBean();
          aluno.setId(resultSet.getInt("id"));
          aluno.setNome(resultSet.getString("nome"));
          aluno.setMatricula(resultSet.getString("matricula"));
          aluno.setNota1(resultSet.getFloat("nota1"));
          aluno.setNota2(resultSet.getFloat("nota2"));
          aluno.setNota3(resultSet.getFloat("nota3"));
          aluno.setFrequencia(resultSet.getByte("frequencia"));
          alunos.add(aluno);
        }
        
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        ConexaoFactory.fecharConexao();
      }
    }
    return alunos;
  }
}


