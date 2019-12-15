package team47pack.models.dto;

public class RateRequest {
    private Integer value;
    private Long id;

    public RateRequest(Integer value, Long id) {
        this.value = value;
        this.id = id;
    }
    public RateRequest(){}

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
