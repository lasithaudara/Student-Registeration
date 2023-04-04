package application;

import java.sql.Date;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Controller {
	
	@FXML
	private TableView<Users> table_view;
	
    @FXML
    private DatePicker birth_date;

    @FXML
    private TextField phone_number;

    @FXML
    private TableColumn<Users, Date> BirthDate;

    @FXML
    private TableColumn<Users, String> studentName;

    @FXML
    private TableColumn<Users, Integer> studentPhoneNumber;

    @FXML
    private TextField student_name;

    @FXML
    private TableColumn<Users, String> subjects;

    @FXML
    private TextField txt_subject;
    

}
