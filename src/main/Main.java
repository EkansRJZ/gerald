package main;

import controller.KendaraanController;
import dao.KendaraanDAO;
import view.KendaraanView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                KendaraanView view = new KendaraanView();
                KendaraanDAO dao = new KendaraanDAO();
                new KendaraanController(view, dao);
                view.setVisible(true);
            }
        });
    }
}
