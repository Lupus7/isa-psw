package team47pack.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.ClinicAdmin;
import team47pack.models.Doctor;
import team47pack.models.ExaminationType;
import team47pack.models.NextProcedure;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.DoctorRepo;
import team47pack.repository.ExaminationTypeRepo;
import team47pack.repository.NextProcedureRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationTypeService {

	@Autowired
	ClinicAdminRepo clinicAdminRepo;

	@Autowired
	DoctorRepo doctorRepo;

	@Autowired
	ExaminationTypeRepo examinTypeRepo;

	@Autowired
	NextProcedureRepo nextProcedureRepo;

	public List<ExaminationType> getExaminTypes(String email) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		Long clinicId = Long.parseLong("" + ca.getClinic());

		return examinTypeRepo.findByClinic(clinicId);

	}

	public List<ExaminationType> getNextExmType(String email) {
		Doctor doctor = doctorRepo.findByEmail(email);
		if (doctor == null)
			return new ArrayList<>();

		return examinTypeRepo.findByClinicAndSpecialization(doctor.getClinicId(), doctor.getSpecialization());

	}

	public boolean addExaminType(JSONObject obj, String email) throws JSONException {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null || obj == null)
			return false;

		if (obj.getString("name") == null || obj.getString("specialization") == null || obj.getString("price") == null)
			return false;

		if (obj.getString("name").equals("") || obj.getString("specialization").equals("")
				|| obj.getString("price").equals(""))
			return false;

		ExaminationType check = examinTypeRepo.findByName(obj.getString("name"));

		if (check != null)
			return false;

		Long clinicId = Long.parseLong("" + ca.getClinic());
		float price = Float.parseFloat(obj.getString("price"));
		ExaminationType et = new ExaminationType(clinicId, obj.getString("name"), obj.getString("specialization"),
				price);

		examinTypeRepo.save(et);

		return true;
	}

	public boolean editExaminType(JSONObject obj, String email) throws JSONException {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null || obj == null)
			return false;

		if (obj.getString("name") == null || obj.getString("id") == null || obj.getString("specialization") == null
				|| obj.getString("price") == null)
			return false;

		if (obj.getString("name").equals("") || obj.getString("id").equals("")
				|| obj.getString("specialization").equals("") || obj.getString("price").equals(""))
			return false;

		Optional<ExaminationType> et = examinTypeRepo.findById(Long.parseLong(obj.getString("id")));
		if (!et.isPresent())
			return false;

		ExaminationType check = examinTypeRepo.findByName(obj.getString("name"));

		if (check != null && check.getClinic() == Long.parseLong("" + ca.getClinic())
				&& !check.getName().equals(et.get().getName()))
			return false;

		Optional<NextProcedure> np = nextProcedureRepo.findByExaminationtypeId(et.get().getId());
		if (np.isPresent())
			return false;

		et.get().setName(obj.getString("name"));
		et.get().setSpecialization(obj.getString("specialization"));
		float price = Float.parseFloat(obj.getString("price"));
		et.get().setPrice(price);

		examinTypeRepo.save(et.get());

		return true;
	}

	public boolean removeExaminType(Long id, String email) {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return false;

		Optional<ExaminationType> et = examinTypeRepo.findById(id);

		if (!et.isPresent())
			return false;

		Optional<NextProcedure> np = nextProcedureRepo.findByExaminationtypeId(et.get().getId());
		if (np.isPresent()) {
			System.out.println("ne moze!");
			return false;
		}

		examinTypeRepo.delete(et.get());

		return true;

	}

	public ArrayList<ExaminationType> searchET(JSONObject obj, String email) throws JSONException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);

		if (ca == null || obj == null)
			return new ArrayList<>();

		Long clinicId = Long.parseLong("" + ca.getClinic());

		if (obj.get("name") == null || obj.get("id") == null)
			return new ArrayList<>();

		if (obj.get("name").equals("") && obj.get("id").equals(""))
			return new ArrayList<>();

		else if (!obj.get("name").equals("") && obj.get("id").equals(""))
			return examinTypeRepo.findByNameContainingIgnoreCaseAndClinic(obj.getString("name"), clinicId);

		else if (obj.get("name").equals("") && !obj.get("id").equals(""))
			return examinTypeRepo.findByIdAndClinic(obj.getLong("id"), clinicId);

		else if (!obj.get("name").equals("") && !obj.get("id").equals("")) {

			String name = obj.getString("name");
			Long id = obj.getLong("id");
			return examinTypeRepo.findByNameContainingIgnoreCaseAndIdAndClinic(name, id, clinicId);

		}

		return new ArrayList<>();
	}

	public List<ExaminationType> findByClinicID(Long id) {
		return examinTypeRepo.findByClinic(id);
	}
}
