/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import javax.swing.JPanel;
import model.Recipe;
import model.Weekday;

/**
 *
 * @author Jonas
 */
public class WeekdayPanel extends javax.swing.JPanel {
    
    private Weekday wkday;
    private Recipe weekRecipe;

    /**
     * Creates new form WeekdayPanel
     */
    public WeekdayPanel(Weekday wkday) {
        this.wkday = wkday;
        weekRecipe = wkday.getRecipe();
        initComponents();
        setSize(142, 400);
        setWeekday();
        setColors();
    }
    
    public void setColors() {
        setBackground(GUI.weekPanelColor);
        jLabelDescription.setBackground(GUI.weekPanelColor);
    }
    
    public void setWeekday() {
        jLabelDay.setText(wkday.getWeekName());
        jLabelDate.setText("<html>" + wkday.getDate() + "</html>");
        jLabelDescription.setText(getWeekRecipe().getDescription());
        jLabelOpskriftNavn.setText("<html>" + getWeekRecipe().getName() + "</html>");
        jLabelCookingTime.setText(getWeekRecipe().getCookingtime() + " min");
        jLabelPortions.setText(getWeekRecipe().getPortions() + "");
    }
    
    public Recipe getWeekRecipe() {
        return wkday.getRecipe();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelOpskriftNavn = new javax.swing.JLabel();
        jLabelDay = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelCookingTime = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelPortions = new javax.swing.JLabel();
        jLabelDate = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(105, 135, 186));

        jLabelOpskriftNavn.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabelOpskriftNavn.setForeground(new java.awt.Color(255, 255, 255));
        jLabelOpskriftNavn.setText("Stor fed skinke");

        jLabelDay.setFont(new java.awt.Font("Verdana", 0, 30)); // NOI18N
        jLabelDay.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDay.setText("Mandag");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tilberedning:");

        jLabelCookingTime.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabelCookingTime.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCookingTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCookingTime.setText("30 min");
        jLabelCookingTime.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Antal personer");

        jLabelPortions.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabelPortions.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPortions.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPortions.setText("4");

        jLabelDate.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelDate.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDate.setText("06-04-2015");

        jLabelDescription.setEditable(false);
        jLabelDescription.setBackground(new java.awt.Color(105, 135, 186));
        jLabelDescription.setColumns(20);
        jLabelDescription.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        jLabelDescription.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDescription.setLineWrap(true);
        jLabelDescription.setRows(1);
        jLabelDescription.setToolTipText("<html><p width=\"300\">" +wkday.getRecipe().getDescription()+"</p></html>");
        jLabelDescription.setWrapStyleWord(true);
        jLabelDescription.setAutoscrolls(false);
        jLabelDescription.setBorder(null);
        jLabelDescription.setFocusable(false);

        jButton1.setBackground(new java.awt.Color(204, 51, 0));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Udskift");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(16, 16, 16));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Undlad");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelDay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelOpskriftNavn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDescription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jLabelPortions, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabelCookingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelDay)
                .addGap(6, 6, 6)
                .addComponent(jLabelDate)
                .addGap(0, 0, 0)
                .addComponent(jLabelOpskriftNavn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabelDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabelPortions))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabelCookingTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelCookingTime;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelDay;
    private javax.swing.JTextArea jLabelDescription;
    private javax.swing.JLabel jLabelOpskriftNavn;
    private javax.swing.JLabel jLabelPortions;
    // End of variables declaration//GEN-END:variables
}
