import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ComparatorGUI extends JPanel {
    private JDialog comparatorDialog;

    private JLabel lblChosenCharacter;

    private JLabel lblChosenCharacterImage;

    private JLabel lblImported;

    private JTable tblComparatorResults;

    private JList<String> listOfCharacters;

    private ArrayList<GenshinChar> listToCompareWith;

    public ComparatorGUI(ArrayList<GenshinChar> charactersArrayList) {
        buildGUI(charactersArrayList);
    }

    public void buildGUI(final ArrayList<GenshinChar> charactersArrayList) {
        this.listToCompareWith = new ArrayList<>();
        this.comparatorDialog = new JDialog();
        this.comparatorDialog.setTitle("Comparator");
        this.comparatorDialog.add(this);
        this.comparatorDialog.setSize(new Dimension(640, 520));
        this.comparatorDialog.setLayout((LayoutManager)null);
        this.comparatorDialog.setResizable(false);
        this.comparatorDialog.setDefaultCloseOperation(2);
        JLabel lblComparatorHeader = new JLabel("Comparator: Compare stats with another list");
        this.lblChosenCharacterImage = new JLabel(new ImageIcon("resources/noCharSelected.png"));
        JScrollPane listOfCharactersScrollPane = new JScrollPane(buildListOfCharacters(charactersArrayList));
        this.lblImported = new JLabel("Imported file name: ");
        final JButton btnImportCompList = new JButton("Import Comparison List");
        DefaultTableModel initialTableModel = new DefaultTableModel(new Object[][] {
                { "Weapon", "", "" }, { "Weapon Type", "", "" }, { "Max HP", "", "" }, { "ATK", "", "" }, { "DEF", "", "" }, { "EM", "", "" }, { "CR", "", "" }, { "CD", "", "" }, { "ER", "", "" }, { "ELEMDMG", "", "" },
                { "NA LVL", "", "" }, { "ES LVL", "", "" }, { "EB LVL", "", "" },  }, new Object[] { "ATTRIBUTES", "Your char.", "vs. Imported char." }) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.tblComparatorResults = new JTable(initialTableModel);
        JScrollPane comparisonsScrollPane = new JScrollPane(this.tblComparatorResults);
        this.lblChosenCharacter = new JLabel("Chosen character: ");
        final JButton btnExportResults = new JButton("Export Results");
        this.comparatorDialog.add(lblComparatorHeader);
        this.comparatorDialog.add(this.lblChosenCharacterImage);
        this.comparatorDialog.add(listOfCharactersScrollPane);
        this.comparatorDialog.add(this.lblImported);
        this.comparatorDialog.add(btnImportCompList);
        this.comparatorDialog.add(comparisonsScrollPane);
        this.comparatorDialog.add(this.lblChosenCharacter);
        this.comparatorDialog.add(btnExportResults);
        lblComparatorHeader.setBounds(185, 10, 255, 20);
        this.lblChosenCharacterImage.setBounds(330, 45, 165, 165);
        listOfCharactersScrollPane.setBounds(15, 180, 190, 230);
        this.lblImported.setBounds(25, 110, 170, 50);
        btnImportCompList.setBounds(25, 65, 170, 45);
        comparisonsScrollPane.setBounds(220, 250, 385, 160);
        this.lblChosenCharacter.setBounds(220, 220, 385, 20);
        btnExportResults.setBounds(225, 420, 170, 45);
        btnImportCompList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtImportCompList) {
                if (evtImportCompList.getSource() == btnImportCompList) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Import list to compare against");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", new String[] { "txt" }));
                    int result = fileChooser.showOpenDialog(null);
                    if (result == 0) {
                        ComparatorGUI.this.listToCompareWith = new ArrayList<>();
                        File selectedFile = fileChooser.getSelectedFile();
                        ComparatorGUI.this.readFile(selectedFile, ComparatorGUI.this.listToCompareWith);
                    }
                }
            }
        });
        btnExportResults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtExportResults) {
                if (evtExportResults.getSource() == btnExportResults) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Export Full Results");
                    fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", new String[] { "txt" }));
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showSaveDialog(ComparatorGUI.this);
                    if (result == 0) {
                        File selectedFile = fileChooser.getSelectedFile();
                        if (selectedFile.exists()) {
                            int confirmResult = JOptionPane.showConfirmDialog(ComparatorGUI.this, "File already exists. Overwrite file?", "Confirm Overwrite", 0);
                            if (confirmResult == 0)
                                ComparatorGUI.this.exportResults(selectedFile, charactersArrayList, ComparatorGUI.this.listToCompareWith);
                        } else {
                            String selectedFileName = selectedFile.getName();
                            if (!selectedFileName.toLowerCase().endsWith(".txt")) {
                                selectedFileName = selectedFileName + ".txt";
                                selectedFile = new File(selectedFile.getParent(), selectedFileName);
                            }
                            ComparatorGUI.this.exportResults(selectedFile, charactersArrayList, ComparatorGUI.this.listToCompareWith);
                        }
                    }
                }
            }
        });
        this.listOfCharacters.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evtSelectList) {
                if (!evtSelectList.getValueIsAdjusting()) {
                    int row = ComparatorGUI.this.listOfCharacters.getSelectedIndex();
                    if (row != -1) {
                        GenshinChar tempChar = new GenshinChar();
                        String tempCharName = ComparatorGUI.this.listOfCharacters.getModel().getElementAt(row);
                        try {
                            for (GenshinChar x : charactersArrayList) {
                                if (tempCharName.equals(x.getCharacterName())) {
                                    tempChar = x;
                                    break;
                                }
                            }
                            ComparatorGUI.this.lblChosenCharacter.setText("Chosen character: " + tempChar.getCharacterName());
                            String tempImageName = "resources/" + tempChar.getImageFileName();
                            JLabel tempChosenImg = new JLabel(new ImageIcon(tempImageName));
                            ComparatorGUI.this.lblChosenCharacterImage.setIcon(tempChosenImg.getIcon());
                            ComparatorGUI.this.updateTable(tempCharName, charactersArrayList, ComparatorGUI.this.listToCompareWith);
                        } catch (Exception exc) {
                            JOptionPane.showMessageDialog(null, "ERROR: " + String.valueOf(exc), "Error", 0);
                        }
                    }
                }
            }
        });
    }

    public JList<String> buildListOfCharacters(ArrayList<GenshinChar> charactersArrayList) {
        String[] charactersOnList = new String[charactersArrayList.size()];
        int i = 0;
        for (GenshinChar x : charactersArrayList)
            charactersOnList[i++] = x.getCharacterName();
        this.listOfCharacters = new JList<>(charactersOnList);
        return this.listOfCharacters;
    }

    public void readFile(File selectedFile, ArrayList<GenshinChar> listToCompareWith) {
        if (listToCompareWith.size() != 0)
            listToCompareWith.clear();
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
                listToCompareWith.add(newChar);
            }
            this.listOfCharacters.clearSelection();
            this.lblChosenCharacter.setText("Chosen character: ");
            this.lblChosenCharacterImage.setIcon(new ImageIcon("resources/noCharSelected.png"));
            DefaultTableModel updatedTableModel = new DefaultTableModel(new Object[][] {
                    { "Weapon", "", "" }, { "Weapon Type", "", "" }, { "Max HP", "", "" }, { "ATK", "", "" }, { "DEF", "", "" }, { "EM", "", "" }, { "CR", "", "" }, { "CD", "", "" }, { "ER", "", "" }, { "ELEMDMG", "", "" },
                    { "NA LVL", "", "" }, { "ES LVL", "", "" }, { "EB LVL", "", "" },  }, new Object[] { "ATTRIBUTES", "Your char.", "vs. Imported char." }) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.tblComparatorResults.setModel(updatedTableModel);
            this.lblImported.setText("<html>Imported file name:<br>" + selectedFile.getName());
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, "ERROR: " + String.valueOf(exc) + ". Try again.", "Error", 0);
        }
    }

    public void updateTable(String chosenCharacter, ArrayList<GenshinChar> ownList, ArrayList<GenshinChar> againstList) {
        GenshinChar own = new GenshinChar();
        GenshinChar against = new GenshinChar();
        boolean ownExists = false;
        boolean againstExists = false;
        for (GenshinChar x : ownList) {
            if (chosenCharacter.equals(x.getCharacterName())) {
                own = x;
                ownExists = true;
            }
        }
        for (GenshinChar y : againstList) {
            if (chosenCharacter.equals(y.getCharacterName())) {
                against = y;
                againstExists = true;
            }
        }
        if (ownExists && againstExists) {
            DefaultTableModel updatedTableModel = new DefaultTableModel(new Object[][] {
                    { "Weapon", own.getWeapon(), against.getWeapon() },
                    { "Weapon Type", own.getWeaponType(), against.getWeaponType() },
                    { "Max HP", Double.valueOf(own.getMaxHP()), Double.valueOf(against.getMaxHP()) },
                    { "ATK", Double.valueOf(own.getAtk()), Double.valueOf(against.getAtk()) },
                    { "DEF", Double.valueOf(own.getDef()), Double.valueOf(against.getDef()) },
                    { "EM", Double.valueOf(own.getElemMastery()), Double.valueOf(against.getElemMastery()) },
                    { "CR", Double.valueOf(own.getCritRate()), Double.valueOf(against.getCritRate()) },
                    { "CD", Double.valueOf(own.getCritDmg()), Double.valueOf(against.getCritDmg()) },
                    { "ER", Double.valueOf(own.getErgRecharge()), Double.valueOf(against.getErgRecharge()) },
                    { "ELEMDMG", Double.valueOf(own.getElemDmg()), Double.valueOf(against.getElemDmg()) },
                    { "NA LVL", Double.valueOf(own.getNA_lvl()), Double.valueOf(against.getNA_lvl()) },
                    { "ES LVL", Double.valueOf(own.getES_lvl()), Double.valueOf(against.getES_lvl()) },
                    { "EB LVL", Double.valueOf(own.getEB_lvl()), Double.valueOf(against.getEB_lvl()) }
            }, new Object[] { "ATTRIBUTES", "Your char.", "vs. Imported char." }) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.tblComparatorResults.setModel(updatedTableModel);
        }
        else {
            DefaultTableModel updatedTableModel = new DefaultTableModel(new Object[][] {
                    { "Weapon", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "Weapon Type", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "Max HP", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "ATK", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "DEF", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "EM", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "CR", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "CD", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "ER", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "ELEMDMG", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "NA LVL", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "ES LVL", "INCOMPLETE DATA", "INCOMPLETE DATA" },
                    { "EB LVL", "INCOMPLETE DATA", "INCOMPLETE DATA" }
            }, new Object[] { "ATTRIBUTES", "Your char.", "vs. Imported char." }) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.tblComparatorResults.setModel(updatedTableModel);
        }
    }

    public void exportResults(File selectedFile, ArrayList<GenshinChar> ownList, ArrayList<GenshinChar> againstList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
            GenshinChar own = new GenshinChar();
            GenshinChar against = new GenshinChar();
            bw.write("COMPARISON RESULTS (OWN LIST VS IMPORTED LIST)");
            bw.newLine();
            bw.newLine();
            for (int i = 0; i < ownList.size(); i++) {
                own = ownList.get(i);
                for (GenshinChar y : againstList) {
                    if (own.getCharacterName().equals(y.getCharacterName())) {
                        against = y;
                        break;
                    }
                }
                bw.write("Character: " + own.getCharacterName());
                bw.newLine();
                bw.write("Weapon: " + own.getWeapon() + " vs " + against.getWeapon());
                bw.newLine();
                bw.write("Weapon Type: " + own.getWeaponType() + " vs " + against.getWeaponType());
                bw.newLine();
                bw.write("Max HP: " + own.getMaxHP() + " vs " + against.getMaxHP());
                bw.newLine();
                bw.write("ATK: " + own.getAtk() + " vs " + against.getAtk());
                bw.newLine();
                bw.write("DEF: " + own.getDef() + " vs " + against.getDef());
                bw.newLine();
                bw.write("EM: " + own.getElemMastery() + " vs " + against.getElemMastery());
                bw.newLine();
                bw.write("CR: " + own.getCritRate() + " vs " + against.getCritRate());
                bw.newLine();
                bw.write("CD: " + own.getCritDmg() + " vs " + against.getCritDmg());
                bw.newLine();
                bw.write("ER: " + own.getErgRecharge() + " vs " + against.getErgRecharge());
                bw.newLine();
                bw.write("ELEMDMG: " + own.getElemDmg() + " vs " + against.getElemDmg());
                bw.newLine();
                bw.write("NA LVL: " + own.getNA_lvl() + " vs " + against.getNA_lvl());
                bw.newLine();
                bw.write("ES LVL: " + own.getES_lvl() + " vs " + against.getES_lvl());
                bw.newLine();
                bw.write("EB LVL: " + own.getEB_lvl() + " vs " + against.getEB_lvl());
                bw.newLine();
                bw.newLine();
            }
            bw.flush();
            bw.close();
            JOptionPane.showMessageDialog(null, "Results exported successfully.", "Export Results", 1);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void setDialogVisible(boolean isVisible) {
        this.comparatorDialog.setVisible(isVisible);
    }

    public void setModal(boolean isModal) {
        this.comparatorDialog.setModal(isModal);
    }
}
