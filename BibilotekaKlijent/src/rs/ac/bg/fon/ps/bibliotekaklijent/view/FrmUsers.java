/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package rs.ac.bg.fon.ps.bibliotekaklijent.view;

import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.bibliotekaklijent.validation.Validator;
import rs.ac.bg.fon.ps.bibliotekaklijent.view.tableModel.TableModelUser;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.communication.Communication;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.controller.ControllerUI;
import rs.ac.bg.fon.ps.biblioteka.communication.Operations;
import rs.ac.bg.fon.ps.biblioteka.communication.Receiver;
import rs.ac.bg.fon.ps.biblioteka.communication.Request;
import rs.ac.bg.fon.ps.biblioteka.communication.Response;
import rs.ac.bg.fon.ps.biblioteka.communication.Sender;

/**
 *
 * @author Dragana Stefanovic
 */
public class FrmUsers extends javax.swing.JDialog {

    /**
     * Creates new form FrmPretragaClanova
     */
    public FrmUsers(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Korisnici");
        initComponents();
        FrameCenter.CenteredFrame(this);
        prepareView();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblClanovi = new javax.swing.JTable();
        btnIzbrisi = new javax.swing.JButton();
        btnUcitaj = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtVrednostPretrage = new javax.swing.JTextField();
        btnPretrazi = new javax.swing.JButton();
        btnPrikaziSve = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtVrednostPretrage1 = new javax.swing.JTextField();
        btnFindByName = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(100, 100, 0, 0));
        setIconImage(null);

        tblClanovi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblClanovi);

        btnIzbrisi.setText("Izbrisi");
        btnIzbrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzbrisiActionPerformed(evt);
            }
        });

        btnUcitaj.setText("Ucitaj");
        btnUcitaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUcitajActionPerformed(evt);
            }
        });

        jLabel1.setText("Pretrazi prema broju clanske karte: ");

        btnPretrazi.setText("Pretrazi");
        btnPretrazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziActionPerformed(evt);
            }
        });

        btnPrikaziSve.setText("Prikazi sve");
        btnPrikaziSve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrikaziSveActionPerformed(evt);
            }
        });

        jLabel2.setText("Pretrazi prema imenu i prezimenu: ");

        btnFindByName.setText("Pretrazi");
        btnFindByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindByNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIzbrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUcitaj, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVrednostPretrage, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnPretrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtVrednostPretrage1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnFindByName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPrikaziSve, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnPrikaziSve))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVrednostPretrage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPretrazi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVrednostPretrage1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFindByName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIzbrisi)
                    .addComponent(btnUcitaj))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIzbrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzbrisiActionPerformed
        int selectedRow = tblClanovi.getSelectedRow();
        if (selectedRow != -1) {
            
            try {
                ((TableModelUser) tblClanovi.getModel()).removeClan(selectedRow);
                JOptionPane.showMessageDialog(this, "Sistem je obrisao clana.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da obrise clana."+ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnIzbrisiActionPerformed

    private void btnPretraziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziActionPerformed
        try {
            if (txtVrednostPretrage.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Unesite kriterijum pretrage!");
            } else {
                String cardNumber = txtVrednostPretrage.getText().trim();
                
                List<User> clanovi = (List<User>) ControllerUI.getInstance().getUsersByUsersCard(cardNumber);
                if (clanovi == null || clanovi.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje clanove po zadatoj vrednosti.","Greska", JOptionPane.ERROR_MESSAGE);
                    ((TableModelUser) tblClanovi.getModel()).setUsers(new ArrayList<>());
                } else {
                    ((TableModelUser) tblClanovi.getModel()).setUsers(clanovi);
                    JOptionPane.showMessageDialog(this, "Sistem je pronasao clanove po zadatoj vrednosti.");
                }
                
            }            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            
        }
    }//GEN-LAST:event_btnPretraziActionPerformed

    private void btnPrikaziSveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrikaziSveActionPerformed
        try {
            ((TableModelUser) tblClanovi.getModel()).setUsers(ControllerUI.getInstance().getUsers());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage(),"Greska", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_btnPrikaziSveActionPerformed

    private void btnUcitajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUcitajActionPerformed
        int selectedRow = tblClanovi.getSelectedRow();
        if (selectedRow != -1) {
            User clan = ((TableModelUser) tblClanovi.getModel()).getUsers().get(selectedRow);
            
            new FrmChangeUserInfo((Frame) this.getParent(), true, clan, tblClanovi).setVisible(true);
        }
    }//GEN-LAST:event_btnUcitajActionPerformed

    private void btnFindByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindByNameActionPerformed
        try {
            if (txtVrednostPretrage1.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Unesite kriterijum pretrage!");
            } else {
                String name = txtVrednostPretrage1.getText().trim();
                
                List<User> clanovi = (List<User>) ControllerUI.getInstance().getUsersByName(name);
                if (clanovi == null || clanovi.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje clanove po zadatoj vrednosti.","Greska", JOptionPane.ERROR_MESSAGE);
                    ((TableModelUser) tblClanovi.getModel()).setUsers(new ArrayList<>());
                } else {
                    ((TableModelUser) tblClanovi.getModel()).setUsers(clanovi);
                    JOptionPane.showMessageDialog(this, "Sistem je pronasao clanove po zadatoj vrednosti.");
                }
                
            }            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
            
        }
    }//GEN-LAST:event_btnFindByNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFindByName;
    private javax.swing.JButton btnIzbrisi;
    private javax.swing.JButton btnPretrazi;
    private javax.swing.JButton btnPrikaziSve;
    private javax.swing.JButton btnUcitaj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClanovi;
    private javax.swing.JTextField txtVrednostPretrage;
    private javax.swing.JTextField txtVrednostPretrage1;
    // End of variables declaration//GEN-END:variables

    private void prepareView() {
        TableModelUser tm = new TableModelUser();
        tblClanovi.setModel(tm);
    }
}
