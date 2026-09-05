package com.rentsathi.ui.screens.customer;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;

import com.rentsathi.ui.screens.CustomerLoginScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.shape.SVGPath;

public class ProfileScreen {

    private static final String BLUE = "#3158D4";
    private static final String DARK_BLUE = "#102A56";
    private static final String TEXT = "#18345F";
    private static final String SECONDARY = "#66799F";
    private static final String BACKGROUND = "#F6F8FC";
    private static final String CARD = "#FFFFFF";
    private static final String BORDER = "#DFE5F0";
    private static final String LIGHT_BLUE = "#EAF0FF";

    public static void show(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        VBox sidebar = createSidebar(stage);

        VBox mainArea = new VBox();

        HBox topBar = createTopBar();

        ScrollPane scrollPane =
                createProfileContent();

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        mainArea.getChildren().addAll(
                topBar,
                scrollPane
        );

        root.setLeft(sidebar);
        root.setCenter(mainArea);

        Scene scene =
                new Scene(
                        root,
                        1600,
                        900
                );

        scene.setFill(
                Color.web(BACKGROUND)
        );

        stage.setTitle(
                "RentSathi - My Profile"
        );

        stage.setScene(scene);

        stage.setMinWidth(1280);
        stage.setMinHeight(720);

        stage.setMaximized(true);

        stage.show();
    }

    private static VBox createSidebar(Stage stage) {

        VBox sidebar =
                new VBox();

        sidebar.setPrefWidth(265);
        sidebar.setMinWidth(265);
        sidebar.setMaxWidth(265);

        sidebar.setPadding(
                new Insets(
                        28,
                        18,
                        20,
                        18
                )
        );

        sidebar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 1px 0 0;"
        );

        HBox logoBox =
                createLogo();

        Label customerTitle =
                new Label("CUSTOMER");

        customerTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        customerTitle.setTextFill(
                Color.web("#94A0B8")
        );

        customerTitle.setPadding(
                new Insets(
                        30,
                        0,
                        8,
                        14
                )
        );

        VBox navigation =
                new VBox(2);

        Button dashboard =
                createNavigationButton(
                        "home",
                        "Dashboard",
                        false
                );

        dashboard.setOnAction(
                event ->
                        new DashboardScreen(stage).show()
        );

        Button profile =
                createNavigationButton(
                        "profile",
                        "My Profile",
                        true
                );

        Button rentals =
                createNavigationButton(
                        "rentals",
                        "My Rentals",
                        false
                );

        Button bookings =
                createNavigationButton(
                        "bookings",
                        "My Bookings",
                        false
                );

        Button saved =
                createNavigationButton(
                        "heart",
                        "Saved Items",
                        false
                );

        Button history =
                createNavigationButton(
                        "clock",
                        "Rental History",
                        false
                );

        Button payments =
                createNavigationButton(
                        "payments",
                        "Payments",
                        false
                );

        Button notifications =
                createNavigationButton(
                        "bell",
                        "Notifications",
                        false
                );

        navigation.getChildren().addAll(
                customerTitle,
                dashboard,
                profile,
                rentals,
                bookings,
                saved,
                history,
                payments,
                notifications
        );

        Region spacer =
                new Region();

        VBox.setVgrow(
                spacer,
                Priority.ALWAYS
        );

        Button help =
                createBottomButton(
                        "help",
                        "Help Center"
                );

        Button logout =
                createBottomButton(
                        "logout",
                        "Logout"
                );

        logout.setOnAction(
                event ->
                        CustomerLoginScreen.show(stage)
        );

        sidebar.getChildren().addAll(
                logoBox,
                navigation,
                spacer,
                help,
                logout
        );

        return sidebar;
    }

    private static Button createNavigationButton(
            String iconType,
            String text,
            boolean selected
    ) {

        Button button =
                new Button();

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(44);
        button.setMaxHeight(44);
        button.setMinHeight(44);

        HBox content =
                new HBox(14);

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        SVGPath icon =
                createIcon(iconType);

        icon.setScaleX(0.78);
        icon.setScaleY(0.78);

        Label textLabel =
                new Label(text);

        textLabel.setFont(
                Font.font(
                        "Arial",
                        selected
                                ? FontWeight.BOLD
                                : FontWeight.NORMAL,
                        14
                )
        );

        if (selected) {

            button.setStyle(
                    "-fx-background-color: #E8EEFF;" +
                    "-fx-background-radius: 11px;" +
                    "-fx-border-color: #D7E1FF;" +
                    "-fx-border-width: 1px;" +
                    "-fx-border-radius: 11px;" +
                    "-fx-padding: 0 14px;" +
                    "-fx-cursor: hand;"
            );

            textLabel.setTextFill(
                    Color.web(BLUE)
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-background-radius: 11px;" +
                    "-fx-padding: 0 14px;" +
                    "-fx-cursor: hand;"
            );

            textLabel.setTextFill(
                    Color.web("#526A94")
            );
        }

        content.getChildren().addAll(
                icon,
                textLabel
        );

        button.setGraphic(content);

        return button;
    }

    private static Button createBottomButton(
            String iconType,
            String text
    ) {

        Button button =
                new Button();

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(45);

        HBox content =
                new HBox(14);

        content.setAlignment(
                Pos.CENTER_LEFT
        );

        SVGPath icon =
                createIcon(iconType);

        icon.setScaleX(0.72);
        icon.setScaleY(0.72);

        Label textLabel =
                new Label(text);

        textLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        13
                )
        );

        textLabel.setTextFill(
                Color.web("#526A94")
        );

        content.getChildren().addAll(
                icon,
                textLabel
        );

        button.setGraphic(content);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 10px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0 12px;"
        );

        return button;
    }

    private static HBox createLogo() {

        StackPane logo =
                new StackPane();

        Rectangle logoBox =
                new Rectangle(
                        60,
                        60
                );

        logoBox.setArcWidth(16);
        logoBox.setArcHeight(16);

        logoBox.setFill(
                Color.web(BLUE)
        );

        Label rs =
                new Label("RS");

        rs.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );

        rs.setTextFill(
                Color.WHITE
        );

        logo.getChildren().addAll(
                logoBox,
                rs
        );

        Label title =
                new Label("RentSathi");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        title.setTextFill(
                Color.web(DARK_BLUE)
        );

        Label subtitle =
                new Label(
                        "Rent Smart. Live Easy."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        subtitle.setTextFill(
                Color.web(SECONDARY)
        );

        VBox text =
                new VBox(
                        2,
                        title,
                        subtitle
                );

        text.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox box =
                new HBox(
                        14,
                        logo,
                        text
                );

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        return box;
    }

    private static Button createSvgSidebarButton(
        SVGPath icon,
        String text,
        boolean selected
) {

    icon.setScaleX(0.85);
    icon.setScaleY(0.85);

    StackPane iconBox =
            new StackPane(icon);

    iconBox.setPrefWidth(28);
    iconBox.setMinWidth(28);
    iconBox.setMaxWidth(28);

    Label textLabel =
            new Label(text);

    textLabel.setFont(
            Font.font(
                    "Arial",
                    selected
                            ? FontWeight.BOLD
                            : FontWeight.NORMAL,
                    15
            )
    );

    textLabel.setTextFill(
            Color.web(
                    selected
                            ? BLUE
                            : "#506B96"
            )
    );

    HBox content =
            new HBox(
                    12,
                    iconBox,
                    textLabel
            );

    content.setAlignment(
            Pos.CENTER_LEFT
    );

    Button button =
            new Button();

    button.setGraphic(content);

    button.setMaxWidth(
            Double.MAX_VALUE
    );

    button.setPrefHeight(48);

    button.setAlignment(
            Pos.CENTER_LEFT
    );

    if (selected) {

        button.setStyle(
                "-fx-background-color: #EAF0FF;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: transparent;" +
                "-fx-cursor: hand;"
        );

    } else {

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: transparent;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(
                event ->
                        button.setStyle(
                                "-fx-background-color: #F5F7FC;" +
                                "-fx-background-radius: 10px;" +
                                "-fx-cursor: hand;"
                        )
        );

        button.setOnMouseExited(
                event ->
                        button.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-background-radius: 10px;" +
                                "-fx-cursor: hand;"
                        )
        );
    }

    return button;
}

    private static HBox createTopBar() {

        HBox topBar =
                new HBox();

        topBar.setPrefHeight(94);
        topBar.setMinHeight(94);
        topBar.setMaxHeight(94);

        topBar.setPadding(
                new Insets(
                        18,
                        28,
                        18,
                        38
                )
        );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );

        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 0 0 1px 0;"
        );

        VBox titleBox =
                new VBox(3);

        Label title =
                new Label("My Profile");

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        29
                )
        );

        title.setTextFill(
                Color.web(DARK_BLUE)
        );

        Label subtitle =
                new Label(
                        "Manage your account information and preferences."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        subtitle.setTextFill(
                Color.web(SECONDARY)
        );

        titleBox.getChildren().addAll(
                title,
                subtitle
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        TextField search =
                new TextField();

        search.setPromptText(
                "Search rentals..."
        );

        search.setPrefWidth(280);
search.setMinWidth(280);
search.setMaxWidth(280);
        search.setPrefHeight(46);

        search.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #D9E1F0;" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;" +
                "-fx-padding: 0 15px;" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 14px;"
        );

        SVGPath bell =
                createIcon("bell");

        bell.setScaleX(0.95);
        bell.setScaleY(0.95);

        StackPane bellBox =
                new StackPane();

        bellBox.setPrefSize(
                40,
                40
        );

        Circle dot =
                new Circle(
                        5,
                        Color.web("#D83A3A")
                );

        StackPane.setAlignment(
                dot,
                Pos.TOP_RIGHT
        );

        bellBox.getChildren().addAll(
                bell,
                dot
        );

        StackPane avatar =
                createAvatar(
                        "S",
                        22
                );

        SVGPath arrow =
                createIcon("chevron");

        arrow.setScaleX(0.65);
        arrow.setScaleY(0.65);

        HBox profileBox =
                new HBox(
                        8,
                        avatar,
                        arrow
                );

        profileBox.setAlignment(
                Pos.CENTER
        );

        topBar.getChildren().addAll(
                titleBox,
                spacer,
                search,
                bellBox,
                profileBox
        );

        return topBar;
    }

    private static ScrollPane createProfileContent() {

    VBox content = new VBox(18);

    content.setPadding(
            new Insets(20, 26, 30, 26)
    );

    content.setFillWidth(true);
    content.setMinWidth(0);
    content.setMaxWidth(Double.MAX_VALUE);

    HBox profileHeader = createProfileHeader();

    HBox stats = createStats();

    HBox lower = new HBox(18);

    lower.setFillHeight(true);
    lower.setMinWidth(0);
    lower.setMaxWidth(Double.MAX_VALUE);

    VBox left = new VBox(18);
    VBox right = new VBox(18);

    left.setMinWidth(0);
    left.setMaxWidth(Double.MAX_VALUE);

    right.setPrefWidth(410);
    right.setMinWidth(380);
    right.setMaxWidth(410);

    left.getChildren().addAll(
            createPersonalInformation(),
            createAboutMe()
    );

    right.getChildren().addAll(
            createProfilePicture(),
            createAccountSettings()
    );

    HBox.setHgrow(
            left,
            Priority.ALWAYS
    );

    HBox.setHgrow(
            right,
            Priority.NEVER
    );

    lower.getChildren().addAll(
            left,
            right
    );

    content.getChildren().addAll(
            profileHeader,
            stats,
            lower
    );

    VBox.setVgrow(
            lower,
            Priority.ALWAYS
    );

    ScrollPane scroll =
            new ScrollPane(content);

    scroll.setFitToWidth(true);
    scroll.setFitToHeight(false);

    scroll.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
    );

    scroll.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.AS_NEEDED
    );

    scroll.setPannable(true);

    scroll.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-background: transparent;"
    );

    return scroll;
}

    private static HBox createProfileHeader() {

        HBox card = new HBox();

        card.setPadding(
                new Insets(28, 32, 28, 32)
        );

        card.setMinHeight(230);
        card.setPrefHeight(230);
        card.setMaxWidth(Double.MAX_VALUE);

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setStyle(
                cardStyle()
        );

        StackPane avatar =
                createAvatar(
                        "S",
                        80
                );

        VBox information =
                new VBox(7);

        Label name =
                new Label("Sahil Sharma");

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        32
                )
        );

        name.setTextFill(
                Color.web(DARK_BLUE)
        );

        HBox emailRow =
                createInfoRow(
                        "email",
                        "sahil.sharma@example.com"
                );

        HBox phoneRow =
                createInfoRow(
                        "phone",
                        "+91 98765 43210"
                );

        HBox locationRow =
                createInfoRow(
                        "location",
                        "Pune, Maharashtra"
                );

        Label verified =
                new Label(
                        "✓  Verified User"
                );

        verified.setTextFill(
                Color.web("#16834B")
        );

        verified.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        verified.setStyle(
                "-fx-background-color: #E7F7EE;" +
                "-fx-background-radius: 12px;" +
                "-fx-padding: 6px 11px;"
        );

        information.getChildren().addAll(
                name,
                emailRow,
                phoneRow,
                locationRow,
                verified
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button edit =
                new Button();

        SVGPath editIcon =
                createIcon("edit");

        editIcon.setStroke(Color.web(BLUE));
        editIcon.setFill(Color.TRANSPARENT);
        editIcon.setStrokeWidth(1.8);
        editIcon.setScaleX(0.62);
        editIcon.setScaleY(0.62);

        Label editText =
                new Label("Edit Profile");

        editText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        editText.setTextFill(
                Color.web(BLUE)
        );

        HBox editContent =
                new HBox(
                        7,
                        editIcon,
                        editText
                );

        editContent.setAlignment(
                Pos.CENTER
        );

        edit.setGraphic(
                editContent
        );

        edit.setPrefHeight(44);

        edit.setStyle(
                "-fx-background-color: #EAF0FF;" +
                "-fx-background-radius: 10px;" +
                "-fx-padding: 0 18px;" +
                "-fx-cursor: hand;"
        );

        VBox right =
                new VBox(20);

        right.setAlignment(
                Pos.CENTER_RIGHT
        );

        Label quote =
                new Label(
                        "\"Rent anywhere,\nLive everywhere.\""
                );

        quote.setTextFill(
                Color.web("#7286AB")
        );

        quote.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        15
                )
        );

        quote.setStyle(
                "-fx-font-style: italic;"
        );

        right.getChildren().addAll(
                edit,
                quote
        );

        card.getChildren().addAll(
                avatar,
                information,
                spacer,
                right
        );

        return card;
    }

    private static HBox createInfoRow(
            String iconType,
            String text
    ) {

        HBox row =
                new HBox(9);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        SVGPath icon =
                createIcon(iconType);

        icon.setScaleX(0.58);
        icon.setScaleY(0.58);

        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        label.setTextFill(
                Color.web(SECONDARY)
        );

        row.getChildren().addAll(
                icon,
                label
        );

        return row;
    }

    private static HBox createStats() {

        HBox stats =
                new HBox(18);

        stats.setMaxWidth(
                Double.MAX_VALUE
        );

        stats.getChildren().addAll(
                createStat(
                        "cube",
                        "12",
                        "Total Rentals",
                        "#EAF0FF",
                        BLUE
                ),
                createStat(
                        "calendar",
                        "8",
                        "Completed Bookings",
                        "#E7F7EE",
                        "#15965A"
                ),
                createStat(
                        "heart",
                        "5",
                        "Saved Items",
                        "#FFECEF",
                        "#E74D65"
                ),
                createStat(
                        "star",
                        "4.8",
                        "User Rating",
                        "#FFF6DE",
                        "#D69B00"
                )
        );

        return stats;
    }

    private static VBox createStat(
            String iconType,
            String value,
            String label,
            String iconBackground,
            String iconColor
    ) {

        VBox card =
                new VBox();

        card.setPadding(
                new Insets(18)
        );

        card.setPrefHeight(112);
        card.setMinHeight(112);
        card.setMaxHeight(112);

        HBox.setHgrow(
                card,
                Priority.ALWAYS
        );

        card.setStyle(
                cardStyle()
        );

        HBox row =
                new HBox(15);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane iconBox =
                new StackPane();

        iconBox.setPrefSize(
                58,
                58
        );

        iconBox.setMinSize(
                58,
                58
        );

        iconBox.setMaxSize(
                58,
                58
        );

        iconBox.setStyle(
                "-fx-background-color: " +
                iconBackground +
                ";" +
                "-fx-background-radius: 13px;"
        );

        SVGPath icon =
                createIcon(iconType);

        icon.setStroke(
                Color.web(iconColor)
        );

        icon.setFill(
                Color.TRANSPARENT
        );

        icon.setStrokeWidth(1.8);
        icon.setScaleX(0.68);
        icon.setScaleY(0.68);

        if ("star".equals(iconType)) {
            icon.setFill(
                    Color.web(iconColor)
            );
            icon.setStroke(
                    Color.TRANSPARENT
            );
        }

        iconBox.getChildren().add(
                icon
        );

        VBox text =
                new VBox(3);

        Label number =
                new Label(value);

        number.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        26
                )
        );

        number.setTextFill(
                Color.web(DARK_BLUE)
        );

        Label name =
                new Label(label);

        name.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        name.setTextFill(
                Color.web(SECONDARY)
        );

        text.getChildren().addAll(
                number,
                name
        );

        row.getChildren().addAll(
                iconBox,
                text
        );

        card.getChildren().add(
                row
        );

        return card;
    }

    private static VBox createPersonalInformation() {

        VBox card =
                new VBox(17);

        card.setPadding(
                new Insets(20, 24, 22, 24)
        );

        card.setStyle(
                cardStyle()
        );

        HBox header =
                createSectionHeader(
                        "profile",
                        "Personal Information",
                        "Edit"
                );

        GridPane grid =
        new GridPane();

grid.setHgap(20);
grid.setVgap(15);

grid.setMinWidth(0);
grid.setMaxWidth(Double.MAX_VALUE);

        ColumnConstraints c1 =
        new ColumnConstraints();

ColumnConstraints c2 =
        new ColumnConstraints();

c1.setPercentWidth(50);
c2.setPercentWidth(50);

c1.setMinWidth(0);
c2.setMinWidth(0);

c1.setMaxWidth(Double.MAX_VALUE);
c2.setMaxWidth(Double.MAX_VALUE);

grid.getColumnConstraints().addAll(
        c1,
        c2
);

        grid.add(
                createField(
                        "Full Name",
                        "Sahil"
                ),
                0,
                0
        );

        grid.add(
                createField(
                        "Email Address",
                        "sahil.sharma@example.com"
                ),
                1,
                0
        );

        grid.add(
                createField(
                        "Phone Number",
                        "+91 98765 43210"
                ),
                0,
                1
        );

        grid.add(
                createField(
                        "Date of Birth",
                        "15 March 2002"
                ),
                1,
                1
        );

        grid.add(
                createField(
                        "Gender",
                        "Male"
                ),
                0,
                2
        );

        grid.add(
                createField(
                        "Address",
                        "123 Main Street, Narhe\nPune, Maharashtra - 411041"
                ),
                1,
                2
        );

        card.getChildren().addAll(
                header,
                grid
        );

        return card;
    }

    private static VBox createField(
            String label,
            String value
    ) {

        VBox box =
                new VBox(6);

        Label title =
                new Label(label);

        title.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        title.setTextFill(
                Color.web("#55709F")
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setWrapText(true);

        valueLabel.setMinHeight(40);

        valueLabel.setMaxWidth(
                Double.MAX_VALUE
        );

        valueLabel.setAlignment(
                Pos.CENTER_LEFT
        );

        valueLabel.setPadding(
                new Insets(
                        0,
                        12,
                        0,
                        12
                )
        );

        valueLabel.setStyle(
                "-fx-background-color: #F8FAFD;" +
                "-fx-border-color: #DCE4F1;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 13px;"
        );

        box.getChildren().addAll(
                title,
                valueLabel
        );

        return box;
    }

    private static VBox createProfilePicture() {

        VBox card =
                new VBox(18);

        card.setMinWidth(0);
        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setPrefHeight(240);
        card.setPadding(
                new Insets(20, 22, 18, 22)
        );

        card.setStyle(
                cardStyle()
        );

        HBox header =
                createSectionHeader(
                        "image",
                        "Profile Picture",
                        ""
                );

        StackPane avatar =
                createAvatar(
                        "S",
                        58
                );

        HBox avatarBox =
                new HBox(
                        avatar
                );

        avatarBox.setAlignment(
                Pos.CENTER
        );

        Button change =
                new Button();

        SVGPath camera =
                createIcon("camera");

        camera.setStroke(Color.WHITE);
        camera.setFill(Color.TRANSPARENT);
        camera.setStrokeWidth(1.8);
        camera.setScaleX(0.55);
        camera.setScaleY(0.55);

        Label changeText =
                new Label("Change Photo");

        changeText.setTextFill(
                Color.WHITE
        );

        changeText.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        HBox changeContent =
                new HBox(
                        7,
                        camera,
                        changeText
                );

        changeContent.setAlignment(
                Pos.CENTER
        );

        change.setGraphic(
                changeContent
        );

        Button remove =
                new Button();

        SVGPath trash =
                createIcon("trash");

        trash.setStroke(
                Color.web(TEXT)
        );

        trash.setFill(
                Color.TRANSPARENT
        );

        trash.setStrokeWidth(1.8);
        trash.setScaleX(0.55);
        trash.setScaleY(0.55);

        Label removeText =
                new Label("Remove Photo");

        removeText.setTextFill(
                Color.web(TEXT)
        );

        removeText.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        HBox removeContent =
                new HBox(
                        7,
                        trash,
                        removeText
                );

        removeContent.setAlignment(
                Pos.CENTER
        );

        remove.setGraphic(
                removeContent
        );

        change.setMaxWidth(
                Double.MAX_VALUE
        );

        remove.setMaxWidth(
                Double.MAX_VALUE
        );

        HBox.setHgrow(
                change,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                remove,
                Priority.ALWAYS
        );

        change.setStyle(
                "-fx-background-color: " + BLUE + ";" +
                "-fx-background-radius: 9px;" +
                "-fx-cursor: hand;"
        );

        remove.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #D5DDEB;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;" +
                "-fx-cursor: hand;"
        );

        HBox buttons =
                new HBox(
                        10,
                        change,
                        remove
                );

        card.getChildren().addAll(
                header,
                avatarBox,
                buttons
        );

        return card;
    }

    private static VBox createAccountSettings() {

        VBox card =
                new VBox();

        card.setMinWidth(0);
        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setPrefHeight(278);
        card.setMinHeight(278);
        card.setPadding(
                new Insets(20, 22, 18, 22)
        );

        card.setStyle(
                cardStyle()
        );

        HBox header =
                createSectionHeader(
                        "settings",
                        "Account Settings",
                        ""
                );

        VBox settings =
                new VBox();

        settings.getChildren().addAll(
                createSettingRow(
                        "bell",
                        "Notification Preferences"
                ),
                createSettingRow(
                        "lock",
                        "Change Password"
                ),
                createSettingRow(
                        "globe",
                        "Language",
                        "English"
                ),
                createSettingRow(
                        "moon",
                        "Dark Mode",
                        ""
                )
        );

        card.getChildren().addAll(
                header,
                settings
        );

        return card;
    }

    private static HBox createSettingRow(
            String iconType,
            String text
    ) {
        return createSettingRow(
                iconType,
                text,
                ""
        );
    }

    private static HBox createSettingRow(
            String iconType,
            String text,
            String value
    ) {

        HBox row =
                new HBox();

        row.setPrefHeight(56);
        row.setMinHeight(56);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setStyle(
                "-fx-border-color: #E8EDF5;" +
                "-fx-border-width: 1px 0 0 0;"
        );

        SVGPath icon =
                createIcon(iconType);

        icon.setStroke(
                Color.web("#5875A5")
        );

        icon.setFill(
                Color.TRANSPARENT
        );

        icon.setStrokeWidth(1.8);
        icon.setScaleX(0.60);
        icon.setScaleY(0.60);

        StackPane iconBox =
                new StackPane(
                        icon
                );

        iconBox.setPrefWidth(35);
        iconBox.setMinWidth(35);
        iconBox.setMaxWidth(35);

        Label name =
                new Label(text);

        name.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        name.setTextFill(
                Color.web(TEXT)
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        if (!value.isEmpty()) {

            Label valueLabel =
                    new Label(value);

            valueLabel.setFont(
                    Font.font(
                            "Arial",
                            12
                    )
            );

            valueLabel.setTextFill(
                    Color.web(SECONDARY)
            );

            SVGPath arrow =
                    createIcon("chevron");

            arrow.setScaleX(0.50);
            arrow.setScaleY(0.50);

            row.getChildren().addAll(
                    iconBox,
                    name,
                    spacer,
                    valueLabel,
                    arrow
            );

        } else {

            SVGPath arrow =
                    createIcon("chevron");

            arrow.setScaleX(0.50);
            arrow.setScaleY(0.50);

            row.getChildren().addAll(
                    iconBox,
                    name,
                    spacer,
                    arrow
            );
        }

        return row;
    }

    private static VBox createAboutMe() {

        VBox card =
                new VBox(15);

        card.setPadding(
                new Insets(20, 24, 22, 24)
        );

        card.setStyle(
                cardStyle()
        );

        HBox header =
                createSectionHeader(
                        "document",
                        "About Me",
                        "Edit"
                );

        Label about =
                new Label(
                        "Hi! I'm Sahil. I love renting useful items " +
                        "and exploring new experiences through RentSathi. " +
                        "Let's make renting simple and accessible for everyone!"
                );

        about.setWrapText(true);

        about.setPadding(
                new Insets(13)
        );

        about.setMinHeight(75);

        about.setStyle(
                "-fx-background-color: #F8FAFD;" +
                "-fx-border-color: #DCE4F1;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-text-fill: " + TEXT + ";" +
                "-fx-font-family: Arial;" +
                "-fx-font-size: 13px;"
        );

        card.getChildren().addAll(
                header,
                about
        );

        return card;
    }

    private static HBox createSectionHeader(
            String iconType,
            String title,
            String action
    ) {

        HBox header =
                new HBox(10);

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane iconBox =
                new StackPane();

        iconBox.setPrefSize(
                38,
                38
        );

        iconBox.setMinSize(
                38,
                38
        );

        iconBox.setMaxSize(
                38,
                38
        );

        iconBox.setStyle(
                "-fx-background-color: " +
                LIGHT_BLUE +
                ";" +
                "-fx-background-radius: 10px;"
        );

        SVGPath icon =
                createIcon(iconType);

        icon.setStroke(
                Color.web(BLUE)
        );

        icon.setFill(
                Color.TRANSPARENT
        );

        icon.setStrokeWidth(1.8);
        icon.setScaleX(0.65);
        icon.setScaleY(0.65);

        iconBox.getChildren().add(
                icon
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        titleLabel.setTextFill(
                Color.web(DARK_BLUE)
        );

        header.getChildren().addAll(
                iconBox,
                titleLabel
        );

        if (!action.isEmpty()) {

            Region spacer =
                    new Region();

            HBox.setHgrow(
                    spacer,
                    Priority.ALWAYS
            );

            Button button =
                    new Button();

            SVGPath editIcon =
                    createIcon("edit");

            editIcon.setStroke(
                    Color.web(BLUE)
            );

            editIcon.setFill(
                    Color.TRANSPARENT
            );

            editIcon.setStrokeWidth(1.8);
            editIcon.setScaleX(0.55);
            editIcon.setScaleY(0.55);

            Label actionLabel =
                    new Label(action);

            actionLabel.setFont(
                    Font.font(
                            "Arial",
                            FontWeight.BOLD,
                            12
                    )
            );

            actionLabel.setTextFill(
                    Color.web(BLUE)
            );

            HBox actionContent =
                    new HBox(
                            6,
                            editIcon,
                            actionLabel
                    );

            actionContent.setAlignment(
                    Pos.CENTER
            );

            button.setGraphic(
                    actionContent
            );

            button.setStyle(
                    "-fx-background-color: " + LIGHT_BLUE + ";" +
                    "-fx-background-radius: 8px;" +
                    "-fx-cursor: hand;" +
                    "-fx-padding: 7px 12px;"
            );

            header.getChildren().addAll(
                    spacer,
                    button
            );
        }

        return header;
    }

    private static SVGPath createIcon(String type) {

        SVGPath icon = new SVGPath();

        icon.setFill(Color.TRANSPARENT);
        icon.setStroke(Color.web("#526A94"));
        icon.setStrokeWidth(1.8);

        switch (type) {

            case "home":
                icon.setContent(
                        "M3 10 L12 3 L21 10 V21 H14 V15 H10 V21 H3 Z"
                );
                break;

            case "profile":
                icon.setContent(
                        "M12 12 " +
                        "C14.76 12 17 9.76 17 7 " +
                        "C17 4.24 14.76 2 12 2 " +
                        "C9.24 2 7 4.24 7 7 " +
                        "C7 9.76 9.24 12 12 12 Z " +
                        "M4 21 C4 16.58 7.58 13 12 13 " +
                        "C16.42 13 20 16.58 20 21"
                );
                break;

            case "rentals":
            case "cube":
                icon.setContent(
                        "M12 3 L20 7.5 L12 12 L4 7.5 Z " +
                        "M4 7.5 V16.5 L12 21 L20 16.5 V7.5 " +
                        "M12 12 V21"
                );
                break;

            case "bookings":
            case "calendar":
                icon.setContent(
                        "M5 5 H19 V20 H5 Z " +
                        "M8 3 V7 M16 3 V7 " +
                        "M5 10 H19 " +
                        "M8 13 H10 M12 13 H14 M16 13 H17 " +
                        "M8 16 H10 M12 16 H14"
                );
                break;

            case "payments":
                icon.setContent(
                        "M3 6 H21 V18 H3 Z " +
                        "M3 10 H21 " +
                        "M7 15 H11"
                );
                break;

            case "help":
                icon.setContent(
                        "M12 21 C16.97 21 21 16.97 21 12 " +
                        "C21 7.03 16.97 3 12 3 " +
                        "C7.03 3 3 7.03 3 12 " +
                        "C3 16.97 7.03 21 12 21 Z " +
                        "M9.5 9 A2.5 2.5 0 1 1 14 11 " +
                        "C13 12 12 12.5 12 14 " +
                        "M12 17 V17.1"
                );
                break;

            case "logout":
                icon.setContent(
                        "M10 5 H4 V19 H10 " +
                        "M14 8 L18 12 L14 16 " +
                        "M18 12 H8"
                );
                break;

            case "search":
                icon.setContent(
                        "M10.5 3 C6.36 3 3 6.36 3 10.5 " +
                        "C3 14.64 6.36 18 10.5 18 " +
                        "C14.64 18 18 14.64 18 10.5 " +
                        "C18 6.36 14.64 3 10.5 3 Z " +
                        "M16 16 L21 21"
                );
                break;

            case "bell":
                icon.setContent(
                        "M6 17 H18 L16.5 14.5 V10 " +
                        "C16.5 7.5 14.5 5 12 5 " +
                        "C9.5 5 7.5 7.5 7.5 10 V14.5 Z " +
                        "M10 19 C10.5 20.3 13.5 20.3 14 19"
                );
                break;

            case "location":
                icon.setContent(
                        "M12 21 C12 21 5 14.5 5 9 " +
                        "C5 5.69 8.13 3 12 3 " +
                        "C15.87 3 19 5.69 19 9 " +
                        "C19 14.5 12 21 12 21 Z " +
                        "M12 11.5 C13.38 11.5 14.5 10.38 14.5 9 " +
                        "C14.5 7.62 13.38 6.5 12 6.5 " +
                        "C10.62 6.5 9.5 7.62 9.5 9 " +
                        "C9.5 10.38 10.62 11.5 12 11.5 Z"
                );
                break;

            case "clock":
                icon.setContent(
                        "M12 3 C16.97 3 21 7.03 21 12 " +
                        "C21 16.97 16.97 21 12 21 " +
                        "C7.03 21 3 16.97 3 12 " +
                        "C3 7.03 7.03 3 12 3 Z " +
                        "M12 7 V12 L15 14"
                );
                break;

            case "heart":
                icon.setContent(
                        "M12 20 C10 18.2 4 14.2 4 9 " +
                        "C4 5.2 8.8 3.6 12 7 " +
                        "C15.2 3.6 20 5.2 20 9 " +
                        "C20 14.2 14 18.2 12 20 Z"
                );
                break;

            case "star":
                icon.setContent(
                        "M12 3 L14.8 8.7 L21 9.5 L16.5 13.9 " +
                        "L17.6 20 L12 17.1 L6.4 20 L7.5 13.9 " +
                        "L3 9.5 L9.2 8.7 Z"
                );
                break;

            case "edit":
                icon.setContent(
                        "M4 17.5 V20 H6.5 L18.5 8 " +
                        "L16 5.5 Z M14.5 7 L17 9.5"
                );
                break;

            case "camera":
                icon.setContent(
                        "M4 7 H8 L10 4 H14 L16 7 H20 V19 H4 Z " +
                        "M12 16 C14.21 16 16 14.21 16 12 " +
                        "C16 9.79 14.21 8 12 8 " +
                        "C9.79 8 8 9.79 8 12 " +
                        "C8 14.21 9.79 16 12 16 Z"
                );
                break;

            case "image":
                icon.setContent(
                        "M4 4 H20 V20 H4 Z " +
                        "M7 16 L10.5 12.5 L13 15 L15 13 L18 16 " +
                        "M8 8.5 C8 9.33 8.67 10 9.5 10 " +
                        "C10.33 10 11 9.33 11 8.5 " +
                        "C11 7.67 10.33 7 9.5 7 " +
                        "C8.67 7 8 7.67 8 8.5"
                );
                break;

            case "trash":
                icon.setContent(
                        "M5 7 H19 M9 7 V4 H15 V7 " +
                        "M7 7 V20 H17 V7 " +
                        "M10 10 V17 M14 10 V17"
                );
                break;

            case "document":
                icon.setContent(
                        "M5 3 H15 L19 7 V21 H5 Z " +
                        "M15 3 V8 H19 " +
                        "M8 12 H16 M8 16 H16"
                );
                break;

            case "settings":
                icon.setContent(
                        "M12 8 C14.21 8 16 9.79 16 12 " +
                        "C16 14.21 14.21 16 12 16 " +
                        "C9.79 16 8 14.21 8 12 " +
                        "C8 9.79 9.79 8 12 8 Z " +
                        "M12 3 V5 M12 19 V21 " +
                        "M3 12 H5 M19 12 H21 " +
                        "M5.64 5.64 L7.05 7.05 " +
                        "M16.95 16.95 L18.36 18.36 " +
                        "M18.36 5.64 L16.95 7.05 " +
                        "M7.05 16.95 L5.64 18.36"
                );
                break;

            case "lock":
                icon.setContent(
                        "M6 10 H18 V20 H6 Z " +
                        "M8 10 V7 C8 4.79 9.79 3 12 3 " +
                        "C14.21 3 16 4.79 16 7 V10"
                );
                break;

            case "globe":
                icon.setContent(
                        "M12 3 C16.97 3 21 7.03 21 12 " +
                        "C21 16.97 16.97 21 12 21 " +
                        "C7.03 21 3 16.97 3 12 " +
                        "C3 7.03 7.03 3 12 3 Z " +
                        "M3 12 H21 " +
                        "M12 3 C9 6 9 18 12 21 " +
                        "M12 3 C15 6 15 18 12 21"
                );
                break;

            case "moon":
                icon.setContent(
                        "M19 15 C17.5 18.5 13.5 20 10 18.5 " +
                        "C6.5 17 4.5 13.5 5 10 " +
                        "C5.4 7.2 7.1 5 9.5 4 " +
                        "C8.7 7.4 10.5 10.8 13.5 12.2 " +
                        "C15.2 13 17.2 13.4 19 12.8 Z"
                );
                break;

            case "email":
                icon.setContent(
                        "M3 6 H21 V18 H3 Z M3 7 L12 13 L21 7"
                );
                break;

            case "phone":
                icon.setContent(
                        "M6 4 L9 3 L12 8 L9.5 10 " +
                        "C10.5 13 12 14.5 15 15.5 L17 13 " +
                        "L21 16 L20 19 " +
                        "C19 21 15 20 12 18.5 " +
                        "C7 16 4 12 3 7 C2.5 5 4 4.5 6 4 Z"
                );
                break;

            default:
                icon.setContent(
                        "M12 3 C16.97 3 21 7.03 21 12 " +
                        "C21 16.97 16.97 21 12 21 " +
                        "C7.03 21 3 16.97 3 12 " +
                        "C3 7.03 7.03 3 12 3 Z"
                );
        }

        return icon;
    }

    private static StackPane createAvatar(
            String letter,
            double radius
    ) {

        double size = radius * 2;

        StackPane avatar = new StackPane();

        avatar.setPrefSize(size, size);
        avatar.setMinSize(size, size);
        avatar.setMaxSize(size, size);

        Circle background =
                new Circle(
                        radius,
                        Color.web("#E7EEF9")
                );

        avatar.getChildren().add(background);

        URL imageUrl =
                ProfileScreen.class.getResource(
                        "/images/profile-photo.png"
                );

        if (imageUrl != null) {

            Image image =
                    new Image(
                            imageUrl.toExternalForm(),
                            false
                    );

            if (!image.isError() && image.getWidth() > 0 && image.getHeight() > 0) {

                ImageView imageView =
                        new ImageView(image);

                double imageWidth = image.getWidth();
                double imageHeight = image.getHeight();
                double cropSize = Math.min(imageWidth, imageHeight);

                double cropX =
                        (imageWidth - cropSize) / 2.0;

                double cropY =
                        Math.max(
                                0,
                                (imageHeight - cropSize) * 0.28
                        );

                if (cropY + cropSize > imageHeight) {
                    cropY = imageHeight - cropSize;
                }

                imageView.setViewport(
                        new Rectangle2D(
                                cropX,
                                cropY,
                                cropSize,
                                cropSize
                        )
                );

                imageView.setFitWidth(size);
                imageView.setFitHeight(size);
                imageView.setPreserveRatio(false);
                imageView.setSmooth(true);
                imageView.setCache(true);

                Circle clip =
                        new Circle(
                                radius,
                                radius,
                                radius
                        );

                imageView.setClip(clip);

                avatar.getChildren().add(imageView);

            } else {
                addAvatarLetter(avatar, letter, radius);
            }

        } else {
            addAvatarLetter(avatar, letter, radius);
        }

        return avatar;
    }

    private static void addAvatarLetter(
            StackPane avatar,
            String letter,
            double radius
    ) {

        Label text =
                new Label(letter);

        text.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        radius * 0.65
                )
        );

        text.setTextFill(
                Color.web(BLUE)
        );

        avatar.getChildren().add(text);
    }

    private static String cardStyle() {

        return
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 14px;" +
                "-fx-background-radius: 14px;" +
                "-fx-effect: dropshadow(" +
                "gaussian, rgba(30,50,90,0.08), 12, 0.12, 0, 3);";
    }
}