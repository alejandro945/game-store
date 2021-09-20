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

    @Override
    public void run() {
        while (!line.isEmpty()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    nController.setPack("");
                    nController.getCashier().newPack();
                    nController.getCashier().setPayment(0);
                    nController.setToPay(0);
                    Costumer c = line.dequeue();
                    nController.setCurrent(c);
                    nController.getCashier().setBusy(true);
                    nController.setCashier(nController.getCashier());
                    try {
                        pController.initLine();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        AuxThread aux = new AuxThread(c, nController);
                        aux.start();
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
