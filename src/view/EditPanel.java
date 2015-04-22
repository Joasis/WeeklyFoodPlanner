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
import java.util.ArrayList;
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
    private final Image searchImg = Toolkit.getDefaultToolkit().getImage("src\\view\\images\\search.png");
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
        setColors(GUI.buttonHoverColor);
    }

    public void setColors(Color col) {
        jPanel1.setBackground(col);
        jPanelEditIngredient.setBackground(col);
        jPanelEditRecipe.setBackground(col);
    }

    public void setUnits() {
        jComboBoxEditRecipeUnit.removeAllItems();
        for (Unit unit : unitList) {
            jComboBoxEditRecipeUnit.addItem(unit);
            jComboBoxEditIngredientUnit.addItem(unit);
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

    public void autoFillTextField(java.awt.event.KeyEvent evt, JTextField textField, ArrayList list, JLabel jPic, int offset) {
        if (textField.getText().length() > 0) {
            if (Character.isAlphabetic(evt.getKeyChar()) || evt.getKeyCode() == 32 || Character.isDigit(evt.getKeyChar()) || evt.getKeyCode() == 44 || evt.getKeyCode() == 45 || evt.getKeyCode() == 46 || evt.getKeyCode() == 53) {
                boolean found = false;
                for (int i = 0; i < list.size() && !found; i++) {
                    boolean active = true;
                    if ("Recipe".equals(list.get(i).getClass().getSimpleName())) {
                        Recipe rec = (Recipe) list.get(i);
                        if (!rec.isActive()) {
                            active = false;
                        }
                    }
                    if (list.get(i).toString().toLowerCase().startsWith(textField.getText().toLowerCase()) && active) {
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
                toggleIngredientPanel(true);
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
                jTextAreaDescription.setCaretPosition(0);
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
        if (selectedIngredient != null) {
            jButtonAddIngredient.setEnabled(enable);
        }
    }

    public void toggleIngredientPanel(boolean enable) {
        if (selectedRecipe != null) {
            jButtonAddIngredient.setEnabled(enable);
        }
        jTextFieldIngredientNewName.setEnabled(enable);
        jButtonDeleteIngredient.setEnabled(enable);
        jButtonSaveIngredient.setEnabled(enable);
        jTextFieldEditIngredientAmount.setEnabled(enable);
        jComboBoxEditIngredientUnit.setEnabled(enable);
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
            selectedRecipe = null;
            toggleRecipePanel(false);
            clearFeedBack("Recipe");
        }
        if (clearPanel.equals("Ingredient")) {
            jTextFieldIngredientNewName.setText("");
            jTextFieldEditIngredientAmount.setText("");
            jComboBoxEditIngredientUnit.setSelectedIndex(0);
            selectedIngredient = null;
            toggleIngredientPanel(false);
            clearFeedBack("Ingredient");
        }
    }

    public void drawTextField(Graphics g, JTextField textField) {
        if (textField.getText().isEmpty()) {
            g.drawImage(searchImg, textField.getWidth() - 22, 0, this);
        } else {
            repaint();
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
        jTextFieldRecipeSearch = new javax.swing.JTextField(){
            public void paint(Graphics g) {
                super.paint(g);
                drawTextField(g, jTextFieldRecipeSearch);
            }
        };
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
        jLabelShowAllRecipes = new javax.swing.JLabel();
        jPanelEditIngredient = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldIngredientSearch = new javax.swing.JTextField() {
            public void paint(Graphics g) {
                super.paint(g);
                drawTextField(g, jTextFieldIngredientSearch);
            }
        };
        jLabel10 = new javax.swing.JLabel();
        jPicIngredientFound = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldIngredientNewName = new javax.swing.JTextField();
        jButtonSaveIngredient = new javax.swing.JButton();
        jButtonDeleteIngredient = new javax.swing.JButton();
        jButtonAddIngredient = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldEditIngredientAmount = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxEditIngredientUnit = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabelShowAllIngredients = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(100, 50, 0));

        jPanelEditRecipe.setBackground(new java.awt.Color(150, 150, 150));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ret opskrift");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Søg opskrift");

        jTextFieldRecipeSearch.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldRecipeSearch.setBorder(null);
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
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Ingredienser");

        jTextFieldPortions.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldPortions.setBorder(null);
        jTextFieldPortions.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Antal personer");

        jTextFieldCookingTime.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldCookingTime.setBorder(null);
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
        jButtonSaveRecipe.setText("Gem opskrift");
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
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Enhed");

        jTextFieldEditRecipeAmount.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldEditRecipeAmount.setBorder(null);
        jTextFieldEditRecipeAmount.setEnabled(false);

        jPicRecipeFound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/unchecked.png"))); // NOI18N

        jComboBoxEditRecipeUnit.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBoxEditRecipeUnit.setBorder(null);
        jComboBoxEditRecipeUnit.setEnabled(false);

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setFont(new java.awt.Font("Verdana", 2, 11)); // NOI18N
        jTextAreaDescription.setLineWrap(true);
        jTextAreaDescription.setRows(1);
        jTextAreaDescription.setWrapStyleWord(true);
        jTextAreaDescription.setBorder(null);
        jTextAreaDescription.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaDescription);

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Navn");

        jTextFieldRecipeNewName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldRecipeNewName.setBorder(null);
        jTextFieldRecipeNewName.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Beskrivelse");

        jLabelShowAllRecipes.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabelShowAllRecipes.setForeground(new java.awt.Color(107, 191, 76));
        jLabelShowAllRecipes.setText("Vis alle");
        jLabelShowAllRecipes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelShowAllRecipes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelShowAllRecipesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelEditRecipeLayout = new javax.swing.GroupLayout(jPanelEditRecipe);
        jPanelEditRecipe.setLayout(jPanelEditRecipeLayout);
        jPanelEditRecipeLayout.setHorizontalGroup(
            jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditRecipeLayout.createSequentialGroup()
                                        .addComponent(jButtonDeleteRecipe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(23, 23, 23)
                                        .addComponent(jButtonSaveRecipe))
                                    .addComponent(jTextFieldRecipeNewName)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTextFieldCookingTime, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextFieldPortions, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                    .addComponent(jButtonRemoveIngredient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditRecipeLayout.createSequentialGroup()
                                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                                            .addComponent(jTextFieldEditRecipeAmount))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                            .addComponent(jComboBoxEditRecipeUnit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelShowAllRecipes))
                                    .addComponent(jTextFieldRecipeSearch))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPicRecipeFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(219, 219, 219)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelEditRecipeLayout.setVerticalGroup(
            jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabelShowAllRecipes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRecipeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPicRecipeFound))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addComponent(jTextFieldRecipeNewName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldPortions, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCookingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEditRecipeLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEditRecipeLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldEditRecipeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxEditRecipeUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(16, 16, 16)
                .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDeleteRecipe)
                    .addGroup(jPanelEditRecipeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSaveRecipe)
                        .addComponent(jButtonRemoveIngredient)))
                .addContainerGap())
        );

        jPanelEditIngredient.setBackground(new java.awt.Color(150, 150, 150));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Ret ingrediens");

        jTextFieldIngredientSearch.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldIngredientSearch.setBorder(null);
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
        jTextFieldIngredientNewName.setBorder(null);

        jButtonSaveIngredient.setText("Gem ingrediens");
        jButtonSaveIngredient.setEnabled(false);
        jButtonSaveIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveIngredientActionPerformed(evt);
            }
        });

        jButtonDeleteIngredient.setText("Slet ingrediens");
        jButtonDeleteIngredient.setEnabled(false);
        jButtonDeleteIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteIngredientActionPerformed(evt);
            }
        });

        jButtonAddIngredient.setText("Føj til opskrift");
        jButtonAddIngredient.setEnabled(false);
        jButtonAddIngredient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddIngredientActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Antal");

        jTextFieldEditIngredientAmount.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextFieldEditIngredientAmount.setBorder(null);
        jTextFieldEditIngredientAmount.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Enhed");

        jComboBoxEditIngredientUnit.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jComboBoxEditIngredientUnit.setBorder(null);
        jComboBoxEditIngredientUnit.setEnabled(false);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/images/editwatermark.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Tilføj ingrediens");

        jLabelShowAllIngredients.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jLabelShowAllIngredients.setForeground(new java.awt.Color(107, 191, 76));
        jLabelShowAllIngredients.setText("Vis alle");
        jLabelShowAllIngredients.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelShowAllIngredients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelShowAllIngredientsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelEditIngredientLayout = new javax.swing.GroupLayout(jPanelEditIngredient);
        jPanelEditIngredient.setLayout(jPanelEditIngredientLayout);
        jPanelEditIngredientLayout.setHorizontalGroup(
            jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelEditIngredientLayout.createSequentialGroup()
                            .addComponent(jButtonDeleteIngredient)
                            .addGap(62, 62, 62)
                            .addComponent(jButtonSaveIngredient))
                        .addComponent(jTextFieldIngredientNewName, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                        .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                                    .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldEditIngredientAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(31, 31, 31)
                                    .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxEditIngredientUnit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)))
                                .addComponent(jButtonAddIngredient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                        .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelShowAllIngredients)
                            .addComponent(jTextFieldIngredientSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPicIngredientFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 0, 0))
        );
        jPanelEditIngredientLayout.setVerticalGroup(
            jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabelShowAllIngredients))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldIngredientSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPicIngredientFound, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEditIngredientLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldIngredientNewName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDeleteIngredient)
                            .addComponent(jButtonSaveIngredient))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEditIngredientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxEditIngredientUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEditIngredientAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addComponent(jButtonAddIngredient, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelEditRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanelEditIngredient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelEditRecipe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanelEditIngredient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldRecipeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRecipeSearchActionPerformed
        jTextFieldRecipeSearch.setCaretPosition(jTextFieldRecipeSearch.getText().length());
    }//GEN-LAST:event_jTextFieldRecipeSearchActionPerformed

    private void jTextFieldRecipeSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRecipeSearchKeyReleased
        autoFillTextField(evt, (JTextField) evt.getSource(), recipeList, jPicRecipeFound, recipeOffset);
    }//GEN-LAST:event_jTextFieldRecipeSearchKeyReleased

    private void jListEditRecipeIngredientsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListEditRecipeIngredientsValueChanged
        if (!evt.getValueIsAdjusting()) {
            if (!jListEditRecipeIngredients.isSelectionEmpty()) {
                IngredientAmount ing = (IngredientAmount) jListEditRecipeIngredients.getSelectedValue();
                jTextFieldEditRecipeAmount.setText(ing.getAmountString());
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
        autoFillTextField(evt, (JTextField) evt.getSource(), ingredientList, jPicIngredientFound, IngredientOffset);
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

    public void setFeedback(JTextField textField) {
        textField.setBorder(new LineBorder(Color.RED, 1));
    }

    public void clearFeedBack(String panel) {
        if (panel.equals("Recipe")) {
            jTextFieldRecipeNewName.setBorder(null);
            jTextFieldCookingTime.setBorder(null);
            jTextFieldPortions.setBorder(null);
            jTextFieldEditRecipeAmount.setBorder(null);
        }
        if (panel.equals("Ingredient")) {
            jTextFieldIngredientNewName.setBorder(null);
            jTextFieldEditIngredientAmount.setBorder(null);
        }
    }

    public boolean validateIngredientInput() {
        boolean ok = true;
        clearFeedBack("Ingredient");
        if (jTextFieldIngredientNewName.getText().isEmpty()) {
            setFeedback(jTextFieldEditIngredientAmount);
            ok = false;
        }
        try {
            jTextFieldEditIngredientAmount.setText(jTextFieldEditIngredientAmount.getText().replace(",", "."));
            double a = Double.parseDouble(jTextFieldEditIngredientAmount.getText());
        } catch (NumberFormatException ex2) {
            setFeedback(jTextFieldEditIngredientAmount);
            ok = false;
        }
        return ok;
    }

    public boolean validateRecipeInput() {
        boolean ok = true;
        clearFeedBack("Recipe");
        if (jTextFieldRecipeNewName.getText().isEmpty()) {
            ok = false;
            setFeedback(jTextFieldRecipeNewName);
        }
        try {
            int a = Integer.parseInt(jTextFieldCookingTime.getText());
        } catch (NumberFormatException ex2) {
            setFeedback(jTextFieldCookingTime);
            ok = false;
        }
        try {
            int a = Integer.parseInt(jTextFieldPortions.getText());
        } catch (NumberFormatException ex3) {
            setFeedback(jTextFieldPortions);
            ok = false;
        }
        try {
            if (!jListEditRecipeIngredients.isSelectionEmpty()) {
                jTextFieldEditRecipeAmount.setText(jTextFieldEditRecipeAmount.getText().replace(",", "."));
                double c = Double.parseDouble(jTextFieldEditRecipeAmount.getText());
            }
        } catch (NumberFormatException ex4) {
            setFeedback(jTextFieldEditRecipeAmount);
            ok = false;
        }
        return ok;
    }
    private void jButtonDeleteRecipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteRecipeActionPerformed
        deleteSelectedRecipe();
    }//GEN-LAST:event_jButtonDeleteRecipeActionPerformed

    private void jButtonSaveRecipeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveRecipeActionPerformed
        updateSelectedRecipe();
    }//GEN-LAST:event_jButtonSaveRecipeActionPerformed
    public void addIngredientToRecipe() {
        if (validateIngredientInput()) {
            IngredientAmount ingAm = new IngredientAmount((Unit) jComboBoxEditIngredientUnit.getSelectedItem(), selectedIngredient, Double.parseDouble(jTextFieldEditIngredientAmount.getText()));
            if (selectedRecipe != null) {
                if (!GUI.getCh().getRh().isIngredientFound(selectedRecipe, ingAm)) {
                    try {
                        GUI.getCh().getDbh().insertIngredientToRecipe(selectedRecipe, ingAm);
                        selectedRecipe.getIngredientList().add(ingAm);
                        model.addElement(ingAm);
                        jListEditRecipeIngredients.setSelectedValue(ingAm, true);
                        clearSelection("Ingredient");
                        jTextFieldIngredientSearch.setText("");
                    } catch (SQLException ex) {
                        System.out.println("Fejl ved indsættelse af ingredient til nuværende opskrift (DB)");
                        System.out.println(ex);
                    }
                }
            }
        }
    }
    private void jButtonAddIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddIngredientActionPerformed
        addIngredientToRecipe();
    }//GEN-LAST:event_jButtonAddIngredientActionPerformed

    private void jButtonSaveIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveIngredientActionPerformed
        updateIngredient();
    }//GEN-LAST:event_jButtonSaveIngredientActionPerformed

    private void jButtonDeleteIngredientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteIngredientActionPerformed
        deleteIngredient();
    }//GEN-LAST:event_jButtonDeleteIngredientActionPerformed

    private void jLabelShowAllRecipesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelShowAllRecipesMouseClicked
        showAllRecipes();
    }//GEN-LAST:event_jLabelShowAllRecipesMouseClicked

    private void jLabelShowAllIngredientsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelShowAllIngredientsMouseClicked
        showAllIngredients();
    }//GEN-LAST:event_jLabelShowAllIngredientsMouseClicked
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
                    jTextFieldIngredientSearch.setText(selectedIngredient + "");
                    setObject((Ingredient) list.getSelectedValue());
                    jPicIngredientFound.setIcon(checkedImg);
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

    public void showAllRecipes() {
        DefaultListModel tempModel = new DefaultListModel();
        JList list = new JList(tempModel);
        list.setBorder(null);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    SwingUtilities.getWindowAncestor(list).dispose();
                    selectedRecipe = (Recipe) list.getSelectedValue();
                    jTextFieldRecipeSearch.setText(selectedRecipe + "");
                    setObject((Recipe) list.getSelectedValue());
                    jPicRecipeFound.setIcon(checkedImg);
                }
            }
        });
        JScrollPane sp = new JScrollPane(list);
        for (Recipe rec : recipeList) {
            if (rec.isActive()) {
                tempModel.addElement(rec);
            }
        }
        UIManager UI = new UIManager();
        UI.put("OptionPane.background", GUI.buttonHoverColor);
        UI.put("Panel.background", GUI.buttonHoverColor);
        JOptionPane.showMessageDialog(this, sp, "Opskrifter", JOptionPane.PLAIN_MESSAGE);
    }

    public void updateIngredient() {
        String newIngName = jTextFieldIngredientNewName.getText();
        if (selectedIngredient != null && !newIngName.isEmpty() && !newIngName.equals(selectedIngredient.getName())) {
            try {
                selectedIngredient.setName(newIngName);
                GUI.getCh().getDbh().updateIngredient(selectedIngredient);
            } catch (SQLException ex) {
                System.out.println("Fejl ved opdatering af ingrediens i DB");
            }
        } else {
            setFeedback(jTextFieldIngredientNewName);
        }
    }

    public void deleteIngredient() {
        if (selectedIngredient != null) {
            try {
                GUI.getCh().getDbh().deleteIngredient(selectedIngredient);
                ingredientList.remove(selectedIngredient);
                clearSelection("Ingredient");
                jTextFieldIngredientSearch.setText("");
            } catch (SQLException ex) {
                System.out.println("Fejl ved sletning af ingrediens fra database");
            }
        }
    }

    public void updateSelectedRecipe() {
        if (selectedRecipe != null) {
            if (validateRecipeInput()) {
                try {
                    selectedRecipe.setName(jTextFieldRecipeNewName.getText());
                    selectedRecipe.setDescription(jTextAreaDescription.getText());
                    selectedRecipe.setCookingtime(Integer.parseInt(jTextFieldCookingTime.getText()));
                    selectedRecipe.setPortions(Integer.parseInt(jTextFieldPortions.getText()));

                    if (!jListEditRecipeIngredients.isSelectionEmpty()) {
                        if (!jTextFieldEditRecipeAmount.getText().isEmpty()) {
                            IngredientAmount ingAm = (IngredientAmount) jListEditRecipeIngredients.getSelectedValue();
                            ingAm.setAmount(Double.parseDouble(jTextFieldEditRecipeAmount.getText()));
                            ingAm.setUnit((Unit) jComboBoxEditRecipeUnit.getSelectedItem());
                        } else {
                            setFeedback(jTextFieldEditRecipeAmount);
                        }
                    }

                    ArrayList<IngredientAmount> ingAmList;
                    ingAmList = new ArrayList<>();

                    for (int i = 0; i < model.size(); i++) {
                        ingAmList.add((IngredientAmount) model.get(i));
                    }

                    selectedRecipe.setIngredientList(ingAmList);

                    GUI.getCh().getDbh().updateRecipe(selectedRecipe);
                } catch (SQLException ex) {
                    System.out.println("Fejl ved update recipe atabase");
                }
            }
        }
    }

    public void deleteSelectedRecipe() {
        if (selectedRecipe != null) {
            try {
                GUI.getCh().getDbh().deleteRecipe(selectedRecipe);
                selectedRecipe.setActive(false);
                clearSelection("Recipe");
                jTextFieldRecipeSearch.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "FEJL VED SLETNING FRA DATABASE");
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddIngredient;
    private javax.swing.JButton jButtonDeleteIngredient;
    private javax.swing.JButton jButtonDeleteRecipe;
    private javax.swing.JButton jButtonRemoveIngredient;
    private javax.swing.JButton jButtonSaveIngredient;
    private javax.swing.JButton jButtonSaveRecipe;
    private javax.swing.JComboBox jComboBoxEditIngredientUnit;
    private javax.swing.JComboBox jComboBoxEditRecipeUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelShowAllIngredients;
    private javax.swing.JLabel jLabelShowAllRecipes;
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
    private javax.swing.JTextField jTextFieldEditIngredientAmount;
    private javax.swing.JTextField jTextFieldEditRecipeAmount;
    private javax.swing.JTextField jTextFieldIngredientNewName;
    private javax.swing.JTextField jTextFieldIngredientSearch;
    private javax.swing.JTextField jTextFieldPortions;
    private javax.swing.JTextField jTextFieldRecipeNewName;
    private javax.swing.JTextField jTextFieldRecipeSearch;
    // End of variables declaration//GEN-END:variables

}
