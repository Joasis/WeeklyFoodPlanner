/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ControlHandler;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.plaf.basic.BasicComboBoxUI;
import model.Week;

/**
 *
 * @author Jonas
 */
public class GUI extends javax.swing.JFrame {

    private ArrayList<Week> weekList;
    private static ControlHandler ch;
    protected final static Color weekPanelColor = new Color(132, 153, 204);
    protected final static Color mainColor = new Color(51, 70, 102);
    private boolean currentWeekSat;
    private boolean firstRun;
    private String chooseWeek;

    /**
     * Creates new form GUI
     */
    public GUI(ControlHandler ch) {
        currentWeekSat = false;
        this.ch = ch;
        firstRun = true;
        chooseWeek = "Vælg uge";
        this.weekList = ch.getWh().getWeekList();
        BasicComboBoxUI bcb = new BasicComboBoxUI();

        /*
         Font font = new Font("Verdana", 2, 12);
         UIManager.put("Label.font", font);
         */
        initComponents();
        jComboWeek.setUI(bcb);

        jPanelContent.setBackground(mainColor);

        addWeeks();

    }

    protected static ControlHandler getCh() {
        return ch;
    }

    public void addWeeks() {
        Week tempweek = null;
        Calendar cal = Calendar.getInstance();
        jComboWeek.addItem(chooseWeek);
        for (Week week : weekList) {
            jComboWeek.addItem(week);
            if (cal.get(Calendar.WEEK_OF_YEAR) == week.getDate()) {
                currentWeekSat = true;
                tempweek = week;
            }
        }

        if (currentWeekSat) {
            jComboWeek.removeItem(chooseWeek);
            jComboWeek.setSelectedItem(tempweek);
            changeTo(getSelectedWeekPanel());
        }
        firstRun = false;
    }

    public WeekPanel getSelectedWeekPanel() {
        WeekPanel wp = null;
        if (jComboWeek.getSelectedItem() != chooseWeek) {
            Week week = (Week) jComboWeek.getSelectedItem();
            wp = new WeekPanel(week);
        }
        return wp;
    }

    public void changeTo(Component page) {
        hidePages();
        jPanelWeek.add(page);
        page.setSize(1000, 400);
        if (page.getClass().getSimpleName().equals("WeekPanel")) {
            enableWeekChooser();
            enableShop();
            enableWeekGen();
            disableBack();
            disableWeekGen();
        }
        if (page.getClass().getSimpleName().equals("ShopPanel")) {
            disableWeekChooser();
            disableShop();
            disableWeekGen();
            enableBack();
        }
        if (page.getClass().getSimpleName().equals("JLabel")) {
            disableShop();
            disableBack();
            enableWeekChooser();
            enableShop();
            enableWeekGen();
        }
        if (page.getClass().getSimpleName().equals("EditPanel")) {
            disableWeekChooser();
            disableShop();
            disableWeekGen();
            enableBack();
        }
        jPanelWeek.revalidate();
        jPanelWeek.repaint();
    }

    public void hidePages() {
        jPanelWeek.removeAll();
    }

    public void disableWeekChooser() {
        jButtonDatePrevious.setEnabled(false);
        jButtonDateNext.setEnabled(false);
        jComboWeek.setEnabled(false);
    }

    public void enableWeekChooser() {
        jButtonDatePrevious.setEnabled(true);
        jButtonDateNext.setEnabled(true);
        jComboWeek.setEnabled(true);
    }

    public void enableShop() {
        jButtonShop.setEnabled(true);
    }

    public void disableShop() {
        jButtonShop.setEnabled(false);
    }

    public void enableWeekGen() {
        jButtonGenerate.setEnabled(true);
    }

    public void disableWeekGen() {
        jButtonGenerate.setEnabled(false);
    }

    public void enableBack() {
        jButtonBack.setEnabled(true);
    }

    public void disableBack() {
        jButtonBack.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelContent = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelWeek = new javax.swing.JPanel();
        jLabelNoWeek = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();
        jButtonGenerate = new javax.swing.JButton();
        jButtonShop = new javax.swing.JButton();
        jPanelWeekNumbers = new javax.swing.JPanel();
        jButtonDatePrevious = new javax.swing.JButton();
        jComboWeek = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButtonDateNext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanelContent.setBackground(new java.awt.Color(51, 0, 102));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Weekly Food Planner");

        jPanelWeek.setOpaque(false);
        jPanelWeek.setPreferredSize(new java.awt.Dimension(1000, 400));

        jLabelNoWeek.setBackground(mainColor);
        jLabelNoWeek.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabelNoWeek.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNoWeek.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNoWeek.setText("Ingen ugeplan fundet ");
        jLabelNoWeek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabelNoWeek.setOpaque(true);

        javax.swing.GroupLayout jPanelWeekLayout = new javax.swing.GroupLayout(jPanelWeek);
        jPanelWeek.setLayout(jPanelWeekLayout);
        jPanelWeekLayout.setHorizontalGroup(
            jPanelWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWeekLayout.createSequentialGroup()
                .addComponent(jLabelNoWeek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanelWeekLayout.setVerticalGroup(
            jPanelWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNoWeek, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jButtonAdd.setBackground(mainColor);
        jButtonAdd.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonAdd.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/add.png"))); // NOI18N
        jButtonAdd.setText("Tilføj");
        jButtonAdd.setBorder(null);
        jButtonAdd.setFocusPainted(false);
        jButtonAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonUpdate.setBackground(mainColor);
        jButtonUpdate.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/update.png"))); // NOI18N
        jButtonUpdate.setText("Ret");
        jButtonUpdate.setBorder(null);
        jButtonUpdate.setFocusPainted(false);
        jButtonUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonBack.setBackground(mainColor);
        jButtonBack.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonBack.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/home.png"))); // NOI18N
        jButtonBack.setText("Tilbage");
        jButtonBack.setBorder(null);
        jButtonBack.setEnabled(false);
        jButtonBack.setFocusPainted(false);
        jButtonBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonGenerate.setBackground(mainColor);
        jButtonGenerate.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jButtonGenerate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/generate.png"))); // NOI18N
        jButtonGenerate.setText("Generér madplan");
        jButtonGenerate.setBorder(null);
        jButtonGenerate.setFocusPainted(false);
        jButtonGenerate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGenerate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateActionPerformed(evt);
            }
        });

        jButtonShop.setBackground(mainColor);
        jButtonShop.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonShop.setForeground(new java.awt.Color(255, 255, 255));
        jButtonShop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/shop.png"))); // NOI18N
        jButtonShop.setText("Indkøbsliste");
        jButtonShop.setBorder(null);
        jButtonShop.setEnabled(false);
        jButtonShop.setFocusPainted(false);
        jButtonShop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonShop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShopActionPerformed(evt);
            }
        });

        jPanelWeekNumbers.setBackground(mainColor);

        jButtonDatePrevious.setBackground(mainColor);
        jButtonDatePrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/back.png"))); // NOI18N
        jButtonDatePrevious.setBorder(null);
        jButtonDatePrevious.setFocusPainted(false);
        jButtonDatePrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDatePreviousActionPerformed(evt);
            }
        });

        jComboWeek.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jComboWeek.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jComboWeek.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jComboWeek.setFocusable(false);
        jComboWeek.setOpaque(false);
        jComboWeek.setRequestFocusEnabled(false);
        jComboWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboWeekActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Uge");

        jButtonDateNext.setBackground(mainColor);
        jButtonDateNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/forward.png"))); // NOI18N
        jButtonDateNext.setBorder(null);
        jButtonDateNext.setFocusPainted(false);
        jButtonDateNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDateNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelWeekNumbersLayout = new javax.swing.GroupLayout(jPanelWeekNumbers);
        jPanelWeekNumbers.setLayout(jPanelWeekNumbersLayout);
        jPanelWeekNumbersLayout.setHorizontalGroup(
            jPanelWeekNumbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWeekNumbersLayout.createSequentialGroup()
                .addComponent(jButtonDatePrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelWeekNumbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboWeek, 0, 108, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDateNext, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelWeekNumbersLayout.setVerticalGroup(
            jPanelWeekNumbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelWeekNumbersLayout.createSequentialGroup()
                .addGroup(jPanelWeekNumbersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDatePrevious, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelWeekNumbersLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonDateNext, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelWeekNumbersLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, 0)
                        .addComponent(jComboWeek)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanelContentLayout = new javax.swing.GroupLayout(jPanelContent);
        jPanelContent.setLayout(jPanelContentLayout);
        jPanelContentLayout.setHorizontalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelWeek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelContentLayout.createSequentialGroup()
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(jButtonGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200)
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonShop, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelContentLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelWeekNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelContentLayout.setVerticalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContentLayout.createSequentialGroup()
                .addGroup(jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelWeekNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addComponent(jPanelWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonShop, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateActionPerformed

    }//GEN-LAST:event_jButtonGenerateActionPerformed

    private void jComboWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboWeekActionPerformed
        if (!firstRun) {
            if (jComboWeek.getSelectedItem().toString() != "Vælg uge") {
                changeTo(getSelectedWeekPanel());
            } else {
                changeTo(jLabelNoWeek);
            }
        }
    }//GEN-LAST:event_jComboWeekActionPerformed

    private void jButtonDatePreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatePreviousActionPerformed
        if (jComboWeek.getSelectedIndex() > 0) {
            jComboWeek.setSelectedIndex(jComboWeek.getSelectedIndex() - 1);
        }
    }//GEN-LAST:event_jButtonDatePreviousActionPerformed

    private void jButtonShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShopActionPerformed
        if (getSelectedWeekPanel() != null) {
            Week week = (Week) jComboWeek.getSelectedItem();
            ShopPanel sp = new ShopPanel(week);
            changeTo(sp);
        }
    }//GEN-LAST:event_jButtonShopActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        if (getSelectedWeekPanel() != null) {
            changeTo(getSelectedWeekPanel());
        } else {
            changeTo(jLabelNoWeek);
        }
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonDateNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDateNextActionPerformed
        int selectedIndexPlus1 = jComboWeek.getSelectedIndex() + 1;
        if (selectedIndexPlus1 < jComboWeek.getItemCount()) {
            jComboWeek.setSelectedIndex(selectedIndexPlus1);
        }
    }//GEN-LAST:event_jButtonDateNextActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        EditPanel ep = new EditPanel();
        changeTo(ep);
    }//GEN-LAST:event_jButtonUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonDateNext;
    private javax.swing.JButton jButtonDatePrevious;
    private javax.swing.JButton jButtonGenerate;
    private javax.swing.JButton jButtonShop;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox jComboWeek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelNoWeek;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelWeek;
    private javax.swing.JPanel jPanelWeekNumbers;
    // End of variables declaration//GEN-END:variables
}
