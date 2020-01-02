package team47pack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team47pack.models.ClinicAdmin;
import team47pack.models.ExaminationType;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.ExaminationTypeRepo;

@Service
public class ExaminationTypeService {

	@Autowired
	ClinicAdminRepo clinicAdminRepo;

	@Autowired
	ExaminationTypeRepo examinTypeRepo;

	public List<ExaminationType> getExaminTypes(String email) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		Long clinicId = Long.parseLong("" + ca.getClinic());

		return examinTypeRepo.findByClinic(clinicId);

	}

	public boolean addExaminType(JSONObject obj, String email) throws JSONException {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null || obj == null)
			return false;

		if (obj.getString("name") == null || obj.getString("specialization") == null || obj.getString("price") == null)
			return false;

		if (obj.getString("name").equals("") || obj.getString("specialization").equals("") || obj.getString("price").equals("") )
			return false;

		ExaminationType check = examinTypeRepo.findByName(obj.getString("name"));

		if (check != null)
			return false;

		Long clinicId = Long.parseLong("" + ca.getClinic());
		float price = Float.parseFloat(obj.getString("price"));
		ExaminationType et = new ExaminationType(clinicId, obj.getString("name"), obj.getString("specialization"), price);

		examinTypeRepo.save(et);

		return true;
	}

	public boolean editExaminType(JSONObject obj, String email) throws JSONException {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null || obj == null)
			return false;

		if (obj.getString("name") == null || obj.getString("id") == null || obj.getString("specialization") == null || obj.getString("price") == null)
			return false;

		if (obj.getString("name").equals("") || obj.getString("id").equals("") || obj.getString("specialization").equals("") || obj.getString("price").equals(""))
			return false;

		Optional<ExaminationType> et = examinTypeRepo.findById(Long.parseLong(obj.getString("id")));
		if (!et.isPresent())
			return false;

		ExaminationType check = examinTypeRepo.findByName(obj.getString("name"));

		if (check != null && check.getClinic() == Long.parseLong("" + ca.getClinic())
				&& !check.getName().equals(et.get().getName()))
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

}
