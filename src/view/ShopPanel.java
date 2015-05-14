/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTextArea;
import model.IngredientAmount;
import model.Week;
import model.Weekday;

/**
 *
 * @author Morten
 */
public class ShopPanel extends javax.swing.JPanel {

    private Week week;
    private String fullIngredientList;

    /**
     * Creates new form ShopPanel
     */
    public ShopPanel(GUI gui) {
	this.week = gui.getSelectedWeekPanel().getWeek();
	initComponents();
	jLabelHeadline.setText("Indkøbsliste for uge " + week.getDate());
	fillIngredientListDays();
	fullIngredientList();
	jPanelDays.remove(jPanelMan);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTop = new javax.swing.JPanel();
        jLabelHeadline = new javax.swing.JLabel();
        jPanelDays = new javax.swing.JPanel();
        jPanelFre = new javax.swing.JPanel();
        jLabelFre = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaFre = new javax.swing.JTextArea();
        jPanelTir = new javax.swing.JPanel();
        jLabelTir = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaTir = new javax.swing.JTextArea();
        jPanelSon = new javax.swing.JPanel();
        jLabelSon = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaSon = new javax.swing.JTextArea();
        jPanelTor = new javax.swing.JPanel();
        jLabelTor = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaTor = new javax.swing.JTextArea();
        jPanelLor = new javax.swing.JPanel();
        jLabelLor = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaLor = new javax.swing.JTextArea();
        jPanelMan = new javax.swing.JPanel();
        jLabelMan = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMan = new javax.swing.JTextArea();
        jPanelOns = new javax.swing.JPanel();
        jLabelOns = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaOns = new javax.swing.JTextArea();
        jPanelFull = new javax.swing.JPanel();
        jLabelHeadline1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaFull = new javax.swing.JTextArea();

        setBackground(GUI.buttonHoverColor);
        setForeground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N

        jPanelTop.setBackground(GUI.buttonHoverColor);

        jLabelHeadline.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        jLabelHeadline.setForeground(new java.awt.Color(255, 255, 255));

        jPanelDays.setBackground(GUI.buttonHoverColor);

        jPanelFre.setBackground(GUI.dayColor);

        jLabelFre.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelFre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFre.setText("Fredag");

        jScrollPane6.setBackground(GUI.dayColor);
        jScrollPane6.setBorder(null);

        jTextAreaFre.setEditable(false);
        jTextAreaFre.setBackground(GUI.dayColor);
        jTextAreaFre.setColumns(20);
        jTextAreaFre.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextAreaFre.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaFre.setLineWrap(true);
        jTextAreaFre.setRows(5);
        jTextAreaFre.setWrapStyleWord(true);
        jTextAreaFre.setBorder(null);
        jTextAreaFre.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextAreaFre.setEnabled(false);
        jScrollPane6.setViewportView(jTextAreaFre);

        javax.swing.GroupLayout jPanelFreLayout = new javax.swing.GroupLayout(jPanelFre);
        jPanelFre.setLayout(jPanelFreLayout);
        jPanelFreLayout.setHorizontalGroup(
            jPanelFreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFreLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelFre, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelFreLayout.setVerticalGroup(
            jPanelFreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFreLayout.createSequentialGroup()
                .addComponent(jLabelFre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6))
        );

        jPanelTir.setBackground(GUI.dayColor);

        jLabelTir.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelTir.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTir.setText("Tirsdag");

        jScrollPane3.setBackground(GUI.dayColor);
        jScrollPane3.setBorder(null);

        jTextAreaTir.setEditable(false);
        jTextAreaTir.setBackground(GUI.dayColor);
        jTextAreaTir.setColumns(20);
        jTextAreaTir.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextAreaTir.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaTir.setLineWrap(true);
        jTextAreaTir.setRows(5);
        jTextAreaTir.setWrapStyleWord(true);
        jTextAreaTir.setBorder(null);
        jTextAreaTir.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextAreaTir.setEnabled(false);
        jScrollPane3.setViewportView(jTextAreaTir);

        javax.swing.GroupLayout jPanelTirLayout = new javax.swing.GroupLayout(jPanelTir);
        jPanelTir.setLayout(jPanelTirLayout);
        jPanelTirLayout.setHorizontalGroup(
            jPanelTirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTirLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelTir, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelTirLayout.setVerticalGroup(
            jPanelTirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTirLayout.createSequentialGroup()
                .addComponent(jLabelTir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3))
        );

        jPanelSon.setBackground(GUI.dayColor);

        jLabelSon.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelSon.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSon.setText("Søndag");

        jScrollPane8.setBackground(GUI.dayColor);
        jScrollPane8.setBorder(null);

        jTextAreaSon.setEditable(false);
        jTextAreaSon.setBackground(GUI.dayColor);
        jTextAreaSon.setColumns(20);
        jTextAreaSon.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextAreaSon.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaSon.setLineWrap(true);
        jTextAreaSon.setRows(5);
        jTextAreaSon.setWrapStyleWord(true);
        jTextAreaSon.setBorder(null);
        jTextAreaSon.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextAreaSon.setEnabled(false);
        jScrollPane8.setViewportView(jTextAreaSon);

        javax.swing.GroupLayout jPanelSonLayout = new javax.swing.GroupLayout(jPanelSon);
        jPanelSon.setLayout(jPanelSonLayout);
        jPanelSonLayout.setHorizontalGroup(
            jPanelSonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelSon, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelSonLayout.setVerticalGroup(
            jPanelSonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSonLayout.createSequentialGroup()
                .addComponent(jLabelSon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8))
        );

        jPanelTor.setBackground(GUI.dayColor);

        jLabelTor.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelTor.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTor.setText("Torsdag");

        jScrollPane5.setBackground(GUI.dayColor);
        jScrollPane5.setBorder(null);

        jTextAreaTor.setEditable(false);
        jTextAreaTor.setBackground(GUI.dayColor);
        jTextAreaTor.setColumns(20);
        jTextAreaTor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextAreaTor.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaTor.setLineWrap(true);
        jTextAreaTor.setRows(5);
        jTextAreaTor.setWrapStyleWord(true);
        jTextAreaTor.setBorder(null);
        jTextAreaTor.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextAreaTor.setEnabled(false);
        jScrollPane5.setViewportView(jTextAreaTor);

        javax.swing.GroupLayout jPanelTorLayout = new javax.swing.GroupLayout(jPanelTor);
        jPanelTor.setLayout(jPanelTorLayout);
        jPanelTorLayout.setHorizontalGroup(
            jPanelTorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelTor, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelTorLayout.setVerticalGroup(
            jPanelTorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTorLayout.createSequentialGroup()
                .addComponent(jLabelTor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5))
        );

        jPanelLor.setBackground(GUI.dayColor);

        jLabelLor.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelLor.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLor.setText("Lørdag");

        jScrollPane7.setBackground(GUI.dayColor);
        jScrollPane7.setBorder(null);

        jTextAreaLor.setEditable(false);
        jTextAreaLor.setBackground(GUI.dayColor);
        jTextAreaLor.setColumns(20);
        jTextAreaLor.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextAreaLor.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaLor.setLineWrap(true);
        jTextAreaLor.setRows(5);
        jTextAreaLor.setWrapStyleWord(true);
        jTextAreaLor.setBorder(null);
        jTextAreaLor.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextAreaLor.setEnabled(false);
        jScrollPane7.setViewportView(jTextAreaLor);

        javax.swing.GroupLayout jPanelLorLayout = new javax.swing.GroupLayout(jPanelLor);
        jPanelLor.setLayout(jPanelLorLayout);
        jPanelLorLayout.setHorizontalGroup(
            jPanelLorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelLor, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelLorLayout.setVerticalGroup(
            jPanelLorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLorLayout.createSequentialGroup()
                .addComponent(jLabelLor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
        );

        jPanelMan.setBackground(GUI.dayColor);

        jLabelMan.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelMan.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMan.setText("Mandag");

        jScrollPane2.setBackground(GUI.dayColor);
        jScrollPane2.setBorder(null);

        jTextAreaMan.setEditable(false);
        jTextAreaMan.setBackground(GUI.dayColor);
        jTextAreaMan.setColumns(20);
        jTextAreaMan.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextAreaMan.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaMan.setLineWrap(true);
        jTextAreaMan.setRows(5);
        jTextAreaMan.setWrapStyleWord(true);
        jTextAreaMan.setBorder(null);
        jTextAreaMan.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextAreaMan.setEnabled(false);
        jScrollPane2.setViewportView(jTextAreaMan);

        javax.swing.GroupLayout jPanelManLayout = new javax.swing.GroupLayout(jPanelMan);
        jPanelMan.setLayout(jPanelManLayout);
        jPanelManLayout.setHorizontalGroup(
            jPanelManLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelManLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelMan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanelManLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanelManLayout.setVerticalGroup(
            jPanelManLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelManLayout.createSequentialGroup()
                .addComponent(jLabelMan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2))
        );

        jPanelOns.setBackground(GUI.dayColor);

        jLabelOns.setFont(new java.awt.Font("Verdana", 1, 11)); // NOI18N
        jLabelOns.setForeground(new java.awt.Color(255, 255, 255));
        jLabelOns.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOns.setText("Onsdag");

        jScrollPane4.setBackground(GUI.dayColor);
        jScrollPane4.setBorder(null);

        jTextAreaOns.setEditable(false);
        jTextAreaOns.setBackground(GUI.dayColor);
        jTextAreaOns.setColumns(20);
        jTextAreaOns.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jTextAreaOns.setForeground(new java.awt.Color(255, 255, 255));
        jTextAreaOns.setLineWrap(true);
        jTextAreaOns.setRows(5);
        jTextAreaOns.setWrapStyleWord(true);
        jTextAreaOns.setBorder(null);
        jTextAreaOns.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextAreaOns.setEnabled(false);
        jScrollPane4.setViewportView(jTextAreaOns);

        javax.swing.GroupLayout jPanelOnsLayout = new javax.swing.GroupLayout(jPanelOns);
        jPanelOns.setLayout(jPanelOnsLayout);
        jPanelOnsLayout.setHorizontalGroup(
            jPanelOnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOnsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelOns, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanelOnsLayout.setVerticalGroup(
            jPanelOnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelOnsLayout.createSequentialGroup()
                .addComponent(jLabelOns)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4))
        );

        javax.swing.GroupLayout jPanelDaysLayout = new javax.swing.GroupLayout(jPanelDays);
        jPanelDays.setLayout(jPanelDaysLayout);
        jPanelDaysLayout.setHorizontalGroup(
            jPanelDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDaysLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanelMan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanelTir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanelOns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanelTor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanelFre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanelLor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanelSon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanelDaysLayout.setVerticalGroup(
            jPanelDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDaysLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanelDaysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelLor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelOns, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelFre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanelTopLayout = new javax.swing.GroupLayout(jPanelTop);
        jPanelTop.setLayout(jPanelTopLayout);
        jPanelTopLayout.setHorizontalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelHeadline, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addComponent(jPanelDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelTopLayout.setVerticalGroup(
            jPanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTopLayout.createSequentialGroup()
                .addComponent(jLabelHeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelFull.setBackground(GUI.buttonHoverColor);

        jLabelHeadline1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabelHeadline1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHeadline1.setText("Fuld indkøbsliste:");

        jScrollPane1.setBorder(null);

        jTextAreaFull.setEditable(false);
        jTextAreaFull.setBackground(GUI.dayColor);
        jTextAreaFull.setColumns(20);
        jTextAreaFull.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTextAreaFull.setLineWrap(true);
        jTextAreaFull.setRows(5);
        jTextAreaFull.setWrapStyleWord(true);
        jTextAreaFull.setBorder(null);
        jTextAreaFull.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTextAreaFull.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaFull);

        javax.swing.GroupLayout jPanelFullLayout = new javax.swing.GroupLayout(jPanelFull);
        jPanelFull.setLayout(jPanelFullLayout);
        jPanelFullLayout.setHorizontalGroup(
            jPanelFullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelHeadline1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelFullLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanelFullLayout.setVerticalGroup(
            jPanelFullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFullLayout.createSequentialGroup()
                .addComponent(jLabelHeadline1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFull, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelFull, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelFre;
    private javax.swing.JLabel jLabelHeadline;
    private javax.swing.JLabel jLabelHeadline1;
    private javax.swing.JLabel jLabelLor;
    private javax.swing.JLabel jLabelMan;
    private javax.swing.JLabel jLabelOns;
    private javax.swing.JLabel jLabelSon;
    private javax.swing.JLabel jLabelTir;
    private javax.swing.JLabel jLabelTor;
    private javax.swing.JPanel jPanelDays;
    private javax.swing.JPanel jPanelFre;
    private javax.swing.JPanel jPanelFull;
    private javax.swing.JPanel jPanelLor;
    private javax.swing.JPanel jPanelMan;
    private javax.swing.JPanel jPanelOns;
    private javax.swing.JPanel jPanelSon;
    private javax.swing.JPanel jPanelTir;
    private javax.swing.JPanel jPanelTop;
    private javax.swing.JPanel jPanelTor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextArea jTextAreaFre;
    private javax.swing.JTextArea jTextAreaFull;
    private javax.swing.JTextArea jTextAreaLor;
    private javax.swing.JTextArea jTextAreaMan;
    private javax.swing.JTextArea jTextAreaOns;
    private javax.swing.JTextArea jTextAreaSon;
    private javax.swing.JTextArea jTextAreaTir;
    private javax.swing.JTextArea jTextAreaTor;
    // End of variables declaration//GEN-END:variables

    private void fillIngredientListDays() {
	JTextArea day;
	Component parent;
	Weekday weekday;
	for (int i = 0; i < 7; i++) {
	    switch(i){
		case 0:
		    day = jTextAreaMan;
		    parent = jPanelMan;
		    break;
		case 1:
		    day = jTextAreaTir;
		    parent = jPanelTir;
		    break;
		case 2:
		    day = jTextAreaOns;
		    parent = jPanelOns;
		    break;
		case 3:
		    day = jTextAreaTor;
		    parent = jPanelTor;
		    break;
		case 4:
		    day = jTextAreaFre;
		    parent = jPanelFre;
		    break;
		case 5:
		    day = jTextAreaLor;
		    parent = jPanelLor;
		    break;
		default:
		    day = jTextAreaSon;
		    parent = jPanelSon;
		    break;
	    }
	    weekday = week.getWeekdays()[i];
	    if (weekday.isOmit() || !weekday.getRecipe().isActive()) {
		day.setBackground(GUI.buttonHoverColor);
		parent.setBackground(GUI.buttonHoverColor);
		day.setText("Dag undladt");
	    } else {
		day.append(weekday.getRecipe().getName() + "\n\n");
		for (IngredientAmount ingAm : weekday.getRecipe().getIngredientList()) {
		    day.append(ingAm.toString() + "\n");
		}
	    }
	}
    }

    private void fullIngredientList() {
	boolean firstElementPushed = false;
	fullIngredientList = "";
	ArrayList<IngredientAmount> tempShopList = new ArrayList<>();
	for (Weekday wd : week.getWeekdays()) {
	    if (!wd.isOmit() && wd.getRecipe().isActive()) {
		for (IngredientAmount ingAm : wd.getRecipe().getIngredientList()) {
		    IngredientAmount tempIng = new IngredientAmount(ingAm.getUnit(), ingAm.getIngredient(), ingAm.getAmount());
		    Iterator<IngredientAmount> iter = tempShopList.iterator();
		    boolean push = true;
		    while (iter.hasNext() && firstElementPushed) {
			IngredientAmount ing = iter.next();
			if (ing.getIngredient() == tempIng.getIngredient()) {
			    for (int i = 0; i < tempShopList.size(); i++) {
				if (tempShopList.get(i).getIngredient() == ing.getIngredient()) {
				    tempShopList.get(i).setAmount(ing.getAmount() + tempIng.getAmount());
				    push = false;
				}
			    }
			}
		    }
		    if (push) {
			tempShopList.add(tempIng);
			firstElementPushed = true;
		    }
		}
	    }
	}
	for (int i = 0; i < tempShopList.size(); i++) {
	    fullIngredientList += tempShopList.get(i).getAmountString() + tempShopList.get(i).getUnit().getShortname() + " " + tempShopList.get(i).getIngredient().getName();
	    if (i < tempShopList.size() - 1) {
		fullIngredientList += ", ";
	    }
	}
	jTextAreaFull.setText(fullIngredientList);
    }
}
