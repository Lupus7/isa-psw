package team47pack.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
		System.out.println("aaaaaaaaaa:   "+email);
		User u = medicallRepo.findByEmail(email);
		if (u == null)
			return ResponseEntity.badRequest().body("Unsuccessful!");

		if (req.getBeginDate() == null || req.getEndDate() == null || req.getReason() == null || req.getTypeRequest() == null
				|| req.getEmail() == null)
			return ResponseEntity.badRequest().body("Fill all the fields");

		if (req.getReason().equals("") || req.getTypeRequest().equals(""))
			return ResponseEntity.badRequest().body("Fill all the fields");
		
		Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(req.getBeginDate());
		Date date2 =new SimpleDateFormat("dd/MM/yyyy").parse(req.getEndDate());  
		
		if (date1.compareTo(date2) >= 0)
			return ResponseEntity.badRequest().body("Wrong Date!");
		
		Date date = new Date(); 
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(formatter.format(date));
		
		if (date.compareTo(date1) >= 0)
			return ResponseEntity.badRequest().body("Wrong Date!");
		
		if (date.compareTo(date2) >= 0)
			return ResponseEntity.badRequest().body("Wrong Date!");
		

		HolidayTimeOff h = new HolidayTimeOff(date1, date2, req.getTypeRequest(), req.getReason(),(MedicalStaff) u);
		holidayTimeRepo.save(h);

		return ResponseEntity.ok("Success - request sent!");

	}

}
