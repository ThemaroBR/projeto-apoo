package br.edu.facthus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.facthus.exception.CustomException;
import br.edu.facthus.model.Pessoa;

public class InquilinoDAO {
	
private Connection connection = null;
	
	private Connection getConnection() {
		try {
			if (connection == null)
				connection = DriverManager
					.getConnection("jdbc:sqlite:imoveis.db");
			
			return connection;
		} catch (SQLException e) {
			throw new CustomException("Erro abrindo conexão com o banco de dados.");
		}
	}
	
	public void inserePessoa(Pessoa contato) {
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO pessoas (nome, cpf, telefone) "
					+ "VALUES (?, ?)");
			
			statement.setString(1, contato.getNome());
			statement.setString(2, contato.getCpf());
			statement.setString(2, contato.getTelefone());
			
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomException("Ocorreu um erro ao inserir .");
		}
	}
	
	public List<Pessoa> pesquisaPessoas() {
		List<Pessoa> pessoas = new ArrayList<>();
		
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(
					"SELECT id, nome, cpf, telefone FROM pessoas ORDER BY email");
			
			while (rs.next()) {
				Pessoa pessoa = new Pessoa(rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"));
				pessoa.setId(rs.getInt("id"));
				
				pessoas.add(pessoa);
			}
			
			return pessoas; 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CustomException("Ocorreu um erro ao pesquisar .");
		}		
	}
}