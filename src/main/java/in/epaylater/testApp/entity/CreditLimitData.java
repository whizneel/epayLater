package in.epaylater.testApp.entity;

public class CreditLimitData {
    private Long id;
    private String phone;
    private Double creditLimit;
    private Double remainingCreditLimit;

    public CreditLimitData(Long id, String phone, Double creditLimit, Double remainingCreditLimit) {
        this.id = id;
        this.phone = phone;
        this.creditLimit = creditLimit;
        this.remainingCreditLimit = remainingCreditLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getRemainingCreditLimit() {
        return remainingCreditLimit;
    }

    public void setRemainingCreditLimit(Double remainingCreditLimit) {
        this.remainingCreditLimit = remainingCreditLimit;
    }

    @Override
    public String toString() {
        return "CreditLimitData{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", creditLimit=" + creditLimit +
                ", remainingCreditLimit=" + remainingCreditLimit +
                '}';
    }
}
