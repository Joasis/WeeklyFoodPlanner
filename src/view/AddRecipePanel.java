/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.RecipeHandler;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Ingredient;
import model.IngredientAmount;
import model.Recipe;
import model.Unit;

/**
 *
 * @author BonneOWN
 */
public class AddRecipePanel extends javax.swing.JPanel {

    private DefaultListModel list;
    private ArrayList<Ingredient> ingredientList;
    private ArrayList<Unit> unitList;
    private int offset;
    private final ImageIcon checkedImg = new ImageIcon(getClass().getClassLoader().getResource("res/checked.png"));
    private final ImageIcon uncheckedImg = new ImageIcon(getClass().getClassLoader().getResource("res/unchecked.png"));
    private Ingredient selectedIngredient;

    public AddRecipePanel() {
        offset = 0;
        ingredientList = GUI.getCh().getIh().getIngredientList();
        unitList = GUI.getCh().getUh().getUnitList();
        initComponents();
        selectedIngredient = null;
        list = new DefaultListModel();
        jListIngredients.setModel(list);
        setTextFieldListener(jTextField_searchIngredient, jPic, "Ingredient");
        setUnits();
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
        jLabelShowAllIngredients = new javax.swing.JLabel();
        jTextField_searchIngredient = new javax.swing.JTextField();
        jPic = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton_saveRecipe = new javax.swing.JButton();
        jComboBoxRecipeUnit = new javax.swing.JComboBox();
        jTextFieldRecipeAmount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListIngredients = new javax.swing.JList();
        jButton_removeIngredient = new javax.swing.JButton();
        jButton_addIngredient = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
        jTextField_portion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_description = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jTextField_cooktime = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setBackground(view.GUI.buttonHoverColor);

        jPanel1.setBackground(GUI.buttonHoverColor);

        jLabelShowAllIngredients.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabelShowAllIngredients.setForeground(new java.awt.Color(107, 191, 76));
        jLabelShowAllIngredients.setText("Vis alle");
        jLabelShowAllIngredients.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelShowAllIngredients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelShowAllIngredientsMouseClicked(evt);
            }
        });

        jTextField_searchIngredient.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_searchIngredient.setBorder(null);
        jTextField_searchIngredient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchIngredientKeyReleased(evt);
            }
        });

        jPic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/unchecked.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tilføj Ingrediens");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/addwatermark.png"))); // NOI18N

        jPanel3.setBackground(GUI.buttonHoverColor);

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Indgredienser");

        jButton_saveRecipe.setBackground(GUI.succesColor);
        jButton_saveRecipe.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton_saveRecipe.setForeground(new java.awt.Color(255, 255, 255));
        jButton_saveRecipe.setText("Gem Opskrift");
        jButton_saveRecipe.setBorder(null);
        jButton_saveRecipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveRecipeActionPerformed(evt);
            }
        });

        jComboBoxRecipeUnit.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBoxRecipeUnit.setBorder(null);

        jTextFieldRecipeAmount.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldRecipeAmount.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Antal");

        jScrollPane3.setAutoscrolls(true);

        jListIngredients.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jListIngredients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jListIngredients.setPreferredSize(new java.awt.Dimension(160, 90));
        jListIngredients.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListIngredientsValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jListIngredients);

        jButton_removeIngredient.setBackground(GUI.dangerColor);
        jButton_removeIngredient.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton_removeIngredient.setForeground(new java.awt.Color(255, 255, 255));
        jButton_removeIngredient.setText("Fjern Ingrediens");
        jButton_removeIngredient.setBorder(null);
        jButton_removeIngredient.setEnabled(false);
        jButton_removeIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_removeIngredientActionPerformed(evt);
            }
        });

        jButton_addIngredient.setBackground(GUI.succesColor);
        jButton_addIngredient.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton_addIngredient.setForeground(new java.awt.Color(255, 255, 255));
        jButton_addIngredient.setText("Tilføj");
        jButton_addIngredient.setBorder(null);
        jButton_addIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addIngredientActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Enhed");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRecipeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jComboBoxRecipeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_addIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel9)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(jButton_removeIngredient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_saveRecipe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxRecipeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_addIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jTextFieldRecipeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addGap(7, 7, 7)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_removeIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jButton_saveRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(399, 399, 399)
                                .addComponent(jLabelShowAllIngredients))
                            .addComponent(jTextField_searchIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPic, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelShowAllIngredients))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPic, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_searchIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel2.setBackground(GUI.buttonHoverColor);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tilberedningstid (min)");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Beskrivelse");

        jTextField_name.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_name.setBorder(null);

        jTextField_portion.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_portion.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Navn");

        jTextArea_description.setColumns(20);
        jTextArea_description.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        jTextArea_description.setLineWrap(true);
        jTextArea_description.setRows(5);
        jTextArea_description.setWrapStyleWord(true);
        jTextArea_description.setBorder(null);
        jScrollPane1.setViewportView(jTextArea_description);

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Portioner");

        jTextField_cooktime.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextField_cooktime.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jTextField_cooktime, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(69, 69, 69)
                            .addComponent(jTextField_portion))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_cooktime, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_portion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1))
        );

        jLabel6.setBackground(GUI.succesColor);
        jLabel6.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tilføj Opskrift");
        jLabel6.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_saveRecipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saveRecipeActionPerformed
        try {
            addRecipe();
        } catch (SQLException ex) {
            System.out.println("Sqlfejl ved getNextRecipeNumber");;
        }
    }//GEN-LAST:event_jButton_saveRecipeActionPerformed

    private void jButton_addIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addIngredientActionPerformed
        try {
            addIngredient();

            // 
        } catch (SQLException ex) {
            System.out.println("SQLFEJL ved getNextAI ingr");
        } catch (NullPointerException ex) {
            System.out.println("Nullpointer ved addingredient");
        }
    }//GEN-LAST:event_jButton_addIngredientActionPerformed

    private void jTextField_searchIngredientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_searchIngredientKeyReleased
        autoFillRecipe(evt, (JTextField) evt.getSource(), ingredientList, jPic, offset);
    }//GEN-LAST:event_jTextField_searchIngredientKeyReleased

    private void jLabelShowAllIngredientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelShowAllIngredientsMouseClicked
        showAllIngredients();
    }//GEN-LAST:event_jLabelShowAllIngredientsMouseClicked

    private void jButton_removeIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_removeIngredientActionPerformed
        if (!jListIngredients.isSelectionEmpty()) {
            IngredientAmount ingam = (IngredientAmount) jListIngredients.getSelectedValue();
            list.removeElement(ingam);
            jListIngredients.setSelectedIndex(0);
            jListIngredients.setBorder(null);
        }
    }//GEN-LAST:event_jButton_removeIngredientActionPerformed

    private void jListIngredientsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListIngredientsValueChanged
        if (!evt.getValueIsAdjusting()) {
            if (!jListIngredients.isSelectionEmpty()) {
                jButton_removeIngredient.setEnabled(true);
            } else {
                jButton_removeIngredient.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jListIngredientsValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_addIngredient;
    private javax.swing.JButton jButton_removeIngredient;
    private javax.swing.JButton jButton_saveRecipe;
    private javax.swing.JComboBox jComboBoxRecipeUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelShowAllIngredients;
    private javax.swing.JList jListIngredients;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPic;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea_description;
    private javax.swing.JTextField jTextFieldRecipeAmount;
    private javax.swing.JTextField jTextField_cooktime;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_portion;
    private javax.swing.JTextField jTextField_searchIngredient;
    // End of variables declaration//GEN-END:variables

    public void autoFillRecipe(java.awt.event.KeyEvent evt, JTextField textField, ArrayList list, JLabel jPic, int offset) {
        if (textField.getText().length() > 0) {
            if (Character.isAlphabetic(evt.getKeyChar()) || evt.getKeyCode() == 32) {
                boolean found = false;
                for (int i = 0; i < list.size() && !found; i++) {
                    if (list.get(i).toString().toLowerCase().startsWith(textField.getText().toLowerCase())) {
                        found = true;
                        textField.setText(list.get(i).toString());
                        textField.select(offset, textField.getText().length());
                        selectedIngredient = (Ingredient) list.get(i);
                        jPic.setIcon(checkedImg);
                    }
                }
            }
        }
    }

    public void setTextFieldListener(JTextField textField, JLabel jPic, String clearPanel) {
        textField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                setOffset(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setOffset(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                setOffset(e);
            }

            public void setOffset(DocumentEvent value) {
                if (jPic.getIcon() != uncheckedImg) {
                    jPic.setIcon(uncheckedImg);
                    selectedIngredient = null;
                }

                if (value.getLength() == 1) {
                    if (clearPanel == "Ingredient") {
                        offset = value.getOffset() + 1;
                    }
                }
            }
        });
    }

    public void addRecipe() throws SQLException {
        if (validateRecipeInput()) {
            String name = jTextField_name.getText();
            if (GUI.getCh().getRh().isRecipeFound(name.trim())) {
                JOptionPane.showMessageDialog(this, "Der findes allerede en opskrift med navnet: " + name + "\nVælg et andet navn til din opskrift", "ADVARSEL", JOptionPane.OK_OPTION);
                jTextField_name.selectAll();
                jTextField_name.requestFocus();
            } else {
                int portion = Integer.parseInt(jTextField_portion.getText());
                int cookTime = Integer.parseInt(jTextField_cooktime.getText());
                String description = jTextArea_description.getText();
                int recipeID = GUI.getCh().getDbh().getNextAI("recipe");
                if (recipeID != -1) {
                    ArrayList<IngredientAmount> ingAmList = new ArrayList<>();
                    for (int i = 0; i < list.getSize(); i++) {
                        ingAmList.add((IngredientAmount) list.getElementAt(i));
                    }
                    Recipe newRecipe = new Recipe(recipeID, name, description, portion, cookTime, true, ingAmList);
                    GUI.getCh().getDbh().insertRecipe(newRecipe);
                    GUI.getCh().getRh().getRecipeList().add(newRecipe);
                    if (ingAmList.isEmpty()) {
                        IngredientAmount ingAm = new IngredientAmount(GUI.getCh().getUh().getUnitList().get(0), GUI.getCh().getIh().getIngredientList().get(0), RecipeHandler.NO_INGREDIENTS);
                        GUI.getCh().getDbh().insertIngredientToRecipe(newRecipe, ingAm);
                    }
                    System.out.println("Name: " + newRecipe.getName());
                    System.out.println("Descr: " + newRecipe.getDescription());
                    System.out.println("Cook: " + newRecipe.getCookingtime());
                    System.out.println("Personer: " + newRecipe.getPortions());
                    System.out.println("ing: " + newRecipe.getIngredientList().size());
                }
                clearPanel("All");
            }
        }
    }

    public void addIngredient() throws SQLException, NullPointerException {
        if (validateIngredientInput()) {
            if (selectedIngredient != null) {
                IngredientAmount ingAm = new IngredientAmount((Unit) jComboBoxRecipeUnit.getSelectedItem(), selectedIngredient, Double.parseDouble(jTextFieldRecipeAmount.getText()));
                boolean found = false;
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.getSize() && !found; i++) {
                        IngredientAmount ingAmTemp = (IngredientAmount) list.getElementAt(i);
                        if (ingAmTemp.getIngredient().getName().equals(ingAm.getIngredient().getName()) && ingAmTemp.getUnit().getName().equals(ingAm.getUnit().getName())) {
                            found = true;
                        }
                    }
                    if (!found) {
                        list.addElement(ingAm);
                        clearIngredients();
                    } else {
                        jListIngredients.setBorder(new LineBorder(Color.RED, 1));
                        System.out.println("DEN er på listen");
                    }
                } else {
                    list.addElement(ingAm);
                    clearIngredients();
                }
            } else {
                int ingrID = GUI.getCh().getDbh().getNextAI("ingr");
                Ingredient newIng = new Ingredient(ingrID, jTextField_searchIngredient.getText());
                GUI.getCh().getDbh().insertIngredient(newIng);
                GUI.getCh().getIh().getIngredientList().add(newIng);
                IngredientAmount ingAm = new IngredientAmount((Unit) jComboBoxRecipeUnit.getSelectedItem(), newIng, Double.parseDouble(jTextFieldRecipeAmount.getText()));
                list.addElement(ingAm);
                clearIngredients();
            }
        }
    }

    public void clearIngredients() {
        jTextField_searchIngredient.setText("");
        jTextFieldRecipeAmount.setText("");
        jComboBoxRecipeUnit.setSelectedIndex(0);
        jButton_saveRecipe.setEnabled(true);
        clearFeedBack("Ingredient");
    }

    public void setUnits() {
        jComboBoxRecipeUnit.removeAllItems();
        for (Unit unit : unitList) {
            jComboBoxRecipeUnit.addItem(unit);
        }
    }

    public void setFeedback(JTextField textField) {
        textField.setBorder(new LineBorder(Color.RED, 1));
    }

    public void clearFeedBack(String section) {
        if (section.equals("All")) {
            jTextField_name.setBorder(null);
            jTextField_cooktime.setBorder(null);
            jTextField_portion.setBorder(null);
            jTextFieldRecipeAmount.setBorder(null);
            jTextField_searchIngredient.setBorder(null);
            jListIngredients.setBorder(null);
        }
        if (section.equals("Ingredient")) {
            jTextFieldRecipeAmount.setBorder(null);
            jTextField_searchIngredient.setBorder(null);
            jListIngredients.setBorder(null);
        }
    }

    public boolean validateRecipeInput() {
        boolean ok = true;
        clearFeedBack("All");
        if (jTextField_name.getText().isEmpty()) {
            ok = false;
            setFeedback(jTextField_name);
        }
        if (jTextField_cooktime.getText().length() > 8) {
            setFeedback(jTextField_cooktime);
            ok = false;
            JOptionPane.showMessageDialog(this, "Tilberedningstiden overskrider den maksimale værdi på 8 tegn\nRet værdien, og prøv igen", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
            GUI.decorateUI("Luk", "");
        } else {
            try {
                int a = Integer.parseInt(jTextField_cooktime.getText());
            } catch (NumberFormatException ex2) {
                setFeedback(jTextField_cooktime);
                ok = false;
            }
        }
        if (jTextField_portion.getText().length() > 8) {
            setFeedback(jTextField_portion);
            ok = false;
            JOptionPane.showMessageDialog(this, "Antal overskrider den maksimale værdi på 8 tegn\nRet værdien, og prøv igen", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
            GUI.decorateUI("Luk", "");
        } else {
            try {
                int a = Integer.parseInt(jTextField_portion.getText());
            } catch (NumberFormatException ex3) {
                setFeedback(jTextField_portion);
                ok = false;
            }
        }
        return ok;
    }

    public boolean validateIngredientInput() {
        boolean ok = true;
        clearFeedBack("Ingredient");
        if (jTextField_searchIngredient.getText().isEmpty()) {
            setFeedback(jTextField_searchIngredient);
            ok = false;
        }
        if (jTextFieldRecipeAmount.getText().length() > 8) {
            setFeedback(jTextFieldRecipeAmount);
            ok = false;
            JOptionPane.showMessageDialog(this, "Antal overskrider den maksimale værdi på 8 tegn\nRet værdien, og prøv igen", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
            GUI.decorateUI("Luk", "");
        } else {
            try {
                jTextFieldRecipeAmount.setText(jTextFieldRecipeAmount.getText().replace(",", "."));
                double c = Double.parseDouble(jTextFieldRecipeAmount.getText());

            } catch (NumberFormatException ex4) {
                setFeedback(jTextFieldRecipeAmount);
                ok = false;
            }
        }
        return ok;
    }

//    public void toggleIngredientPanel(boolean enable) {
//        if (selectedRecipe != null) {
//            jButtonAddIngredient.setEnabled(enable);
//        }
//        jTextFieldIngredientNewName.setEnabled(enable);
//        jButtonDeleteIngredient.setEnabled(enable);
//        jButtonSaveIngredient.setEnabled(enable);
//        jTextFieldEditIngredientAmount.setEnabled(enable);
//        jComboBoxEditIngredientUnit.setEnabled(enable);
//    }
    public void setObject(Object sentObj) {

//        toggleIngredientPanel(true);
        Ingredient ing = (Ingredient) sentObj;
        jTextField_searchIngredient.setText(ing.getName());
        selectedIngredient = ing;

    }

    public void showAllIngredients() {
        DefaultListModel tempModel = new DefaultListModel();
        JList list = new JList(tempModel);

        list.setBorder(null);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    SwingUtilities.getWindowAncestor(list).dispose();
                    selectedIngredient = (Ingredient) list.getSelectedValue();
                    jTextField_searchIngredient.setText(selectedIngredient + "");
                    setObject((Ingredient) list.getSelectedValue());
                    jPic.setIcon(checkedImg);
                }
            }
        });
        JScrollPane sp = new JScrollPane(list);
        for (Ingredient ing : ingredientList) {
            tempModel.addElement(ing);
        }
        GUI.decorateUI("OK", "");
        JOptionPane.showMessageDialog(this, sp, "Ingredienser", JOptionPane.PLAIN_MESSAGE);
    }

    public void clearPanel(String clearPanel) {
        if (clearPanel.equals("All")) {
            jTextField_name.setText("");
            jTextField_cooktime.setText("");
            jTextField_portion.setText("");
            jTextField_searchIngredient.setText("");
            jTextArea_description.setText("");
            jTextFieldRecipeAmount.setText("");
            jComboBoxRecipeUnit.setSelectedIndex(0);
            selectedIngredient = null;
            list.clear();
            clearFeedBack("All");
        }
        if (clearPanel.equals("Ingredient")) {
            jTextField_searchIngredient.setText("");
            jTextFieldRecipeAmount.setText("");
            jComboBoxRecipeUnit.setSelectedIndex(0);
            selectedIngredient = null;
            list.clear();
        }
    }
}
