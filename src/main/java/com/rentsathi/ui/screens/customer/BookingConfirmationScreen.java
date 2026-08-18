package com.rentsathi.ui.screens.customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;


public class BookingConfirmationScreen {

    private static final String BLUE = "#3554C7";
    private static final String LIGHT_BLUE = "#E8EDFF";
    private static final String BORDER = "#C8CEE0";
    private static final String TEXT = "#101828";
    private static final String MUTED = "#5E6B85";
    private static final String BG = "#F8F8FD";
    private static final String GREEN = "#218739";

    public static void show(Stage stage) {
        BorderPane root = createRoot(stage);

        Scene scene = new Scene(root, 1500, 830);
        scene.setFill(Color.web(BG));

        stage.setTitle("RentSathi - Booking Confirmed");
        stage.setScene(scene);
        stage.setWidth(1500);
        stage.setHeight(830);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.centerOnScreen();
        stage.show();
    }

    private static BorderPane createRoot(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG + ";");

        HBox topBar = createTopBar(stage);
        root.setTop(topBar);

        VBox content = new VBox(18);
        content.setFillWidth(true);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(25, 40, 30, 40));
        content.setFillWidth(true);
        VBox successHeader = createSuccessHeader();

        HBox middle = new HBox(20);
        middle.setAlignment(Pos.TOP_CENTER);
        middle.setMaxWidth(1050);

        VBox summary = createBookingSummary();
        VBox actions = createActions(stage);

        summary.setPrefWidth(650);
        summary.setMinWidth(650);
        summary.setMaxWidth(650);

        actions.setPrefWidth(270);
        actions.setMinWidth(270);
        actions.setMaxWidth(270);

        middle.getChildren().addAll(summary, actions);

        VBox next = createWhatsNext();

        content.getChildren().addAll(
                successHeader,
                middle,
                next
        );

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setStyle(
        "-fx-background-color: transparent;" +
        "-fx-background: transparent;"
        );

root.setCenter(scrollPane);

        return root;
    }

    private static HBox createTopBar(Stage stage) {
        HBox bar = new HBox();
        bar.setAlignment(Pos.CENTER_LEFT);
        bar.setPadding(new Insets(8, 28, 8, 28));
        bar.setPrefHeight(48);
        bar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1px 0;"
        );

        FontIcon logoIcon = new FontIcon(FontAwesomeSolid.HOME);
        logoIcon.setIconSize(18);
        logoIcon.setIconColor(Color.web(BLUE));

        Label logo = new Label("RentSathi");
        logo.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + BLUE + ";"
        );

        HBox brand = new HBox(8, logoIcon, logo);
        brand.setAlignment(Pos.CENTER_LEFT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        FontIcon helpIcon = new FontIcon(FontAwesomeSolid.QUESTION_CIRCLE);
        helpIcon.setIconSize(16);
        helpIcon.setIconColor(Color.web(BLUE));

        Button help = new Button();
        help.setGraphic(helpIcon);
        help.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 4px;"
        );

        Button cancel = new Button("Cancel");
        cancel.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 16px;" +
                "-fx-background-radius: 16px;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-padding: 6px 15px;" +
                "-fx-cursor: hand;"
        );

        cancel.setOnAction(event -> {
            if (stage.getScene() != null) {
                stage.close();
            }
        });

        bar.getChildren().addAll(brand, spacer, help, cancel);

        return bar;
    }

    private static VBox createSuccessHeader() {
        VBox box = new VBox(6);
        box.setAlignment(Pos.CENTER);

        StackPane success = new StackPane();

success.setPrefSize(82, 82);
success.setMinSize(82, 82);
success.setMaxSize(82, 82);

success.setStyle(
        "-fx-background-color: #E2E9FF;" +
        "-fx-background-radius: 50%;"
);

Circle innerCircle = new Circle(18);
innerCircle.setFill(Color.web("#3554C7"));

FontIcon check = new FontIcon(FontAwesomeSolid.CHECK);
check.setIconSize(18);
check.setIconColor(Color.WHITE);

StackPane checkCircle = new StackPane();
checkCircle.setPrefSize(36, 36);
checkCircle.setMinSize(36, 36);
checkCircle.setMaxSize(36, 36);

checkCircle.getChildren().addAll(
        innerCircle,
        check
);

success.getChildren().add(checkCircle);

        Label title = new Label("Booking Confirmed!");
        title.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle = new Label(
                "Your rental has been successfully booked."
        );
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        box.getChildren().addAll(success, title, subtitle);
        box.setTranslateY(-2);

        return box;
    }

    private static VBox createBookingSummary() {
        VBox card = baseCard();
        card.setSpacing(12);
        card.setPrefWidth(650);
        card.setMinWidth(650);
        card.setMaxWidth(650);

        HBox heading = new HBox();
        heading.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Booking Summary");
        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label bookingId = new Label("#RS-884920");
        bookingId.setStyle(
                "-fx-background-color: " + LIGHT_BLUE + ";" +
                "-fx-background-radius: 15px;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-size: 11px;" +
                "-fx-padding: 5px 10px;"
        );

        heading.getChildren().addAll(title, spacer, bookingId);

        HBox line = separator();

        VBox left = new VBox(5);
        left.getChildren().addAll(
                smallHeading("RENTAL ITEM"),
                iconText(FontAwesomeSolid.CAMERA, "Sony Alpha A7 III Mirrorless\nCamera")
        );

        VBox right = new VBox(5);
        right.getChildren().addAll(
                smallHeading("OWNER"),
                iconText(FontAwesomeSolid.USER, "Alex Johnson")
        );

        HBox row1 = new HBox(45, left, right);

        VBox dates = new VBox(5);
        dates.getChildren().addAll(
                smallHeading("DATES"),
                iconText(FontAwesomeSolid.CALENDAR_ALT, "Nov 15, 2023 - Nov 18, 2023\n(3 days)")
        );

        VBox quantity = new VBox(5);
        quantity.getChildren().addAll(
                smallHeading("QUANTITY"),
                iconText(FontAwesomeSolid.HASHTAG, "1")
        );

        HBox row2 = new HBox(65, dates, quantity);

        VBox fulfillment = new VBox(5);
        fulfillment.getChildren().addAll(
                smallHeading("FULFILLMENT"),
                iconText(FontAwesomeSolid.TRUCK, "Delivery to 'Downtown, 2 miles away'")
        );

        HBox totalBox = new HBox();
        totalBox.setAlignment(Pos.CENTER_LEFT);
        totalBox.setPadding(new Insets(12));
        totalBox.setStyle(
                "-fx-background-color: " + LIGHT_BLUE + ";" +
                "-fx-background-radius: 6px;"
        );

        VBox totalLeft = new VBox(2);
        totalLeft.getChildren().addAll(
                smallHeading("TOTAL AMOUNT"),
                styledLabel("$660.50", 20, BLUE, true)
        );

        Region totalSpacer = new Region();
        HBox.setHgrow(totalSpacer, Priority.ALWAYS);

        VBox statusBox = new VBox(3);
        statusBox.setAlignment(Pos.CENTER_LEFT);
        statusBox.getChildren().add(
                smallHeading("PAYMENT STATUS")
        );

        Label paid = new Label("✓ Paid");
        paid.setStyle(
                "-fx-background-color: #E4F7E8;" +
                "-fx-background-radius: 15px;" +
                "-fx-text-fill: " + GREEN + ";" +
                "-fx-padding: 5px 12px;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        statusBox.getChildren().add(paid);

        totalBox.getChildren().addAll(
                totalLeft,
                totalSpacer,
                statusBox
        );

        card.getChildren().addAll(
                heading,
                line,
                row1,
                row2,
                fulfillment,
                totalBox
        );

        return card;
    }

    private static VBox createActions(Stage stage) {
        VBox card = baseCard();
        card.setPrefWidth(270);
        card.setMinWidth(270);
        card.setMaxWidth(270);

        Button viewBooking = actionButton(
                FontAwesomeSolid.CLIPBOARD_LIST,
                "View Booking",
                true
        );

        Button track = actionButton(
                FontAwesomeSolid.TRUCK,
                "Track Delivery",
                false
        );

        Button dashboard = actionButton(
                FontAwesomeSolid.ARROW_LEFT,
                "Back to Dashboard",
                false
        );

        dashboard.setOnAction(event -> {
            new DashboardScreen(stage).show();
        });

        card.getChildren().addAll(
                viewBooking,
                track,
                dashboard
        );

        VBox notice = new VBox(10);
        notice.setPrefWidth(270);
        notice.setMinWidth(270);
        notice.setMaxWidth(270);
        notice.setPrefHeight(104);
        notice.setMinHeight(104);
        notice.setPadding(new Insets(14));

        notice.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #C8CEE0;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;"
        );

        HBox noticeRow = new HBox(10);
        noticeRow.setAlignment(Pos.TOP_LEFT);

        FontIcon info = new FontIcon(FontAwesomeSolid.INFO_CIRCLE);
        info.setIconSize(15);
        info.setIconColor(Color.web(BLUE));

        VBox message = new VBox(0);

        Label noticeText = new Label(
                "Booking confirmation has\n" +
                "been sent to your\n" +
                "registered email. Need\n" +
                "help?"
        );

        noticeText.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label contact = new Label("Contact Support");
        contact.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + BLUE + ";" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        message.getChildren().addAll(
                noticeText,
                contact
        );

        noticeRow.getChildren().addAll(
                info,
                message
        );

        notice.getChildren().add(noticeRow);

        VBox wrapper = new VBox(14);
        wrapper.setPrefWidth(270);
        wrapper.setMinWidth(270);
        wrapper.setMaxWidth(270);
        wrapper.getChildren().addAll(
                card,
                notice
        );

        return wrapper;
    }

    private static VBox createWhatsNext() {
        VBox card = baseCard();

        card.setPrefWidth(1050);
        card.setMinWidth(1050);
        card.setMaxWidth(1050);

        card.setPadding(new Insets(20, 20, 18, 20));
        card.setSpacing(15);

        Label title = new Label("What's Next?");
        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        HBox steps = new HBox();
        steps.setAlignment(Pos.TOP_CENTER);
        steps.setFillHeight(false);

        steps.getChildren().addAll(
                step(FontAwesomeSolid.CHECK, "Booking\nConfirmed", true),
                connector(),
                step(FontAwesomeSolid.HOURGLASS_HALF, "Owner\nAccepted", false),
                connector(),
                step(FontAwesomeSolid.ARCHIVE, "Preparing", false),
                connector(),
                step(FontAwesomeSolid.TRUCK, "Out for\nDelivery", false),
                connector(),
                step(FontAwesomeSolid.PLAY, "Rental\nActive", false)
        );

        card.getChildren().addAll(
                title,
                steps
        );

        return card;
    }

    private static VBox step(
            FontAwesomeSolid icon,
            String text,
            boolean active
    ) {
        VBox box = new VBox(7);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPrefWidth(150);
        box.setMinWidth(150);
        box.setMaxWidth(150);

        StackPane iconContainer = new StackPane();
        iconContainer.setPrefSize(30, 30);
        iconContainer.setMinSize(30, 30);
        iconContainer.setMaxSize(30, 30);

        Circle circle = new Circle(15);

        if (active) {
            circle.setFill(Color.web(BLUE));
        } else {
            circle.setFill(Color.web("#E8EAF2"));
            circle.setStroke(Color.web("#BFC5D6"));
            circle.setStrokeWidth(1);
        }

        FontIcon iconView = new FontIcon(icon);
        iconView.setIconSize(11);
        iconView.setIconColor(
                Color.web(active ? "#FFFFFF" : "#65708A")
        );

        iconContainer.getChildren().addAll(
                circle,
                iconView
        );

        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );
        label.setWrapText(true);

        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: " +
                (active ? "bold" : "normal") + ";" +
                "-fx-text-fill: " +
                (active ? BLUE : "#7D8497") + ";"
        );

        box.getChildren().addAll(
                iconContainer,
                label
        );

        return box;
    }

    private static Region connector() {
        Region line = new Region();

        line.setPrefWidth(95);
        line.setMinWidth(40);
        line.setMaxWidth(95);

        line.setPrefHeight(4);
        line.setMinHeight(4);
        line.setMaxHeight(4);

        line.setStyle(
                "-fx-background-color: #E8EAF2;" +
                "-fx-background-radius: 4px;"
        );

        line.setTranslateY(13);

        return line;
    }

    private static Button actionButton(
            FontAwesomeSolid icon,
            String text,
            boolean primary
    ) {
        FontIcon iconView = new FontIcon(icon);
        iconView.setIconSize(13);
        iconView.setIconColor(
                Color.web(primary ? "#FFFFFF" : BLUE)
        );

        Button button = new Button(text, iconView);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(40);
        button.setStyle(
                "-fx-background-color: " +
                        (primary ? BLUE : "#F0F1F8") + ";" +
                "-fx-text-fill: " +
                        (primary ? "white" : BLUE) + ";" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6px;" +
                "-fx-border-color: " +
                        (primary ? BLUE : "transparent") + ";" +
                "-fx-border-radius: 6px;" +
                "-fx-cursor: hand;"
        );
        return button;
    }

    private static HBox iconText(
            FontAwesomeSolid icon,
            String text
    ) {
        FontIcon iconView = new FontIcon(icon);
        iconView.setIconSize(11);
        iconView.setIconColor(Color.web(MUTED));

        Label label = new Label(text);
        label.setWrapText(true);
        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        HBox box = new HBox(6, iconView, label);
        box.setAlignment(Pos.TOP_LEFT);

        return box;
    }

    private static Label smallHeading(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #5B6680;"
        );
        return label;
    }

    private static Label styledLabel(
            String text,
            int size,
            String color,
            boolean bold
    ) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-size: " + size + "px;" +
                "-fx-text-fill: " + color + ";" +
                "-fx-font-weight: " +
                (bold ? "bold" : "normal") + ";"
        );
        return label;
    }

    private static HBox separator() {
        HBox line = new HBox();
        line.setPrefHeight(1);
        line.setStyle(
                "-fx-background-color: " + BORDER + ";"
        );
        return line;
    }

    private static VBox baseCard() {
        VBox box = new VBox(14);
        box.setPadding(new Insets(20));
        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;"
        );
        return box;
    }

}