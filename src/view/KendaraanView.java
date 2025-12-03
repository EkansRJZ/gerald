package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KendaraanView extends JFrame {
    private JTextField tfId = new JTextField(10);
    private JTextField tfMerek = new JTextField(15);
    private JTextField tfPlat = new JTextField(12);
    private JTextField tfWarna = new JTextField(12);
    private JTextField tfTahun = new JTextField(8);

    private JButton btnSimpan = new JButton("Simpan");
    private JButton btnUbah = new JButton("Ubah");
    private JButton btnHapus = new JButton("Hapus");
    private JButton btnClear = new JButton("Clear");
    private JButton btnRefresh = new JButton("Refresh");

    private JTable table;
    private DefaultTableModel tableModel;

    public KendaraanView() {
        setTitle("CRUD Kendaraan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);

        JPanel panelInput = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0
        gbc.gridx = 0; gbc.gridy = 0;
        panelInput.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        panelInput.add(tfId, gbc);
        tfId.setEditable(false);

        gbc.gridx = 2;
        panelInput.add(new JLabel("Merek:"), gbc);
        gbc.gridx = 3;
        panelInput.add(tfMerek, gbc);

        gbc.gridx = 4;
        panelInput.add(new JLabel("Plat:"), gbc);
        gbc.gridx = 5;
        panelInput.add(tfPlat, gbc);

        // Row 1
        gbc.gridx = 0; gbc.gridy = 1;
        panelInput.add(new JLabel("Warna:"), gbc);
        gbc.gridx = 1;
        panelInput.add(tfWarna, gbc);

        gbc.gridx = 2;
        panelInput.add(new JLabel("Tahun:"), gbc);
        gbc.gridx = 3;
        panelInput.add(tfTahun, gbc);

        // Buttons panel
        JPanel panelButton = new JPanel();
        panelButton.add(btnSimpan);
        panelButton.add(btnUbah);
        panelButton.add(btnHapus);
        panelButton.add(btnClear);
        panelButton.add(btnRefresh);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Merek", "Plat", "Warna", "Tahun"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout(8,8));
        add(panelInput, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    // getters
    public JTextField getTfId() { return tfId; }
    public JTextField getTfMerek() { return tfMerek; }
    public JTextField getTfPlat() { return tfPlat; }
    public JTextField getTfWarna() { return tfWarna; }
    public JTextField getTfTahun() { return tfTahun; }

    public JButton getBtnSimpan() { return btnSimpan; }
    public JButton getBtnUbah() { return btnUbah; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnClear() { return btnClear; }
    public JButton getBtnRefresh() { return btnRefresh; }

    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public void clearFields() {
        tfId.setText(""); tfMerek.setText(""); tfPlat.setText(""); tfWarna.setText(""); tfTahun.setText(""); 
    }
}
