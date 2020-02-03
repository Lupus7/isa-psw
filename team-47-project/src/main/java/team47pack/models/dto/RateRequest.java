package team47pack.models.dto;

public class RateRequest {
    private Integer value;
    private Long id;
    private Long examination;

    public RateRequest(Integer value, Long id, Long examination) {
        this.value = value;
        this.id = id;
        this.examination = examination;
    }
    public RateRequest(){}

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getExamination() {
        return examination;
    }

    public void setExamination(Long examination) {
        this.examination = examination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
