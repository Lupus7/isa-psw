package team47pack.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import team47pack.models.HolidayTimeOff;
import team47pack.models.MedicalStaff;
import team47pack.models.User;
import team47pack.models.dto.HolidayTimeOffRequest;
import team47pack.repository.HolidayTimeOffRepository;
import team47pack.repository.MedicalStaffRepo;

@Service
public class MedicallStaffService {

	@Autowired
	private MedicalStaffRepo medicallRepo;
	@Autowired
	private HolidayTimeOffRepository holidayTimeRepo;

	public ResponseEntity<?> sendRequest(HolidayTimeOffRequest req, String email) throws ParseException {
		System.out.println("aaaaaaaaaa:   " + email);
		User u = medicallRepo.findByEmail(email);
		if (u == null)
			return ResponseEntity.badRequest().body("Unsuccessful!");

		if (req.getBeginDate() == null || req.getEndDate() == null || req.getReason() == null
				|| req.getTypeRequest() == null || req.getEmail() == null)
			return ResponseEntity.badRequest().body("Fill all the fields");

		if (req.getReason().equals("") || req.getTypeRequest().equals(""))
			return ResponseEntity.badRequest().body("Fill all the fields");

		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(req.getBeginDate());
		Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(req.getEndDate());

		if (date1.compareTo(date2) >= 0)
			return ResponseEntity.badRequest().body("Wrong Date!");

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(formatter.format(date));

		if (date.compareTo(date1) >= 0)
			return ResponseEntity.badRequest().body("Wrong Date!");

		if (date.compareTo(date2) >= 0)
			return ResponseEntity.badRequest().body("Wrong Date!");

		HolidayTimeOff h = new HolidayTimeOff(date1, date2, req.getTypeRequest(), req.getReason(), (MedicalStaff) u);
		holidayTimeRepo.save(h);

		return ResponseEntity.ok("Success - request sent!");

	}

	// TODO: dodaj medStaffService funkciju, gde ce se popuniti datovi, i setovati
	// onvacation
	// @author:Jokara
	public boolean goOnHoliday(String email, Date dateB, Date dateE) {
		MedicalStaff ms = medicallRepo.findByEmail(email);
		if (ms == null)
			return false;
		ms.setHolidayBegin(dateB);
		ms.setHolidayEnd(dateE);

		medicallRepo.save(ms);

		return true;
	}

	// TODO: dodaj medService scheldue funkciju gde ce se gledati da li je neko na
	// odmoru ili ne i setovati polja
	@Scheduled(cron = "00 30 12 * * ?")
	//@Scheduled(cron = "0/5 * * * * ?")
	public void holidayCheck() throws ParseException {
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		Date todayDate = dateFormatter.parse(dateFormatter.format(new Date() ));

		List<MedicalStaff> ms = medicallRepo.findByHolidayBeginNotNullAndHolidayEndNotNull();
		if(ms.isEmpty())
			return;
		for (MedicalStaff m : ms) {
			Date dateB = dateFormatter.parse(dateFormatter.format(m.getHolidayBegin()));
			Date dateE = dateFormatter.parse(dateFormatter.format(m.getHolidayEnd()));
			if (dateB.compareTo(todayDate) == 0) {
				m.setOnVacation(true);
				medicallRepo.save(m);
			}

			else if (dateE.compareTo(todayDate) == 0) {
				m.setOnVacation(false);
				m.setHolidayBegin(null);
				m.setHolidayEnd(null);
				medicallRepo.save(m);
			}
		}


	}

}
