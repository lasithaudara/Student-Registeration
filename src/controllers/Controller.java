package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.User;
import utility.DatabaseUtility;

public class Controller implements Initializable {

	PreparedStatement pst;
	DatabaseUtility connectNow = new DatabaseUtility();
	Connection connectDB = connectNow.getConnection();

	@FXML
	private Button addBtn;

	@FXML
	private TextField birth_date;

	@FXML
	private TextField txt_id;

	@FXML
	private TableColumn<User, String> dateColumn;

	@FXML
	private Button deleteBtn;

	@FXML
	private TableColumn<User, String> nameColumn;

	@FXML
	private TableColumn<User, String> phoneColumn;

	@FXML
	private TableColumn<User, String> idColumn;

	@FXML
	private TextField phone_number;

	@FXML
	private TableColumn<User, String> subjectColumn;

	@FXML
	private TableView<User> table_view;

	@FXML
	private TextField txt_name;

	@FXML
	private TextField txt_subject;

	@FXML
	private Button updateBtn;

	@FXML
	void add(ActionEvent event) {

		String studentName, subjects, phoneNumber, birthDate, studentId;

		studentId = txt_id.getText();
		studentName = txt_name.getText();
		birthDate = birth_date.getText();
		subjects = txt_subject.getText();
		phoneNumber = phone_number.getText();

		String addQuery = "INSERT INTO student_details (student_id, student_name, birth_date, subjects, phone_number) VALUES(?, ?, ?, ?, ?)";

		try {
			pst = connectDB.prepareStatement(addQuery);
			pst.setString(1, studentId);
			pst.setString(2, studentName);
			pst.setString(3, birthDate);
			pst.setString(4, subjects);
			pst.setString(5, phoneNumber);
			pst.executeUpdate();

			table_view();

			txt_id.setText("");
			txt_name.setText("");
			birth_date.setText("");
			txt_subject.setText("");
			txt_name.requestFocus();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Student Registered");
	}

	@FXML
	void update(ActionEvent event) {
		String studentId = txt_id.getText();
		String studentName = txt_name.getText();
		String birthDate = birth_date.getText();
		String subjects = txt_subject.getText();
		String phoneNumber = phone_number.getText();

		try {
			pst = connectDB.prepareStatement(
					"UPDATE student_details SET student_name=?, birth_date=?, subjects=?, phone_number=? WHERE student_id=?");
			pst.setString(1, studentName);
			pst.setString(2, birthDate);
			pst.setString(3, subjects);
			pst.setString(4, phoneNumber);
			pst.setString(5, studentId);
			pst.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
		}

		JOptionPane.showMessageDialog(null, "Student Record Updated");

		table_view();
	}

	@FXML
	void delete(ActionEvent event) {
		String studentId = txt_id.getText();

		try {
			pst = connectDB.prepareStatement("DELETE FROM student_details WHERE student_id = ?");
			pst.setString(1, studentId);
			pst.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
		}

		JOptionPane.showMessageDialog(null, "Student Record Deleted");

		table_view();
	}

	public void table_view() {
		ObservableList<User> users = FXCollections.observableArrayList();

		try {
			pst = connectDB.prepareStatement(
					"SELECT student_id, student_name, birth_date, subjects, phone_number FROM student_details");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setStudentID(rs.getString("student_id"));
				user.setStudentName(rs.getString("student_name"));
				user.setBirthDate(rs.getString("birth_date"));
				user.setSubject(rs.getString("subjects"));
				user.setPhoneNumber(rs.getString("phone_number"));
				users.add(user);
			}

			table_view.setItems(users);
			idColumn.setCellValueFactory(f -> f.getValue().studentIDProperty());
			nameColumn.setCellValueFactory(f -> f.getValue().studentNameProperty());
			dateColumn.setCellValueFactory(f -> f.getValue().birthDateProperty());
			subjectColumn.setCellValueFactory(f -> f.getValue().subjectProperty());
			phoneColumn.setCellValueFactory(f -> f.getValue().phoneNumberProperty());

		} catch (SQLException e) {
			Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
		}

		table_view.setRowFactory(tv -> {
			TableRow<User> myRow = new TableRow<>();
			myRow.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
					int myIndex = table_view.getSelectionModel().getSelectedIndex();
					txt_id.setText(table_view.getItems().get(myIndex).getStudentID());
					txt_name.setText(table_view.getItems().get(myIndex).getStudentName());
					birth_date.setText(table_view.getItems().get(myIndex).getBirthDate());
					txt_subject.setText(table_view.getItems().get(myIndex).getSubject());
					phone_number.setText(table_view.getItems().get(myIndex).getPhoneNumber());
				}
			});
			return myRow;
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		table_view();
	}

}