package edu.ijse.layered.fx.view;

import edu.ijse.layered.fx.controller.EnrollController;
import edu.ijse.layered.fx.dto.EnrollDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageEnrollmentController {

    final private EnrollController enrollController = new EnrollController();

    @FXML
    private TableColumn<EnrollDto, String> colCourseId;

    @FXML
    private TableColumn<EnrollDto, Integer> colRegNum;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<EnrollDto> detailsTable;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private Label regLabel;

    @FXML
    private TextField regTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize(){
        colRegNum.setCellValueFactory(new PropertyValueFactory<>("regNum"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        loadTable();
    }

    @FXML
    public void loadTable(){
        try {
            detailsTable.getItems().clear();
            detailsTable.getItems().addAll(enrollController.getAllEnroll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        detailsTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                searchEnrollment();
            }
        });
    }

    @FXML
    void saveEnrollment(ActionEvent event) {
        try {
            EnrollDto enrollDto = new EnrollDto(
                    Integer.parseInt(regTxt.getText()),
                    idTxt.getText()
            );

            String rsp = enrollController.addEnroll(enrollDto);
            loadTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rsp);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void updateEnrollment(ActionEvent event) {

        try {
            EnrollDto enrollDto = new EnrollDto(
                    Integer.parseInt(regTxt.getText()),
                    idTxt.getText());

            String rsp = enrollController.updateEnroll(enrollDto);
            loadTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rsp);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void deleteEnrollment(ActionEvent event) {

        try {
            String rsp = enrollController.deleteEnroll(regTxt.getText());
            loadTable();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(rsp);
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    public void searchEnrollment(){

        EnrollDto getSelectedEnrollment = detailsTable.getSelectionModel().getSelectedItem();
        if(getSelectedEnrollment == null){
            new Alert(Alert.AlertType.ERROR,"Please Select Row");
        }

        try{
            EnrollDto enrollDto = enrollController.searchEnroll(String.valueOf(getSelectedEnrollment.getRegNum()));
            regTxt.setText(String.valueOf(enrollDto.getRegNum()));
            idTxt.setText(enrollDto.getCourseId());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }

    }

    public void clear(ActionEvent event) {
        regTxt.setText("");
        idTxt.setText("");
    }
}
