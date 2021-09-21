package thread;

import java.io.IOException;

import collection.queue.IQueue;
import controller.NodeController;
import controller.PaymentController;
import javafx.application.Platform;
import model.Costumer;

public class PaymentThread extends Thread {
    private IQueue<Costumer> line;
    private NodeController nController;
    private PaymentController pController;

    public PaymentThread(IQueue<Costumer> line, NodeController nController, PaymentController pController) {
        this.line = line;
        this.nController = nController;
        this.pController = pController;
    }

    private void initCashier() {
        nController.getCashier().newPack();
        nController.setPack("");
        nController.setToPay(0);
        nController.getCashier().setPayment(0);
        nController.getCashier().setBusy(true);
    }

    @Override
    public void run() {
        while (!line.isEmpty()) {
            Costumer c = line.dequeue();
            int render = c.getShopBasket().size();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if (c != null) {
                        initCashier();
                        nController.setCurrent(c);
                        nController.setCashier(nController.getCashier());
                        try {
                            pController.initLine();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        AuxThread aux = new AuxThread(c, nController, pController);
                        aux.start();
                    }
                }
            });
            try {
                Thread.sleep(4200 * render);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
