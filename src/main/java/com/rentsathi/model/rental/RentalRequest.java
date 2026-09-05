package com.rentsathi.model.rental;

import java.time.LocalDate;

public class RentalRequest {

    private String requestId;

    private RentalModel rental;

    private String customerId;
    private String customerAddress;

    private LocalDate startDate;
    private LocalDate endDate;

    private int quantity;

    private String fulfillmentMethod;
    private String paymentMethod;

    private double totalAmount;

    private String status;
    private String deliveryStatus;
    private String requestedAt;

    public RentalRequest() {
    }

    public RentalRequest(
            String requestId,
            RentalModel rental,
            String customerId,
            LocalDate startDate,
            LocalDate endDate,
            int quantity,
            String fulfillmentMethod,
            String paymentMethod,
            double totalAmount,
            String status,
            String requestedAt) {

        this.requestId = requestId;
        this.rental = rental;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
        this.fulfillmentMethod = fulfillmentMethod;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
        this.status = status;
        this.requestedAt = requestedAt;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public RentalModel getRental() {
        return rental;
    }

    public void setRental(RentalModel rental) {
        this.rental = rental;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFulfillmentMethod() {
        return fulfillmentMethod;
    }

    public void setFulfillmentMethod(String fulfillmentMethod) {
        this.fulfillmentMethod = fulfillmentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(
            String deliveryStatus) {

        this.deliveryStatus = deliveryStatus;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(String requestedAt) {
        this.requestedAt = requestedAt;
    }
}
