package edu.ijse.layered.fx.view;

import edu.ijse.layered.fx.controller.LectureController;
import edu.ijse.layered.fx.dto.LecturerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ManageLecturesController {

    private final LectureController lectureController = new LectureController();

    @FXML
    private TableColumn<LecturerDto, String> contactColmn;

    @FXML
    private Label contactLabel;

    @FXML
    private TextField contactTxt;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<LecturerDto> detailsTabel;

    @FXML
    private TableColumn<LecturerDto, String> idColmn;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idTxt;

    @FXML
    private TableColumn<LecturerDto, String> nameColmn;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTxt;

    @FXML
    private Button resetBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TableColumn<LecturerDto, String> subjectsColmn;

    @FXML
    private Label subjectsLabel;

    @FXML
    private TextArea subjectsTxt;

    @FXML
    private Button updateBtn;

    @FXML
    private void initialize() {
        idColmn.setCellValueFactory(new PropertyValueFactory<>("lectureId"));
        nameColmn.setCellValueFactory(new PropertyValueFactory<>("name"));
        contactColmn.setCellValueFactory(new PropertyValueFactory<>("contactDetails"));

        loadTable();

        detailsTabel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selectLecture();
            }
        });
    }

    @FXML
    public void loadTable() {
        try {
            detailsTabel.getItems().clear();
            detailsTabel.getItems().addAll(lectureController.getAllLectures());
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    @FXML
    void clear(ActionEvent event) {
        idTxt.clear();
        nameTxt.clear();
        contactTxt.clear();
        subjectsTxt.clear();
    }

    @FXML
    void saveLecture(ActionEvent event) {
        try {
            LecturerDto lecturerDto = new LecturerDto(
                    idTxt.getText(),
                    nameTxt.getText(),
                    contactTxt.getText()
            );

            String rsp = lectureController.addLecture(lecturerDto);
            clear(event);
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, rsp).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    @FXML
    void updateLecture(ActionEvent event) {
        try {
            LecturerDto lecturerDto = new LecturerDto(
                    idTxt.getText(),
                    nameTxt.getText(),
                    contactTxt.getText()
            );

            String rsp = lectureController.updateLecture(lecturerDto);
            clear(event);
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, rsp).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    @FXML
    public void deleteLecture(ActionEvent event) {
        try {
            String rsp = lectureController.deleteLecture(idTxt.getText());
            clear(event);
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, rsp).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).showAndWait();
        }
    }

    public void selectLecture() {
        LecturerDto getSelectedLecture = detailsTabel.getSelectionModel().getSelectedItem();
        if (getSelectedLecture == null) {
            new Alert(Alert.AlertType.WARNING, "Please Select Row").showAndWait();
            return;
        }

        try {
            LecturerDto lecturerDto = lectureController.searchLecture(getSelectedLecture.getLectureId());
            idTxt.setText(lecturerDto.getLectureId());
            nameTxt.setText(lecturerDto.getName());
            contactTxt.setText(lecturerDto.getContactDetails());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }
}
