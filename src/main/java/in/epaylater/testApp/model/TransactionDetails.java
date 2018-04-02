package in.epaylater.testApp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class TransactionDetails {
    @NotNull
    @NotEmpty
    private String date;

    @NotNull
    @NotEmpty
    private String description;

    private Double amount;

    @NotEmpty
    @NotNull
    private String phone;

    @JsonCreator
    public TransactionDetails(@JsonProperty("date") String date,
                              @JsonProperty("description") String description,
                              @JsonProperty("amount") Double amount,
                              @JsonProperty("phone") String phone) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.phone = phone;
    }

    public TransactionDetails() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "TransactionDetails{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", phone='" + phone + '\'' +
                '}';
    }
}
