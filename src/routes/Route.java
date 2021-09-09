package routes;

public enum Route {

    MAINPANE("screens/mainPane.fxml");
    
    private String route;

    private Route(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
