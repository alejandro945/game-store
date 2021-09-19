package routes;

public enum Route {
    MAINPANE("screens/mainPane.fxml"), LOGIN("/ui/screens/admLogin.fxml"), WELCOME("/ui/screens/welcome.fxml"),
    ICON("src/ui/assets/images/Controller.png"), SUCCESS("/ui/assets/images/Success.png"),
    ERROR("/ui/assets/images/Error.png"), WARNING("/ui/assets/images/Warning.png"),
    ALERT("/ui/assets/styles/alert.css"), ECOMMERCE("/ui/screens/ecommerce.fxml"),
    GAMEMODAL("/ui/screens/gameModal.fxml"), ADMDASH("/ui/screens/admDash.fxml"),
    ADDCOSTUMER("/ui/screens/addCostumer.fxml"), SHELVEMODAL("/ui/screens/shelveModal.fxml"),
    TABLE("/ui/assets/styles/table.css"), SECTION1("/ui/screens/section1.fxml"),NODE_COSTUMER("/ui/screens/costumer.fxml"),
    SECTION2("/ui/screens/section2.fxml"), NODE_RACK("/ui/screens/rack.fxml"), SECTION3("/ui/screens/section3.fxml");

    private String route;

    private Route(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
