package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Diagnosis;
import team47pack.models.Prescription;
import team47pack.models.dto.DiagnosisDTO;
import team47pack.models.dto.PrescriptionDTO;
import team47pack.repository.MedEntryRepo;
import team47pack.repository.PresVerifRepo;

import java.util.ArrayList;
import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Service
public class CodebookService {

    @Autowired
    PrescriptionService prescriptionService;

    @Autowired
    DiagnosisService diagnosisService;

    @Autowired
    MedEntryRepo medEntryRepo;

    @Autowired
    PresVerifRepo presVerifRepo;

    public List<PrescriptionDTO> getAllPres() {
        List<PrescriptionDTO> prescriptions = new ArrayList<>();
        for (Prescription p : prescriptionService.findAllByOrderByIdAsc()) {
            prescriptions.add(new PrescriptionDTO(p));
        }
        return prescriptions;
    }

    public List<DiagnosisDTO> getAllDiag() {
        List<DiagnosisDTO> diagnosis = new ArrayList<>();
        for (Diagnosis d : diagnosisService.findAllByOrderByIdAsc()) {
            diagnosis.add(new DiagnosisDTO(d));
        }
        return diagnosis;
    }

    public void saveDiag(DiagnosisDTO diagnosisDTO) {
        Diagnosis diag = null;
        if (diagnosisDTO.getId() == null) {
            diag = new Diagnosis(diagnosisDTO);
        }
        else {
            diag = diagnosisService.findOneById(diagnosisDTO.getId());
            diag.setName(diagnosisDTO.getName());
            diag.setDesc(diagnosisDTO.getDescription());
        }

        diagnosisService.save(diag);
    }

    public void savePres(PrescriptionDTO prescriptionDTO) {
        Prescription pres = null;
        if (prescriptionDTO.getId() == null) {
            pres = new Prescription(prescriptionDTO);
        }
        else {
            pres = prescriptionService.findOneById(prescriptionDTO.getId());
            pres.setName(prescriptionDTO.getName());
        }

        prescriptionService.save(pres);
    }

    public void deleteDiag(Long id) {
        diagnosisService.delete(id);
    }

    public void deletePres(Long id) {
        prescriptionService.delete(id);
    }
}
