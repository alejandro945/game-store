package thread;

import controller.NodeController;
import controller.PaymentController;
import javafx.application.Platform;
import model.Costumer;
import model.Game;

public class AuxThread extends Thread {
    private Costumer c;
    private NodeController nController;
    PaymentController pController;

    public AuxThread(Costumer c, NodeController nController, PaymentController pController) {
        this.c = c;
        this.nController = nController;
        this.pController = pController;
    }

    private void report() {
        pController.setReport(c.getId() + " " + nController.getCashier().getToPay() + "\n"
                + nController.getCashier().getPack().getInfo() + "\n");
        nController.setPack("");
        nController.setToPay(0);
    }

    @Override
    public void run() {
        while (!c.getShopBasket().isEmpty()) {
            try {
                Thread.sleep(3200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Game g = c.getShopBasket().pop();
                    if (g != null) {
                        nController.getCashier().getPack().push(g);
                        nController.setPack(nController.getCashier().getPack().getInfo());
                        nController.getCashier().setToPay(g.getPrice());
                        nController.setToPay(nController.getCashier().getToPay());
                        nController.setCurrent(c);
                        g.purchase();
                        if (c.getShopBasket().isEmpty()) {
                            report();
                            nController.getCashier().setBusy(false);
                            nController.setCashier(nController.getCashier());
                            nController.setCurrent(null);
                            pController.initBtn();
                        }
                    }
                }
            });
        }
    }
}
