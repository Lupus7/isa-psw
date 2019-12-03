package team47pack.service;

import org.springframework.data.jpa.domain.Specification;
import team47pack.models.Clinic;

public class ClinicSpecification {
    public static Specification<Clinic> clinicLocation(String address) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            String location1 = address == null ? "" : address;
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + location1.toLowerCase() + "%");
        });
    }
}
