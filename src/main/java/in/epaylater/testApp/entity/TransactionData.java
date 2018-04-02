package in.epaylater.testApp.entity;

import java.util.Date;

public class TransactionData {
    private Long id;
    private String phone;
    private Date date;
    private String description;
    private Double amount;

    public TransactionData(Long id, String phone, Date date, String description, Double amount) {
        this.id = id;
        this.phone = phone;
        this.date = date;
        this.description = description;
        this.amount = amount;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionData{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
