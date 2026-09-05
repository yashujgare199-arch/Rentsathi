package com.rentsathi.ui.screens.admin;

import com.google.api.client.json.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rentsathi.firebase.authentication.FirebaseSession;
import com.rentsathi.firebase.firestore.FirestoreService;
import com.rentsathi.ui.screens.AdminLoginScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdminCustomersScreen {

        private static final String BLUE = "#3657C8";
        private static final String TEXT = "#182235";
        private static final String SECONDARY = "#64708A";
        private static final String BORDER = "#D5DBE8";
        private static final String BACKGROUND = "#F8F9FD";
        private static final String WHITE = "#FFFFFF";

        public static void show(Stage stage) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: "
                                                + BACKGROUND
                                                + ";");

                VBox sidebar = createSidebar(stage);

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

                root.setLeft(sidebar);
                root.setCenter(scrollPane);

                Scene scene = new Scene(
                                root,
                                1500,
                                830);

                scene.setFill(
                                Color.web(BACKGROUND));

                stage.setTitle(
                                "RentSathi - Customers");

                stage.setScene(scene);

                stage.setMinWidth(1100);
                stage.setMinHeight(700);

                stage.show();
        }

        // =========================================================
        // SIDEBAR
        // =========================================================

        private static VBox createSidebar(
                        Stage stage) {

                VBox sidebar = new VBox(8);

                sidebar.setPrefWidth(160);
                sidebar.setMinWidth(160);
                sidebar.setMaxWidth(160);

                sidebar.setPadding(
                                new Insets(
                                                15,
                                                10,
                                                12,
                                                10));

                sidebar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1 0 0;");

                Label brand = new Label("RentSathi");

                brand.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label role = new Label("Admin Panel");

                role.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

                VBox brandBox = new VBox(
                                2,
                                brand,
                                role);

                sidebar.getChildren().add(
                                brandBox);

                Label customers = navigationItem(
                                "♙",
                                "Customers",
                                true);

                customers.setOnMouseClicked(
                                event -> {
                                        // Already on Customers page
                                });

                Label owners = navigationItem(
                                "▣",
                                "Owners",
                                false);

                owners.setOnMouseClicked(
                                event -> {
                                        // AdminOwnersScreen.show(
                                        // stage
                                        // );
                                });

                Label delivery = navigationItem(
                                "▰",
                                "Delivery Partners",
                                false);

                delivery.setOnMouseClicked(
                                event -> {
                                        // AdminDeliveryPartnersScreen.show(
                                        // stage
                                        // );
                                });

                sidebar.getChildren().addAll(
                                customers,
                                owners,
                                delivery);

                VBox.setVgrow(
                                new VBox(),
                                Priority.ALWAYS);

                VBox spacer = new VBox();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                sidebar.getChildren().add(
                                spacer);

                Label help = navigationItem(
                                "?",
                                "Help Center",
                                false);

                sidebar.getChildren().add(
                                help);

                Label logout = navigationItem(
                                "↪",
                                "Logout",
                                false);

                logout.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #D32626;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-padding: 9px 8px;" +
                                                "-fx-cursor: hand;");

                logout.setOnMouseClicked(
                                event -> {
                                        AdminLoginScreen.show(
                                                        stage);
                                });

                sidebar.getChildren().add(
                                logout);

                return sidebar;
        }

        private static Label navigationItem(
                        String icon,
                        String text,
                        boolean selected) {

                Label item = new Label(
                                icon + "   " + text);

                item.setMaxWidth(
                                Double.MAX_VALUE);

                if (selected) {

                        item.setStyle(
                                        "-fx-background-color: #E8EEFF;" +
                                                        "-fx-text-fill: " + BLUE + ";" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-padding: 10px 8px;" +
                                                        "-fx-background-radius: 6px;" +
                                                        "-fx-cursor: hand;");

                } else {

                        item.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: " + TEXT + ";" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-padding: 10px 8px;" +
                                                        "-fx-background-radius: 6px;" +
                                                        "-fx-cursor: hand;");
                }

                return item;
        }

        // =========================================================
        // CONTENT
        // =========================================================

        private static VBox createContent(Stage stage) {

                VBox content = new VBox(18);

                content.setPadding(
                                new Insets(
                                                28,
                                                32,
                                                30,
                                                32));

                Label title = new Label(
                                "Customers");

                title.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label subtitle = new Label(
                                "View customers who have submitted rental requests.");

                subtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " + SECONDARY + ";");

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
                                                "-fx-cursor: hand;" +
                                                "-fx-padding: 5px 0 5px 0;");

                back.setOnAction(
                                event -> {
                                        AdminDashboardScreen.show(
                                                        stage);
                                });

                TextField search = new TextField();

                search.setPromptText(
                                "Search by Customer ID...");

                search.setPrefHeight(
                                40);

                search.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-padding: 0 12px;" +
                                                "-fx-font-size: 13px;");

                VBox tableContainer = new VBox();

                VBox.setVgrow(
                                tableContainer,
                                Priority.ALWAYS);

                tableContainer.getChildren().add(
                                createCustomerTable(""));

                search.textProperty().addListener(
                                (observable, oldValue, newValue) -> {

                                        tableContainer.getChildren().setAll(
                                                        createCustomerTable(
                                                                        newValue.trim()));
                                });

                VBox.setVgrow(
                                tableContainer,
                                Priority.ALWAYS);

                content.getChildren().addAll(
                                heading,
                                back,
                                search,
                                tableContainer);

                return content;
        }

        // =========================================================
        // CUSTOMER TABLE
        // =========================================================

        private static VBox createCustomerTable(
                        String searchText) {

                VBox table = new VBox();

                table.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;" +
                                                "-fx-background-radius: 10px;");

                JsonArray documents = FirestoreService.getCollectionDocuments(
                                "rental_requests");

                // =========================================================
                // HEADER
                // =========================================================

                HBox header = new HBox();

                header.setPadding(
                                new Insets(14));

                header.setStyle(
                                "-fx-background-color: #F3F5FC;" +
                                                "-fx-background-radius: 10px 10px 0 0;");

                Label requestHeader = tableLabel(
                                "REQUEST ID",
                                true);

                Label customerHeader = tableLabel(
                                "CUSTOMER ID",
                                true);

                Label rentalHeader = tableLabel(
                                "RENTAL",
                                true);

                Label periodHeader = tableLabel(
                                "PERIOD",
                                true);

                Label amountHeader = tableLabel(
                                "AMOUNT",
                                true);

                Label statusHeader = tableLabel(
                                "STATUS",
                                true);

                requestHeader.setPrefWidth(190);
                customerHeader.setPrefWidth(190);
                rentalHeader.setPrefWidth(180);
                periodHeader.setPrefWidth(180);
                amountHeader.setPrefWidth(120);
                statusHeader.setPrefWidth(150);

                header.getChildren().addAll(
                                requestHeader,
                                customerHeader,
                                rentalHeader,
                                periodHeader,
                                amountHeader,
                                statusHeader);

                table.getChildren().add(
                                header);

                // =========================================================
                // ACTUAL REQUEST DATA
                // =========================================================

                int visibleRows = 0;

                for (int i = 0; i < documents.size(); i++) {

                        JsonObject document = documents
                                        .get(i)
                                        .getAsJsonObject();

                        if (!document.has("fields")) {
                                continue;
                        }

                        JsonObject fields = document.getAsJsonObject(
                                        "fields");

                        String customerId = getStringField(
                                        fields,
                                        "customerId");

                        // -----------------------------------------------------
                        // SEARCH
                        // -----------------------------------------------------

                        if (!searchText.isEmpty()
                                        && !customerId
                                                        .toLowerCase()
                                                        .contains(
                                                                        searchText.toLowerCase())) {

                                continue;
                        }

                        String requestId = getStringField(
                                        fields,
                                        "requestId");

                        if (requestId.isEmpty()) {

                                // Firestore document name fallback
                                if (document.has("name")) {

                                        String name = document
                                                        .get("name")
                                                        .getAsString();

                                        int lastSlash = name.lastIndexOf('/');

                                        if (lastSlash >= 0) {

                                                requestId = name.substring(
                                                                lastSlash + 1);
                                        }
                                }
                        }

                        String rentalName = getRentalName(
                                        fields);

                        String startDate = getStringField(
                                        fields,
                                        "startDate");

                        String endDate = getStringField(
                                        fields,
                                        "endDate");

                        String period = startDate;

                        if (!endDate.isEmpty()) {

                                period = startDate
                                                + " - "
                                                + endDate;
                        }

                        String amount = getStringField(
                                        fields,
                                        "totalAmount");

                        if (amount.isEmpty()) {

                                amount = getStringField(
                                                fields,
                                                "totalPrice");
                        }

                        String status = getStringField(
                                        fields,
                                        "deliveryStatus");

                        if (status.isEmpty()) {

                                status = getStringField(
                                                fields,
                                                "status");
                        }

                        if (customerId.isEmpty()) {
                                customerId = "Unknown";
                        }

                        if (requestId.isEmpty()) {
                                requestId = "Unknown";
                        }

                        if (rentalName.isEmpty()) {
                                rentalName = "Rental Item";
                        }

                        if (period.isEmpty()) {
                                period = "-";
                        }

                        if (amount.isEmpty()) {
                                amount = "0";
                        }

                        if (status.isEmpty()) {
                                status = "Unknown";
                        }

                        // =====================================================
                        // ROW
                        // =====================================================

                        HBox row = new HBox();

                        row.setPadding(
                                        new Insets(14));

                        row.setStyle(
                                        "-fx-border-color: transparent transparent "
                                                        + BORDER
                                                        + " transparent;" +
                                                        "-fx-border-width: 0 0 1 0;");

                        Label requestLabel = tableLabel(
                                        requestId,
                                        false);

                        Label customerLabel = tableLabel(
                                        customerId,
                                        false);

                        Label rentalLabel = tableLabel(
                                        rentalName,
                                        false);

                        Label periodLabel = tableLabel(
                                        period,
                                        false);

                        Label amountLabel = tableLabel(
                                        "₹" + amount,
                                        false);

                        Label statusLabel = tableLabel(
                                        status,
                                        false);

                        requestLabel.setPrefWidth(190);
                        customerLabel.setPrefWidth(190);
                        rentalLabel.setPrefWidth(180);
                        periodLabel.setPrefWidth(180);
                        amountLabel.setPrefWidth(120);
                        statusLabel.setPrefWidth(150);

                        row.getChildren().addAll(
                                        requestLabel,
                                        customerLabel,
                                        rentalLabel,
                                        periodLabel,
                                        amountLabel,
                                        statusLabel);

                        table.getChildren().add(
                                        row);

                        visibleRows++;
                }

                // =========================================================
                // EMPTY RESULT
                // =========================================================

                if (visibleRows == 0) {

                        Label empty = new Label(
                                        searchText.isEmpty()
                                                        ? "No customer booking data available."
                                                        : "No customer found for: "
                                                                        + searchText);

                        empty.setStyle(
                                        "-fx-font-size: 14px;" +
                                                        "-fx-text-fill: "
                                                        + SECONDARY
                                                        + ";");

                        VBox emptyBox = new VBox(
                                        empty);

                        emptyBox.setAlignment(
                                        Pos.CENTER);

                        emptyBox.setPadding(
                                        new Insets(50));

                        table.getChildren().add(
                                        emptyBox);
                }

                return table;
        }

        private static String getRentalName(
                        JsonObject fields) {

                try {

                        if (!fields.has("rental")) {
                                return "";
                        }

                        JsonObject rentalField = fields.getAsJsonObject(
                                        "rental");

                        if (!rentalField.has(
                                        "stringValue")) {

                                return "";
                        }

                        String rentalJson = rentalField
                                        .get("stringValue")
                                        .getAsString();

                        JsonObject rentalObject = new Gson().fromJson(
                                        rentalJson,
                                        JsonObject.class);

                        if (rentalObject.has(
                                        "rentalName")) {

                                return rentalObject
                                                .get("rentalName")
                                                .getAsString();
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

                return "";
        }

        private static Label tableLabel(
                        String text,
                        boolean header) {

                Label label = new Label(text);

                label.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: "
                                                + (header
                                                                ? "bold;"
                                                                : "normal;")
                                                +
                                                "-fx-text-fill: "
                                                + (header
                                                                ? SECONDARY
                                                                : TEXT)
                                                + ";");

                return label;
        }

        // =========================================================
        // FIRESTORE FIELD HELPER
        // =========================================================

        private static String getStringField(
                        JsonObject fields,
                        String name) {

                try {

                        if (!fields.has(name)) {
                                return "";
                        }

                        JsonObject field = fields.getAsJsonObject(
                                        name);

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

                return "";
        }
}
