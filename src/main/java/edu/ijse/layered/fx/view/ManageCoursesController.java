package edu.ijse.layered.fx.view;

import edu.ijse.layered.fx.controller.CourseController;
import edu.ijse.layered.fx.dto.CourseDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageCoursesController {

    private CourseController courseController = new CourseController();

    @FXML
    private TableColumn<CourseDto, String> colId;

    @FXML
    private TableColumn<CourseDto, String> colName;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<CourseDto> detailsTabel;

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
    private void initialize(){
        colId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        loadTable();

    }

    @FXML
    public void loadTable(){
        try {
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(courseController.getAllCourse());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        detailsTabel.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                searchCourse();
            }
        });
    }

    @FXML
    void clear(ActionEvent event) {
            idTxt.setText("");
            nameTxt.setText("");
    }

    @FXML
    void saveCourse(ActionEvent event) {

        try {
            CourseDto courseDto = new CourseDto(
                    idTxt.getText(),
                    nameTxt.getText()
            );

            String rsp = courseController.addCourse(courseDto);
            clear(event);
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

    @FXML
    void updateCourse(ActionEvent event) {

        try {
            CourseDto courseDto = new CourseDto(
              idTxt.getText(),
              nameTxt.getText()
            );

            String rsp = courseController.updateCourse(courseDto);
            clear(event);
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

    public void deleteCourse(ActionEvent event) {
        try {
            String rsp = courseController.deleteCourse(idTxt.getText());
            clear(event);
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

    public void searchCourse(){
        CourseDto getSelectedCourse = detailsTabel.getSelectionModel().getSelectedItem();
        if(getSelectedCourse == null){
            new Alert(Alert.AlertType.ERROR,"Please Select Row");
        }

        try{
            CourseDto courseDto = courseController.searchCourse(getSelectedCourse.getCourseId());
            idTxt.setText(courseDto.getCourseId());
            nameTxt.setText(courseDto.getName());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }
}
