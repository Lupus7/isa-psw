package team47pack.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team47pack.models.ClinicAdmin;
import team47pack.models.Examination;
import team47pack.models.Procedure;
import team47pack.models.dto.DayReportDTO;
import team47pack.models.dto.MonthDTO;
import team47pack.models.dto.WeekDTO;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ProcedureRepo;

@Service
public class ReportService {

	@Autowired
	ClinicAdminRepo clinicAdminRepo;

	@Autowired
	ProcedureRepo procedureRepo;

	public List<DayReportDTO> dayReport(JSONObject obj, String name) throws JSONException, ParseException {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(name);
		if (ca == null)
			return new ArrayList<DayReportDTO>();

		if (obj.get("day1") == null || obj.get("day2") == null)
			return new ArrayList<DayReportDTO>();

		if (obj.get("day1").equals("") || obj.get("day2").equals(""))
			return new ArrayList<DayReportDTO>();

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = formatter1.parse(obj.getString("day1"));
		Date date2 = formatter1.parse(obj.getString("day2"));

		if (date2.before(date1) || date2.equals(date1))
			return new ArrayList<DayReportDTO>();

		List<Date> dates = new ArrayList<>();
		long interval = 24 * 1000 * 60 * 60;
		long endTime = date2.getTime();
		long curTime = date1.getTime();
		while (curTime <= endTime) {
			dates.add(new Date(curTime));
			curTime += interval;
		}

		List<DayReportDTO> dtos = new ArrayList<DayReportDTO>();

		for (Date d : dates) {
			List<Procedure> procedures = procedureRepo.findByDate(d);
			List<Procedure> real = new ArrayList<>();
			for (Procedure p : procedures) {
				if (p.getRoom().getClinicId() == ca.getClinic()) {
					real.add(p);
				}
			}
			DayReportDTO dto = new DayReportDTO();
			dto.setDate(d);
			if (real.isEmpty()) {
				dto.setProcedures(0);
			} else {
				dto.setProcedures(real.size());
			}
			dtos.add(dto);
		}

		return dtos;
	}

	public List<WeekDTO> weekReport(JSONObject obj, String name) throws JSONException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(name);
		if (ca == null)
			return new ArrayList<WeekDTO>();

		if (obj.get("week") == null)
			return new ArrayList<WeekDTO>();

		if (obj.get("week").equals(""))
			return new ArrayList<WeekDTO>();

		// String [] s = obj.getString("week").split("-");
		// int month = Integer.parseInt(s[1]);
		// int year = Integer.parseInt(s[0]);

		YearMonth ym = YearMonth.parse((CharSequence) obj.get("week"));
		LocalDate firstOfMonth = ym.atDay(1);
		LocalDate lastOfMonth = ym.atEndOfMonth();
		int nthWeek = 1;
		List<Date> dates1 = new ArrayList<Date>();
		List<Date> dates2 = new ArrayList<Date>();
		List<Date> dates3 = new ArrayList<Date>();
		List<Date> dates4 = new ArrayList<Date>();
		List<Date> dates5 = new ArrayList<Date>();

		LocalDate ld = firstOfMonth;
		while (!ld.isAfter(lastOfMonth)) {
			Date date = java.sql.Date.valueOf(ld);
			if (nthWeek == 1)
				dates1.add(date);
			else if (nthWeek == 2)
				dates2.add(date);
			else if (nthWeek == 3)
				dates3.add(date);
			else if (nthWeek == 4)
				dates4.add(date);
			else
				dates5.add(date);

			if (ld.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				++nthWeek;
			}
			ld = ld.plusDays(1);
		}

		List<WeekDTO> dtos = new ArrayList<WeekDTO>();

		int weekP1 = 0;
		for (Date dt : dates1) {

			List<Procedure> procedures = procedureRepo.findByDate(dt);
			List<Procedure> real = new ArrayList<>();
			for (Procedure p : procedures) {
				if (p.getRoom().getClinicId() == ca.getClinic()) {
					real.add(p);
				}
			}
			weekP1 += real.size();

		}

		dtos.add(new WeekDTO(1, weekP1));

		int weekP2 = 0;
		for (Date dt : dates2) {

			List<Procedure> procedures = procedureRepo.findByDate(dt);
			List<Procedure> real = new ArrayList<>();
			for (Procedure p : procedures) {
				if (p.getRoom().getClinicId() == ca.getClinic()) {
					real.add(p);
				}
			}
			weekP2 += real.size();

		}

		dtos.add(new WeekDTO(2, weekP2));

		int weekP3 = 0;
		for (Date dt : dates3) {

			List<Procedure> procedures = procedureRepo.findByDate(dt);
			List<Procedure> real = new ArrayList<>();
			for (Procedure p : procedures) {
				if (p.getRoom().getClinicId() == ca.getClinic()) {
					real.add(p);
				}
			}
			weekP3 += real.size();

		}

		dtos.add(new WeekDTO(3, weekP3));

		int weekP4 = 0;
		for (Date dt : dates4) {

			List<Procedure> procedures = procedureRepo.findByDate(dt);
			List<Procedure> real = new ArrayList<>();
			for (Procedure p : procedures) {
				if (p.getRoom().getClinicId() == ca.getClinic()) {
					real.add(p);
				}
			}
			weekP4 += real.size();

		}

		dtos.add(new WeekDTO(4, weekP4));

		int weekP5 = 0;
		for (Date dt : dates5) {

			List<Procedure> procedures = procedureRepo.findByDate(dt);
			List<Procedure> real = new ArrayList<>();
			for (Procedure p : procedures) {
				if (p.getRoom().getClinicId() == ca.getClinic()) {
					real.add(p);
				}
			}
			weekP5 += real.size();

		}

		dtos.add(new WeekDTO(5, weekP5));

		return dtos;
	}

	public List<MonthDTO> monthReport(JSONObject obj, String name) throws JSONException, ParseException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(name);
		if (ca == null)
			return new ArrayList<MonthDTO>();

		if (obj.get("month1") == null || obj.get("month2") == null)
			return new ArrayList<MonthDTO>();

		if (obj.get("month1").equals("") || obj.get("month2").equals(""))
			return new ArrayList<MonthDTO>();

		YearMonth y1 = YearMonth.parse((CharSequence) obj.get("month1"));
		YearMonth y2 = YearMonth.parse((CharSequence) obj.get("month2"));

		if (y2.isBefore(y1) || y1.equals(y2))
			return new ArrayList<MonthDTO>();

		LocalDate firstDay = y1.atDay(1);
		LocalDate lastDay = y2.atEndOfMonth();
		List<Date> dates = new ArrayList<Date>();
		HashMap<Date, List<Date>> months = new HashMap<>();

		LocalDate lastDayofMonth = y1.atEndOfMonth();

		LocalDate ld = firstDay;
		while (!ld.isAfter(lastDay)) {
			if (ld.equals(lastDayofMonth)) {
				months.put(java.sql.Date.valueOf(ld), dates);
				dates = new ArrayList<>();
				ld = ld.plusDays(1);
				String[] s = ld.toString().split("-");
				String temp = s[0] + "-" + s[1];
				YearMonth ym = YearMonth.parse((CharSequence) temp);
				lastDayofMonth = ym.atEndOfMonth();
			} else {
				Date date = java.sql.Date.valueOf(ld);
				dates.add(date);
				ld = ld.plusDays(1);
			}
		}

		List<MonthDTO> dtos = new ArrayList<>();
		for (Date s : months.keySet()) {
			int ukupno = 0;
			for (Date dat : months.get(s)) {
				List<Procedure> procedures = procedureRepo.findByDate(dat);
				List<Procedure> real = new ArrayList<>();
				for (Procedure p : procedures) {
					if (p.getRoom().getClinicId() == ca.getClinic()) {
						real.add(p);
					}
				}
				ukupno += real.size();

			}

			MonthDTO moDto = new MonthDTO(s.toString(), ukupno);
			dtos.add(moDto);
		}

		// sortiraj moDto
		MonthDTO temp;
		for (int i = 0; i < dtos.size(); i++) {
			for (int j = 0; j < dtos.size(); j++) {

				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = formatter1.parse(dtos.get(i).getMonth());
				Date date2 = formatter1.parse(dtos.get(j).getMonth());

				if (date1.before(date2)) {
					temp = dtos.get(j);
					dtos.set(j, dtos.get(i));
					dtos.set(i, temp);
				}
			}
		}

		return dtos;
	}

	public List<DayReportDTO> incomeReport(JSONObject obj, String name) throws JSONException, ParseException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(name);
		if (ca == null)
			return new ArrayList<DayReportDTO>();

		if (obj.get("day1") == null || obj.get("day2") == null)
			return new ArrayList<DayReportDTO>();

		if (obj.get("day1").equals("") || obj.get("day2").equals(""))
			return new ArrayList<DayReportDTO>();

		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = formatter1.parse(obj.getString("day1"));
		Date date2 = formatter1.parse(obj.getString("day2"));

		if (date2.before(date1) || date2.equals(date1))
			return new ArrayList<DayReportDTO>();

		List<Date> dates = new ArrayList<>();
		long interval = 24 * 1000 * 60 * 60;
		long endTime = date2.getTime();
		long curTime = date1.getTime();
		while (curTime <= endTime) {
			dates.add(new Date(curTime));
			curTime += interval;
		}

		List<DayReportDTO> dtos = new ArrayList<DayReportDTO>();

		for (Date d : dates) {
			List<Procedure> procedures = procedureRepo.findByDate(d);
			List<Procedure> real = new ArrayList<>();
			for (Procedure p : procedures) {
				if (p.getRoom().getClinicId() == ca.getClinic()) {
					real.add(p);
				}
			}

			DayReportDTO dto = new DayReportDTO();
			dto.setDate(d);
			if (real.isEmpty()) {
				dto.setProcedures(0);
			} else {
				double ukupno = 0;
				for (Procedure p : real) {
					if (p.getType().equals("Examination")) {
						Examination e = (Examination) p;
						ukupno += e.getExaminationtype().getPrice();
					} // else if(p.getType().equals("Operation")) {
						// Operation o = (Operation) p;
						// ukupno += // o.getOperationType().getPrice();
						// }
				}
				dto.setProcedures(ukupno);
			}
			dtos.add(dto);
		}

		return dtos;
	}

}
