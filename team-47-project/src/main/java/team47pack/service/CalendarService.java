package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.models.dto.CalendarEvent;
import team47pack.repository.NextProcedureRepo;
import team47pack.repository.OperationRepo;
import team47pack.repository.RoomArrangeRepo;

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
    private NextProcedureRepo nextProcedureRepo;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private MedicallStaffService medicallStaffService;

    @Autowired
    private OperationRepo operationRepo;

    @Autowired
    private RoomArrangeRepo roomArrangeRepo;

    public List<CalendarEvent> docInfo(String email, String date) throws ParseException {
        if (email == null || date == null)
            return null;

        Doctor doctor = doctorService.getDoctor(email);
        if (doctor == null)
            return null;

        List<NextProcedure> examinations = nextProcedureRepo.findByDoctorIdAndArranged(doctor.getId(), true);

        List<Operation> operations = operationRepo.findAllByDoctorsIdAndApproved(doctor.getId(), true);

        if (examinations == null && operations == null)
            return null;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        List<CalendarEvent> retVal = examinations
                .stream()
                .filter(examination -> {
                    cal.setTime(examination.getDate());
                    return date.contentEquals(format.format(cal.getTime()));
                })
                .map(ex -> {
                    Examination e = new Examination();
                    e.setDoctor(ex.getDoctor());
                    e.setDate(ex.getDate());
                    e.setTime(ex.getPickedtime());
                    return new CalendarEvent(e);
                })
                .collect(Collectors.toList());

        retVal.addAll(
                operations
                    .stream()
                    .filter(operation -> {
                        cal.setTime(operation.getDate());
                        return date.contentEquals(format.format(cal.getTime()));
                    })
                    .map(CalendarEvent::new)
                    .collect(Collectors.toList())
        );

        List<HolidayTimeOff> holidayTimeOffs = medicallStaffService.getTimeOff(doctor);

        for (HolidayTimeOff h : holidayTimeOffs) {
            CalendarEvent ce = new CalendarEvent(h);
            ce.setShift(doctor.getShift());
            retVal.add(ce);
        }

        if (retVal.isEmpty()) {
            retVal.add(new CalendarEvent(new Examination("none", new Date(0), null, doctor, true)));
        }

        return retVal;
    }

    public List<CalendarEvent> roomInfo(String adminMail) {
        List<Room> rooms = roomService.getRooms(adminMail);

        if (rooms == null || rooms.size() == 0)
            return Collections.emptyList();

        List<CalendarEvent> events = new ArrayList<>();

        rooms.forEach(room -> {
            List<RoomArrange> roomArrange = roomArrangeRepo.findByRoom(room.getId());

            roomArrange.forEach(ra -> {
                Optional<NextProcedure> ex = nextProcedureRepo.findById(ra.getNextExamination());
                if (ex.isPresent()) {
                    Examination e = new Examination();
                    e.setDoctor(ex.get().getDoctor());
                    e.setDate(ex.get().getDate());
                    e.setTime(ex.get().getPickedtime());
                    e.setPatient(ex.get().getPatient());
                    e.setRoom(room);
                    events.add(new CalendarEvent(e, "CADMIN_ROOM"));
                }
            });

            List<Operation> operations = operationRepo.findAllByRoomIdAndApproved(room.getId(), true);

            operations.forEach(operation -> {
                events.add(new CalendarEvent(operation, "CADMIN_ROOM"));
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

        List<CalendarEvent> retVal = new ArrayList<>();

        if (ms instanceof Doctor) {
            List<NextProcedure> examinations = nextProcedureRepo.findByDoctorIdAndArranged(ms.getId(), true);
            if (examinations == null)
                return null;

            retVal = examinations
                    .stream()
                    .map(ex-> {
                        Examination e = new Examination();
                        e.setDoctor(ex.getDoctor());
                        e.setDate(ex.getDate());
                        e.setTime(ex.getPickedtime());
                        e.setPatient(ex.getPatient());
                        return new CalendarEvent(e, "FOR_STAFF");
                    })
                    .collect(Collectors.toList());

            List<Operation> operations = operationRepo.findAllByDoctorsIdAndApproved(ms.getId(), true);
            if (operations == null)
                return null;

            retVal.addAll(
                operations
                    .stream()
                    .map(operation -> new CalendarEvent(operation, "FOR_STAFF"))
                    .collect(Collectors.toList())
            );
        }

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

