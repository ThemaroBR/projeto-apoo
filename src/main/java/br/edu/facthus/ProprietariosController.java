package br.edu.facthus;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import br.edu.facthus.model.Pessoa;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProprietariosController implements Initializable {
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtCPF;
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private TableView<Pessoa> tblPessoa;
	
	@FXML
	private TableColumn<Pessoa, String> colNome;
	
	@FXML
	private TableColumn<Pessoa, String> colCPF;
	
	@FXML
	private TableColumn<Pessoa, String> colTelefone;
	
	private ArrayList<Pessoa> pessoa = new ArrayList<>();
	
	@FXML
	private void cadastra() {
		Pessoa p = new Pessoa(txtNome.getText(),
				txtCPF.getText(), txtTelefone.getText());
		pessoa.add(p);
		tblPessoa.setItems(FXCollections.observableList(pessoa));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colNome.setCellValueFactory(
				new PropertyValueFactory<Pessoa, String>("nome"));
		colCPF.setCellValueFactory(
				new PropertyValueFactory<Pessoa, String>("CPF"));
		colTelefone.setCellValueFactory(
				new PropertyValueFactory<Pessoa, String>("Telefone"));
		
		
		pessoa.add(new Pessoa("Mário", "mario@gmail.com", "98758475"));
		pessoa.add(new Pessoa("Cleiton", "cleiton@gmail.com", "99325847"));
		pessoa.add(new Pessoa("Giulia", "giulia@gmail.com", "91065714"));
		
		tblPessoa.setItems(FXCollections.observableList(pessoa));
	}
}