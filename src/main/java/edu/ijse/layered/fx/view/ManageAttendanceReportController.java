package edu.ijse.layered.fx.view;

import edu.ijse.layered.fx.controller.AttendanceReportsController;
import edu.ijse.layered.fx.dto.AttendanceDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Date;

public class ManageAttendanceReportController {

    private final AttendanceReportsController attendanceReportsController = new AttendanceReportsController();

    @FXML
    private TableColumn<AttendanceDto, String> colCourseName;

    @FXML
    private TableColumn<AttendanceDto, Date> colDate;

    @FXML
    private TableColumn<AttendanceDto, String> colLectureId;

    @FXML
    private TableColumn<AttendanceDto, String> colStatus;

    @FXML
    private TableColumn<AttendanceDto, String> colStudentName;

    @FXML
    private TableColumn<AttendanceDto, String> colSubjectName;

    @FXML
    private Button filterBtn;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private Button reportBtn;

    @FXML
    private TableView<AttendanceDto> tableView;

    @FXML
    private Label titleLabel;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    public void initialize(){
        colLectureId.setCellValueFactory(new PropertyValueFactory<>("lectureId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    void genarateReport(ActionEvent event) {

    }

    @FXML
    void onFilter(ActionEvent event) {
        LocalDate startDate = fromDatePicker.getValue();
        LocalDate endDate = toDatePicker.getValue();

        try {
            tableView.getItems().clear();
            tableView.getItems().addAll(attendanceReportsController.searchByDate(startDate,endDate));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

}

