package edu.ijse.layered.fx.view;

import edu.ijse.layered.fx.controller.StudentsController;
import edu.ijse.layered.fx.dto.StudentDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageStudentsController {

    private StudentsController studentsController = new StudentsController();

    @FXML
    private TableColumn<StudentDto, String> colContact;

    @FXML
    private TableColumn<StudentDto, Integer> colId;

    @FXML
    private TableColumn<StudentDto, String> colName;

    @FXML
    private Label contactLabel;

    @FXML
    private TextField contactTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<StudentDto> detailsTabel;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("regNum"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactDetails"));
        loadTable();
    }

    @FXML
    private void loadTable() {
        try {
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(studentsController.getAllStudent());
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }

        detailsTabel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                searchStudent();
            }
        });
    }

    @FXML
    void clear(ActionEvent event) {
        idTxt.clear();
        nameTxt.clear();
        contactTxt.clear();
    }

    @FXML
    void deleteStudents(ActionEvent event) {
        try {
            String rsp = studentsController.deleteStudent(idTxt.getText());
            clear(event);
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, rsp).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    @FXML
    void saveStudents(ActionEvent event) {
        try {
            StudentDto studentDto = new StudentDto(
                    Integer.parseInt(idTxt.getText()),
                    nameTxt.getText(),
                    contactTxt.getText()
            );

            String rsp = studentsController.addStudent(studentDto);
            clear(event);
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, rsp).showAndWait();

        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    @FXML
    void updateStudents(ActionEvent event) {
        try {
            StudentDto studentDto = new StudentDto(
                    Integer.parseInt(idTxt.getText()),
                    nameTxt.getText(),
                    contactTxt.getText()
            );

            String rsp = studentsController.updateStudents(studentDto);
            clear(event);
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, rsp).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void searchStudent() {
        StudentDto getSelectedStudent = detailsTabel.getSelectionModel().getSelectedItem();
        if (getSelectedStudent == null) {
            new Alert(Alert.AlertType.WARNING, "Select Row").showAndWait();
            return;
        }

        try {
            StudentDto studentDto = studentsController.searchStudent(String.valueOf(getSelectedStudent.getRegNum()));
            idTxt.setText(String.valueOf(studentDto.getRegNum()));
            nameTxt.setText(studentDto.getName());
            contactTxt.setText(studentDto.getContactDetails());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }
}
