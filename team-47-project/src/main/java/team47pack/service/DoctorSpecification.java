package team47pack.service;


import org.springframework.data.jpa.domain.Specification;
import team47pack.models.Doctor;

public class DoctorSpecification {

    public static Specification<Doctor> doctorFirstName(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            String name1 = name == null ? "" : name;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + name1.toLowerCase() + "%");
        });
    }

    public static Specification<Doctor> doctormLastName(String surname) {
        return ((root, criteriaQuery, criteriaBuilder) -> {

            String surname1 = surname == null ? "" : surname;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + surname1.toLowerCase() + "%");
        });
    }
    public static Specification<Doctor> doctorSpecialization(String specialization) {
        return ((root, criteriaQuery, criteriaBuilder) -> {

            String specialization1 = specialization == null ? "" : specialization;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("specialization")), "%" + specialization1.toLowerCase() + "%");
        });
    }
}
