package thread;

import controller.NodeController;
import javafx.application.Platform;
import model.Cashier;
import model.Costumer;
import model.Game;

public class AuxThread extends Thread {
    private Costumer c;
    private NodeController nController;
    private PaymentThread pController;

    public AuxThread(Costumer c, NodeController nController, PaymentThread pController) {
        this.c = c;
        this.nController = nController;
        this.pController = pController;
    }

    @Override
    public void run() {
        pController.suspendThread();
        while (!c.getShopBasket().isEmpty()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Game g = c.getShopBasket().pop();
                    nController.getCashier().getPack().push(g);
                    nController.setPack(nController.getCashier().getPack().getInfo());
                    nController.getCashier().setToPay(g.getPrice());
                    nController.setToPay(nController.getCashier().getToPay());
                    g.purchase();
                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(nController.getCashier().getToPay());
        Cashier c = nController.getCashier();
        c.setBusy(false);
        pController.rnd();
    }
}
