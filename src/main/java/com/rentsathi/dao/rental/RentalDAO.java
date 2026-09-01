package com.rentsathi.dao.rental;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.rentsathi.firebase.firestore.FirestoreService;
import com.rentsathi.model.rental.RentalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RentalDAO {

        private static final String COLLECTION = "rentals";

        private RentalDAO() {
        }

        // ============================================================
        // CREATE RENTAL
        // ============================================================

        public static boolean createRental(RentalModel rental) {

                if (rental == null) {
                        System.out.println("Rental data cannot be null.");
                        return false;
                }

                try {

                        // Generate unique Firestore document ID
                        String rentalId = UUID.randomUUID().toString();

                        rental.setRentalId(rentalId);

                        // ====================================================
                        // FIRESTORE FIELDS
                        // ====================================================

                        JsonObject fields = new JsonObject();

                        // ----------------------------------------------------
                        // BASIC INFORMATION
                        // ----------------------------------------------------

                        fields.add(
                                        "rentalId",
                                        FirestoreService.stringField(
                                                        rental.getRentalId()));

                        fields.add(
                                        "ownerId",
                                        FirestoreService.stringField(
                                                        rental.getOwnerId()));

                        fields.add(
                                        "rentalName",
                                        FirestoreService.stringField(
                                                        rental.getRentalName()));

                        fields.add(
                                        "category",
                                        FirestoreService.stringField(
                                                        rental.getCategory()));

                        fields.add(
                                        "subcategory",
                                        FirestoreService.stringField(
                                                        rental.getSubcategory()));

                        fields.add(
                                        "description",
                                        FirestoreService.stringField(
                                                        rental.getDescription()));

                        // ----------------------------------------------------
                        // PRICING
                        // ----------------------------------------------------

                        fields.add(
                                        "pricePerDay",
                                        FirestoreService.doubleField(
                                                        rental.getPricePerDay()));

                        fields.add(
                                        "pricePerWeek",
                                        FirestoreService.doubleField(
                                                        rental.getPricePerWeek()));

                        fields.add(
                                        "pricePerMonth",
                                        FirestoreService.doubleField(
                                                        rental.getPricePerMonth()));

                        fields.add(
                                        "securityDeposit",
                                        FirestoreService.doubleField(
                                                        rental.getSecurityDeposit()));

                        // ----------------------------------------------------
                        // AVAILABILITY
                        // ----------------------------------------------------

                        fields.add(
                                        "availableFrom",
                                        FirestoreService.stringField(
                                                        rental.getAvailableFrom()));

                        fields.add(
                                        "availableUntil",
                                        FirestoreService.stringField(
                                                        rental.getAvailableUntil()));

                        fields.add(
                                        "minDays",
                                        FirestoreService.integerField(
                                                        rental.getMinDays()));

                        fields.add(
                                        "maxDays",
                                        FirestoreService.integerField(
                                                        rental.getMaxDays()));

                        // ----------------------------------------------------
                        // LOCATION
                        // ----------------------------------------------------

                        fields.add(
                                        "address",
                                        FirestoreService.stringField(
                                                        rental.getAddress()));

                        fields.add(
                                        "city",
                                        FirestoreService.stringField(
                                                        rental.getCity()));

                        fields.add(
                                        "state",
                                        FirestoreService.stringField(
                                                        rental.getState()));

                        fields.add(
                                        "pinCode",
                                        FirestoreService.stringField(
                                                        rental.getPinCode()));

                        // ----------------------------------------------------
                        // RENTAL TERMS
                        // ----------------------------------------------------

                        fields.add(
                                        "rentalRules",
                                        FirestoreService.stringField(
                                                        rental.getRentalRules()));

                        fields.add(
                                        "cancellationPolicy",
                                        FirestoreService.stringField(
                                                        rental.getCancellationPolicy()));

                        // ----------------------------------------------------
                        // SYSTEM INFORMATION
                        // ----------------------------------------------------

                        fields.add(
                                        "status",
                                        FirestoreService.stringField(
                                                        rental.getStatus()));

                        fields.add(
                                        "createdAt",
                                        FirestoreService.stringField(
                                                        rental.getCreatedAt()));

                        // ====================================================
                        // SEND TO FIRESTORE
                        // ====================================================

                        boolean success = FirestoreService.createDocument(
                                        COLLECTION,
                                        rentalId,
                                        fields);

                        if (success) {

                                System.out.println(
                                                "Rental created successfully.");

                                System.out.println(
                                                "Rental ID: " + rentalId);

                                return true;
                        }

                        System.out.println(
                                        "Failed to create rental.");

                        return false;

                } catch (Exception e) {

                        System.out.println(
                                        "Error while creating rental:");

                        e.printStackTrace();

                        return false;
                }
        }
        // ============================================================
        // GET RENTALS BY CATEGORY
        // ============================================================

        public static List<RentalModel> getRentalsByCategory(
                        String category) {

                List<RentalModel> rentals = new ArrayList<>();

                if (category == null ||
                                category.trim().isEmpty()) {

                        System.out.println(
                                        "Category cannot be empty.");

                        return rentals;
                }

                try {

                        JsonArray documents = FirestoreService
                                        .getCollectionDocuments(
                                                        COLLECTION);

                        for (JsonElement element : documents) {

                                if (!element.isJsonObject()) {
                                        continue;
                                }

                                JsonObject document = element.getAsJsonObject();

                                if (!document.has("fields")) {
                                        continue;
                                }

                                JsonObject fields = document.getAsJsonObject(
                                                "fields");

                                // ------------------------------------------------
                                // CATEGORY
                                // ------------------------------------------------

                                String rentalCategory = getStringField(
                                                fields,
                                                "category");

                                if (rentalCategory == null) {
                                        continue;
                                }

                                if (!rentalCategory.trim()
                                                .equalsIgnoreCase(
                                                                category.trim())) {

                                        continue;
                                }

                                // ------------------------------------------------
                                // CREATE RENTAL MODEL
                                // ------------------------------------------------

                                RentalModel rental = new RentalModel();

                                rental.setRentalId(
                                                getStringField(
                                                                fields,
                                                                "rentalId"));

                                rental.setOwnerId(
                                                getStringField(
                                                                fields,
                                                                "ownerId"));

                                rental.setRentalName(
                                                getStringField(
                                                                fields,
                                                                "rentalName"));

                                rental.setCategory(
                                                rentalCategory);

                                rental.setSubcategory(
                                                getStringField(
                                                                fields,
                                                                "subcategory"));

                                rental.setDescription(
                                                getStringField(
                                                                fields,
                                                                "description"));

                                // ------------------------------------------------
                                // PRICING
                                // ------------------------------------------------

                                rental.setPricePerDay(
                                                getDoubleField(
                                                                fields,
                                                                "pricePerDay"));

                                rental.setPricePerWeek(
                                                getDoubleField(
                                                                fields,
                                                                "pricePerWeek"));

                                rental.setPricePerMonth(
                                                getDoubleField(
                                                                fields,
                                                                "pricePerMonth"));

                                rental.setSecurityDeposit(
                                                getDoubleField(
                                                                fields,
                                                                "securityDeposit"));

                                // ------------------------------------------------
                                // AVAILABILITY
                                // ------------------------------------------------

                                rental.setAvailableFrom(
                                                getStringField(
                                                                fields,
                                                                "availableFrom"));

                                rental.setAvailableUntil(
                                                getStringField(
                                                                fields,
                                                                "availableUntil"));

                                rental.setMinDays(
                                                getIntegerField(
                                                                fields,
                                                                "minDays"));

                                rental.setMaxDays(
                                                getIntegerField(
                                                                fields,
                                                                "maxDays"));

                                // ------------------------------------------------
                                // LOCATION
                                // ------------------------------------------------

                                rental.setAddress(
                                                getStringField(
                                                                fields,
                                                                "address"));

                                rental.setCity(
                                                getStringField(
                                                                fields,
                                                                "city"));

                                rental.setState(
                                                getStringField(
                                                                fields,
                                                                "state"));

                                rental.setPinCode(
                                                getStringField(
                                                                fields,
                                                                "pinCode"));

                                // ------------------------------------------------
                                // RENTAL TERMS
                                // ------------------------------------------------

                                rental.setRentalRules(
                                                getStringField(
                                                                fields,
                                                                "rentalRules"));

                                rental.setCancellationPolicy(
                                                getStringField(
                                                                fields,
                                                                "cancellationPolicy"));

                                // ------------------------------------------------
                                // SYSTEM INFORMATION
                                // ------------------------------------------------

                                rental.setStatus(
                                                getStringField(
                                                                fields,
                                                                "status"));

                                rental.setCreatedAt(
                                                getStringField(
                                                                fields,
                                                                "createdAt"));

                                // ------------------------------------------------
                                // ADD TO RESULT
                                // ------------------------------------------------

                                rentals.add(rental);
                        }

                        System.out.println(
                                        "Category: " + category);

                        System.out.println(
                                        "Rentals found: "
                                                        + rentals.size());

                        return rentals;

                } catch (Exception e) {

                        System.out.println(
                                        "Error while getting rentals by category:");

                        e.printStackTrace();

                        return rentals;
                }
        }
        // ============================================================
        // GET STRING FIELD
        // ============================================================

        private static String getStringField(
                        JsonObject fields,
                        String fieldName) {

                if (fields == null ||
                                !fields.has(fieldName)) {

                        return "";
                }

                JsonObject field = fields.getAsJsonObject(fieldName);

                if (field.has("stringValue")) {

                        return field
                                        .get("stringValue")
                                        .getAsString();
                }

                return "";
        }

        // ============================================================
        // GET DOUBLE FIELD
        // ============================================================

        private static double getDoubleField(
                        JsonObject fields,
                        String fieldName) {

                if (fields == null ||
                                !fields.has(fieldName)) {

                        return 0.0;
                }

                JsonObject field = fields.getAsJsonObject(fieldName);

                if (field.has("doubleValue")) {

                        return field
                                        .get("doubleValue")
                                        .getAsDouble();
                }

                if (field.has("integerValue")) {

                        return field
                                        .get("integerValue")
                                        .getAsDouble();
                }

                return 0.0;
        }

        // ============================================================
        // GET INTEGER FIELD
        // ============================================================

        private static int getIntegerField(
                        JsonObject fields,
                        String fieldName) {

                if (fields == null ||
                                !fields.has(fieldName)) {

                        return 0;
                }

                JsonObject field = fields.getAsJsonObject(fieldName);

                if (field.has("integerValue")) {

                        return field
                                        .get("integerValue")
                                        .getAsInt();
                }

                if (field.has("doubleValue")) {

                        return field
                                        .get("doubleValue")
                                        .getAsInt();
                }

                return 0;
        }
}