/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.IngredientAmount;
import model.Recipe;
import model.Unit;

/**
 *
 * @author Jonas
 */
public class EditPanel extends javax.swing.JPanel {

    private ArrayList<Recipe> recipeList;
    private ArrayList<Unit> unitList;
    private int offset;
    private final ImageIcon checkedImg = new ImageIcon("src\\view\\images\\checked.png");
    private final ImageIcon uncheckedImg = new ImageIcon("src\\view\\images\\unchecked.png");
    private DefaultListModel model;

    /**
     * Creates new form EditPanel
     */
    public EditPanel() {
        offset = 0;
        recipeList = new ArrayList<>();
        recipeList = GUI.getRecipeList();
        unitList = new ArrayList<>();
        unitList = GUI.getUnitList();
        model = new DefaultListModel();
        initComponents();
        jListEditRecipeIngredients.setModel(model);
        setSize(400, 1000);

        setTextFieldListener(jTextFieldName, jPicRecipeFound, "Recipe");
        setTextFieldListener(jTextFieldName1, jPicIngredientFound, "Ingredient");
        setUnits();
    }

    public void setUnits() {
        jComboBoxEditRecipeUnit.removeAllItems();
        for (Unit unit : unitList) {
            jComboBoxEditRecipeUnit.addItem(unit);
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
                    clearSelection(clearPanel);
                }
                if (value.getLength() == 1) {
                    offset = value.getOffset() + 1;
                }
            }
        });
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
        jPanelEditRecipe = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPortions = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCookingTime = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListEditRecipeIngredients = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEditRecipeAmount = new javax.swing.JTextField();
        jPicRecipeFound = new javax.swing.JLabel();
        jComboBoxEditRecipeUnit = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jPanelEditIngredient = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldName1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPicIngredientFound = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(100, 50, 0));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        jLabel1.setText("Ret opskrift");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Navn");

        jTextFieldName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameActionPerformed(evt);
            }
        });
        jTextFieldName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNameKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Ingredienser");

        jTextFieldPortions.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Antal personer");

        jTextFieldCookingTime.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setText("Tilberedningstid");

        jButton1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton1.setText("Slet opskrift");

        jButton2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton2.setText("Gem opskrift");

        jListEditRecipeIngredients.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jListEditRecipeIngredients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListEditRecipeIngredients.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListEditRecipeIngredientsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListEditRecipeIngredients);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("Beskrivelse");

        jButton3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton3.setText("Fjern ingrediens");

        jButton4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton4.setText("Gem ingrediens");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setText("Antal");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setText("Enhed");

        jTextFieldEditRecipeAmount.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jPicRecipeFound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/unchecked.png"))); // NOI18N

        jComboBoxEditRecipeUnit.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        jTextAreaDescription.setLineWrap(true);
        jTextAreaDescription.setRows(1);
        jTextAreaDescription.setWrapStyleWord(true);
        jTextAreaDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jTextAreaDescription);

        javax.swing.GroupLayout jPanelEditRecipeLayout = new javax.swing.GroupLayout(jPanelEditRecipe);
        jPanelEditRecipe.setLayout(jPanelEditRecipeLayout);
        jPanelEditRecipeLayout.setHorizontalGroup(
            jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditRecipeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditRecipeLayout.createSequentialGroup()
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldEditRecipeAmount)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jComboBoxEditRecipeUnit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditRecipeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton3)
                                .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPicRecipeFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCookingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPortions)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        jPanelEditRecipeLayout.setVerticalGroup(
            jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldCookingTime, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPicRecipeFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEditRecipeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxEditRecipeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addComponent(jTextFieldPortions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 23, Short.MAX_VALUE)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        jLabel9.setText("Ret ingrediens");

        jTextFieldName1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldName1ActionPerformed(evt);
            }
        });
        jTextFieldName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldName1KeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setText("Navn");

        jPicIngredientFound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/unchecked.png"))); // NOI18N

        javax.swing.GroupLayout jPanelEditIngredientLayout = new javax.swing.GroupLayout(jPanelEditIngredient);
        jPanelEditIngredient.setLayout(jPanelEditIngredientLayout);
        jPanelEditIngredientLayout.setHorizontalGroup(
            jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(220, 220, 220))
                    .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                        .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10)
                            .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                                .addComponent(jTextFieldName1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPicIngredientFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelEditIngredientLayout.setVerticalGroup(
            jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldName1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPicIngredientFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelEditRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelEditIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelEditIngredient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelEditRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameActionPerformed
        jTextFieldName.setCaretPosition(jTextFieldName.getText().length());
    }//GEN-LAST:event_jTextFieldNameActionPerformed

    private void jTextFieldNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNameKeyReleased
        if (jTextFieldName.getText().length() > 0) {
            if (Character.isAlphabetic(evt.getKeyChar()) || evt.getKeyCode() == 32) {
                boolean found = false;
                for (int i = 0; i < recipeList.size() && !found; i++) {
                    if (recipeList.get(i).getName().toLowerCase().startsWith(jTextFieldName.getText().toLowerCase())) {
                        found = true;
                        jTextFieldName.setText(recipeList.get(i).getName());
                        jTextFieldName.select(offset, jTextFieldName.getText().length());
                        jPicRecipeFound.setIcon(checkedImg);
                        setRecipe(recipeList.get(i));
                    }
                }
            }
        }
    }//GEN-LAST:event_jTextFieldNameKeyReleased

    private void jListEditRecipeIngredientsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListEditRecipeIngredientsValueChanged
        if (!evt.getValueIsAdjusting() && !jListEditRecipeIngredients.isSelectionEmpty()) {
            IngredientAmount ing = (IngredientAmount) jListEditRecipeIngredients.getSelectedValue();
            jTextFieldEditRecipeAmount.setText(ing.getAmount() + "");
            jComboBoxEditRecipeUnit.setSelectedItem(ing.getUnit());
        }

    }//GEN-LAST:event_jListEditRecipeIngredientsValueChanged

    private void jTextFieldName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldName1ActionPerformed

    private void jTextFieldName1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldName1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldName1KeyReleased

    public void setRecipe(Recipe recipe) {
        model.clear();
        for (IngredientAmount ingr : recipe.getIngredientList()) {
            model.addElement(ingr);
        }
        jTextFieldCookingTime.setText(recipe.getCookingtime() + "");
        jTextFieldPortions.setText(recipe.getPortions() + "");
        jTextAreaDescription.setText(recipe.getDescription());
    }

    public void clearSelection(String clearPanel) {
        if (clearPanel == "Recipe") {
            model.clear();
            jTextFieldEditRecipeAmount.setText("");
            jComboBoxEditRecipeUnit.setSelectedIndex(0);
            jTextFieldCookingTime.setText("");
            jTextFieldPortions.setText("");
            jTextAreaDescription.setText("");
        } else {

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBoxEditRecipeUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jListEditRecipeIngredients;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelEditIngredient;
    private javax.swing.JPanel jPanelEditRecipe;
    private javax.swing.JLabel jPicIngredientFound;
    private javax.swing.JLabel jPicRecipeFound;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescription;
    private javax.swing.JTextField jTextFieldCookingTime;
    private javax.swing.JTextField jTextFieldEditRecipeAmount;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldName1;
    private javax.swing.JTextField jTextFieldPortions;
    // End of variables declaration//GEN-END:variables

}
