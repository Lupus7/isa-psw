package team47pack.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.*;
import team47pack.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OperationService {

    @Autowired
    OperationRepo operationRepo;

    @Autowired
    ClinicAdminRepo caRepo;

    @Autowired
    ClinicRepo clinicRepo;

    @Autowired
    EmailService emailService;

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    DoctorRepo doctorRepo;

    @Autowired
    RoomArrangeRepo roomArrangeRepo;

    @Autowired
    OperationTypeRepo operationTypeRepo;

    public List<Operation> getNextOper(String email) {
        ClinicAdmin ca = caRepo.findByEmail(email);

        if (ca == null)
            return new ArrayList<Operation>();

        Long clinicId = Long.parseLong("" + ca.getClinic());
        List<OperationType> operationTypes = operationTypeRepo.findByClinic(clinicId);
        List<Operation> operations = operationRepo.findAllByApproved(false);

        List<Operation> retVal = new ArrayList<>();

        for (Operation op : operations) {
            for (OperationType ot : operationTypes) {
                if (op.getOperationtype().getId() == ot.getId()) {
                    retVal.add(op);
                    break;
                }
            }
        }

        return retVal;
    }

    public boolean arrangeOperation(String json, String email) throws JSONException, ParseException {
        JSONObject obj = new JSONObject(json);

        ClinicAdmin ca = caRepo.findByEmail(email);
        if (ca == null)
            return false;
        Long clinicId = Long.parseLong("" + ca.getClinic());

        Optional<Clinic> clinic = clinicRepo.findById(clinicId);
        if (!clinic.isPresent())
            return false;

        if (obj.get("idNextProcedure") == null || obj.get("idRoom") == null || obj.get("date") == null
                || obj.get("time") == null || obj.get("idDoctorNew") == null)
            return false;

        if (obj.get("idNextProcedure").equals("") || obj.get("idRoom").equals("") || obj.get("date").equals(""))
            return false;

        Long idNextProcedure = obj.getLong("idNextProcedure");
        Long idRoom = obj.getLong("idRoom");

        Date dateConv = new SimpleDateFormat("yyyy-MM-dd").parse(obj.getString("date"));

        Optional<Operation> nextP = operationRepo.findById(idNextProcedure);
        if (!nextP.isPresent() || nextP.get().getApproved())
            return false;
        Optional<Room> room = roomRepo.findById(idRoom);
        if (!room.isPresent() || room.get().getType().equals("Examination"))
            return false;

        Date dateT = new Date();
        if (dateConv.compareTo(dateT) <= 0)
            return false;

        int time = 0;
        if (!obj.get("time").equals("")) {
            time = obj.getInt("time");
        } else
            time = nextP.get().getTime();

        List<Doctor> doctors = nextP.get().getDoctors();
        if (doctors == null)
            doctors = new ArrayList<>();

        if (!obj.get("idDoctorNew").equals("none")) {
            JSONArray idDoc = obj.getJSONArray("idDoctorNew");

            for(int i = 0; i < idDoc.length(); i++) {
                Optional<Doctor> d = doctorRepo.findById(idDoc.getLong(i));
                if (d.isPresent()) {
                    if (!doctors.contains(d.get())) {
                        doctors.add(d.get());
                    }
                    if (d.get().getOnVacation(dateConv)) {
                        return false;
                    }
                }
            }
        }

        ///////////////////////////////////////
        Operation check = operationRepo.findByDateAndTimeAndApproved(dateConv, time, true);
        if (check != null)
            return false;
        //////////////////////////////////////

        nextP.get().setApproved(true);
        nextP.get().setDate(dateConv);
        nextP.get().setDoctors(doctors);
        nextP.get().setTime(time);
        RoomArrange ra = new RoomArrange(room.get().getId(), dateConv, time, true, nextP.get().getId(), clinicId);
        room.get().getTakenDates().add(ra);

        roomArrangeRepo.save(ra);
        operationRepo.save(nextP.get());
        roomRepo.save(room.get());

        String[] s = dateConv.toString().split(" ");
        String dateEmail = s[2] + "/" + s[1] + "/" + s[5];

        String type = nextP.get().getOperationtype().getName();

        String bodyPatient = "Dear Sir/Madam \n \nYour operation request has been arranged for date: " + dateEmail
                + ", time: " + time + ":00h and room: " + room.get().getName()
                + "!\nLead Doctor who is going to operate you is " + doctors.get(0).getFirstName() + " " + doctors.get(0).getLastName()
                + "!\nAll the best\n\n" + clinic.get().getName().toUpperCase();

        for (Doctor doctor : doctors) {
            String bodyDoctor = "Dear Sir/Madam \n \nYou got a new Operation.\nExamination has been arranged for date: "
                    + dateEmail + ", time: " + time + ":00h and in room: " + room.get().getName() + "!\nAll the best\n\n"
                    + "Admin Team";
            try {
                emailService.sendSimpleMessage(doctor.getUsername(), type, bodyDoctor);
            } catch (Exception e) {}
        }


        try {
            emailService.sendSimpleMessage(nextP.get().getPatient().getUsername(), type, bodyPatient);
        } catch (Exception e) {}

        return true;
    }
}
