package com.rentsathi.model.rental;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.rentsathi.firebase.firestore.FirestoreService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalRequestStore {

        private static final List<RentalRequest> requests = new ArrayList<>();

        private static final String COLLECTION = "rental_requests";

        private static final Gson GSON = new Gson();

        // ============================================================
        // ADD REQUEST
        // ============================================================

        public static void addRequest(
                        RentalRequest request) {

                // Keep local copy
                requests.add(request);

                // Save to Firestore
                saveToFirestore(request);
        }

        // ============================================================
        // SAVE REQUEST TO FIRESTORE
        // ============================================================

        private static void saveToFirestore(
                        RentalRequest request) {

                try {

                        JsonObject fields = new JsonObject();

                        fields.add(
                                        "requestId",
                                        FirestoreService.stringField(
                                                        request.getRequestId()));

                        fields.add(
                                        "customerId",
                                        FirestoreService.stringField(
                                                        request.getCustomerId()));

                        System.out.println(
                                        "CUSTOMER ADDRESS BEFORE FIRESTORE = ["
                                                        + request.getCustomerAddress()
                                                        + "]");

                        fields.add(
                                        "customerAddress",
                                        FirestoreService.stringField(
                                                        request.getCustomerAddress()));

                        fields.add(
                                        "startDate",
                                        FirestoreService.stringField(
                                                        request.getStartDate().toString()));

                        fields.add(
                                        "endDate",
                                        FirestoreService.stringField(
                                                        request.getEndDate().toString()));

                        fields.add(
                                        "quantity",
                                        FirestoreService.integerField(
                                                        request.getQuantity()));

                        fields.add(
                                        "fulfillmentMethod",
                                        FirestoreService.stringField(
                                                        request.getFulfillmentMethod()));

                        fields.add(
                                        "paymentMethod",
                                        FirestoreService.stringField(
                                                        request.getPaymentMethod()));

                        fields.add(
                                        "totalAmount",
                                        FirestoreService.doubleField(
                                                        request.getTotalAmount()));

                        fields.add(
                                        "status",
                                        FirestoreService.stringField(
                                                        request.getStatus()));

                        fields.add(
                                        "requestedAt",
                                        FirestoreService.stringField(
                                                        request.getRequestedAt()));

                        /*
                         * Store the RentalModel as JSON text.
                         */
                        fields.add(
                                        "rental",
                                        FirestoreService.stringField(
                                                        GSON.toJson(
                                                                        request.getRental())));

                        boolean success = FirestoreService.createDocument(
                                        COLLECTION,
                                        request.getRequestId(),
                                        fields);

                        if (success) {

                                System.out.println(
                                                "Rental request saved to Firestore.");

                        } else {

                                System.out.println(
                                                "Failed to save rental request.");

                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Error saving rental request:");

                        e.printStackTrace();
                }
        }

        // ============================================================
        // GET ALL REQUESTS
        // ============================================================

        public static List<RentalRequest> getRequests() {

                /*
                 * Load from Firestore.
                 */
                loadFromFirestore();

                return requests;
        }

        // ============================================================
        // GET REQUESTS BY STATUS
        // ============================================================

        public static List<RentalRequest> getRequestsByStatus(
                        String status) {

                /*
                 * Refresh local list from Firestore.
                 */
                loadFromFirestore();

                List<RentalRequest> result = new ArrayList<>();

                for (RentalRequest request : requests) {

                        if (status.equalsIgnoreCase(
                                        request.getStatus())) {

                                result.add(request);
                        }
                }

                return result;
        }

        // ============================================================
        // LOAD FROM FIRESTORE
        // ============================================================

        private static void loadFromFirestore() {

                try {

                        JsonArray documents = FirestoreService.getCollectionDocuments(
                                        COLLECTION);

                        /*
                         * Clear old local data before loading.
                         */
                        requests.clear();

                        for (int i = 0; i < documents.size(); i++) {

                                JsonObject document = documents
                                                .get(i)
                                                .getAsJsonObject();

                                if (!document.has("fields")) {
                                        continue;
                                }

                                JsonObject fields = document.getAsJsonObject(
                                                "fields");

                                System.out.println(
                                                "========== FIRESTORE REQUEST ==========");

                                System.out.println(
                                                fields.toString());

                                System.out.println(
                                                "========================================");

                                RentalRequest request = new RentalRequest();

                                // -----------------------------
                                // REQUEST ID
                                // -----------------------------

                                if (fields.has("requestId")) {

                                        request.setRequestId(
                                                        fields
                                                                        .getAsJsonObject("requestId")
                                                                        .get("stringValue")
                                                                        .getAsString());
                                }

                                // -----------------------------
                                // CUSTOMER ID
                                // -----------------------------

                                if (fields.has("customerId")) {

                                        request.setCustomerId(
                                                        fields
                                                                        .getAsJsonObject("customerId")
                                                                        .get("stringValue")
                                                                        .getAsString());
                                }

                                // -----------------------------
                                // START DATE
                                // -----------------------------

                                if (fields.has("startDate")) {

                                        request.setStartDate(
                                                        LocalDate.parse(
                                                                        fields
                                                                                        .getAsJsonObject("startDate")
                                                                                        .get("stringValue")
                                                                                        .getAsString()));
                                }

                                // -----------------------------
                                // END DATE
                                // -----------------------------

                                if (fields.has("endDate")) {

                                        request.setEndDate(
                                                        LocalDate.parse(
                                                                        fields
                                                                                        .getAsJsonObject("endDate")
                                                                                        .get("stringValue")
                                                                                        .getAsString()));
                                }

                                // -----------------------------
                                // QUANTITY
                                // -----------------------------

                                if (fields.has("quantity")) {

                                        request.setQuantity(
                                                        Integer.parseInt(
                                                                        fields
                                                                                        .getAsJsonObject("quantity")
                                                                                        .get("integerValue")
                                                                                        .getAsString()));
                                }

                                // -----------------------------
                                // FULFILLMENT
                                // -----------------------------

                                if (fields.has("fulfillmentMethod")) {

                                        request.setFulfillmentMethod(
                                                        fields
                                                                        .getAsJsonObject("fulfillmentMethod")
                                                                        .get("stringValue")
                                                                        .getAsString());
                                }

                                // -----------------------------
                                // PAYMENT METHOD
                                // -----------------------------

                                if (fields.has("paymentMethod")) {

                                        request.setPaymentMethod(
                                                        fields
                                                                        .getAsJsonObject("paymentMethod")
                                                                        .get("stringValue")
                                                                        .getAsString());
                                }

                                // -----------------------------
                                // TOTAL AMOUNT
                                // -----------------------------

                                if (fields.has("totalAmount")) {

                                        request.setTotalAmount(
                                                        fields
                                                                        .getAsJsonObject("totalAmount")
                                                                        .get("doubleValue")
                                                                        .getAsDouble());
                                }

                                // -----------------------------
                                // STATUS
                                // -----------------------------

                                if (fields.has("status")) {

                                        request.setStatus(
                                                        fields
                                                                        .getAsJsonObject("status")
                                                                        .get("stringValue")
                                                                        .getAsString());
                                }
                                // -----------------------------
                                // DELIVERY STATUS
                                // -----------------------------

                                if (fields.has("deliveryStatus")) {

                                        request.setDeliveryStatus(
                                                        fields
                                                                        .getAsJsonObject("deliveryStatus")
                                                                        .get("stringValue")
                                                                        .getAsString());
                                }

                                // -----------------------------
                                // CUSTOMER ADDRESS
                                // -----------------------------
                                if (fields.has("customerAddress")) {

                                        request.setCustomerAddress(
                                                        fields
                                                                        .getAsJsonObject("customerAddress")
                                                                        .get("stringValue")
                                                                        .getAsString());
                                }
                                System.out.println(
                                                "LOADED CUSTOMER ADDRESS = ["
                                                                + request.getCustomerAddress()
                                                                + "]");

                                // -----------------------------
                                // REQUESTED AT
                                // -----------------------------

                                if (fields.has("requestedAt")) {

                                        request.setRequestedAt(
                                                        fields
                                                                        .getAsJsonObject("requestedAt")
                                                                        .get("stringValue")
                                                                        .getAsString());
                                }

                                // -----------------------------
                                // RENTAL
                                // -----------------------------

                                if (fields.has("rental")) {

                                        String rentalJson = fields
                                                        .getAsJsonObject("rental")
                                                        .get("stringValue")
                                                        .getAsString();

                                        RentalModel rental = GSON.fromJson(
                                                        rentalJson,
                                                        RentalModel.class);

                                        request.setRental(rental);
                                }

                                requests.add(request);
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Error loading rental requests from Firestore:");

                        e.printStackTrace();
                }
        }
}