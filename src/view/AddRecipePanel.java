/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Arc;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
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
    private final ImageIcon checkedImg = new ImageIcon("src\\view\\images\\checked.png");
    private final ImageIcon uncheckedImg = new ImageIcon("src\\view\\images\\unchecked.png");
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

        jTextField_name = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_description = new javax.swing.JTextArea();
        jButton_addIngredient = new javax.swing.JButton();
        jTextField_portion = new javax.swing.JTextField();
        jTextField_cooktime = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField_searchIngredient = new javax.swing.JTextField();
        jButton_saveRecipe = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPic = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListIngredients = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldRecipeAmount = new javax.swing.JTextField();
        jComboBoxRecipeUnit = new javax.swing.JComboBox();
        jLabelShowAllIngredients = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton_removeIngredient = new javax.swing.JButton();

        setBackground(view.GUI.buttonHoverColor);

        jTextField_name.setBorder(null);

        jTextArea_description.setColumns(20);
        jTextArea_description.setRows(5);
        jTextArea_description.setBorder(null);
        jScrollPane1.setViewportView(jTextArea_description);

        jButton_addIngredient.setText("Tilføj");
        jButton_addIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addIngredientActionPerformed(evt);
            }
        });

        jTextField_portion.setBorder(null);

        jTextField_cooktime.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Navn");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Portion(er)");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tilberedningstid");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Beskrivelse");

        jTextField_searchIngredient.setBorder(null);
        jTextField_searchIngredient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_searchIngredientKeyReleased(evt);
            }
        });

        jButton_saveRecipe.setText("Gem Opskrift");
        jButton_saveRecipe.setEnabled(false);
        jButton_saveRecipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveRecipeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tilføj Ingrediens");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tilføj Opskrift");

        jListIngredients.setPreferredSize(new java.awt.Dimension(160, 90));
        jListIngredients.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListIngredientsValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jListIngredients);

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Antal");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Enhed");

        jTextFieldRecipeAmount.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldRecipeAmount.setBorder(null);

        jComboBoxRecipeUnit.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jLabelShowAllIngredients.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabelShowAllIngredients.setForeground(new java.awt.Color(107, 191, 76));
        jLabelShowAllIngredients.setText("Vis alle");
        jLabelShowAllIngredients.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelShowAllIngredients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelShowAllIngredientsMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Indgredienser");

        jButton_removeIngredient.setText("Fjern Ingrediens");
        jButton_removeIngredient.setEnabled(false);
        jButton_removeIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_removeIngredientActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField_name, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_cooktime, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jTextField_portion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel4))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldRecipeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxRecipeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addComponent(jButton_addIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelShowAllIngredients))
                                .addComponent(jTextField_searchIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPic, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton_removeIngredient)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_saveRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(414, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(402, 402, 402))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelShowAllIngredients))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_searchIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPic, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_portion, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_cooktime, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxRecipeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldRecipeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_addIngredient))
                                .addGap(7, 7, 7)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_saveRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton_removeIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
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
            jListIngredients.setSelectedIndex(0);
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
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_removeIngredientActionPerformed

    private void jListIngredientsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListIngredientsValueChanged
        if (!evt.getValueIsAdjusting()) {
            if (!jListIngredients.isSelectionEmpty()) {
                jButton_removeIngredient.setEnabled(true);
                jButton_saveRecipe.setEnabled(true);
            } else {
                jButton_removeIngredient.setEnabled(false);
                jButton_saveRecipe.setEnabled(false);
            }
        }      
    }//GEN-LAST:event_jListIngredientsValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_addIngredient;
    private javax.swing.JButton jButton_removeIngredient;
    private javax.swing.JButton jButton_saveRecipe;
    private javax.swing.JComboBox jComboBoxRecipeUnit;
    private javax.swing.JLabel jLabel1;
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
                System.out.println("Name: " + newRecipe.getName());
                System.out.println("Descr: " + newRecipe.getDescription());
                System.out.println("Cook: " + newRecipe.getCookingtime());
                System.out.println("Personer: " + newRecipe.getPortions());
                System.out.println("ing: " + newRecipe.getIngredientList().size());
            }
        }
    }

    public void addIngredient() throws SQLException, NullPointerException {
        if (validateIngredientInput()) {
            if (selectedIngredient != null) {
                IngredientAmount ingAm = new IngredientAmount((Unit) jComboBoxRecipeUnit.getSelectedItem(), selectedIngredient, Double.parseDouble(jTextFieldRecipeAmount.getText()));
                list.addElement(ingAm);
            } else {
                int ingrID = GUI.getCh().getDbh().getNextAI("ingr");
                Ingredient newIng = new Ingredient(ingrID, jTextField_searchIngredient.getText());
                GUI.getCh().getDbh().insertIngredient(newIng);
                GUI.getCh().getIh().getIngredientList().add(newIng);
                IngredientAmount ingAm = new IngredientAmount((Unit) jComboBoxRecipeUnit.getSelectedItem(), newIng, Double.parseDouble(jTextFieldRecipeAmount.getText()));
                list.addElement(ingAm);
            }
        }
    }

    public void setUnits() {
        jComboBoxRecipeUnit.removeAllItems();
        for (Unit unit : unitList) {
            jComboBoxRecipeUnit.addItem(unit);
        }
    }

    public void setFeedback(JTextField textField) {
        textField.setBorder(new LineBorder(Color.RED, 1));
        textField.setBackground(Color.pink);
    }

    public void clearFeedBack() {
        jTextField_name.setBorder(null);
        jTextField_cooktime.setBorder(null);
        jTextField_portion.setBorder(null);
        jTextFieldRecipeAmount.setBorder(null);
        jTextField_searchIngredient.setBorder(null);
        jTextField_name.setBackground(Color.white);
        jTextField_cooktime.setBackground(Color.white);
        jTextField_portion.setBackground(Color.white);
        jTextFieldRecipeAmount.setBackground(Color.white);
        jTextField_searchIngredient.setBackground(Color.white);

    }

    public boolean validateRecipeInput() {
        boolean ok = true;
        clearFeedBack();
        if (jTextField_name.getText().isEmpty()) {
            ok = false;
            setFeedback(jTextField_name);
        }
        try {
            int a = Integer.parseInt(jTextField_cooktime.getText());
        } catch (NumberFormatException ex2) {
            setFeedback(jTextField_cooktime);
            ok = false;
        }
        try {
            int a = Integer.parseInt(jTextField_portion.getText());
        } catch (NumberFormatException ex3) {
            setFeedback(jTextField_portion);
            ok = false;
        }
        return ok;
    }

    public boolean validateIngredientInput() {
        boolean ok = true;
        clearFeedBack();
        if (jTextField_searchIngredient.getText().isEmpty()) {
            setFeedback(jTextField_searchIngredient);
            ok = false;
        }
        try {

            jTextFieldRecipeAmount.setText(jTextFieldRecipeAmount.getText().replace(",", "."));
            double c = Double.parseDouble(jTextFieldRecipeAmount.getText());

        } catch (NumberFormatException ex4) {
            setFeedback(jTextFieldRecipeAmount);
            ok = false;
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
        UIManager UI = new UIManager();
        UI.put("OptionPane.background", GUI.buttonHoverColor);
        UI.put("Panel.background", GUI.buttonHoverColor);
        JOptionPane.showMessageDialog(this, sp, "Ingredienser", JOptionPane.PLAIN_MESSAGE);
    }
}
