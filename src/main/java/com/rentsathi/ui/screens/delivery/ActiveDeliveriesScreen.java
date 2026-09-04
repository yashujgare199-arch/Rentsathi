
package com.rentsathi.ui.screens.delivery;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rentsathi.firebase.firestore.FirestoreService;
import com.rentsathi.firebase.authentication.FirebaseSession;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.google.gson.Gson;
import com.rentsathi.model.rental.RentalModel;
import com.rentsathi.model.rental.RentalRequest;

import java.time.LocalDate;

public class ActiveDeliveriesScreen {

        private static final String BLUE = "#3657C8";
        private static final String BACKGROUND = "#F8F9FD";
        private static final String BORDER = "#D5DBE8";
        private static final String TEXT = "#182235";
        private static final String MUTED = "#64748B";
        private static final String GREEN = "#218739";

        public static void show(Stage stage) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: "
                                                + BACKGROUND
                                                + ";");

                VBox content = createContent(stage);

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);
                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                root.setCenter(scrollPane);

                Scene scene = new Scene(
                                root,
                                1500,
                                830);

                stage.setTitle(
                                "RentSathi - Active Delivery");

                stage.setScene(scene);

                stage.setMinWidth(1100);
                stage.setMinHeight(700);

                stage.show();
        }

        private static VBox createContent(
                        Stage stage) {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                28,
                                                30,
                                                30,
                                                30));

                Label title = new Label(
                                "Active Delivery");

                title.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label subtitle = new Label(
                                "Manage your accepted delivery tasks.");

                subtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                VBox heading = new VBox(
                                4,
                                title,
                                subtitle);

                Button back = new Button(
                                "← Back to Dashboard");

                back.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                back.setOnAction(
                                event -> DeliveryPartnerDashboard.show(stage));

                HBox top = new HBox(
                                heading);

                top.setAlignment(
                                Pos.CENTER_LEFT);

                VBox acceptedDeliveries = loadAcceptedDeliveries(stage);

                content.getChildren().addAll(
                                top,
                                back,
                                acceptedDeliveries);

                VBox.setVgrow(
                                acceptedDeliveries,
                                Priority.ALWAYS);

                return content;
        }

        private static VBox loadAcceptedDeliveries(Stage stage) {

                VBox container = new VBox(15);

                JsonArray documents = FirestoreService.getCollectionDocuments(
                                "rental_requests");

                int count = 0;

                for (int i = 0; i < documents.size(); i++) {

                        JsonObject document = documents
                                        .get(i)
                                        .getAsJsonObject();

                        if (!document.has("fields")) {
                                continue;
                        }

                        JsonObject fields = document.getAsJsonObject(
                                        "fields");

                        if (!fields.has(
                                        "deliveryStatus")) {
                                continue;
                        }

                        String deliveryStatus = fields
                                        .getAsJsonObject(
                                                        "deliveryStatus")
                                        .get("stringValue")
                                        .getAsString();

                        if (!"ACCEPTED".equalsIgnoreCase(
                                        deliveryStatus)) {
                                continue;
                        }

                        container.getChildren().add(
                                        createDeliveryCard(
                                                        stage,
                                                        fields));

                        count++;
                }

                if (count == 0) {

                        VBox empty = new VBox(8);

                        empty.setAlignment(
                                        Pos.CENTER);

                        empty.setPadding(
                                        new Insets(70));

                        Label title = new Label(
                                        "No Active Deliveries");

                        title.setStyle(
                                        "-fx-font-size: 20px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + TEXT + ";");

                        Label message = new Label(
                                        "Accepted delivery jobs will appear here.");

                        message.setStyle(
                                        "-fx-font-size: 13px;" +
                                                        "-fx-text-fill: " + MUTED + ";");

                        empty.getChildren().addAll(
                                        title,
                                        message);

                        container.getChildren().add(
                                        empty);
                }

                return container;
        }

        private static VBox createDeliveryCard(
                        Stage stage,
                        JsonObject fields) {

                VBox card = new VBox(10);

                card.setPadding(
                                new Insets(18));

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;" +
                                                "-fx-background-radius: 10px;");

                String requestId = getStringField(
                                fields,
                                "requestId",
                                "Unknown");

                String deliveryStatus = getStringField(
                                fields,
                                "deliveryStatus",
                                "ACCEPTED");

                String startDate = getStringField(
                                fields,
                                "startDate",
                                "");

                String endDate = getStringField(
                                fields,
                                "endDate",
                                "");

                String quantity = getStringField(
                                fields,
                                "quantity",
                                "1");

                Label request = new Label(
                                "Request: " + requestId);

                request.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label status = new Label(
                                "✓ " + deliveryStatus);

                status.setStyle(
                                "-fx-background-color: #E4F7E8;" +
                                                "-fx-text-fill: " + GREEN + ";" +
                                                "-fx-padding: 6px 10px;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;");

                HBox heading = new HBox(
                                10,
                                request,
                                status);

                heading.setAlignment(
                                Pos.CENTER_LEFT);

                Label dates = new Label(
                                "Rental Period: "
                                                + startDate
                                                + " - "
                                                + endDate);

                dates.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                Label qty = new Label(
                                "Quantity: "
                                                + quantity);

                qty.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                Button track = new Button(
                                "Track Delivery →");

                track.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-padding: 10px 18px;" +
                                                "-fx-cursor: hand;");

                RentalRequest trackingRequest = createRentalRequest(
                                fields);

                track.setOnAction(
                                event -> {

                                        System.out.println(
                                                        "TRACK DELIVERY CLICKED: "
                                                                        + trackingRequest.getRequestId());

                                        TrackDeliveryScreen.show(
                                                        trackingRequest);
                                });

                card.getChildren().addAll(
                                heading,
                                dates,
                                qty,
                                track);

                return card;
        }

        private static String getStringField(
                        JsonObject fields,
                        String name,
                        String defaultValue) {

                try {

                        if (!fields.has(name)) {
                                return defaultValue;
                        }

                        JsonObject field = fields.getAsJsonObject(name);

                        if (field.has("stringValue")) {

                                return field
                                                .get("stringValue")
                                                .getAsString();
                        }

                        if (field.has("integerValue")) {

                                return field
                                                .get("integerValue")
                                                .getAsString();
                        }

                        if (field.has("doubleValue")) {

                                return field
                                                .get("doubleValue")
                                                .getAsString();
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return defaultValue;
        }

        private static RentalRequest createRentalRequest(
                        JsonObject fields) {

                RentalRequest request = new RentalRequest();

                try {

                        // -----------------------------------------
                        // REQUEST ID
                        // -----------------------------------------

                        request.setRequestId(
                                        getStringField(
                                                        fields,
                                                        "requestId",
                                                        ""));

                        // -----------------------------------------
                        // CUSTOMER ID
                        // -----------------------------------------

                        request.setCustomerId(
                                        getStringField(
                                                        fields,
                                                        "customerId",
                                                        ""));

                        // -----------------------------------------
                        // START DATE
                        // -----------------------------------------

                        String startDate = getStringField(
                                        fields,
                                        "startDate",
                                        "");

                        if (!startDate.isEmpty()) {

                                request.setStartDate(
                                                LocalDate.parse(
                                                                startDate));
                        }

                        // -----------------------------------------
                        // END DATE
                        // -----------------------------------------

                        String endDate = getStringField(
                                        fields,
                                        "endDate",
                                        "");

                        if (!endDate.isEmpty()) {

                                request.setEndDate(
                                                LocalDate.parse(
                                                                endDate));
                        }

                        // -----------------------------------------
                        // QUANTITY
                        // -----------------------------------------

                        request.setQuantity(
                                        Integer.parseInt(
                                                        getStringField(
                                                                        fields,
                                                                        "quantity",
                                                                        "1")));

                        // -----------------------------------------
                        // FULFILLMENT
                        // -----------------------------------------

                        request.setFulfillmentMethod(
                                        getStringField(
                                                        fields,
                                                        "fulfillmentMethod",
                                                        ""));

                        // -----------------------------------------
                        // PAYMENT
                        // -----------------------------------------

                        request.setPaymentMethod(
                                        getStringField(
                                                        fields,
                                                        "paymentMethod",
                                                        ""));

                        // -----------------------------------------
                        // TOTAL AMOUNT
                        // -----------------------------------------

                        request.setTotalAmount(
                                        Double.parseDouble(
                                                        getStringField(
                                                                        fields,
                                                                        "totalAmount",
                                                                        "0")));

                        // -----------------------------------------
                        // STATUS
                        // -----------------------------------------

                        request.setStatus(
                                        getStringField(
                                                        fields,
                                                        "status",
                                                        ""));

                        // -----------------------------------------
                        // DELIVERY STATUS
                        // -----------------------------------------

                        request.setDeliveryStatus(
                                        getStringField(
                                                        fields,
                                                        "deliveryStatus",
                                                        "ACCEPTED"));

                        // -----------------------------------------
                        // REQUESTED AT
                        // -----------------------------------------

                        request.setRequestedAt(
                                        getStringField(
                                                        fields,
                                                        "requestedAt",
                                                        ""));

                        // -----------------------------------------
                        // RENTAL MODEL
                        // -----------------------------------------

                        if (fields.has("rental")) {

                                String rentalJson = fields
                                                .getAsJsonObject(
                                                                "rental")
                                                .get("stringValue")
                                                .getAsString();

                                RentalModel rental = new Gson().fromJson(
                                                rentalJson,
                                                RentalModel.class);

                                request.setRental(
                                                rental);
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Error creating RentalRequest:");

                        e.printStackTrace();
                }

                return request;
        }
}
