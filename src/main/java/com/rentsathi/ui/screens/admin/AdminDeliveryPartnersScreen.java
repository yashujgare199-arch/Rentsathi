package com.rentsathi.ui.screens.admin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rentsathi.firebase.firestore.FirestoreService;
import com.rentsathi.ui.screens.AdminLoginScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdminDeliveryPartnersScreen {

    private static final String BLUE = "#3657C8";
    private static final String TEXT = "#182235";
    private static final String SECONDARY = "#64708A";
    private static final String BORDER = "#D5DBE8";
    private static final String BACKGROUND = "#F8F9FD";

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: "
                        + BACKGROUND
                        + ";"
        );

        VBox sidebar =
                createSidebar(stage);

        VBox content =
                createContent();

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;"
        );

        root.setLeft(sidebar);
        root.setCenter(scrollPane);

        Scene scene =
                new Scene(
                        root,
                        1500,
                        830
                );

        stage.setTitle(
                "RentSathi - Delivery Partners"
        );

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

        VBox sidebar =
                new VBox(8);

        sidebar.setPrefWidth(160);
        sidebar.setMinWidth(160);
        sidebar.setMaxWidth(160);

        sidebar.setPadding(
                new Insets(
                        15,
                        10,
                        12,
                        10
                )
        );

        sidebar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        Label brand =
                new Label("RentSathi");

        brand.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        Label role =
                new Label("Admin Panel");

        role.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        VBox brandBox =
                new VBox(
                        2,
                        brand,
                        role
                );

        sidebar.getChildren().add(
                brandBox
        );

        Label customers =
                navigationItem(
                        "♙",
                        "Customers",
                        false
                );

        customers.setOnMouseClicked(
                event ->
                        AdminCustomersScreen.show(stage)
        );

        Label owners =
                navigationItem(
                        "▣",
                        "Owners",
                        false
                );

        owners.setOnMouseClicked(
                event ->
                        AdminOwnersScreen.show(stage)
        );

        Label delivery =
                navigationItem(
                        "▰",
                        "Delivery Partners",
                        true
                );

        sidebar.getChildren().addAll(
                customers,
                owners,
                delivery
        );

        VBox spacer =
                new VBox();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        sidebar.getChildren().add(
                spacer
        );

        Label help =
                navigationItem(
                        "?",
                        "Help Center",
                        false
                );

        sidebar.getChildren().add(
                help
        );

        Label logout =
                navigationItem(
                        "↪",
                        "Logout",
                        false
                );

        logout.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #D32626;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 9px 8px;" +
                "-fx-cursor: hand;"
        );

        logout.setOnMouseClicked(
                event ->
                        AdminLoginScreen.show(stage)
        );

        sidebar.getChildren().add(
                logout
        );

        return sidebar;
    }

    private static Label navigationItem(
            String icon,
            String text,
            boolean selected) {

        Label item =
                new Label(
                        icon + "   " + text
                );

        item.setMaxWidth(
                Double.MAX_VALUE
        );

        item.setStyle(
                selected
                        ? "-fx-background-color: #E8EEFF;" +
                          "-fx-text-fill: " + BLUE + ";" +
                          "-fx-font-size: 12px;" +
                          "-fx-font-weight: bold;" +
                          "-fx-padding: 10px 8px;" +
                          "-fx-background-radius: 6px;" +
                          "-fx-cursor: hand;"
                        : "-fx-background-color: transparent;" +
                          "-fx-text-fill: " + TEXT + ";" +
                          "-fx-font-size: 12px;" +
                          "-fx-padding: 10px 8px;" +
                          "-fx-background-radius: 6px;" +
                          "-fx-cursor: hand;"
        );

        return item;
    }

    // =========================================================
    // CONTENT
    // =========================================================

    private static VBox createContent() {

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(
                        28,
                        32,
                        30,
                        32
                )
        );

        Label title =
                new Label(
                        "Delivery Partners"
                );

        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "View delivery activity from accepted and completed jobs."
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        VBox summary =
                createSummary();

        VBox table =
                createDeliveryTable();

        VBox.setVgrow(
                table,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                heading,
                summary,
                table
        );

        return content;
    }

    // =========================================================
    // SUMMARY
    // =========================================================

    private static VBox createSummary() {

        JsonArray documents =
                FirestoreService.getCollectionDocuments(
                        "rental_requests"
                );

        int accepted = 0;
        int delivered = 0;
        int active = 0;

        for (int i = 0;
                i < documents.size();
                i++) {

            JsonObject document =
                    documents.get(i)
                            .getAsJsonObject();

            if (!document.has("fields")) {
                continue;
            }

            JsonObject fields =
                    document.getAsJsonObject(
                            "fields"
                    );

            String status =
                    getStringField(
                            fields,
                            "deliveryStatus"
                    );

            if ("ACCEPTED".equalsIgnoreCase(status)) {
                accepted++;
                active++;
            }

            if ("DELIVERED".equalsIgnoreCase(status)) {
                delivered++;
            }
        }

        HBox cards =
                new HBox(14);

        cards.getChildren().addAll(
                summaryCard(
                        "Accepted",
                        String.valueOf(accepted)
                ),
                summaryCard(
                        "Active",
                        String.valueOf(active)
                ),
                summaryCard(
                        "Delivered",
                        String.valueOf(delivered)
                )
        );

        VBox box =
                new VBox(cards);

        return box;
    }

    private static VBox summaryCard(
            String title,
            String value) {

        VBox card =
                new VBox(6);

        card.setPrefWidth(210);

        card.setPadding(
                new Insets(16)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + SECONDARY + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        card.getChildren().addAll(
                titleLabel,
                valueLabel
        );

        return card;
    }

    // =========================================================
    // TABLE
    // =========================================================

    private static VBox createDeliveryTable() {

        VBox table =
                new VBox();

        table.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );

        JsonArray documents =
                FirestoreService.getCollectionDocuments(
                        "rental_requests"
                );

        Map<String, Integer> partnerJobs =
                new LinkedHashMap<>();

        for (int i = 0;
                i < documents.size();
                i++) {

            JsonObject document =
                    documents.get(i)
                            .getAsJsonObject();

            if (!document.has("fields")) {
                continue;
            }

            JsonObject fields =
                    document.getAsJsonObject(
                            "fields"
                    );

            String deliveryStatus =
                    getStringField(
                            fields,
                            "deliveryStatus"
                    );

            if (deliveryStatus.isEmpty()) {
                continue;
            }

            String partnerId =
                    getStringField(
                            fields,
                            "deliveryPartnerId"
                    );

            if (partnerId.isEmpty()) {
                partnerId =
                        "Assigned Partner";
            }

            partnerJobs.put(
                    partnerId,
                    partnerJobs.getOrDefault(
                            partnerId,
                            0
                    ) + 1
            );
        }

        HBox header =
                new HBox();

        header.setPadding(
                new Insets(14)
        );

        header.setStyle(
                "-fx-background-color: #F3F5FC;" +
                "-fx-background-radius: 10px 10px 0 0;"
        );

        Label partnerHeader =
                tableLabel(
                        "PARTNER ID",
                        true
                );

        Label jobsHeader =
                tableLabel(
                        "DELIVERY JOBS",
                        true
                );

        Label activityHeader =
                tableLabel(
                        "ACTIVITY",
                        true
                );

        HBox.setHgrow(
                partnerHeader,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                jobsHeader,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                activityHeader,
                Priority.ALWAYS
        );

        header.getChildren().addAll(
                partnerHeader,
                jobsHeader,
                activityHeader
        );

        table.getChildren().add(
                header
        );

        if (partnerJobs.isEmpty()) {

            Label empty =
                    new Label(
                            "No delivery partner activity available."
                    );

            empty.setStyle(
                    "-fx-font-size: 14px;" +
                    "-fx-text-fill: " + SECONDARY + ";"
            );

            VBox emptyBox =
                    new VBox(empty);

            emptyBox.setAlignment(
                    Pos.CENTER
            );

            emptyBox.setPadding(
                    new Insets(50)
            );

            table.getChildren().add(
                    emptyBox
            );

            return table;
        }

        for (Map.Entry<String, Integer> entry :
                partnerJobs.entrySet()) {

            HBox row =
                    new HBox();

            row.setPadding(
                    new Insets(14)
            );

            row.setStyle(
                    "-fx-border-color: transparent transparent "
                            + BORDER
                            + " transparent;" +
                    "-fx-border-width: 0 0 1 0;"
            );

            Label partner =
                    tableLabel(
                            entry.getKey(),
                            false
                    );

            Label jobs =
                    tableLabel(
                            String.valueOf(
                                    entry.getValue()
                            ),
                            false
                    );

            Label activity =
                    tableLabel(
                            "Active",
                            false
                    );

            HBox.setHgrow(
                    partner,
                    Priority.ALWAYS
            );

            HBox.setHgrow(
                    jobs,
                    Priority.ALWAYS
            );

            HBox.setHgrow(
                    activity,
                    Priority.ALWAYS
            );

            row.getChildren().addAll(
                    partner,
                    jobs,
                    activity
            );

            table.getChildren().add(
                    row
            );
        }

        return table;
    }

    private static Label tableLabel(
            String text,
            boolean header) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: "
                        + (header
                                ? "bold;"
                                : "normal;") +
                "-fx-text-fill: "
                        + (header
                                ? SECONDARY
                                : TEXT)
                        + ";"
        );

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

            JsonObject field =
                    fields.getAsJsonObject(name);

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
