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
    private boolean suspend;



    public PaymentThread(IQueue<Costumer> line, NodeController nController, PaymentController pController) {
        this.line = line;
        this.nController = nController;
        this.pController = pController;
        suspend = false;
    }

    public void suspendThread(){
        suspend = true;
    }

    public synchronized void rnd(){
        suspend = false;
        notify();
    }

    @Override
    public void run() {
        while (!line.isEmpty()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
                    PaymentThread p = (PaymentThread) this.getClass().cast(this);
                    AuxThread aux = new AuxThread(c, nController,);
                    aux.start();
                   synchronized (this) {
                        while (suspend) {
                            try {
                                wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            });

        }
    }

}
