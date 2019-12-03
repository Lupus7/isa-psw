package team47pack.service;

import org.springframework.data.jpa.domain.Specification;
import team47pack.models.Clinic;
import team47pack.models.Examination;

public class ClinicSpecification {
    public static Specification<Clinic> clinicLocation(String address) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            String location1 = address == null ? "" : address;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + location1.toLowerCase() + "%");
        });
    }

    public static Specification<Examination> examinationType(String type) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            String type1 = type == null ? "" : type;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + type1.toLowerCase() + "%");
        });
    }
}
