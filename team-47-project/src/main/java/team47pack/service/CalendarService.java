package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Doctor;
import team47pack.models.Examination;
import team47pack.models.dto.CalendarEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ExaminationService examinationService;

    public List<CalendarEvent> docInfo(String email, String date) {
        if (email == null || date == null)
            return null;

        Doctor doctor = doctorService.getDoctor(email);
        if (doctor == null)
            return null;

        List<Examination> examinations = examinationService.getByDoctorId(doctor.getId());
        if (examinations == null)
            return null;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return examinations
                .stream()
                .filter(examination -> {
                    cal.setTime(examination.getDate());
                    return date.contentEquals(format.format(cal.getTime()));
                })
                .map(CalendarEvent::new)
                .collect(Collectors.toList());
    }

    public boolean docHasTime(String email, String date) { // ONLY WORKS CORRECTLY IF PROCEDURES START ON FULL HOURS, NEEDS FIXING
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


}

