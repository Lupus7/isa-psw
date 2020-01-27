package team47pack.service;

import org.springframework.data.jpa.domain.Specification;
import team47pack.models.Clinic;
import team47pack.models.Examination;
import team47pack.models.ExaminationType;

public class ClinicSpecification {
    public static Specification<Clinic> clinicLocation(String address) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            String address1 = address == null ? "" : address;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address1.toLowerCase() + "%");
        });
    }

    public static Specification<ExaminationType> examinationType(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            String type1 = name == null ? "" : name;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + type1.toLowerCase() + "%");
        });
    }

    public static Specification<Examination> clinicAddress(String address) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            String address1 = address == null ? "" : address;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address1.toLowerCase() + "%");
        });
    }
}
