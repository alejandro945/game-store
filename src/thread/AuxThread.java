package thread;

import controller.NodeController;
import javafx.application.Platform;
import model.Costumer;
import model.Game;

public class AuxThread extends Thread {
    private Costumer c;
    private NodeController nController;

    public AuxThread(Costumer c, NodeController nController) {
        this.c = c;
        this.nController = nController;
    }

    @Override
    public void run() {
        while (!c.getShopBasket().isEmpty()) {
            try {
                Thread.sleep(3800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Game g = c.getShopBasket().pop();
                    if(g!=null && g.validateInventory()){
                        nController.getCashier().getPack().push(g);
                        nController.setPack(nController.getCashier().getPack().getInfo());
                        nController.getCashier().setToPay(g.getPrice());
                        nController.setToPay(nController.getCashier().getToPay());
                        g.purchase();
                    }             
                    if (c.getShopBasket().isEmpty()) {
                        nController.getCashier().setBusy(false);
                        nController.setCashier(nController.getCashier());
                    }
                }
            });
        }
        System.out.println(nController.getCashier().getToPay());
    }
}
