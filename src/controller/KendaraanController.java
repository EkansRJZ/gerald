package controller;

import dao.KendaraanDAO;
import model.Kendaraan;
import view.KendaraanView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class KendaraanController {
    private KendaraanView view;
    private KendaraanDAO dao;

    public KendaraanController(KendaraanView view, KendaraanDAO dao) {
        this.view = view;
        this.dao = dao;
        initController();
        loadTable();
    }

    private void initController() {
        view.getBtnSimpan().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { tambah(); }
        });
        view.getBtnUbah().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { ubah(); }
        });
        view.getBtnHapus().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { hapus(); }
        });
        view.getBtnClear().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { view.clearFields(); }
        });
        view.getBtnRefresh().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { loadTable(); }
        });

        view.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int row = view.getTable().getSelectedRow();
                if (row >= 0) {
                    DefaultTableModel tm = view.getTableModel();
                    view.getTfId().setText(tm.getValueAt(row,0).toString());
                    view.getTfMerek().setText(tm.getValueAt(row,1).toString());
                    view.getTfPlat().setText(tm.getValueAt(row,2).toString());
                    view.getTfWarna().setText(tm.getValueAt(row,3).toString());
                    view.getTfTahun().setText(tm.getValueAt(row,4).toString());
                }
            }
        });
    }

    private void loadTable() {
        DefaultTableModel tm = view.getTableModel();
        tm.setRowCount(0);
        List<Kendaraan> list = dao.getAll();
        for (Kendaraan k : list) {
            tm.addRow(new Object[]{k.getId(), k.getMerek(), k.getPlat(), k.getWarna(), k.getTahun()});
        }
    }

    private void tambah() {
        try {
            String merek = view.getTfMerek().getText().trim();
            String plat = view.getTfPlat().getText().trim();
            String warna = view.getTfWarna().getText().trim();
            String tahun = view.getTfTahun().getText().trim();
            if (merek.isEmpty() || plat.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Merek dan Plat wajib diisi.");
                return;
            }
            Kendaraan k = new Kendaraan();
            k.setMerek(merek); k.setPlat(plat); k.setWarna(warna); k.setTahun(tahun);
            dao.insert(k);
            loadTable();
            view.clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Gagal menambah: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void ubah() {
        try {
            int id = Integer.parseInt(view.getTfId().getText().trim());
            Kendaraan k = new Kendaraan();
            k.setId(id);
            k.setMerek(view.getTfMerek().getText().trim());
            k.setPlat(view.getTfPlat().getText().trim());
            k.setWarna(view.getTfWarna().getText().trim());
            k.setTahun(view.getTfTahun().getText().trim());
            dao.update(k);
            loadTable();
            view.clearFields();
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(view, "Pilih data yang ingin diubah dari tabel.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Gagal ubah: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void hapus() {
        try {
            int id = Integer.parseInt(view.getTfId().getText().trim());
            int confirm = JOptionPane.showConfirmDialog(view, "Hapus ID " + id + " ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dao.delete(id);
                loadTable();
                view.clearFields();
            }
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(view, "Pilih data yang ingin dihapus dari tabel.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Gagal hapus: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
