/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ControlHandler;
import control.SocketServer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Inet4Address;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;
import model.Recipe;
import model.Week;
import model.Weekday;

/**
 *
 * @author Jonas
 */
public class GUI extends javax.swing.JFrame {

    private LinkedList<Week> weekList;
    private static ControlHandler ch;
    protected final static Color weekPanelColor = new Color(132, 153, 204);
    protected final static Color mainColor = new Color(51, 70, 102);
    protected final static Color buttonHoverColor = new Color(75, 100, 145);
    protected final static Color dayColor = new Color(105, 135, 186);
    protected final static Color daySwitchColor = new Color(135, 186, 105);
    protected final static Color legendaryDayColor = new Color(255, 150, 0);
    protected final static Color replaceDayColor = new Color(204, 51, 0);
    protected final static Color disableDayColor = new Color(16, 16, 16);
    protected final static Color deletedColor = new Color(0, 0, 0, 80);
    protected final static Color omittedColor = new Color(118, 118, 118);
    protected final static Color succesColor = new Color(107, 191, 76);
    protected final static Color dangerColor = new Color(201, 48, 44);
    private final ImageIcon loadingIcon = new ImageIcon(getClass().getClassLoader().getResource("res/loading.gif"));
    private final Image logoImage = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("res/logo.png"));
    private boolean currentWeekSat;
    private boolean firstRun;
    private String chooseWeek;
    private String currentWeek;

    /**
     * Creates new form GUI
     */
    public GUI(ControlHandler ch) {
        currentWeekSat = false;
        currentWeek = "";
        this.ch = ch;
        firstRun = true;
        chooseWeek = "Ny uge";
        this.weekList = ch.getWh().getWeekList();
        BasicComboBoxUI bcb = new BasicComboBoxUI();

        /*
         Font font = new Font("Verdana", 2, 12);
         UIManager.put("Label.font", font);
         */
        initComponents();
        setIconImage(logoImage);
        jComboWeek.setUI(bcb);

        jPanelContent.setBackground(mainColor);

        addWeeks();
    }

    public static void decorateUI(String confirmText, String cancelText) {
        UIManager.put("OptionPane.okButtonText", confirmText);
        UIManager.put("OptionPane.cancelButtonText", cancelText);
        UIManager.put("OptionPane.background", GUI.buttonHoverColor);
        UIManager.put("Panel.background", GUI.buttonHoverColor);
        UIManager.put("OptionPane.messageForeground", Color.white);
        UIManager.put("OptionPane.messageFont", new Font("Verdana", Font.PLAIN, 12));
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
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
            jComboWeek.setSelectedItem(tempweek);
            changeTo(getSelectedWeekPanel());
        }
        firstRun = false;
    }

    public WeekPanel getSelectedWeekPanel() {
        WeekPanel wp = null;
        if (jComboWeek.getSelectedItem() != chooseWeek) {
            Week week = (Week) jComboWeek.getSelectedItem();
            wp = new WeekPanel(this, week);
        }
        return wp;
    }

    public void changeTo(Component page) {
        hidePages();
        jPanelWeek.add(page);
        page.setSize(1000, 400);
        switch (currentWeek) {
            case "AddRecipePanel":
                jButtonAdd.setBackground(mainColor);
                break;
            case "EditPanel":
                jButtonUpdate.setBackground(mainColor);
                break;
            case "ShopPanel":
                jButtonShop.setBackground(mainColor);
                break;
        }
        currentWeek = page.getClass().getSimpleName();
        enableAll();
        switch (currentWeek) {
            case "WeekPanel":
                disableBack();
                disableWeekGen();
                break;
            case "ShopPanel":
                disableWeekChooser();
                disableShop();
                disableWeekGen();
                break;
            case "JLabel":
                disableShop();
                disableBack();
                disableSync();
                break;
            case "EditPanel":
                disableWeekChooser();
                disableShop();
                disableWeekGen();
                disableSync();
                disableEdit();
                uncheckCheckBoxes();
                break;
            case "AddRecipePanel":
                disableWeekChooser();
                disableShop();
                disableWeekGen();
                disableSync();
                disableAdd();
                uncheckCheckBoxes();
                break;
        }
        jPanelWeek.revalidate();
        jPanelWeek.repaint();
    }

    public void uncheckCheckBoxes() {
        jCheckBoxAllowDuplicated.setSelected(false);
        jCheckBoxExcludeLastWeek.setSelected(false);
    }

    public void hidePages() {
        jPanelWeek.removeAll();
    }

    public void enableAll() {
        enableAdd();
        enableBack();
        enableEdit();
        enableShop();
        enableSync();
        enableWeekChooser();
        enableWeekGen();
    }

    public void disableSync() {
        jButtonSync.setEnabled(false);
    }

    public void enableSync() {
        jButtonSync.setEnabled(true);
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

    public void enableAdd() {
        jButtonAdd.setEnabled(true);
    }

    public void disableAdd() {
        jButtonAdd.setEnabled(false);
    }

    public void enableEdit() {
        jButtonUpdate.setEnabled(true);
    }

    public void disableEdit() {
        jButtonUpdate.setEnabled(false);
    }

    public void establishConnection() {
        if (getSelectedWeekPanel() != null) {
            SocketServer ss;
            try {
                ss = new SocketServer(getSelectedWeekPanel().getWeek());
                Thread td = new Thread(ss);
                td.start();
                decorateUI("Luk", "");
                JOptionPane.showConfirmDialog(this, "Forbind din telefon til netværket.\nIndtast følgende, som ip adresse i appen:\n" + Inet4Address.getLocalHost().getHostAddress() + "\n\n<html><i>Ved forbindelsesproblemer, tjek da evt. din firewall indstilling</i></html>", "Synkronisering til android", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, loadingIcon);
                ss.setActive(false);
                ss.close();
            } catch (IOException ex) {
                showSqlErrorDialog();
            }
        }
    }

    public void showSqlErrorDialog() {
        decorateUI("Luk", "");
        JOptionPane.showMessageDialog(this, "Indlæsning af ugeplan mislykkedes\nKunne ikke etablere forbindelse til databasen", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
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
        jButtonSync = new javax.swing.JButton();
        jPanelSettings = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jCheckBoxAllowDuplicated = new javax.swing.JCheckBox();
        jCheckBoxExcludeLastWeek = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Min madplan");
        setResizable(false);

        jPanelContent.setBackground(mainColor);

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Min madplan");

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
            .addComponent(jLabelNoWeek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelWeekLayout.setVerticalGroup(
            jPanelWeekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNoWeek, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        jButtonAdd.setBackground(mainColor);
        jButtonAdd.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonAdd.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/add.png"))); // NOI18N
        jButtonAdd.setText("Tilføj");
        jButtonAdd.setBorder(null);
        jButtonAdd.setFocusPainted(false);
        jButtonAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonAddMouseExited(evt);
            }
        });
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonUpdate.setBackground(mainColor);
        jButtonUpdate.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/edit.png"))); // NOI18N
        jButtonUpdate.setText("Ret");
        jButtonUpdate.setBorder(null);
        jButtonUpdate.setFocusPainted(false);
        jButtonUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUpdate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonUpdateMouseExited(evt);
            }
        });
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonBack.setBackground(mainColor);
        jButtonBack.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonBack.setForeground(new java.awt.Color(255, 255, 255));
        jButtonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/home.png"))); // NOI18N
        jButtonBack.setText("Forside");
        jButtonBack.setBorder(null);
        jButtonBack.setEnabled(false);
        jButtonBack.setFocusPainted(false);
        jButtonBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonBackMouseExited(evt);
            }
        });
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonGenerate.setBackground(mainColor);
        jButtonGenerate.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        jButtonGenerate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/generate.png"))); // NOI18N
        jButtonGenerate.setText("Generér madplan");
        jButtonGenerate.setBorder(null);
        jButtonGenerate.setFocusPainted(false);
        jButtonGenerate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGenerate.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGenerate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonGenerateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonGenerateMouseExited(evt);
            }
        });
        jButtonGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateActionPerformed(evt);
            }
        });

        jButtonShop.setBackground(mainColor);
        jButtonShop.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonShop.setForeground(new java.awt.Color(255, 255, 255));
        jButtonShop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/shop.png"))); // NOI18N
        jButtonShop.setText("Indkøbsliste");
        jButtonShop.setBorder(null);
        jButtonShop.setEnabled(false);
        jButtonShop.setFocusPainted(false);
        jButtonShop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonShop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonShop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonShopMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonShopMouseExited(evt);
            }
        });
        jButtonShop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShopActionPerformed(evt);
            }
        });

        jPanelWeekNumbers.setBackground(mainColor);

        jButtonDatePrevious.setBackground(mainColor);
        jButtonDatePrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/back.png"))); // NOI18N
        jButtonDatePrevious.setBorder(null);
        jButtonDatePrevious.setFocusPainted(false);
        jButtonDatePrevious.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonDatePreviousMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonDatePreviousMouseExited(evt);
            }
        });
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
        jButtonDateNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/forward.png"))); // NOI18N
        jButtonDateNext.setBorder(null);
        jButtonDateNext.setFocusPainted(false);
        jButtonDateNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonDateNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonDateNextMouseExited(evt);
            }
        });
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

        jButtonSync.setBackground(mainColor);
        jButtonSync.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jButtonSync.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSync.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/update.png"))); // NOI18N
        jButtonSync.setText("Synkroniser");
        jButtonSync.setBorder(null);
        jButtonSync.setEnabled(false);
        jButtonSync.setFocusPainted(false);
        jButtonSync.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSync.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSync.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonSyncMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonSyncMouseExited(evt);
            }
        });
        jButtonSync.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSyncActionPerformed(evt);
            }
        });

        jPanelSettings.setBackground(buttonHoverColor);
        jPanelSettings.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabel12.setFont(new java.awt.Font("Verdana", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Indstillinger for generering af ugeplan");

        jCheckBoxAllowDuplicated.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jCheckBoxAllowDuplicated.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxAllowDuplicated.setText("Tillad dubletter");
        jCheckBoxAllowDuplicated.setFocusPainted(false);
        jCheckBoxAllowDuplicated.setOpaque(false);
        jCheckBoxAllowDuplicated.setRequestFocusEnabled(false);
        jCheckBoxAllowDuplicated.setVerifyInputWhenFocusTarget(false);

        jCheckBoxExcludeLastWeek.setFont(new java.awt.Font("Verdana", 0, 10)); // NOI18N
        jCheckBoxExcludeLastWeek.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxExcludeLastWeek.setText("Undlad sidste uges opskrifter");
        jCheckBoxExcludeLastWeek.setFocusPainted(false);
        jCheckBoxExcludeLastWeek.setOpaque(false);
        jCheckBoxExcludeLastWeek.setRequestFocusEnabled(false);
        jCheckBoxExcludeLastWeek.setVerifyInputWhenFocusTarget(false);
        jCheckBoxExcludeLastWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxExcludeLastWeekActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelSettingsLayout = new javax.swing.GroupLayout(jPanelSettings);
        jPanelSettings.setLayout(jPanelSettingsLayout);
        jPanelSettingsLayout.setHorizontalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsLayout.createSequentialGroup()
                .addComponent(jCheckBoxAllowDuplicated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxExcludeLastWeek))
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelSettingsLayout.setVerticalGroup(
            jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSettingsLayout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxAllowDuplicated)
                    .addComponent(jCheckBoxExcludeLastWeek)))
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
                .addGap(94, 94, 94)
                .addComponent(jButtonSync, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonShop, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelContentLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanelWeekNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelContentLayout.setVerticalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContentLayout.createSequentialGroup()
                .addGroup(jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelContentLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelWeekNumbers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)))
                .addComponent(jPanelWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonShop, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSync, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        AddRecipePanel arp = new AddRecipePanel();
        changeTo(arp);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateActionPerformed
        if (getCh().getRh().getActiveRecipeListsize() < 7) {
            JOptionPane.showMessageDialog(this, "Generering af ugeplan kræver mindst 7 tilføjede opskrifter\nDu mangler kun: " + (7 - getCh().getRh().getActiveRecipeListsize()) + " mere!", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
            decorateUI("Luk", "");
        } else {
            try {
                generateFoodPlan();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Indlæsning af ugeplan mislykkedes\nKunne ikke etablere forbindelse til databasen", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
                decorateUI("Luk", "");
            }
        }
    }//GEN-LAST:event_jButtonGenerateActionPerformed

    public void generateFoodPlan() throws SQLException {
        Calendar newWeek = Calendar.getInstance();
        Weekday[] weekdays = new Weekday[7];
        int nextWeekAi = getCh().getDbh().getNextAI("wk");

        if (currentWeekSat) {
            newWeek = Calendar.getInstance();
            newWeek.setTime(weekList.getFirst().getCal().getTime());
            newWeek.add(Calendar.WEEK_OF_YEAR, 1);
        }
        Calendar wkdayCal = null;

        for (int i = 0; i < 7; i++) {
            wkdayCal = Calendar.getInstance();
            wkdayCal.setTime(newWeek.getTime());
            wkdayCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY + i);
            Recipe tempRecipe = getCh().getRh().getRandomRecipe(i, jCheckBoxAllowDuplicated.isSelected(), jCheckBoxExcludeLastWeek.isSelected(), newWeek);
            Weekday wkday = new Weekday(getCh().getDbh().getNextAI("wkday"), tempRecipe, wkdayCal, nextWeekAi, false);
            weekdays[i] = wkday;
        }
        getCh().getRh().clearAddedRecipes();

        if (nextWeekAi != -1) {
            Week week = new Week(nextWeekAi, newWeek, 0, weekdays);
            getCh().getDbh().insertWeek(week);
            weekList.addFirst(week);
            currentWeekSat = true;
            firstRun = true;
            jComboWeek.removeAllItems();
            jComboWeek.addItem(chooseWeek);
            for (Week tempWeek : weekList) {
                jComboWeek.addItem(tempWeek);
            }
            firstRun = false;
            jComboWeek.setSelectedIndex(1);
        }
    }

    private void jComboWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboWeekActionPerformed
        if (!firstRun) {
            if (jComboWeek.getSelectedIndex() != -1) {
                if (jComboWeek.getSelectedItem() != chooseWeek) {
                    changeTo(getSelectedWeekPanel());
                    enableShop();
                } else {
                    changeTo(jLabelNoWeek);
                    disableShop();
                }
            }
        }
    }//GEN-LAST:event_jComboWeekActionPerformed

    private void jButtonDatePreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDatePreviousActionPerformed
        int selectedIndexPlus1 = jComboWeek.getSelectedIndex() + 1;
        if (selectedIndexPlus1 < jComboWeek.getItemCount()) {
            jComboWeek.setSelectedIndex(selectedIndexPlus1);
        }
    }//GEN-LAST:event_jButtonDatePreviousActionPerformed

    private void jButtonShopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShopActionPerformed
        if (getSelectedWeekPanel() != null) {
            Week week = (Week) jComboWeek.getSelectedItem();
            ShopPanel sp = new ShopPanel(this);
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
        if (jComboWeek.getSelectedIndex() > 0) {
            jComboWeek.setSelectedIndex(jComboWeek.getSelectedIndex() - 1);
        }
    }//GEN-LAST:event_jButtonDateNextActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        EditPanel ep = new EditPanel();
        changeTo(ep);
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddMouseEntered
        if (jButtonAdd.isEnabled()) {
            jButtonAdd.setBackground(buttonHoverColor);
        }
    }//GEN-LAST:event_jButtonAddMouseEntered

    private void jButtonUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUpdateMouseEntered
        if (jButtonUpdate.isEnabled()) {
            jButtonUpdate.setBackground(buttonHoverColor);
        }
    }//GEN-LAST:event_jButtonUpdateMouseEntered

    private void jButtonGenerateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGenerateMouseEntered
        if (jButtonGenerate.isEnabled()) {
            jButtonGenerate.setBackground(buttonHoverColor);
        }
    }//GEN-LAST:event_jButtonGenerateMouseEntered

    private void jButtonBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonBackMouseEntered
        if (jButtonBack.isEnabled()) {
            jButtonBack.setBackground(buttonHoverColor);
        }
    }//GEN-LAST:event_jButtonBackMouseEntered

    private void jButtonShopMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonShopMouseEntered
        if (jButtonShop.isEnabled()) {
            jButtonShop.setBackground(buttonHoverColor);
        }
    }//GEN-LAST:event_jButtonShopMouseEntered

    private void jButtonDatePreviousMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDatePreviousMouseEntered
        if (jButtonDatePrevious.isEnabled()) {
            jButtonDatePrevious.setBackground(buttonHoverColor);
        }
    }//GEN-LAST:event_jButtonDatePreviousMouseEntered

    private void jButtonDateNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDateNextMouseEntered
        if (jButtonDateNext.isEnabled()) {
            jButtonDateNext.setBackground(buttonHoverColor);
        }
    }//GEN-LAST:event_jButtonDateNextMouseEntered

    private void jButtonAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddMouseExited
        if (!currentWeek.equals("AddRecipePanel")) {
            jButtonAdd.setBackground(mainColor);
        }
    }//GEN-LAST:event_jButtonAddMouseExited

    private void jButtonUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUpdateMouseExited
        if (!currentWeek.equals("EditPanel")) {
            jButtonUpdate.setBackground(mainColor);
        }
    }//GEN-LAST:event_jButtonUpdateMouseExited

    private void jButtonGenerateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGenerateMouseExited
        jButtonGenerate.setBackground(mainColor);
    }//GEN-LAST:event_jButtonGenerateMouseExited

    private void jButtonBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonBackMouseExited
        jButtonBack.setBackground(mainColor);
    }//GEN-LAST:event_jButtonBackMouseExited

    private void jButtonShopMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonShopMouseExited
        if (!currentWeek.equals("ShopPanel")) {
            jButtonShop.setBackground(mainColor);
        }
    }//GEN-LAST:event_jButtonShopMouseExited

    private void jButtonDatePreviousMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDatePreviousMouseExited
        jButtonDatePrevious.setBackground(mainColor);
    }//GEN-LAST:event_jButtonDatePreviousMouseExited

    private void jButtonDateNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDateNextMouseExited
        jButtonDateNext.setBackground(mainColor);
    }//GEN-LAST:event_jButtonDateNextMouseExited

    private void jButtonSyncMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSyncMouseEntered
        if (jButtonSync.isEnabled()) {
            jButtonSync.setBackground(buttonHoverColor);
        }
    }//GEN-LAST:event_jButtonSyncMouseEntered

    private void jButtonSyncMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSyncMouseExited
        jButtonSync.setBackground(mainColor);
    }//GEN-LAST:event_jButtonSyncMouseExited

    private void jButtonSyncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSyncActionPerformed
        jButtonSync.setBackground(GUI.succesColor);
        establishConnection();
    }//GEN-LAST:event_jButtonSyncActionPerformed

    private void jCheckBoxExcludeLastWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxExcludeLastWeekActionPerformed
        if (getCh().getRh().getActiveRecipeListsize() < 14) {
            jCheckBoxExcludeLastWeek.setSelected(false);
            JOptionPane.showMessageDialog(this, "Der er ikke nok opskrfiter til, at kunne undlade opskrifter fra sidste uge\nDenne indstilling kræver minimum 14 opskrifter", "ADVARSEL", JOptionPane.ERROR_MESSAGE);
            decorateUI("Luk", "");
        }
    }//GEN-LAST:event_jCheckBoxExcludeLastWeekActionPerformed

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
        } catch (Exception ex) {
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
    private javax.swing.JButton jButtonSync;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JCheckBox jCheckBoxAllowDuplicated;
    private javax.swing.JCheckBox jCheckBoxExcludeLastWeek;
    private javax.swing.JComboBox jComboWeek;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelNoWeek;
    private javax.swing.JPanel jPanelContent;
    private javax.swing.JPanel jPanelSettings;
    private javax.swing.JPanel jPanelWeek;
    private javax.swing.JPanel jPanelWeekNumbers;
    // End of variables declaration//GEN-END:variables
}
