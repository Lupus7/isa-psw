package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.models.dto.ExaminInfo;
import team47pack.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExaminationService {
    @Autowired
    private ExaminationRepo examinationRepo;
    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private MedFileRepo medFileRepo;
    @Autowired
    private MedEntryRepo medEntryRepo;
    @Autowired
    private DiagnosisRepo diagRepo;
    @Autowired
    private PrescriptionRepo presRepo;


    public List<Examination> getByPatientId(Long id){
        System.out.println("Ovo je iz servica : " + id);
        return examinationRepo.findByPatientId(id);
    }
    public List<Examination>getAll(){
        List<Examination> ret =  examinationRepo.findAll();
        for(Examination e: ret){
            System.out.println(e.toString());
        }
        return ret;
    }

    public boolean addExamination(ExaminInfo examinInfo) {
        Optional<Patient> pat = patientRepo.findById(examinInfo.getPatientId());

        if (!pat.isPresent())
            return false;

        MedFileEntry medFileEntry = new MedFileEntry();
        medFileEntry.setDesc(examinInfo.getDesc());

        Optional<Diagnosis> diag = diagRepo.findById(examinInfo.getDiag().getId());
        if (!diag.isPresent())
            return false;

        medFileEntry.setDiagnosis(diag.get());

        for (Prescription p : examinInfo.getPrescs()) {
            Optional<Prescription> pres = presRepo.findById(p.getId());
            if (!pres.isPresent())
                return false;
            medFileEntry.getPrescriptions().add(pres.get());
        }

        medEntryRepo.save(medFileEntry);

        if (pat.get().getMedicalFile() == null) {
            MedicalFile mf = new MedicalFile(pat.get().getId(), new ArrayList<>());
            mf.getEntries().add(medFileEntry);

            medFileRepo.save(mf);

            pat.get().setMedicalFile(mf);
        }

        patientRepo.save(pat.get());

        return true;
    }
}
