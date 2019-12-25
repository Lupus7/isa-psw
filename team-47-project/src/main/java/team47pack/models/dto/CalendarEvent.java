package team47pack.models.dto;

import team47pack.models.Doctor;
import team47pack.models.Examination;
import team47pack.models.Patient;
import team47pack.models.Room;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

// @author: Lupus7 (Sinisa Canak)
public class CalendarEvent {
    // CALENDAR SPECIFIC PROPERTIES
    private String title;
    private String start;
    private String end;
    private String backgroundColor;
    private String textColor;

    // DATA
    private List<String> staff;
    private String patient;
    private Long roomId;
    private Integer shift;

    public CalendarEvent(Examination examination) {
        initForPatient(examination);
    }

    public CalendarEvent(Examination examination, String type) {
        if (type.contentEquals("CADMIN_ROOM"))
            initForCAdmin(examination);
        else
            initForPatient(examination);
    }

    private void initForPatient(Examination examination) {
        this.title = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        cal.setTime(examination.getDate());
        this.start = format.format(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        this.end = format.format(cal.getTime());
        this.backgroundColor = "#aaf";
        this.textColor = "#000";
        this.shift = examination.getDoctor().getShift();
    }

    public void initForCAdmin(Examination examination) {
        Doctor doctor = examination.getDoctor();
        Patient patient = examination.getPatient();
        Room room = examination.getRoom();
        this.title = "Doctor " + doctor.getFirstName() + " " + doctor.getLastName() +
                " has examination appointed for " + patient.getFirstName() + " " + patient.getLastName() +
                " in room number: " + room.getNumber() + ", \"" + room.getName() + "\".";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        cal.setTime(examination.getDate());
        this.start = format.format(cal.getTime());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        this.end = format.format(cal.getTime());
        this.backgroundColor = "#aaf";
        this.textColor = "#000";
        this.shift = examination.getDoctor().getShift();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public List<String> getStaff() {
        return staff;
    }

    public void setStaff(List<String> staff) {
        this.staff = staff;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Integer getShift() {
        return shift;
    }

    public void setShift(Integer shift) {
        this.shift = shift;
    }
}
