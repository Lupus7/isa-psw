package team47pack.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import team47pack.models.ClinicAdmin;
import team47pack.models.NextProcedure;
import team47pack.repository.ClinicAdminRepo;
import team47pack.repository.NextProcedureRepo;

@Service
public class NextExaminationService {

	@Autowired
	private ClinicAdminRepo caRepo;

	@Autowired
	private NextProcedureRepo nextProcedureRepo;

	// @------author: Jokara
	public java.util.List<NextProcedure> getNextExns(String email) {
		ClinicAdmin ca = caRepo.findByEmail(email);
		if (ca == null)
			return new ArrayList<NextProcedure>();
		Long clinicId = Long.parseLong("" + ca.getClinic());
		Pageable sort = PageRequest.of(0, 15, Sort.by("date"));
		Page<NextProcedure> page = nextProcedureRepo.findByClinic(clinicId, sort);

		return page.getContent();
	}

}
