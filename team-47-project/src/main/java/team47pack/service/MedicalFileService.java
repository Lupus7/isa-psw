package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.MedicalFile;
import team47pack.repository.MedFileRepo;

@Service
public class MedicalFileService {

    @Autowired
    private MedFileRepo medFileRepo;

    public MedicalFile findOne(Long id){
       // MedicalFile mdf =medFileRepo.findByPatientId(id);

        return null;
    }
}
