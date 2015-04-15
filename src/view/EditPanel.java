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
import model.Ingredient;
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
    private ArrayList<Ingredient> ingredientList;
    private int recipeOffset;
    private int IngredientOffset;
    private final ImageIcon checkedImg = new ImageIcon("src\\view\\images\\checked.png");
    private final ImageIcon uncheckedImg = new ImageIcon("src\\view\\images\\unchecked.png");
    private DefaultListModel model;

    /**
     * Creates new form EditPanel
     */
    public EditPanel() {
        recipeOffset = 0;
        IngredientOffset = 0;
        recipeList = GUI.getCh().getRh().getRecipeList();
        unitList = GUI.getCh().getUh().getUnitList();
        ingredientList = GUI.getCh().getIh().getIngredientList();
        model = new DefaultListModel();
        initComponents();
        jListEditRecipeIngredients.setModel(model);
        setSize(400, 1000);

        setTextFieldListener(jTextFieldRecipeSearch, jPicRecipeFound, "Recipe");
        setTextFieldListener(jTextFieldIngredientSearch, jPicIngredientFound, "Ingredient");
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
                    if (clearPanel == "Recipe") {
                        recipeOffset = value.getOffset() + 1;
                    }
                    if (clearPanel == "Ingredient") {
                        IngredientOffset = value.getOffset() + 1;
                    }
                }
            }
        });
    }

    public void autoFillRecipe(java.awt.event.KeyEvent evt, JTextField textField, ArrayList list, JLabel jPic, int offset) {
        if (textField.getText().length() > 0) {
            if (Character.isAlphabetic(evt.getKeyChar()) || evt.getKeyCode() == 32) {
                boolean found = false;
                for (int i = 0; i < list.size() && !found; i++) {
                    if (list.get(i).toString().toLowerCase().startsWith(textField.getText().toLowerCase())) {
                        found = true;
                        textField.setText(list.get(i).toString());
                        textField.select(offset, textField.getText().length());
                        setObject(list.get(i));
                        jPic.setIcon(checkedImg);
                    }
                }
            }
        }
    }

    public void setObject(Object sentObj) {
        if ("Recipe".equals(sentObj.getClass().getSimpleName()) || "Ingredient".equals(sentObj.getClass().getSimpleName())) {

            if ("Ingredient".equals(sentObj.getClass().getSimpleName())) {
                Ingredient ing = (Ingredient) sentObj;
                jTextFieldIngredientNewName.setText(ing.getName());
            }
            if ("Recipe".equals(sentObj.getClass().getSimpleName())) {
                Recipe recipe = (Recipe) sentObj;
                model.clear();
                for (IngredientAmount ingr : recipe.getIngredientList()) {
                    model.addElement(ingr);
                }
                jTextFieldRecipeNewName.setText(recipe.getName());
                jTextFieldCookingTime.setText(recipe.getCookingtime() + "");
                jTextFieldPortions.setText(recipe.getPortions() + "");
                jTextAreaDescription.setText(recipe.getDescription());
            }
        }
    }

    public void clearSelection(String clearPanel) {
        if (clearPanel.equals("Recipe")) {
            model.clear();
            jTextFieldRecipeNewName.setText("");
            jTextFieldEditRecipeAmount.setText("");
            jComboBoxEditRecipeUnit.setSelectedIndex(0);
            jTextFieldCookingTime.setText("");
            jTextFieldPortions.setText("");
            jTextAreaDescription.setText("");
        }
        if (clearPanel.equals("Ingredient")) {
            jTextFieldIngredientNewName.setText("");
        }
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
        jTextFieldRecipeSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPortions = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCookingTime = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListEditRecipeIngredients = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldEditRecipeAmount = new javax.swing.JTextField();
        jPicRecipeFound = new javax.swing.JLabel();
        jComboBoxEditRecipeUnit = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldRecipeNewName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanelEditIngredient = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldIngredientSearch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPicIngredientFound = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldIngredientNewName = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(100, 50, 0));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        jLabel1.setText("Ret opskrift");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Søg opskrift");

        jTextFieldRecipeSearch.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldRecipeSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRecipeSearchActionPerformed(evt);
            }
        });
        jTextFieldRecipeSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldRecipeSearchKeyReleased(evt);
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

        jButton3.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton3.setText("Fjern ingrediens");

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

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setText("Navn");

        jTextFieldRecipeNewName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("Beskrivelse");

        javax.swing.GroupLayout jPanelEditRecipeLayout = new javax.swing.GroupLayout(jPanelEditRecipe);
        jPanelEditRecipe.setLayout(jPanelEditRecipeLayout);
        jPanelEditRecipeLayout.setHorizontalGroup(
            jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditRecipeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEditRecipeLayout.createSequentialGroup()
                        .addComponent(jTextFieldRecipeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPicRecipeFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldRecipeNewName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEditRecipeLayout.createSequentialGroup()
                                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                    .addComponent(jTextFieldEditRecipeAmount))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                                        .addGap(0, 10, Short.MAX_VALUE)
                                        .addComponent(jComboBoxEditRecipeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEditRecipeLayout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldPortions, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldCookingTime, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(10, 10, 10))
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanelEditRecipeLayout.setVerticalGroup(
            jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCookingTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPortions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxEditRecipeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEditRecipeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldRecipeSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPicRecipeFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRecipeNewName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        jLabel9.setText("Ret ingrediens");

        jTextFieldIngredientSearch.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldIngredientSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIngredientSearchActionPerformed(evt);
            }
        });
        jTextFieldIngredientSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldIngredientSearchKeyReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setText("Søg ingrediens");

        jPicIngredientFound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/unchecked.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setText("Navn");

        jTextFieldIngredientNewName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jButton5.setText("Gem");

        jButton6.setText("Slet ingrediens");

        javax.swing.GroupLayout jPanelEditIngredientLayout = new javax.swing.GroupLayout(jPanelEditIngredient);
        jPanelEditIngredient.setLayout(jPanelEditIngredientLayout);
        jPanelEditIngredientLayout.setHorizontalGroup(
            jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldIngredientNewName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditIngredientLayout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10)
                        .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                            .addComponent(jTextFieldIngredientSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPicIngredientFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(220, Short.MAX_VALUE))
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
                    .addComponent(jTextFieldIngredientSearch, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPicIngredientFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIngredientNewName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelEditRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelEditIngredient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jTextFieldRecipeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRecipeSearchActionPerformed
        jTextFieldRecipeSearch.setCaretPosition(jTextFieldRecipeSearch.getText().length());
    }//GEN-LAST:event_jTextFieldRecipeSearchActionPerformed

    private void jTextFieldRecipeSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRecipeSearchKeyReleased
        autoFillRecipe(evt, (JTextField) evt.getSource(), recipeList, jPicRecipeFound, recipeOffset);
    }//GEN-LAST:event_jTextFieldRecipeSearchKeyReleased

    private void jListEditRecipeIngredientsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListEditRecipeIngredientsValueChanged
        if (!evt.getValueIsAdjusting() && !jListEditRecipeIngredients.isSelectionEmpty()) {
            IngredientAmount ing = (IngredientAmount) jListEditRecipeIngredients.getSelectedValue();
            jTextFieldEditRecipeAmount.setText(ing.getAmount() + "");
            jComboBoxEditRecipeUnit.setSelectedItem(ing.getUnit());
        }
    }//GEN-LAST:event_jListEditRecipeIngredientsValueChanged

    private void jTextFieldIngredientSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIngredientSearchActionPerformed
        jTextFieldIngredientSearch.setCaretPosition(jTextFieldIngredientSearch.getText().length());
    }//GEN-LAST:event_jTextFieldIngredientSearchActionPerformed

    private void jTextFieldIngredientSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIngredientSearchKeyReleased
        autoFillRecipe(evt, (JTextField) evt.getSource(), ingredientList, jPicIngredientFound, IngredientOffset);
    }//GEN-LAST:event_jTextFieldIngredientSearchKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBoxEditRecipeUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTextField jTextFieldIngredientNewName;
    private javax.swing.JTextField jTextFieldIngredientSearch;
    private javax.swing.JTextField jTextFieldPortions;
    private javax.swing.JTextField jTextFieldRecipeNewName;
    private javax.swing.JTextField jTextFieldRecipeSearch;
    // End of variables declaration//GEN-END:variables

}
