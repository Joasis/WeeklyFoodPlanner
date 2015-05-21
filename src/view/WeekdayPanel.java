/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Recipe;
import model.Weekday;

/**
 *
 * @author Jonas
 */
public class WeekdayPanel extends javax.swing.JPanel {

    private Weekday wkday;
    private Recipe weekRecipe;
    private GUI gui;
    private boolean drawSwitch;
    private boolean run;

    /**
     * Creates new form WeekdayPanel
     */
    public WeekdayPanel(GUI gui, Weekday wkday) {
        run = false;
        drawSwitch = false;
        this.gui = gui;
        this.wkday = wkday;
        weekRecipe = wkday.getRecipe();
        initComponents();
        setSize(142, 400);
        jPanel1.setSize(142, 400);
        setWeekday();
        fillSubs();
        setColors(GUI.weekPanelColor);
    }

    public void toggleSubs(boolean status) {
        jButtonReplaceRandom.setVisible(status);
        jButtonReplaceRandom.setEnabled(status);
        jComboAllRecipes.setVisible(status);
        jComboAllRecipes.setEnabled(status);

        jButtonReplace.setEnabled(!status);
        jButtonReplace.setVisible(!status);
        jButtonDisable.setEnabled(!status);
        jButtonDisable.setVisible(!status);
    }

    public void setColors(Color col) {
        jLabelDescription.setBackground(col);
        jLabelOpskriftNavn.setBackground(col);
        jPanel1.setBackground(col);
    }

    public void setWeekday() {
        jLabelDay.setText(wkday.getWeekName());
        jLabelDate.setText("<html>" + wkday.getDate() + "</html>");
        jLabelDescription.setText(getWeekRecipe().getDescription());
        jLabelDescription.setToolTipText("<html><p width=\"300\">" + weekRecipe.getDescription() + "</p></html>");
        jLabelOpskriftNavn.setText(getWeekRecipe().getName());
        jLabelOpskriftNavn.setToolTipText("<html><p width=\"300\">" + weekRecipe.getName() + "</p></html>");
        jLabelCookingTime.setText(getWeekRecipe().getCookingtime() + " min");
        jLabelPortions.setText(getWeekRecipe().getPortions() + "");
        if (!wkday.getRecipe().isActive()) {
            disableButtons();
        }
        if (wkday.isOmit() && wkday.getRecipe().isActive()) {
            jButtonDisable.setText("Genaktiver");
        }
        toggleSubs(false);
    }

    public void fillSubs() {
        jComboAllRecipes.removeAllItems();
        jComboAllRecipes.setMaximumRowCount(5);
        for (Recipe recipe : GUI.getCh().getRh().getRecipeList()) {
            if (recipe.isActive()) {
                jComboAllRecipes.addItem(recipe);
            }
        }
        run = true;
    }

    public Recipe getWeekRecipe() {
        return wkday.getRecipe();
    }

    public void switchDay() {
        int swap = GUI.getCh().getWdh().switchDays(wkday);
        if (swap == 0) {
            setColors(GUI.daySwitchColor);
            drawSwitch = true;
        }
        if (swap == 1) {
            setColors(GUI.weekPanelColor);
            drawSwitch = false;
        }
        if (swap == 2) {
            gui.changeTo(gui.getSelectedWeekPanel());
            drawSwitch = false;
        }
    }

    public void draw(Graphics g) {
        if (!wkday.getRecipe().isActive()) {
            if (!drawSwitch) {
                g.setColor(GUI.deletedColor);
                setColors(GUI.omittedColor);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
            Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/deletedred.png"));
            g.drawImage(img, 0, 0, this);
            disableButtons();

        }
        if (wkday.isOmit()) {
            if (!drawSwitch) {
                setColors(GUI.omittedColor);
            }
            Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/undladt.png"));
            g.drawImage(img, 0, 0, this);
            jButtonReplace.setEnabled(true);
        }
    }

    public void disableButtons() {
        jButtonDisable.setEnabled(false);
        jButtonReplace.setEnabled(false);
    }

    public void toggleOmit() {
        if (wkday.isOmit()) {
            setColors(GUI.weekPanelColor);
            wkday.setOmit(false);
            jButtonDisable.setText("Undlad");
        } else {
            setColors(GUI.omittedColor);
            wkday.setOmit(true);
            jButtonDisable.setText("Genaktiver");
        }
        try {
            GUI.getCh().getDbh().updateWeekdayOmit(wkday);
        } catch (SQLException ex) {
            showSqlErrorDialog();
        }
    }

    public void showSqlErrorDialog() {
        GUI.decorateUI("Luk", "");
        JOptionPane.showMessageDialog(this, "Indlæsning af ugeplan mislykkedes\nKunne ikke etablere forbindelse til databasen", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
    }

    public Weekday getWkday() {
        return wkday;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel() {
            public void paint(Graphics g) {
                super.paint(g);
                draw(g);
            }
        };
        jLabelDay = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelCookingTime = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelPortions = new javax.swing.JLabel();
        jLabelDate = new javax.swing.JLabel();
        jLabelDescription = new javax.swing.JTextArea();
        jButtonReplace = new javax.swing.JButton();
        jButtonDisable = new javax.swing.JButton();
        jComboAllRecipes = new javax.swing.JComboBox();
        jButtonReplaceRandom = new javax.swing.JButton();
        jLabelOpskriftNavn = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(105, 135, 186));

        jPanel1.setBackground(GUI.dayColor);
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });
        jPanel1.setLayout(null);

        jLabelDay.setFont(new java.awt.Font("Verdana", 0, 30)); // NOI18N
        jLabelDay.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDay.setText("Mandag");
        jPanel1.add(jLabelDay);
        jLabelDay.setBounds(0, 0, 142, 38);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tilberedning:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(0, 330, 80, 14);

        jLabelCookingTime.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabelCookingTime.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCookingTime.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCookingTime.setText("30 min");
        jLabelCookingTime.setToolTipText("");
        jPanel1.add(jLabelCookingTime);
        jLabelCookingTime.setBounds(90, 330, 50, 14);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Antal personer");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 310, 90, 14);

        jLabelPortions.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabelPortions.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPortions.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPortions.setText("4");
        jPanel1.add(jLabelPortions);
        jLabelPortions.setBounds(110, 310, 30, 14);

        jLabelDate.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelDate.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDate.setText("06-04-2015");
        jPanel1.add(jLabelDate);
        jLabelDate.setBounds(58, 44, 84, 15);

        jLabelDescription.setEditable(false);
        jLabelDescription.setBackground(GUI.dayColor);
        jLabelDescription.setColumns(20);
        jLabelDescription.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        jLabelDescription.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDescription.setLineWrap(true);
        jLabelDescription.setRows(1);
        jLabelDescription.setWrapStyleWord(true);
        jLabelDescription.setAutoscrolls(false);
        jLabelDescription.setBorder(null);
        jLabelDescription.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelDescription.setFocusable(false);
        jLabelDescription.setOpaque(false);
        jLabelDescription.setRequestFocusEnabled(false);
        jLabelDescription.setVerifyInputWhenFocusTarget(false);
        jLabelDescription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelDescriptionMouseReleased(evt);
            }
        });
        jPanel1.add(jLabelDescription);
        jLabelDescription.setBounds(0, 94, 140, 212);

        jButtonReplace.setBackground(GUI.replaceDayColor);
        jButtonReplace.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonReplace.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReplace.setText("Udskift");
        jButtonReplace.setBorder(null);
        jButtonReplace.setBorderPainted(false);
        jButtonReplace.setFocusPainted(false);
        jButtonReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReplaceActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonReplace);
        jButtonReplace.setBounds(0, 350, 142, 25);

        jButtonDisable.setBackground(GUI.disableDayColor);
        jButtonDisable.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonDisable.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDisable.setText("Undlad");
        jButtonDisable.setBorder(null);
        jButtonDisable.setBorderPainted(false);
        jButtonDisable.setFocusPainted(false);
        jButtonDisable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisableActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonDisable);
        jButtonDisable.setBounds(0, 375, 142, 25);

        jComboAllRecipes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboAllRecipesActionPerformed(evt);
            }
        });
        jPanel1.add(jComboAllRecipes);
        jComboAllRecipes.setBounds(0, 375, 142, 25);

        jButtonReplaceRandom.setBackground(GUI.replaceDayColor);
        jButtonReplaceRandom.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonReplaceRandom.setForeground(new java.awt.Color(255, 255, 255));
        jButtonReplaceRandom.setText("Tilfældig");
        jButtonReplaceRandom.setBorder(null);
        jButtonReplaceRandom.setBorderPainted(false);
        jButtonReplaceRandom.setFocusPainted(false);
        jButtonReplaceRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReplaceRandomActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonReplaceRandom);
        jButtonReplaceRandom.setBounds(0, 350, 142, 25);

        jLabelOpskriftNavn.setEditable(false);
        jLabelOpskriftNavn.setBackground(new java.awt.Color(240, 240, 240));
        jLabelOpskriftNavn.setColumns(20);
        jLabelOpskriftNavn.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelOpskriftNavn.setForeground(new java.awt.Color(255, 255, 255));
        jLabelOpskriftNavn.setLineWrap(true);
        jLabelOpskriftNavn.setRows(2);
        jLabelOpskriftNavn.setTabSize(2);
        jLabelOpskriftNavn.setWrapStyleWord(true);
        jLabelOpskriftNavn.setBorder(null);
        jLabelOpskriftNavn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelOpskriftNavn.setFocusable(false);
        jLabelOpskriftNavn.setMaximumSize(new java.awt.Dimension(140, 35));
        jLabelOpskriftNavn.setMinimumSize(new java.awt.Dimension(140, 35));
        jLabelOpskriftNavn.setOpaque(false);
        jLabelOpskriftNavn.setRequestFocusEnabled(false);
        jLabelOpskriftNavn.setVerifyInputWhenFocusTarget(false);
        jLabelOpskriftNavn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelOpskriftNavnMouseReleased(evt);
            }
        });
        jPanel1.add(jLabelOpskriftNavn);
        jLabelOpskriftNavn.setBounds(0, 59, 140, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReplaceActionPerformed
        toggleSubs(true);
    }//GEN-LAST:event_jButtonReplaceActionPerformed

    private void jButtonDisableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisableActionPerformed
        toggleOmit();
    }//GEN-LAST:event_jButtonDisableActionPerformed

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
        switchDay();
    }//GEN-LAST:event_jPanel1MouseReleased

    private void jLabelDescriptionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDescriptionMouseReleased
        switchDay();
    }//GEN-LAST:event_jLabelDescriptionMouseReleased

    private void jButtonReplaceRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReplaceRandomActionPerformed
        getRandomRecipe();
        swapRecipe();
    }//GEN-LAST:event_jButtonReplaceRandomActionPerformed

    public void swapRecipe() {
        try {
            GUI.getCh().getDbh().swapRecipe(getWkday());
            setWeekday();
        } catch (SQLException ex) {
           showSqlErrorDialog();
        }
    }

    public void getRandomRecipe() {
        int random = (int) (Math.random() * GUI.getCh().getRh().getRecipeList().size());
        Recipe randomRecipe = GUI.getCh().getRh().getRecipeList().get(random);
        if (getWeekRecipe() == randomRecipe || !randomRecipe.isActive()) {
            getRandomRecipe();
        } else {
            wkday.setRecipe(randomRecipe);
            weekRecipe = wkday.getRecipe();
        }
    }
    private void jComboAllRecipesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboAllRecipesActionPerformed
        if (run) {
            wkday.setRecipe((Recipe) jComboAllRecipes.getSelectedItem());
            swapRecipe();
        }
    }//GEN-LAST:event_jComboAllRecipesActionPerformed

    private void jLabelOpskriftNavnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelOpskriftNavnMouseReleased
        switchDay();
    }//GEN-LAST:event_jLabelOpskriftNavnMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDisable;
    private javax.swing.JButton jButtonReplace;
    private javax.swing.JButton jButtonReplaceRandom;
    private javax.swing.JComboBox jComboAllRecipes;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelCookingTime;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelDay;
    private javax.swing.JTextArea jLabelDescription;
    private javax.swing.JTextArea jLabelOpskriftNavn;
    private javax.swing.JLabel jLabelPortions;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
