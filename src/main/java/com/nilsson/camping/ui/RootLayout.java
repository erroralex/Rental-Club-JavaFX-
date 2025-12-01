package com.nilsson.camping.ui;

import com.nilsson.camping.ui.views.HomeView;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The main container for the application.
 * Uses a BorderPane to hold the custom title bar (Top), the persistent side navigation (Left)
 * and the dynamic content (Center).
 */
public class RootLayout extends BorderPane {

    /**
     * Constructor requires the main Stage reference, a logout callback, and the pre-initialized CustomTitleBar.
     * @param stage The main application Stage.
     * @param onLogout The Runnable to execute to switch back to the login screen.
     * @param titleBar The single, initialized CustomTitleBar instance.
     */
    public RootLayout(Stage stage, Runnable onLogout, CustomTitleBar titleBar) {
        // Apply the CSS class
        this.getStyleClass().add("root-layout");

        // Side Navigation (LEFT)
        SideNavigation sideNav = new SideNavigation(this, stage, onLogout);
        this.setLeft(sideNav);

        // Styling for the navigation width
        sideNav.setPrefWidth(250);

        // Default View (CENTER)
        setContent(new HomeView());
    }

    /**
     * Public method to swap the main content view.
     * @param view The JavaFX Node to display.
     */
    public void setContent(Node view) {
        this.setCenter(view);
    }
}