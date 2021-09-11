package routes;

public enum Route {
    MAINPANE("screens/mainPane.fxml"), LOGIN("/ui/screens/admLogin.fxml"), WELCOME("/ui/screens/welcome.fxml"),
    ICON("/ui/assets/images/Controller.png"), SUCCESS("/ui/assets/images/Success.png"),
    ERROR("/ui/assets/images/Error.png"), WARNING("/ui/assets/images/Warning.png"),
    ALERT("/ui/assets/styles/alert.css"), ECOMERCE("/ui/screens/ecommerce.fxml"),
    GAMEMODAL("/ui/screens/gameModal.fxml"),ADMDASH("/ui/screens/admDash.fxml");

    private String route;

    private Route(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
