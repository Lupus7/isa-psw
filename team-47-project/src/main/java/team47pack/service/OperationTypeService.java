package team47pack.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationTypeService {

	@Autowired
	ClinicAdminRepo clinicAdminRepo;

	@Autowired
	OperationTypeRepo operationTypeRepo;

	@Autowired
	OperationRepo operationRepo;

	@Autowired
	DoctorService doctorService;

	@Autowired
	ClinicRepo clinicRepo;

	public List<OperationType> getOperationTypes(String email) {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<>();
		Long clinicId = Long.parseLong("" + ca.getClinic());

		return operationTypeRepo.findByClinic(clinicId);
	}

	public List<OperationType> getOperTypes(String email) {
		Doctor doc = doctorService.getDoctor(email);
		if (doc == null)
			return new ArrayList<>();

		Long clinicId = Long.parseLong("" + clinicRepo.findByDoctorsId(doc.getId()).getId());

		return operationTypeRepo.findByClinic(clinicId);
	}

	public boolean addOperationType(JSONObject obj, String email) throws JSONException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null || obj == null)
			return false;

		if (obj.getString("name") == null || obj.getString("specialization") == null || obj.getString("price") == null)
			return false;

		if (obj.getString("name").equals("") || obj.getString("specialization").equals("")
				|| obj.getString("price").equals(""))
			return false;

		OperationType check = operationTypeRepo.findByName(obj.getString("name"));

		if (check != null)
			return false;

		Long clinicId = Long.parseLong("" + ca.getClinic());
		float price = Float.parseFloat(obj.getString("price"));
		OperationType ot = new OperationType(clinicId, obj.getString("name"), obj.getString("specialization"),
				price);

		operationTypeRepo.save(ot);

		return true;
	}

	public boolean editOperationType(JSONObject obj, String email) throws JSONException {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null || obj == null)
			return false;

		if (obj.getString("name") == null || obj.getString("id") == null || obj.getString("specialization") == null
				|| obj.getString("price") == null)
			return false;

		if (obj.getString("name").equals("") || obj.getString("id").equals("")
				|| obj.getString("specialization").equals("") || obj.getString("price").equals(""))
			return false;

		Optional<OperationType> ot = operationTypeRepo.findById(Long.parseLong(obj.getString("id")));
		if (!ot.isPresent())
			return false;

		OperationType check = operationTypeRepo.findByName(obj.getString("name"));

		if (check != null && check.getClinic() == Long.parseLong("" + ca.getClinic())
				&& !check.getName().equals(ot.get().getName()))
			return false;

		List<Operation> operations = operationRepo.findAllByOperationtypeId(ot.get().getId());
		if (operations.isEmpty())
			return false;

		ot.get().setName(obj.getString("name"));
		ot.get().setSpecialization(obj.getString("specialization"));
		float price = Float.parseFloat(obj.getString("price"));
		ot.get().setPrice(price);

		operationTypeRepo.save(ot.get());

		return true;
	}

	public boolean removeOperationType(Long id, String email) {

		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);
		if (ca == null)
			return false;

		Optional<OperationType> ot = operationTypeRepo.findById(id);

		if (!ot.isPresent())
			return false;

		List<Operation> operations = operationRepo.findAllByOperationtypeId(ot.get().getId());
		if (operations.isEmpty())
			return false;

		operationTypeRepo.delete(ot.get());

		return true;

	}

	public ArrayList<OperationType> searchOT(JSONObject obj, String email) throws JSONException {
		ClinicAdmin ca = clinicAdminRepo.findByEmail(email);

		if (ca == null || obj == null)
			return new ArrayList<>();

		Long clinicId = Long.parseLong("" + ca.getClinic());

		if (obj.get("name") == null || obj.get("id") == null)
			return new ArrayList<>();

		if (obj.get("name").equals("") && obj.get("id").equals(""))
			return new ArrayList<>();

		else if (!obj.get("name").equals("") && obj.get("id").equals(""))
			return operationTypeRepo.findByNameContainingIgnoreCaseAndClinic(obj.getString("name"), clinicId);

		else if (obj.get("name").equals("") && !obj.get("id").equals(""))
			return operationTypeRepo.findByIdAndClinic(obj.getLong("id"), clinicId);

		else if (!obj.get("name").equals("") && !obj.get("id").equals("")) {

			String name = obj.getString("name");
			Long id = obj.getLong("id");
			return operationTypeRepo.findByNameContainingIgnoreCaseAndIdAndClinic(name, id, clinicId);

		}

		return new ArrayList<>();
	}

	public List<OperationType> findByClinicID(Long id) {
		return operationTypeRepo.findByClinic(id);
	}
}
