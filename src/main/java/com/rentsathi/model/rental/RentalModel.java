package com.rentsathi.model.rental;

public class RentalModel {

    private String rentalId;
    private String ownerId;

    private String rentalName;
    private String category;
    private String subcategory;
    private String description;

    private double pricePerDay;
    private double pricePerWeek;
    private double pricePerMonth;
    private double securityDeposit;

    private String availableFrom;
    private String availableUntil;

    private int minDays;
    private int maxDays;

    private String status;
    private String createdAt;

    private String address;
    private String city;
    private String state;
    private String pinCode;

    private double latitude;
    private double longitude;

    private String rentalRules;
    private String cancellationPolicy;

    public RentalModel() {
    }

    public RentalModel(
            String rentalId,
            String ownerId,
            String rentalName,
            String category,
            String subcategory,
            String description,
            double pricePerDay,
            double pricePerWeek,
            double pricePerMonth,
            double securityDeposit,
            String availableFrom,
            String availableUntil,
            int minDays,
            int maxDays,
            String status,
            String createdAt) {

        this.rentalId = rentalId;
        this.ownerId = ownerId;
        this.rentalName = rentalName;
        this.category = category;
        this.subcategory = subcategory;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.pricePerWeek = pricePerWeek;
        this.pricePerMonth = pricePerMonth;
        this.securityDeposit = securityDeposit;
        this.availableFrom = availableFrom;
        this.availableUntil = availableUntil;
        this.minDays = minDays;
        this.maxDays = maxDays;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getRentalId() {
        return rentalId;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getRentalName() {
        return rentalName;
    }

    public void setRentalName(String rentalName) {
        this.rentalName = rentalName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getPricePerWeek() {
        return pricePerWeek;
    }

    public void setPricePerWeek(double pricePerWeek) {
        this.pricePerWeek = pricePerWeek;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public double getSecurityDeposit() {
        return securityDeposit;
    }

    public void setSecurityDeposit(double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(String availableUntil) {
        this.availableUntil = availableUntil;
    }

    public int getMinDays() {
        return minDays;
    }

    public void setMinDays(int minDays) {
        this.minDays = minDays;
    }

    public int getMaxDays() {
        return maxDays;
    }

    public void setMaxDays(int maxDays) {
        this.maxDays = maxDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getRentalRules() {
        return rentalRules;
    }

    public void setRentalRules(String rentalRules) {
        this.rentalRules = rentalRules;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }
}
