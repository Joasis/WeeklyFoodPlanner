/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private Recipe selectedRecipe;
    private Ingredient selectedIngredient;
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
        setColors();
    }

    public void setColors() {
        jPanel1.setBackground(GUI.mainColor);
        jPanelEditIngredient.setBackground(GUI.mainColor);
        jPanelEditRecipe.setBackground(GUI.mainColor);
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
                selectedIngredient = ing;
            }
            if ("Recipe".equals(sentObj.getClass().getSimpleName())) {
                toggleRecipePanel(true);
                Recipe recipe = (Recipe) sentObj;
                model.clear();
                for (IngredientAmount ingr : recipe.getIngredientList()) {
                    model.addElement(ingr);
                }
                jTextFieldRecipeNewName.setText(recipe.getName());
                jTextFieldCookingTime.setText(recipe.getCookingtime() + "");
                jTextFieldPortions.setText(recipe.getPortions() + "");
                jTextAreaDescription.setText(recipe.getDescription());
                selectedRecipe = recipe;
            }
        }
    }

    public void toggleRecipePanel(boolean enable) {
        jTextFieldRecipeNewName.setEnabled(enable);
        jTextFieldCookingTime.setEnabled(enable);
        jTextFieldPortions.setEnabled(enable);
        jTextAreaDescription.setEnabled(enable);
        jButtonDeleteRecipe.setEnabled(enable);
        jButtonSaveRecipe.setEnabled(enable);
        jButtonRemoveIngredient.setEnabled(enable);
        jListEditRecipeIngredients.setEnabled(enable);
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
            toggleRecipePanel(false);
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
        jButtonDeleteRecipe = new javax.swing.JButton();
        jButtonSaveRecipe = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListEditRecipeIngredients = new javax.swing.JList();
        jButtonRemoveIngredient = new javax.swing.JButton();
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
        jButtonSaveIngredient = new javax.swing.JButton();
        jButtonDeleteIngredient = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(100, 50, 0));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ret opskrift");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
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
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ingredienser");

        jTextFieldPortions.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldPortions.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Antal personer");

        jTextFieldCookingTime.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldCookingTime.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tilberedningstid");

        jButtonDeleteRecipe.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButtonDeleteRecipe.setText("Slet opskrift");
        jButtonDeleteRecipe.setEnabled(false);
        jButtonDeleteRecipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteRecipeActionPerformed(evt);
            }
        });

        jButtonSaveRecipe.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButtonSaveRecipe.setText("Gem");
        jButtonSaveRecipe.setEnabled(false);
        jButtonSaveRecipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveRecipeActionPerformed(evt);
            }
        });

        jListEditRecipeIngredients.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jListEditRecipeIngredients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListEditRecipeIngredients.setEnabled(false);
        jListEditRecipeIngredients.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListEditRecipeIngredientsValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListEditRecipeIngredients);

        jButtonRemoveIngredient.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButtonRemoveIngredient.setText("Fjern ingrediens");
        jButtonRemoveIngredient.setEnabled(false);
        jButtonRemoveIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveIngredientActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Antal");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Enhed");

        jTextFieldEditRecipeAmount.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldEditRecipeAmount.setEnabled(false);

        jPicRecipeFound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/unchecked.png"))); // NOI18N

        jComboBoxEditRecipeUnit.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBoxEditRecipeUnit.setEnabled(false);

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        jTextAreaDescription.setLineWrap(true);
        jTextAreaDescription.setRows(1);
        jTextAreaDescription.setWrapStyleWord(true);
        jTextAreaDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextAreaDescription.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaDescription);

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Navn");

        jTextFieldRecipeNewName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldRecipeNewName.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
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
                    .addComponent(jButtonRemoveIngredient, javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(jButtonDeleteRecipe)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSaveRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
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
                    .addComponent(jButtonRemoveIngredient)
                    .addComponent(jButtonSaveRecipe)
                    .addComponent(jButtonDeleteRecipe))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
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
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Søg ingrediens");

        jPicIngredientFound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/unchecked.png"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Navn");

        jTextFieldIngredientNewName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jButtonSaveIngredient.setText("Gem");

        jButtonDeleteIngredient.setText("Slet ingrediens");

        jButton1.setText("Tilføj til opskrift");

        javax.swing.GroupLayout jPanelEditIngredientLayout = new javax.swing.GroupLayout(jPanelEditIngredient);
        jPanelEditIngredient.setLayout(jPanelEditIngredientLayout);
        jPanelEditIngredientLayout.setHorizontalGroup(
            jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldIngredientNewName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10)
                        .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                            .addComponent(jTextFieldIngredientSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPicIngredientFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                        .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(jButtonDeleteIngredient))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSaveIngredient)))
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
                    .addComponent(jButtonDeleteIngredient)
                    .addComponent(jButtonSaveIngredient))
                .addGap(41, 41, 41)
                .addComponent(jButton1)
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
        if (!evt.getValueIsAdjusting()) {
            if (!jListEditRecipeIngredients.isSelectionEmpty()) {
                IngredientAmount ing = (IngredientAmount) jListEditRecipeIngredients.getSelectedValue();
                jTextFieldEditRecipeAmount.setText(ing.getAmount() + "");
                jComboBoxEditRecipeUnit.setSelectedItem(ing.getUnit());
                jButtonRemoveIngredient.setEnabled(true);
                jTextFieldEditRecipeAmount.setEnabled(true);
                jComboBoxEditRecipeUnit.setEnabled(true);
            } else {
                jButtonRemoveIngredient.setEnabled(false);
                jTextFieldEditRecipeAmount.setEnabled(false);
                jComboBoxEditRecipeUnit.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jListEditRecipeIngredientsValueChanged

    private void jTextFieldIngredientSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIngredientSearchActionPerformed
        jTextFieldIngredientSearch.setCaretPosition(jTextFieldIngredientSearch.getText().length());
    }//GEN-LAST:event_jTextFieldIngredientSearchActionPerformed

    private void jTextFieldIngredientSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldIngredientSearchKeyReleased
        autoFillRecipe(evt, (JTextField) evt.getSource(), ingredientList, jPicIngredientFound, IngredientOffset);
    }//GEN-LAST:event_jTextFieldIngredientSearchKeyReleased

    private void jButtonRemoveIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveIngredientActionPerformed
        removeIngredientFromSelectedRecipe();
    }//GEN-LAST:event_jButtonRemoveIngredientActionPerformed
    public void removeIngredientFromSelectedRecipe() {
        if (selectedRecipe != null) {
            if (!jListEditRecipeIngredients.isSelectionEmpty()) {
                IngredientAmount ingam = (IngredientAmount) jListEditRecipeIngredients.getSelectedValue();
                selectedRecipe.getIngredientList().remove(ingam);
                model.removeElement(ingam);
                jTextFieldEditRecipeAmount.setText("");
                jComboBoxEditRecipeUnit.setSelectedIndex(0);
                try {
                    GUI.getCh().getDbh().deleteIngredientFromRecipe(selectedRecipe, ingam.getIngredient());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "FEJL VED INGREDIENT REMOVAL FRA DATABASE");
                }
            }
        }
    }
    private void jButtonDeleteRecipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteRecipeActionPerformed
        deleteSelectedRecipe();
    }//GEN-LAST:event_jButtonDeleteRecipeActionPerformed

    private void jButtonSaveRecipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveRecipeActionPerformed
        updateSelectedRecipe();
    }//GEN-LAST:event_jButtonSaveRecipeActionPerformed
    public void updateSelectedRecipe() {
        if (selectedRecipe != null) {
            try {
                selectedRecipe.setName(jTextFieldRecipeNewName.getText());
                selectedRecipe.setDescription(jTextAreaDescription.getText());
                selectedRecipe.setCookingtime(Integer.parseInt(jTextFieldCookingTime.getText()));
                selectedRecipe.setPortions(Integer.parseInt(jTextFieldPortions.getText()));

                if (!jListEditRecipeIngredients.isSelectionEmpty()) {
                    System.out.println("Den er ikke tom");
                    IngredientAmount ingAm = (IngredientAmount) jListEditRecipeIngredients.getSelectedValue();
                    ingAm.setAmount(Double.parseDouble(jTextFieldEditRecipeAmount.getText()));
                    ingAm.setUnit((Unit) jComboBoxEditRecipeUnit.getSelectedItem());
                }

                ArrayList<IngredientAmount> ingAmList;
                ingAmList = new ArrayList<>();

                for (int i = 0; i < model.size(); i++) {
                    ingAmList.add((IngredientAmount) model.get(i));
                }

                selectedRecipe.setIngredientList(ingAmList);

                GUI.getCh().getDbh().update(selectedRecipe);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "FEJL VED UPDATE TIL DATABASE");
                System.out.println(ex);
            }
        }
    }

    public void deleteSelectedRecipe() {
        if (selectedRecipe != null) {
            try {
                GUI.getCh().getDbh().deleteRecipe(selectedRecipe);
                recipeList.remove(selectedRecipe);
                clearSelection("Recipe");
                jTextFieldRecipeSearch.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "FEJL VED SLETNING FRA DATABASE");
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDeleteIngredient;
    private javax.swing.JButton jButtonDeleteRecipe;
    private javax.swing.JButton jButtonRemoveIngredient;
    private javax.swing.JButton jButtonSaveIngredient;
    private javax.swing.JButton jButtonSaveRecipe;
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
