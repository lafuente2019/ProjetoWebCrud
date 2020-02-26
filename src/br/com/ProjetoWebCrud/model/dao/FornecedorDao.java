package br.com.ProjetoWebCrud.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ProjetoWebCrud.model.domain.Fornecedor;
import util.ValidacaoException;

public class FornecedorDao {
   
	public List<Fornecedor> getFornecedores() throws SQLException, ClassNotFoundException{
		    Connection conexao = ConexaoJDBC.getConexao();
			PreparedStatement ps = conexao.prepareStatement(
			"SELECT CD_FORNECEDOR,NM_FORNECEDOR,DS_EMAIL,DS_RAZAO_SOCIAL,DS_CNPJ FROM TB_FORNECEDOR");
		    ResultSet rs = ps.executeQuery();
		    List<Fornecedor>fornecedores = new ArrayList<>();
		    while(rs.next()) {
		    	fornecedores.add(new Fornecedor(rs.getInt(1),rs.getString(2),
		        rs.getString(3),rs.getString(4), rs.getString(5)));
		    }
		    return fornecedores;
		}

	public void salvar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException {
		 Connection conexao = ConexaoJDBC.getConexao();
		 PreparedStatement statement = conexao.prepareStatement(
				"INSERT INTO TB_FORNECEDOR(NM_FORNECEDOR,DS_EMAIL,DS_RAZAO_SOCIAL,DS_CNPJ) VALUES(?,?,?,?)" );
		 statement.setString(1, fornecedor.getNome());
		 statement.setString(2, fornecedor.getEmail());
		 statement.setString(3, fornecedor.getRazaoSocial());
		 statement.setString(4, fornecedor.getCnpj());
		 statement.execute();
	}

	public void excluir(Integer codFornecedor) throws ClassNotFoundException, SQLException {
		 Connection conexao = ConexaoJDBC.getConexao();
		 PreparedStatement statement = conexao.prepareStatement(
				"DELETE FROM TB_FORNECEDOR WHERE CD_FORNECEDOR = ?");
		 statement.setInt(1, codFornecedor);
		 statement.execute();
	}

	public Fornecedor getFornecedorId(Integer codFornecedor) throws ClassNotFoundException, SQLException, ValidacaoException {
		Connection conexao = ConexaoJDBC.getConexao();
		PreparedStatement ps = conexao.prepareStatement(
		"SELECT CD_FORNECEDOR,NM_FORNECEDOR,DS_EMAIL,DS_RAZAO_SOCIAL,DS_CNPJ FROM TB_FORNECEDOR WHERE CD_FORNECEDOR = ?");
		ps.setInt(1, codFornecedor);
	    ResultSet rs = ps.executeQuery();
	    if(rs.next()) {
	    	return new Fornecedor(rs.getInt(1),rs.getString(2), rs.getString(3),
	    			rs.getString(4), rs.getString(5));
	    }
	    throw new ValidacaoException(" Não achou fornecedor para o codigo " + codFornecedor);
	   
	}

	public void atualizar(Fornecedor fornecedor) throws ClassNotFoundException, SQLException, ValidacaoException  {
		Connection conexao = ConexaoJDBC.getConexao();
		 PreparedStatement statement = conexao.prepareStatement(
				"UPDATE TB_FORNECEDOR SET NM_FORNECEDOR = ?,DS_EMAIL = ?,DS_RAZAO_SOCIAL = ?,DS_CNPJ = ?  WHERE CD_FORNECEDOR = ?" );
		 statement.setString(1, fornecedor.getNome());
		 statement.setString(2, fornecedor.getEmail());
		 statement.setString(3, fornecedor.getRazaoSocial());
		 statement.setString(4, fornecedor.getCnpj());
		 statement.setInt(5, fornecedor.getCodigo());
		 statement.execute();
		
	}
}
