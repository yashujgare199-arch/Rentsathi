package com.rentsathi.ui.screens.delivery;

import com.rentsathi.ui.screens.DeliveryPartnerLoginScreen;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import com.rentsathi.firebase.firestore.FirestoreService;
import com.rentsathi.model.rental.RentalModel;
import com.rentsathi.model.rental.RentalRequest;
import com.rentsathi.model.rental.RentalRequestStore;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.List;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Separator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DeliveryPartnerDashboard {

        private static final String BG = "#F6F7FB";
        private static final String BLUE = "#3659C9";
        private static final String LIGHT_BLUE = "#DDE7FF";
        private static final String TEXT = "#111827";
        private static final String MUTED = "#64748B";
        private static final String BORDER = "#D5DCE8";
        private static final String WHITE = "#FFFFFF";

        // ============================================================
        // SHOW
        // ============================================================

        public static void show(Stage stage) {

                BorderPane root = new BorderPane();

                root.setStyle(
                                "-fx-background-color: " + BG + ";");

                root.setLeft(
                                createSidebar(stage));

                BorderPane mainArea = new BorderPane();

                mainArea.setTop(
                                createTopBar());

                VBox content = createContent();

                ScrollPane scrollPane = new ScrollPane(content);

                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(false);

                scrollPane.setPannable(true);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;");

                mainArea.setCenter(scrollPane);

                root.setCenter(mainArea);

                Scene scene = new Scene(
                                root,
                                1500,
                                830);

                scene.setFill(
                                Color.web(BG));

                stage.setTitle(
                                "RentSathi - Delivery Partner");

                stage.setScene(scene);

                stage.setWidth(1500);
                stage.setHeight(830);

                stage.setMinWidth(1100);
                stage.setMinHeight(700);

                stage.centerOnScreen();
                stage.show();
        }

        // ============================================================
        // SIDEBAR
        // ============================================================

        private static VBox createSidebar(
                        Stage stage) {

                VBox sidebar = new VBox(10);

                sidebar.setPrefWidth(280);
                sidebar.setMinWidth(280);
                sidebar.setMaxWidth(280);

                sidebar.setPadding(
                                new Insets(
                                                25,
                                                15,
                                                25,
                                                15));

                sidebar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 1 0 0;");

                Label brand = new Label(
                                "RentSathi");

                brand.setStyle(
                                "-fx-font-size: 24px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label role = new Label(
                                "Delivery Partner");

                role.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                VBox brandBox = new VBox(3);

                brandBox.getChildren().addAll(
                                brand,
                                role);

                Button dashboard = navigationButton(
                                "⌂",
                                "Dashboard");

                Button available = navigationButton(
                                "▣",
                                "Available Deliveries");

                Button active = navigationButton(
                                "●",
                                "Active Delivery");

                Button completed = navigationButton(
                                "✓",
                                "Completed Deliveries");

                Button returns = navigationButton(
                                "↩",
                                "Returns");

                dashboard.setStyle(
                                selectedNavigationStyle());

                Region spacer = new Region();

                VBox.setVgrow(
                                spacer,
                                Priority.ALWAYS);

                Button help = navigationButton(
                                "?",
                                "Help Center");

                Button logout = navigationButton(
                                "↪",
                                "Logout");

                logout.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #D32626;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 11px 14px;" +
                                                "-fx-cursor: hand;");

                /*
                 * We will connect these navigation buttons to their
                 * real screens after the dashboard UI is confirmed.
                 */

                logout.setOnAction(
                                event -> {
                                        DeliveryPartnerLoginScreen.show(stage);
                                });

                sidebar.getChildren().addAll(
                                brandBox,
                                dashboard,
                                available,
                                active,
                                completed,
                                returns,
                                spacer,
                                help,
                                logout);

                return sidebar;
        }

        // ============================================================
        // TOP BAR
        // ============================================================

        private static HBox createTopBar() {

                HBox topBar = new HBox();

                topBar.setAlignment(
                                Pos.CENTER_LEFT);

                topBar.setPadding(
                                new Insets(
                                                15,
                                                28,
                                                15,
                                                28));

                topBar.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-width: 0 0 1 0;");

                Label search = new Label(
                                "Search delivery jobs...");

                search.setStyle(
                                "-fx-background-color: #FAFAFD;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-text-fill: #A0A9B8;" +
                                                "-fx-padding: 12px 18px;" +
                                                "-fx-min-width: 420px;");

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                Button help = new Button("?");

                help.setMinSize(
                                32,
                                32);

                help.setMaxSize(
                                32,
                                32);

                help.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BLUE + ";" +
                                                "-fx-text-fill: " + BLUE + ";" +
                                                "-fx-border-radius: 50%;" +
                                                "-fx-background-radius: 50%;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                topBar.getChildren().addAll(
                                search,
                                spacer,
                                help);

                return topBar;
        }

        // ============================================================
        // CONTENT
        // ============================================================

        private static VBox createContent() {

                VBox content = new VBox(20);

                content.setPadding(
                                new Insets(
                                                28,
                                                30,
                                                35,
                                                30));

                Label title = new Label(
                                "Dashboard");

                title.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label subtitle = new Label(
                                "Manage your delivery tasks and active deliveries.");

                subtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                // Load current requests from Firestore
                List<RentalRequest> availableDeliveries = RentalRequestStore.getRequestsByStatus(
                                "OUT_FOR_DELIVERY");

                List<RentalRequest> allRequests = RentalRequestStore.getRequests();

                int activeDeliveryCount = 0;

                for (RentalRequest request : allRequests) {

                        if ("OUT_FOR_DELIVERY".equalsIgnoreCase(
                                        request.getStatus())
                                        && "ACCEPTED".equalsIgnoreCase(
                                                        request.getDeliveryStatus())) {

                                activeDeliveryCount++;
                        }
                }

                List<RentalRequest> completedDeliveries = RentalRequestStore.getRequestsByStatus(
                                "COMPLETED");

                HBox summaryCards = new HBox(18);

                summaryCards.getChildren().addAll(

                                summaryCard(
                                                "Available Deliveries",
                                                String.valueOf(availableDeliveries.size()),
                                                "Jobs available to accept"),

                                summaryCard(
                                                "Active Delivery",
                                                String.valueOf(activeDeliveryCount),
                                                "Currently assigned"),

                                summaryCard(
                                                "Completed Deliveries",
                                                String.valueOf(completedDeliveries.size()),
                                                "Successfully completed"));

                VBox availableSection = createAvailableDeliverySection();

                content.getChildren().addAll(
                                title,
                                subtitle,
                                summaryCards,
                                availableSection);

                return content;
        }

        private static VBox createAvailableDeliverySection() {

                VBox section = new VBox(12);

                Label availableTitle = new Label(
                                "Available Delivery Jobs");

                availableTitle.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                VBox jobs = new VBox(15);

                List<RentalRequest> requests = loadAvailableDeliveries();

                if (requests.isEmpty()) {

                        VBox emptyCard = new VBox(8);

                        emptyCard.setAlignment(
                                        Pos.CENTER);

                        emptyCard.setPrefHeight(
                                        220);

                        emptyCard.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: " + BORDER + ";" +
                                                        "-fx-border-radius: 10px;" +
                                                        "-fx-background-radius: 10px;");

                        Label emptyTitle = new Label(
                                        "No delivery jobs available");

                        emptyTitle.setStyle(
                                        "-fx-font-size: 17px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-text-fill: " + TEXT + ";");

                        Label emptyMessage = new Label(
                                        "New delivery requests will appear here.");

                        emptyMessage.setStyle(
                                        "-fx-font-size: 13px;" +
                                                        "-fx-text-fill: " + MUTED + ";");

                        emptyCard.getChildren().addAll(
                                        emptyTitle,
                                        emptyMessage);

                        jobs.getChildren().add(
                                        emptyCard);

                } else {

                        for (RentalRequest request : requests) {

                                jobs.getChildren().add(
                                                createDeliveryJobCard(
                                                                request));
                        }
                }

                section.getChildren().addAll(
                                availableTitle,
                                jobs);

                return section;
        }

        private static List<RentalRequest> loadAvailableDeliveries() {

                List<RentalRequest> all = RentalRequestStore.getRequests();

                List<RentalRequest> result = new ArrayList<>();

                for (RentalRequest request : all) {

                        boolean outForDelivery = "OUT_FOR_DELIVERY".equalsIgnoreCase(
                                        request.getStatus());

                        boolean notAccepted = !"ACCEPTED".equalsIgnoreCase(
                                        request.getDeliveryStatus());

                        if (outForDelivery && notAccepted) {
                                result.add(request);
                        }
                }

                return result;
        }

        private static VBox createDeliveryJobCard(
                        RentalRequest request) {

                VBox card = new VBox(12);

                card.setPadding(
                                new Insets(18));

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 10px;" +
                                                "-fx-background-radius: 10px;");

                // ============================================================
                // TOP
                // ============================================================

                HBox top = new HBox(15);

                top.setAlignment(
                                Pos.CENTER_LEFT);

                // ------------------------------------------------------------
                // IMAGE
                // ------------------------------------------------------------

                StackPane imageBox = createRentalImage(request);

                // ------------------------------------------------------------
                // INFORMATION
                // ------------------------------------------------------------

                VBox info = new VBox(6);

                String rentalName = request.getRental() != null
                                ? request.getRental().getRentalName()
                                : "Rental Item";

                Label name = new Label(
                                rentalName);

                name.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label customer = new Label(
                                "Customer: "
                                                + request.getCustomerId());

                customer.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                Label dates = new Label(
                                "Rental Period: "
                                                + request.getStartDate()
                                                + " - "
                                                + request.getEndDate());

                dates.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                Label amount = new Label(
                                "Amount: ₹"
                                                + String.format(
                                                                "%.2f",
                                                                request.getTotalAmount()));

                amount.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label quantity = new Label(
                                "Quantity: "
                                                + request.getQuantity());

                quantity.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                info.getChildren().addAll(
                                name,
                                customer,
                                dates,
                                amount,
                                quantity);

                // ------------------------------------------------------------
                // SPACER
                // ------------------------------------------------------------

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // ------------------------------------------------------------
                // STATUS
                // ------------------------------------------------------------

                Label status = new Label(
                                "◷ Pending");

                status.setStyle(
                                "-fx-background-color: #FFF3D6;" +
                                                "-fx-text-fill: #D48A00;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-padding: 6px 10px;" +
                                                "-fx-background-radius: 12px;");

                // ------------------------------------------------------------
                // ACCEPT BUTTON
                // ------------------------------------------------------------

                Button acceptJob = new Button(
                                "✓ Accept Job");

                acceptJob.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-padding: 10px 20px;" +
                                                "-fx-cursor: hand;");

                Button trackDelivery = new Button(
                                "➤ Track Delivery");

                trackDelivery.setStyle(
                                "-fx-background-color: " + BLUE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-padding: 10px 20px;" +
                                                "-fx-cursor: hand;");

                boolean alreadyAccepted = "ACCEPTED".equalsIgnoreCase(
                                request.getDeliveryStatus());

                trackDelivery.setVisible(
                                alreadyAccepted);

                trackDelivery.setManaged(
                                alreadyAccepted);

                trackDelivery.setOnAction(event -> {

                        TrackDeliveryScreen.show(
                                        request);
                });
                acceptJob.setOnAction(event -> {

                        request.setDeliveryStatus(
                                        "ACCEPTED");

                        JsonObject fields = new JsonObject();

                        fields.add(
                                        "deliveryStatus",
                                        FirestoreService.stringField(
                                                        "ACCEPTED"));

                        boolean success = FirestoreService.updateDocument(
                                        "rental_requests",
                                        request.getRequestId(),
                                        fields);

                        if (success) {

                                status.setText(
                                                "✓ Accepted");

                                status.setStyle(
                                                "-fx-background-color: #E4F7E8;" +
                                                                "-fx-text-fill: #218739;" +
                                                                "-fx-font-size: 11px;" +
                                                                "-fx-padding: 6px 10px;" +
                                                                "-fx-background-radius: 12px;");

                                acceptJob.setVisible(false);
                                acceptJob.setManaged(false);

                                trackDelivery.setVisible(true);
                                trackDelivery.setManaged(true);

                                showMessage(
                                                "Delivery Accepted",
                                                "The delivery job has been accepted.");

                        } else {

                                request.setDeliveryStatus(
                                                "PENDING");

                                showMessage(
                                                "Error",
                                                "Could not accept the delivery job.");
                        }
                });
                // ------------------------------------------------------------
                // TOP ROW
                // ------------------------------------------------------------

                VBox rightSide = new VBox(10);

                rightSide.setAlignment(
                                Pos.CENTER_RIGHT);

                rightSide.getChildren().addAll(
                                status,
                                acceptJob,
                                trackDelivery);

                top.getChildren().addAll(
                                imageBox,
                                info,
                                spacer,
                                rightSide);

                // ============================================================
                // DELIVERY INFORMATION
                // ============================================================

                Separator separator = new Separator();

                HBox deliveryInfo = new HBox(50);

                VBox pickup = deliveryDetail(
                                "PICKUP",
                                "Owner Location");

                String destination = request.getRental() != null
                                ? request.getRental().getAddress()
                                                + ", "
                                                + request.getRental().getCity()
                                                + ", "
                                                + request.getRental().getState()
                                                + " - "
                                                + request.getRental().getPinCode()
                                : "Not available";

                VBox delivery = deliveryDetail(
                                "DELIVERY",
                                destination);

                HBox.setHgrow(
                                pickup,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                delivery,
                                Priority.ALWAYS);

                deliveryInfo.getChildren().addAll(
                                pickup,
                                delivery);

                // ============================================================
                // CARD
                // ============================================================

                card.getChildren().addAll(
                                top,
                                separator,
                                deliveryInfo);

                return card;
        }

        private static StackPane createRentalImage(
                        RentalRequest request) {

                StackPane container = new StackPane();

                container.setPrefSize(
                                110,
                                90);

                container.setStyle(
                                "-fx-background-color: #EEF1F7;" +
                                                "-fx-background-radius: 8px;");

                String imageUrl = "https://images.unsplash.com/"
                                + "photo-1516035069371-29a1b244cc32"
                                + "?w=500";

                try {

                        Image image = new Image(
                                        imageUrl,
                                        100,
                                        80,
                                        true,
                                        true,
                                        true);

                        ImageView imageView = new ImageView(
                                        image);

                        imageView.setFitWidth(100);
                        imageView.setFitHeight(80);
                        imageView.setPreserveRatio(true);

                        container.getChildren().add(
                                        imageView);

                } catch (Exception e) {

                        Label fallback = new Label("📷");

                        fallback.setStyle(
                                        "-fx-font-size: 30px;");

                        container.getChildren().add(
                                        fallback);
                }

                return container;
        }

        private static VBox deliveryDetail(
                        String heading,
                        String value) {

                VBox box = new VBox(5);

                Label headingLabel = new Label(heading);

                headingLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + BLUE + ";");

                Label valueLabel = new Label(
                                value == null
                                                ? "Not available"
                                                : value);

                valueLabel.setWrapText(true);

                valueLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: #405171;");

                box.getChildren().addAll(
                                headingLabel,
                                valueLabel);

                return box;
        }

        // ============================================================
        // SUMMARY CARD
        // ============================================================

        private static VBox summaryCard(
                        String title,
                        String value,
                        String description) {

                VBox card = new VBox(8);

                card.setPadding(
                                new Insets(18));

                card.setPrefHeight(
                                120);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: " + BORDER + ";" +
                                                "-fx-border-radius: 9px;" +
                                                "-fx-background-radius: 9px;");

                HBox.setHgrow(
                                card,
                                Priority.ALWAYS);

                Label titleLabel = new Label(
                                title.toUpperCase());

                titleLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + MUTED + ";");

                Label valueLabel = new Label(
                                value);

                valueLabel.setStyle(
                                "-fx-font-size: 28px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                Label descriptionLabel = new Label(
                                description);

                descriptionLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                card.getChildren().addAll(
                                titleLabel,
                                valueLabel,
                                descriptionLabel);

                return card;
        }

        // ============================================================
        // NAVIGATION BUTTON
        // ============================================================

        private static Button navigationButton(
                        String icon,
                        String text) {

                Button button = new Button(
                                icon + "    " + text);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(
                                44);

                button.setAlignment(
                                Pos.CENTER_LEFT);

                button.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #405171;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-padding: 10px 14px;" +
                                                "-fx-cursor: hand;");

                return button;
        }

        // ============================================================
        // SELECTED NAVIGATION STYLE
        // ============================================================

        private static String selectedNavigationStyle() {

                return "-fx-background-color: " +
                                LIGHT_BLUE + ";" +
                                "-fx-text-fill: " +
                                BLUE + ";" +
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 7px;" +
                                "-fx-padding: 10px 14px;";
        }

        private static void showMessage(
                        String title,
                        String message) {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);

                alert.showAndWait();
        }
}