/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Week;

/**
 *
 * @author Jonas
 */
public class WeekPanel extends javax.swing.JPanel {

    private WeekdayPanel[] weekdays;
    private Week week;

    /**
     * Creates new form WeekPanel
     */
    public WeekPanel(Week week) {
        weekdays = new WeekdayPanel[7];
        initComponents();
        setBackground(GUI.mainColor);
        setSize(1000, 400);

        int x = 0;
        int y = 0;

        for (int i = 0; i < 7; i++) {
            WeekdayPanel wdp = new WeekdayPanel(week.getWeekdays()[i]);
            wdp.setLocation(x, y);
            jPanelWeekDays.add(wdp);
            x += 143;
        }
    }

    public WeekdayPanel[] getWeekdays() {
        return weekdays;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelWeekDays = new javax.swing.JPanel();

        jPanelWeekDays.setOpaque(false);

        javax.swing.GroupLayout jPanelWeekDaysLayout = new javax.swing.GroupLayout(jPanelWeekDays);
        jPanelWeekDays.setLayout(jPanelWeekDaysLayout);
        jPanelWeekDaysLayout.setHorizontalGroup(
            jPanelWeekDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jPanelWeekDaysLayout.setVerticalGroup(
            jPanelWeekDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelWeekDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 900, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelWeekDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 300, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelWeekDays;
    // End of variables declaration//GEN-END:variables
}
