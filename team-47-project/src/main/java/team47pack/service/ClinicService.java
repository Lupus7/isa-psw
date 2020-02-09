package team47pack.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.models.dto.ClinicAndAdmin;
import team47pack.models.dto.ClinicSearchRequest;
import team47pack.models.dto.ClinicSearchResult;
import team47pack.models.dto.RateRequest;
import team47pack.repository.*;

import java.util.*;

@Service
public class ClinicService {
	@Autowired
	ClinicRepo clinicRepo;

	@Autowired
	ClinicAdminRepo caRepo;

	@Autowired
	ExaminationRepo exRepo;

	@Autowired
	RateRepo rateRepo;

	@Autowired
	ExaminationTypeRepo exTypeRepo;

	@Autowired
	ClinicAdminRepo clinicAdminRepo;

	@Autowired
	OperationRepo opRepo;

	// @author: Lupus7 (Sinisa Canak)
	public List<ClinicAndAdmin> getClinics() {
		List<Clinic> clinics = clinicRepo.findAll();
		List<ClinicAndAdmin> retVal = new ArrayList<>();

		for (Clinic c : clinics) {
			List<String> admins = new ArrayList<>();

			for (ClinicAdmin admin : c.getClinicAdmins()) {
				admins.add(admin.getUsername());
			}

			retVal.add(new ClinicAndAdmin(c, admins));
		}

		return retVal;
	}

	public void save(Clinic clinic) {
		clinicRepo.save(clinic);
	}
	////

	public ArrayList<ClinicSearchResult> search(ClinicSearchRequest csr) {
		ArrayList<Clinic> clinics1 = new ArrayList<>();
		clinics1 = retriveClinics(csr.getLocation());
		if (clinics1.size() != 0) {
			for(Clinic c: clinics1){
				System.out.println("Klinika : " + c.getName() + c.getAddress());
			}
		}
		System.out.println("Prosek koji trazimo : " + csr.getRate());
		ArrayList<Integer> indexestoremove = new ArrayList<>();
		int size = clinics1.size();
		if (csr.getRate() != 0.0) {
			for (int i=0;i<clinics1.size();i++) {
				System.out.println("Iteriramo kroz" + clinics1.get(i).getName() + clinics1.get(i).getDescription());
				clinics1.get(i).setAverage(clinics1.get(i).calculateRate());
				System.out.println("CALCULATED RATE :" + clinics1.get(i).getAverage());
				if (clinics1.get(i).getAverage() < csr.getRate()) {
					System.out.println("BRISE SE OVA: " + clinics1.get(i).getName() + " " + clinics1.get(i).getDescription() + " sa prosekom " + clinics1.get(i).getAverage());
					indexestoremove.add(clinics1.get(i).getId().intValue());
				}
			}
		}else{
			for (int i=0;i<clinics1.size();i++) {
				clinics1.get(i).setAverage(clinics1.get(i).calculateRate());
			}
		}
		for(int i = 0 ; i<indexestoremove.size();i++){
			Iterator<Clinic> iter = clinics1.iterator();
			while(iter.hasNext()){
				Clinic cl = iter.next();
				if(cl.getId().intValue() == indexestoremove.get(i)){
					iter.remove();
				}
			}
		}
		System.out.println("PRitmao one koje su ostale");
		for (Clinic c : clinics1) {
			System.out.println(c.getAddress() + c.getName() + c.getDescription() + c.getAverage());
		}
		ArrayList<ExaminationType> examinationsTypes = retrieveExamination(csr.getExamination());
		System.out.println("EXAMINATION TYPE SIZE " + examinationsTypes.size());
		ArrayList<ClinicSearchResult> result = new ArrayList<>();
		if(csr.getExamination().equals("")){ // AKO je ovo prazan string, vracamo
			for(Clinic c:clinics1){
				ClinicSearchResult res = new ClinicSearchResult();
				res.setClinic(c);
				result.add(res);
			}
		}else{
			for(ExaminationType et: examinationsTypes){
				for(Clinic c: clinics1){
					if (et.getClinic() == c.getId()){
						ClinicSearchResult res = new ClinicSearchResult();
						res.setClinic(c);
						res.setCost((int)et.getPrice());
						result.add(res);
					}
				}
			}
		}

		return removeDuplicated(result);

	}

	public ArrayList<ExaminationType> retrieveExamination(String name) {
		Specification<ExaminationType> spec = Specification.where(ClinicSpecification.examinationType(name));
		return new ArrayList<>(new HashSet<>(exTypeRepo.findAll(spec, PageRequest.of(0, 10, Sort.by("name"))).toList()));
	}

	public ArrayList<Clinic> retriveClinics(String address) {
		Specification<Clinic> spec = Specification.where(ClinicSpecification.clinicLocation(address));
		return new ArrayList<>(
				new HashSet<>(clinicRepo.findAll(spec, PageRequest.of(0, 10, Sort.by("address"))).toList()));
	}

	public Clinic getClinic(Long id) {
		Optional<Clinic> c = clinicRepo.findById(id);
		if (c.isPresent())
			return c.get();
		return null;
	}

	// @author:Jokara
	public Clinic getClinicInfo(String email) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return null;
		Long id = Long.parseLong("" + ca.getClinic());

		return getClinic(id);
	}

	public boolean leaveRate(RateRequest rateRequest) {
		System.out.println(rateRequest.getId() + rateRequest.getValue() + rateRequest.getExamination());

		try {
			Examination e = exRepo.getOne(rateRequest.getExamination());
			e.setRatedClinic(true);
			exRepo.save(e);
		} catch (Exception e){
			Optional<Operation> o = opRepo.findById(rateRequest.getExamination());
			o.get().setRatedClinic(true);
			opRepo.save(o.get());
		}
		List<Clinic> klinike = clinicRepo.findAll();
		for (Clinic c : klinike) {
			for (Doctor d : c.getDoctors()) {
				if (d.getId() == rateRequest.getId()) {
					System.out.println("To je ta klinika: " + c.getName() + " " + c.getDescription());
					Rate rate = new Rate();
					rate.setValue(rateRequest.getValue());
					rateRepo.save(rate);
					c.getRatings().add(rate);
					clinicRepo.save(c);
					return true;
				}
			}
		}
		return false;
	}
	
	// @author:Jokara
	public boolean updateInfo(JSONObject obj, String email) throws JSONException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return false;
		Long idClinic = Long.parseLong("" + ca.getClinic());
		Optional<Clinic> cl = clinicRepo.findById(idClinic);
		if (!cl.isPresent())
			return false;
		Clinic clinic = cl.get();

		if (obj == null || obj.getString("name") == null || obj.getString("address") == null
				|| obj.getString("description") == null)
			return false;
		if (obj.getString("name").equals("") || obj.getString("address").equals("")
				|| obj.getString("description").equals(""))
			return false;
		
		String name = obj.getString("name");
		String address = obj.getString("address");
		String description = obj.getString("description");
		
		clinic.setName(name);
		clinic.setAddress(address);
		clinic.setDescription(description);
		
		clinicRepo.save(clinic);

		return true;
	}

	public Clinic getClinicByDoktorID(Long id) {
		List<Clinic>klinike = clinicRepo.findAll();
		for(Clinic c: klinike){
			for(Doctor d: c.getDoctors()){
				System.out.println("Doctor : " + d.getUsername() + d.getId());
				if(d.getId() == id){
					System.out.println("We found doctor: "  + d.getUsername());
					return c;
				}
			}
		}
		System.out.println("Nema klinike");
		return null;
	}
	public static ArrayList<ClinicSearchResult> removeDuplicated(ArrayList<ClinicSearchResult> rr){
		ArrayList<ClinicSearchResult> newList = new ArrayList<>();
		for(ClinicSearchResult c : rr){
			if(!newList.contains(c)){
				newList.add(c);
			}
		}
		LinkedHashSet<ClinicSearchResult> hashet = new LinkedHashSet<>(newList);
		ArrayList<ClinicSearchResult> rett = new ArrayList<>(hashet);
		return rett;
	}
}

