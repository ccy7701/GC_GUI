import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MainMenuGUI extends JPanel {
    public static JLabel lblProgramStatus;

    public static JTable charactersTable;

    public static JScrollPane scrollPane;

    public static ArrayList<GenshinChar> charactersArrayList = new ArrayList<>();

    public static JLabel chosenCharacterImg;

    public static JLabel chosenCharacterLabel;

    public static JLabel chosenCharWeapon;

    public static JLabel chosenCharMaxHP;

    public static JLabel chosenCharAtk;

    public static JLabel chosenCharDef;

    public static JLabel chosenCharEM;

    public static JLabel chosenCharCR;

    public static JLabel chosenCharCD;

    public static JLabel chosenCharER;

    public static JLabel chosenCharElemDmg;

    public static JLabel chosenCharNA;

    public static JLabel chosenCharES;

    public static JLabel chosenCharEB;

    private JLabel lblToolTip;

    public MainMenuGUI() {
        buildGUI();
    }

    public void buildGUI() {
        String[] tblCharactersTopColumn = {
                "CHARACTER NAME", "WEAPON", "WEAPON TYPE", "MAX HP", "ATK", "DEF", "EM", "CR", "CD", "ER",
                "ELEMDMG", "NA LVL", "ES LVL", "EB LVL" };
        final DefaultTableModel model = new DefaultTableModel((Object[])tblCharactersTopColumn, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        charactersTable = new JTable();
        charactersTable.setModel(model);
        charactersTable.getTableHeader().setReorderingAllowed(false);
        JMenu fileMenu = new JMenu("File");
        final JMenuItem importItem = new JMenuItem("Import");
        fileMenu.add(importItem);
        final JMenuItem exportItem = new JMenuItem("Export");
        fileMenu.add(exportItem);
        final JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);
        JMenu helpMenu = new JMenu("Help");
        final JMenuItem aboutItem = new JMenuItem("About...");
        helpMenu.add(aboutItem);
        final JButton btnAddCharacter = new JButton("Add Character");
        JButton btnEditList = new JButton("Edit Character");
        JButton btnDeleteCharacter = new JButton("Delete Character");
        JButton btnClearList = new JButton("Clear List");
        JButton btnComparator = new JButton("Open comparator");
        JLabel lblMenuTitle = new JLabel("Genshin Comparator GUI v1.0.2");
        this.lblToolTip = new JLabel("   ");
        chosenCharacterImg = new JLabel(new ImageIcon("resources/noCharSelected.png"));
        chosenCharacterLabel = new JLabel("Select a character ");
        chosenCharWeapon = new JLabel("Weapon: ");
        chosenCharMaxHP = new JLabel("Max HP: ");
        chosenCharAtk = new JLabel("ATK: ");
        chosenCharDef = new JLabel("DEF: ");
        chosenCharEM = new JLabel("EM: ");
        chosenCharCR = new JLabel("CR: ");
        chosenCharCD = new JLabel("CD: ");
        chosenCharER = new JLabel("ER: ");
        chosenCharElemDmg = new JLabel("Elemental DMG+: ");
        chosenCharNA = new JLabel("Normal ATK LVL: ");
        chosenCharES = new JLabel("E LVL:");
        chosenCharEB = new JLabel("Q LVL:");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        scrollPane = new JScrollPane(charactersTable);
        lblProgramStatus = new JLabel("...");
        setPreferredSize(new Dimension(1400, 580));
        setLayout((LayoutManager)null);
        add(btnAddCharacter);
        add(btnEditList);
        add(btnDeleteCharacter);
        add(btnClearList);
        add(btnComparator);
        add(lblMenuTitle);
        add(this.lblToolTip);
        add(chosenCharacterImg);
        add(chosenCharacterLabel);
        add(chosenCharWeapon);
        add(chosenCharMaxHP);
        add(chosenCharAtk);
        add(chosenCharDef);
        add(chosenCharEM);
        add(chosenCharCR);
        add(chosenCharCD);
        add(chosenCharER);
        add(chosenCharElemDmg);
        add(chosenCharNA);
        add(chosenCharES);
        add(chosenCharEB);
        add(menuBar);
        add(scrollPane);
        add(lblProgramStatus);
        btnAddCharacter.setBounds(30, 60, 170, 45);
        btnEditList.setBounds(30, 120, 170, 45);
        btnDeleteCharacter.setBounds(30, 180, 170, 45);
        btnClearList.setBounds(30, 240, 170, 45);
        btnComparator.setBounds(900, 150, 170, 45);
        lblMenuTitle.setBounds(580, 25, 240, 30);
        this.lblToolTip.setBounds(230, 50, 180, 30);
        chosenCharacterImg.setBounds(405, 90, 165, 165);
        chosenCharacterLabel.setBounds(595, 60, 255, 25);
        chosenCharWeapon.setBounds(595, 80, 250, 25);
        chosenCharMaxHP.setBounds(595, 100, 250, 25);
        chosenCharAtk.setBounds(595, 120, 130, 25);
        chosenCharDef.setBounds(730, 120, 130, 25);
        chosenCharEM.setBounds(595, 140, 250, 25);
        chosenCharCR.setBounds(595, 160, 130, 25);
        chosenCharCD.setBounds(730, 160, 100, 25);
        chosenCharER.setBounds(595, 180, 100, 25);
        chosenCharElemDmg.setBounds(595, 200, 250, 25);
        chosenCharNA.setBounds(595, 220, 130, 25);
        chosenCharES.setBounds(595, 240, 130, 25);
        chosenCharEB.setBounds(595, 260, 130, 25);
        menuBar.setBounds(0, 0, 1600, 25);
        scrollPane.setBounds(30, 305, 1340, 245);
        lblProgramStatus.setBounds(5, 555, 700, 25);
        importItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtImportItem) {
                if (evtImportItem.getSource() == importItem) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Import Data");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", new String[] { "txt" }));
                    int result = fileChooser.showOpenDialog(null);
                    if (result == 0) {
                        File selectedFile = fileChooser.getSelectedFile();
                        MainMenuGUI.this.readFile(selectedFile, model);
                    }
                }
            }
        });
        exportItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtExportItem) {
                if (evtExportItem.getSource() == exportItem) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Export Data");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", new String[] { "txt" }));
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showSaveDialog(MainMenuGUI.this);
                    if (result == 0) {
                        File selectedFile = fileChooser.getSelectedFile();
                        if (selectedFile.exists()) {
                            int confirmResult = JOptionPane.showConfirmDialog(MainMenuGUI.this, "File already exists. Overwrite file?", "Confirm Overwrite", 0);
                            if (confirmResult == 0)
                                MainMenuGUI.this.exportToFile(selectedFile);
                        } else {
                            String selectedFileName = selectedFile.getName();
                            if (!selectedFileName.toLowerCase().endsWith(".txt")) {
                                selectedFileName = selectedFileName + ".txt";
                                selectedFile = new File(selectedFile.getParent(), selectedFileName);
                            }
                            MainMenuGUI.this.exportToFile(selectedFile);
                        }
                    }
                }
            }
        });
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtExitItem) {
                if (evtExitItem.getSource() == exitItem) {
                    System.out.println("Closing program");
                    System.exit(0);
                }
            }
        });
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtAboutItem) {
                if (evtAboutItem.getSource() == aboutItem)
                    JOptionPane.showMessageDialog(null, "Genshin Comparator GUI\n" +
                            "Version 1.0.2 (13/11/2023)\n" +
                            "Mini project started on 24/2/2023\n" +
                            "Working build completed on 7/3/2023\n" +
                            "Created by Chiew Cheng Yi\n" +
                            "In this version: Revived old code from v1.0.2", "About", -1);
            }
        });
        btnAddCharacter.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evtHover) {
                MainMenuGUI.this.lblToolTip.setText("Add a character to the list.");
            }

            public void mouseExited(MouseEvent evtLeaveHover) {
                MainMenuGUI.this.lblToolTip.setText("   ");
            }
        });
        btnAddCharacter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtClickToAdd) {
                if (evtClickToAdd.getSource() == btnAddCharacter) {
                    AddCharacterGUI newAddGUI = new AddCharacterGUI(MainMenuGUI.charactersTable);
                    newAddGUI.setModal(true);
                    newAddGUI.setDialogVisible(true);
                }
            }
        });
        btnEditList.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evtHover) {
                MainMenuGUI.this.lblToolTip.setText("<html>Edit a character's stats.</html>");
            }

            public void mouseExited(MouseEvent evtLeaveHover) {
                MainMenuGUI.this.lblToolTip.setText("   ");
            }
        });
        btnEditList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtEditList) {
                try {
                    int row = MainMenuGUI.charactersTable.getSelectedRow();
                    if (row != -1) {
                        EditCharacterGUI newEditGUI = new EditCharacterGUI(MainMenuGUI.charactersArrayList, MainMenuGUI.charactersTable, row);
                        newEditGUI.setModal(true);
                        newEditGUI.setDialogVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a character to edit.", "Error", 0);
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "ERROR WHEN OPENING EDIT DIALOG BOX: " + String.valueOf(exc), "Error", 0);
                }
            }
        });
        btnDeleteCharacter.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evtHover) {
                MainMenuGUI.this.lblToolTip.setText("Delete a character from the list.");
            }

            public void mouseExited(MouseEvent evtLeaveHover) {
                MainMenuGUI.this.lblToolTip.setText("   ");
            }
        });
        btnDeleteCharacter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtDeleteCharacter) {
                try {
                    if (MainMenuGUI.charactersTable.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "Table is empty.", "Error", 0);
                        return;
                    }
                    int row = MainMenuGUI.charactersTable.getSelectedRow();
                    if (row != -1) {
                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete Confirmation", 0);
                        if (confirm == 0) {
                            MainMenuGUI.lblProgramStatus.setText("Character deleted");
                            MainMenuGUI.chosenCharacterLabel.setText("Select a character");
                            MainMenuGUI.chosenCharacterImg.setIcon(new ImageIcon("resources/noCharSelected.png"));
                            MainMenuGUI.chosenCharWeapon.setText("Weapon: ");
                            MainMenuGUI.chosenCharMaxHP.setText("Max HP: ");
                            MainMenuGUI.chosenCharAtk.setText("ATK: ");
                            MainMenuGUI.chosenCharDef.setText("DEF: ");
                            MainMenuGUI.chosenCharEM.setText("EM: ");
                            MainMenuGUI.chosenCharCR.setText("CR: ");
                            MainMenuGUI.chosenCharCD.setText("CD: ");
                            MainMenuGUI.chosenCharER.setText("ER: ");
                            MainMenuGUI.chosenCharElemDmg.setText("Elemental DMG+: ");
                            MainMenuGUI.chosenCharNA.setText("Normal ATK LVL: ");
                            MainMenuGUI.chosenCharES.setText("E LVL: ");
                            MainMenuGUI.chosenCharEB.setText("Q LVL: ");
                            MainMenuGUI.charactersArrayList.remove(row);
                            ((DefaultTableModel)MainMenuGUI.charactersTable.getModel()).removeRow(row);
                            JOptionPane.showMessageDialog(null, "Character deleted.", "Delete Confirmation", 1);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", 0);
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "ERROR WHEN EXECUTING DELETE: " + String.valueOf(exc), "Error", 0);
                }
            }
        });
        btnClearList.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evtHover) {
                MainMenuGUI.this.lblToolTip.setText("Clear the list.");
            }

            public void mouseExited(MouseEvent evtLeaveHover) {
                MainMenuGUI.this.lblToolTip.setText("   ");
            }
        });
        btnClearList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtClearList) {
                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "List is empty.", "Error", 0);
                } else {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Clear list", 0);
                    if (choice == 0) {
                        MainMenuGUI.charactersArrayList.clear();
                        model.setRowCount(0);
                        MainMenuGUI.chosenCharacterLabel.setText("Select a character");
                        MainMenuGUI.chosenCharacterImg.setIcon(new ImageIcon("resources/noCharSelected.png"));
                        MainMenuGUI.chosenCharWeapon.setText("Weapon: ");
                        MainMenuGUI.chosenCharMaxHP.setText("Max HP: ");
                        MainMenuGUI.chosenCharAtk.setText("ATK: ");
                        MainMenuGUI.chosenCharDef.setText("DEF: ");
                        MainMenuGUI.chosenCharEM.setText("EM: ");
                        MainMenuGUI.chosenCharCR.setText("CR: ");
                        MainMenuGUI.chosenCharCD.setText("CD: ");
                        MainMenuGUI.chosenCharER.setText("ER: ");
                        MainMenuGUI.chosenCharElemDmg.setText("Elemental DMG+: ");
                        MainMenuGUI.chosenCharNA.setText("Normal ATK LVL: ");
                        MainMenuGUI.chosenCharES.setText("E LVL: ");
                        MainMenuGUI.chosenCharEB.setText("Q LVL: ");
                        MainMenuGUI.lblProgramStatus.setText("List cleared");
                        System.out.println("List cleared");
                    }
                }
            }
        });
        btnComparator.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evtHover) {
                MainMenuGUI.this.lblToolTip.setText("Open the comparator menu.");
            }

            public void mouseExited(MouseEvent evtLeaveHover) {
                MainMenuGUI.this.lblToolTip.setText("   ");
            }
        });
        btnComparator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtOpenComparator) {
                try {
                    boolean isEmpty = false;
                    if (MainMenuGUI.charactersArrayList.size() == 0 && MainMenuGUI.charactersTable.getRowCount() == 0) {
                        isEmpty = true;
                        JOptionPane.showMessageDialog(null, "Characters list is currently empty. Cannot open comparator.", "Error", 0);
                    }
                    if (!isEmpty) {
                        ComparatorGUI newComparatorGUI = new ComparatorGUI(MainMenuGUI.charactersArrayList);
                        newComparatorGUI.setModal(true);
                        newComparatorGUI.setDialogVisible(true);
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "ERROR WHEN OPENING COMPARATOR" + String.valueOf(exc), "Error", 0);
                }
            }
        });
        charactersTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evtSelectList) {
                if (!evtSelectList.getValueIsAdjusting()) {
                    int row = MainMenuGUI.charactersTable.getSelectedRow();
                    if (row != -1) {
                        GenshinChar tempChar = new GenshinChar();
                        String tempCharName = (String)MainMenuGUI.charactersTable.getValueAt(row, 0);
                        try {
                            for (GenshinChar x : MainMenuGUI.charactersArrayList) {
                                if (tempCharName.equals(x.getCharacterName())) {
                                    tempChar = x;
                                    break;
                                }
                            }
                            MainMenuGUI.chosenCharacterLabel.setText("Character chosen: " + tempChar.getCharacterName());
                            MainMenuGUI.chosenCharWeapon.setText("Weapon: " + tempChar.getWeapon());
                            MainMenuGUI.chosenCharMaxHP.setText("Max HP: " + tempChar.getMaxHP());
                            MainMenuGUI.chosenCharAtk.setText("ATK: " + tempChar.getAtk());
                            MainMenuGUI.chosenCharDef.setText("DEF: " + tempChar.getDef());
                            MainMenuGUI.chosenCharEM.setText("EM: " + tempChar.getElemMastery());
                            MainMenuGUI.chosenCharCR.setText("CR: " + tempChar.getCritRate() + "%");
                            MainMenuGUI.chosenCharCD.setText("CD: " + tempChar.getCritDmg() + "%");
                            MainMenuGUI.chosenCharER.setText("ER: " + tempChar.getErgRecharge() + "%");
                            MainMenuGUI.chosenCharElemDmg.setText("Elemental DMG+: " + tempChar.getElemDmg() + "%");
                            MainMenuGUI.chosenCharNA.setText("Normal ATK LVL: " + tempChar.getNA_lvl());
                            MainMenuGUI.chosenCharES.setText("E LVL: " + tempChar.getES_lvl());
                            MainMenuGUI.chosenCharEB.setText("Q LVL: " + tempChar.getEB_lvl());
                            String tempImageName = "resources/" + tempChar.getImageFileName();
                            JLabel tempChosenImg = new JLabel(new ImageIcon(tempImageName));
                            MainMenuGUI.chosenCharacterImg.setIcon(tempChosenImg.getIcon());
                        } catch (Exception exc) {
                            JOptionPane.showMessageDialog(null, "ERROR: " + String.valueOf(exc), "Error", 0);
                        }
                    }
                }
            }
        });
    }

    public void buildTable(DefaultTableModel model) {
        if (model.getRowCount() != 0)
            model.setRowCount(0);
        for (GenshinChar x : charactersArrayList) {
            model.addRow(new Object[] {
                    x
                            .getCharacterName(), x
                    .getWeapon(), x
                    .getWeaponType(),

                    Double.toString(x.getMaxHP()),
                    Double.toString(x.getAtk()),
                    Double.toString(x.getDef()),
                    Double.toString(x.getElemMastery()),
                    Double.toString(x.getCritRate()),
                    Double.toString(x.getCritDmg()),
                    Double.toString(x.getErgRecharge()),
                    Double.toString(x.getElemDmg()),
                    Double.toString(x.getNA_lvl()),
                    Double.toString(x.getES_lvl()),
                    Double.toString(x.getEB_lvl()) });
        }
    }

    public void readFile(File selectedFile, DefaultTableModel model) {
        if (charactersArrayList.size() != 0) {
            charactersArrayList.clear();
            System.out.println("ArrayList overwritten, will be populated by new data");
        }
        try {
            Scanner reader = new Scanner(selectedFile);
            while (reader.hasNextLine()) {
                GenshinChar newChar = new GenshinChar();
                String crn = reader.next();
                crn = crn.replace("_", " ");
                String wpn = reader.next();
                wpn = wpn.replace("_", " ");
                String wpt = reader.next();
                wpt = wpt.replace("_", " ");
                String imgsrc = reader.next();
                double mhp = reader.nextDouble();
                double atk = reader.nextDouble();
                double def = reader.nextDouble();
                double elm = reader.nextDouble();
                double crt = reader.nextDouble();
                double cdm = reader.nextDouble();
                double enr = reader.nextDouble();
                double edm = reader.nextDouble();
                double NA_lvl = reader.nextDouble();
                double ES_lvl = reader.nextDouble();
                double EB_lvl = reader.nextDouble();
                newChar.setCharName(crn);
                newChar.setWeapon(wpn);
                newChar.setWeaponType(wpt);
                newChar.setImageFileName(imgsrc);
                newChar.setMaxHP(mhp);
                newChar.setAtk(atk);
                newChar.setDef(def);
                newChar.setElemMastery(elm);
                newChar.setCritRate(crt);
                newChar.setCritDmg(cdm);
                newChar.setErgRecharge(enr);
                newChar.setElemDmg(edm);
                newChar.setNA_lvl(NA_lvl);
                newChar.setES_lvl(ES_lvl);
                newChar.setEB_lvl(EB_lvl);
                charactersArrayList.add(newChar);
            }
            lblProgramStatus.setText("File imported successfully. Table with " + charactersArrayList.size() + " characters created");
            buildTable(model);
        } catch (Exception exc) {
            lblProgramStatus.setText("FAILED TO LOAD FILE: " + String.valueOf(exc) + ". Try again");
        }
    }

    public void exportToFile(File selectedFile) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
            for (int i = 0; i < charactersArrayList.size(); i++) {
                GenshinChar tempChar = charactersArrayList.get(i);
                String tempCharName = tempChar.getCharacterName();
                String tempWeapon = tempChar.getWeapon();
                tempCharName = tempCharName.replace(" ", "_");
                tempWeapon = tempWeapon.replace(" ", "_");
                bw.write(tempCharName + " " + tempCharName + " " + tempWeapon + " " + tempChar.getWeaponType());
                bw.newLine();
                bw.write("" + tempChar.getMaxHP() + " " + tempChar.getMaxHP() + " " + tempChar.getAtk() + " " + tempChar.getDef() + " " + tempChar.getElemMastery() + " " + tempChar.getCritRate() + " " + tempChar.getCritDmg() + " " + tempChar.getErgRecharge());
                bw.newLine();
                bw.write("" + tempChar.getNA_lvl() + " " + tempChar.getNA_lvl() + " " + tempChar.getES_lvl());
                if (i != charactersArrayList.size() - 1)
                    bw.newLine();
            }
            bw.flush();
            bw.close();
            lblProgramStatus.setText("Data exported successfully");
            System.out.println("Data exported successfully");
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
