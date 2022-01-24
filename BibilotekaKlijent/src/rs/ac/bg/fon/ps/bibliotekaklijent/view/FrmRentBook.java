/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package rs.ac.bg.fon.ps.bibliotekaklijent.view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import rs.ac.bg.fon.ps.biblioteka.bibilotekaklijent.controller.ControllerUI;
import rs.ac.bg.fon.ps.biblioteka.model.Book;
import rs.ac.bg.fon.ps.biblioteka.model.User;
import rs.ac.bg.fon.ps.bibliotekaklijent.validation.ValidationException;
import rs.ac.bg.fon.ps.bibliotekaklijent.validation.Validator;
import rs.ac.bg.fon.ps.bibliotekaklijent.view.tableModel.TableModelBook;
import rs.ac.bg.fon.ps.bibliotekaklijent.view.tableModel.TableModelUser;

/**
 *
 * @author Dragana Stefanovic
 */
public class FrmRentBook extends javax.swing.JDialog {

    /**
     * Creates new form FrmRentBook
     */
    public FrmRentBook(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        prepareForm();
        FrameCenter.CenteredFrame(this);
        setTitle("Iznajmljivanje knjige");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnFindBook = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBooks = new javax.swing.JTable();
        lblClanskakarta = new javax.swing.JLabel();
        txtClanskaKarta = new javax.swing.JTextField();
        btnFindUser = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        lblNazivKnjige = new javax.swing.JLabel();
        txtNazivKnjige = new javax.swing.JTextField();
        btnIznajmi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Iznajmljivanje knjige"));

        btnFindBook.setText("Pronađi knjigu");
        btnFindBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindBookActionPerformed(evt);
            }
        });

        tblBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Naziv", "Autor", "Na stanju"
            }
        ));
        jScrollPane3.setViewportView(tblBooks);

        lblClanskakarta.setText("Unesite broj članske karte");

        btnFindUser.setText("Pronađi korisnika");
        btnFindUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindUserActionPerformed(evt);
            }
        });

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Ime", "Prezime", "Telefon", "Adresa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblUser);

        lblNazivKnjige.setText("Unesite naziv knjige");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnFindUser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblClanskakarta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addComponent(txtClanskaKarta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnFindBook, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblNazivKnjige, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(txtNazivKnjige)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClanskakarta)
                    .addComponent(txtClanskaKarta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFindUser)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNazivKnjige, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNazivKnjige, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFindBook)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        btnIznajmi.setText("Iznajmi");
        btnIznajmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIznajmiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnIznajmi, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIznajmi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindUserActionPerformed
        try {
            validateInput(txtClanskaKarta);
            String cardNumber = txtClanskaKarta.getText().trim();
            List<User> users = ControllerUI.getInstance().getUsersByUsersCard(cardNumber);
            if (users == null || users.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da pronadje clana po zadatoj vrednosti.", "Greska", JOptionPane.ERROR_MESSAGE);
                ((TableModelUser) tblUser.getModel()).setUsers(new ArrayList<>());
                ((TableModelBook) tblBooks.getModel()).setKnjige(new ArrayList<>());
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronašao člana.");
                ((TableModelUser) tblUser.getModel()).setUsers(users);
            }
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFindUserActionPerformed

    private void btnFindBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindBookActionPerformed
        try {
            validateInput(txtNazivKnjige);
            String name = txtNazivKnjige.getText().trim();
            String query = "SELECT * FROM knjiga WHERE naziv='" + name + "'";
            List<Book> books = (List<Book>) ControllerUI.getInstance().getBooksByQuery(query);
            if (books.size() == 0) {
                throw new Exception("Sistem ne moze da pronadje knjige po zadatoj vrednosti!");
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronašao knjige po zadatoj vrednosti.");
            }
            ((TableModelBook) tblBooks.getModel()).setKnjige(books);
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            ((TableModelBook) tblBooks.getModel()).setKnjige(new ArrayList<>());
        }
    }//GEN-LAST:event_btnFindBookActionPerformed

    private void btnIznajmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIznajmiActionPerformed
        int selectedrowB = tblBooks.getSelectedRow();
        int selectedRowU = tblUser.getSelectedRow();
        if (selectedrowB != -1 && selectedRowU != -1) {
            Book selectedBook = ((TableModelBook) tblBooks.getModel()).getKnjiga(selectedrowB);
            User selectedUser = ((TableModelUser) tblUser.getModel()).getUser(selectedRowU);
            try {
                rentABook(selectedUser, selectedBook);
               // JOptionPane.showMessageDialog(this,"Sistem je uspešno izvršio iznajmljivanje knjige:\n Knjiga id: "+selectedBook.getBookid()+"\n Član id; "+selectedUser.getUserId());
                 JOptionPane.showMessageDialog(this, "Sistem ne može da izvrši iznajmljivanje.", "Greska", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);

            }

        }
    }//GEN-LAST:event_btnIznajmiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmRentBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRentBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRentBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRentBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmRentBook dialog = new FrmRentBook(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFindBook;
    private javax.swing.JButton btnFindUser;
    private javax.swing.JButton btnIznajmi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblClanskakarta;
    private javax.swing.JLabel lblNazivKnjige;
    private javax.swing.JTable tblBooks;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtClanskaKarta;
    private javax.swing.JTextField txtNazivKnjige;
    // End of variables declaration//GEN-END:variables

    private void validateInput(JTextField input) throws ValidationException {
        Validator.startValidation().validateNotNullOrEmpty(input.getText(), "Unesite vrednost pretrage!").throwIfInvalide();

    }

    private void prepareForm() {
        TableModelUser tmu = new TableModelUser();
        tmu.setUsers(new ArrayList<>());
        tblUser.setModel(tmu);
        TableModelBook tmb = new TableModelBook(new ArrayList<>());
        tblBooks.setModel(tmb);

    }

    private void rentABook(User selectedUser, Book selectedBook) {
        try {

            ControllerUI.getInstance().rentBook(selectedUser, selectedBook);
            int num = ((TableModelBook) tblBooks.getModel()).getKnjiga(tblBooks.getSelectedRow()).getNumberInStock();
            ((TableModelBook) tblBooks.getModel()).getKnjiga(tblBooks.getSelectedRow()).setNumberInStock(num - 1);
            ((TableModelBook) tblBooks.getModel()).fireTableDataChanged();
            String iznajmljivanje = "Sistem je uspešno izvršio iznajmljivanje knjige:\nKorisnik_id: " + selectedUser.getUsercard() + "\nKnjiga_id: " + selectedBook.getBookid();
            JOptionPane.showMessageDialog(this, iznajmljivanje);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);

        }

    }
}
