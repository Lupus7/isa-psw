package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.models.dto.CalendarEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

// @author: Lupus7 (Sinisa Canak)
@Service
public class CalendarService {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MedicallStaffService medicallStaffService;

    public List<CalendarEvent> docInfo(String email, String date) throws ParseException {
        if (email == null || date == null)
            return null;

        Doctor doctor = doctorService.getDoctor(email);
        if (doctor == null)
            return null;

        List<Examination> examinations = examinationService.getByDoctorId(doctor.getId()); // TODO: ADD OPERATIONS
        if (examinations == null)
            return null;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        List<CalendarEvent> retVal = examinations
                .stream()
                .filter(examination -> {
                    cal.setTime(examination.getDate());
                    return date.contentEquals(format.format(cal.getTime()));
                })
                .map(CalendarEvent::new)
                .collect(Collectors.toList());

        List<HolidayTimeOff> holidayTimeOffs = medicallStaffService.getTimeOff(doctor);

        for (HolidayTimeOff h : holidayTimeOffs) {  // TODO: filter by date
            CalendarEvent ce = new CalendarEvent(h);
            ce.setShift(doctor.getShift());
            retVal.add(ce);
        }

        if (retVal.isEmpty()) {
            retVal.add(new CalendarEvent(new Examination("none", new Date(0), null, doctor, true)));
        }

        return retVal;
    }

    public boolean docHasTime(String email, String date) throws ParseException { // ONLY WORKS CORRECTLY IF PROCEDURES START ON FULL HOURS, NEEDS FIXING
        List<CalendarEvent> events = docInfo(email, date);

        int sumHr = 0;

        for (CalendarEvent event : events) {
            if (event.getBackgroundColor().contentEquals("#aaf"))
                sumHr += 1;
            else if (event.getBackgroundColor().contentEquals("#afa"))
                sumHr += 2;
            else
                sumHr += 8;
        }

        return sumHr <= 7;
    }


    public List<CalendarEvent> roomInfo(String adminMail) {
        List<Room> rooms = roomService.getRooms(adminMail);

        if (rooms == null || rooms.size() == 0)
            return Collections.emptyList();

        List<CalendarEvent> events = new ArrayList<>();

        rooms.forEach(room -> {
            List<Examination> examinations = examinationService.findAllByRoom(room.getId()); // TODO: ADD OPERATIONS

            examinations.forEach(examination -> {
                events.add(new CalendarEvent(examination, "CADMIN_ROOM"));
            });
        });

        return events;
    }

    public List<CalendarEvent> schedule(String mail) throws ParseException {
        if (mail == null)
            return null;

        MedicalStaff ms = medicallStaffService.getStaff(mail);
        if (ms == null)
            return null;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        List<Examination> examinations = examinationService.getByDoctorId(ms.getId()); // TODO: ADD OPERATIONS + NURSES
        if (examinations == null)
            return null;

        List<CalendarEvent> retVal = examinations
                .stream()
                .map(examination -> new CalendarEvent(examination, "FOR_STAFF"))
                .collect(Collectors.toList());


        List<HolidayTimeOff> holidayTimeOffs = medicallStaffService.getTimeOff(ms);

        for (HolidayTimeOff h : holidayTimeOffs) {
            CalendarEvent ce = new CalendarEvent(h);
            ce.setShift(ms.getShift());
            retVal.add(ce);
        }

        if (retVal.isEmpty()) {
            Doctor d = new Doctor();
            d.setShift(ms.getShift());
            retVal.add(new CalendarEvent(new Examination("none", new Date(0), null, d, true)));
        }

        return retVal;
    }
}

