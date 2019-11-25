package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.Examination;
import team47pack.repository.ExaminationRepo;
import team47pack.repository.PatientRepo;

import java.util.List;

@Service
public class ExaminationService {
    @Autowired
    private ExaminationRepo examinationRepo;
    @Autowired
    private PatientRepo patientRepo;

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
}
