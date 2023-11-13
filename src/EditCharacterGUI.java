import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class EditCharacterGUI extends JPanel {
    private final JTable table;

    private JDialog editDialog;

    private JComboBox<String> comboWeapon;

    private JTextField txtMaxHP;

    private JTextField txtAtk;

    private JTextField txtDef;

    private JTextField txtEM;

    private JTextField txtCR;

    private JTextField txtCD;

    private JTextField txtER;

    private JTextField txtElemDmg;

    private JLabel lblEditCharacterImage;

    private JComboBox<String> comboNA;

    private JComboBox<String> comboES;

    private JComboBox<String> comboEB;

    String[] NAComboItems = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

    String[] ESEBComboItems = new String[] {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15" };

    String[] bows = new String[] {
            "Amos' Bow", "Aqua Simulacra", "Elegy for the End", "Hunter's Path", "Polar Star", "Skyward Harp", "Thundering Pulse", "Alley Hunter", "Blackcliff Warbow", "Compound Bow",
            "End of the Line", "Fading Twilight", "Favonius Warbow", "Hamayumi", "King's Squire", "Mitternacht's Waltz", "Mouun's Moon", "Predator", "Prototype Crescent", "Royal Bow",
            "Rust", "Sacrificial Bow", "The Stringless", "The Viridescent Hunt", "Windblume Ode", "Messenger", "Raven Bow", "Recurve Bow", "Sharpshooter's Oath", "Slingshot",
            "Seasoned Hunter's Bow", "Hunter's Bow" };

    String[] catalysts = new String[] {
            "A Thousand Floating Dreams", "Everlasting Moonglow", "Kagura's Verity", "Lost Prayer to the Sacred Winds", "Memory of Dust", "Skyward Atlas", "Tulaytullah's Remembrance", "Blackcliff Agate", "Dodoco Tales", "Eye of Perception",
            "Favonius Codex", "Frostbearer", "Fruit of Fulfillment", "Hakushin Ring", "Mappa Mare", "Oathsworn Eye", "Prototype Amber", "Royal Grimoire", "Sacrificial Fragments", "Solar Pearl",
            "The Widsith", "Wandering Evenstar", "Wine and Song", "Emerald Orb", "Magic Guide", "Otherworldly Story", "Thrilling Tales of Dragon Slayers", "Twin Nephrite", "Pocket Grimoire", "Apprentice's Notes" };

    String[] claymores = new String[] {
            "Beacon of the Reed Sea", "Redhorn Stonethresher", "Skyward Pride", "Song of Broken Pines", "The Unforged", "Wolf's Gravestone", "Akuoumaru", "Blackcliff Slasher", "Favonius Greatsword", "Forest Regalia",
            "Katsuragikiri Nagamasa", "Lithic Blade", "Luxurious Sea-Lord", "Mailed Flower", "Makhaira Aquamarine", "Prototype Archaic", "Rainslasher", "Royal Greatsword", "Sacrificial Greatsword", "Serpent Spine",
            "Snow-Tombed Starsilver", "The Bell", "Whiteblind", "Bloodtainted Greatsword", "Debate Club", "Ferrous Shadow", "Skyrider Greatsword", "White Iron Greatsword", "Old Merc's Pal", "Waster Greatsword" };

    String[] polearms = new String[] {
            "Calamity Queller", "Engulfing Lightning", "Primordial Jade Winged-Spear", "Skyward Spine", "Staff of Homa", "Staff of the Scarlet Sands", "Vortex Vanquisher", "Blackcliff Pole", "Crescent Pike", "Deathmatch",
            "Dragon's Bane", "Dragonspine Spear", "Favonius Lance", "Kitain Cross Spear", "Lithic Spear", "Missive Windspear", "Moonpiercer", "Prototype Starglitter", "Royal Spear", "The Catch",
            "Wavebreaker's Fin", "Black Tassel", "Halberd", "White Tassel", "Iron Point", "Beginner's Protector" };

    String[] swords = new String[] {
            "Aquila Favonia", "Freedom-Sworn", "Haran Geppaku Futsu", "Key of Khaj-Nisut", "Light of Foliar Incision", "Mistsplitter Reforged", "Primordial Jade Cutter", "Skyward Blade", "Summit Shaper", "Amenoma Kageuchi",
            "Blackcliff Longsword", "Cinnabar Spindle", "Favonius Sword", "Festering Desire", "Iron Sting", "Kagotsurube Isshin", "Lion's Roar", "Prized Isshin Blade (Awakened)", "Prized Isshin Blade (Shattered)", "Prototype Rancour",
            "Royal Longsword", "Sacrificial Sword", "Sapwood Blade", "Sword of Descension", "The Alley Flash", "The Black Sword", "The Flute", "Toukabou Shigure", "Xiphos' Moonlight", "Cool Steel",
            "Dark Iron Sword", "Fillet Blade", "Harbinger of Dawn", "Skyrider Sword", "Traveler's Handy Sword", "Silver Sword", "Dull Blade" };

    public EditCharacterGUI(ArrayList<GenshinChar> charactersArrayList, JTable charactersTable, int row) {
        this.table = charactersTable;
        buildGUI(charactersArrayList.get(row));
        prefillFields(charactersArrayList.get(row));
    }

    public void buildGUI(GenshinChar selectedChar) {
        this.editDialog = new JDialog();
        this.editDialog.setTitle("Edit Character");
        this.editDialog.add(this);
        this.editDialog.setSize(new Dimension(485, 700));
        this.editDialog.setLayout((LayoutManager)null);
        this.editDialog.setResizable(false);
        this.editDialog.setDefaultCloseOperation(2);
        JButton btnConfirmEdit = new JButton("Confirm");
        JButton btnCancel = new JButton("Cancel");
        JLabel lblEditCharacterHeader = new JLabel("Edit Character");
        this.lblEditCharacterImage = new JLabel();
        JLabel lblWeapon = new JLabel("Weapon");
        createWeaponsComboBox(selectedChar);
        JLabel lblMaxHP = new JLabel("Max HP");
        this.txtMaxHP = new JTextField(5);
        JLabel lblAtk = new JLabel("ATK");
        this.txtAtk = new JTextField(5);
        JLabel lblDef = new JLabel("DEF");
        this.txtDef = new JTextField(5);
        JLabel lblEM = new JLabel("EM");
        this.txtEM = new JTextField(5);
        JLabel lblCR = new JLabel("CR");
        this.txtCR = new JTextField(5);
        JLabel lblCD = new JLabel("CD");
        this.txtCD = new JTextField(5);
        JLabel lblER = new JLabel("ER");
        this.txtER = new JTextField(5);
        JLabel lblElemDmg = new JLabel("Elemental DMG+");
        this.txtElemDmg = new JTextField(5);
        JLabel lblNA = new JLabel("Normal ATK LVL");
        this.comboNA = new JComboBox<>(this.NAComboItems);
        JLabel lblES = new JLabel("E LVL");
        this.comboES = new JComboBox<>(this.ESEBComboItems);
        JLabel lblEB = new JLabel("Q LVL");
        this.comboEB = new JComboBox<>(this.ESEBComboItems);
        this.editDialog.add(btnConfirmEdit);
        this.editDialog.add(btnCancel);
        this.editDialog.add(lblEditCharacterHeader);
        this.editDialog.add(this.lblEditCharacterImage);
        this.editDialog.add(lblWeapon);
        this.editDialog.add(this.comboWeapon);
        this.editDialog.add(lblMaxHP);
        this.editDialog.add(this.txtMaxHP);
        this.editDialog.add(lblAtk);
        this.editDialog.add(this.txtAtk);
        this.editDialog.add(lblDef);
        this.editDialog.add(this.txtDef);
        this.editDialog.add(lblEM);
        this.editDialog.add(this.txtEM);
        this.editDialog.add(lblCR);
        this.editDialog.add(this.txtCR);
        this.editDialog.add(lblCD);
        this.editDialog.add(this.txtCD);
        this.editDialog.add(lblER);
        this.editDialog.add(this.txtER);
        this.editDialog.add(lblElemDmg);
        this.editDialog.add(this.txtElemDmg);
        this.editDialog.add(lblNA);
        this.editDialog.add(this.comboNA);
        this.editDialog.add(lblES);
        this.editDialog.add(this.comboES);
        this.editDialog.add(lblEB);
        this.editDialog.add(this.comboEB);
        btnConfirmEdit.setBounds(85, 580, 120, 40);
        btnCancel.setBounds(275, 580, 120, 40);
        lblEditCharacterHeader.setBounds(195, 10, 85, 25);
        this.lblEditCharacterImage.setBounds(150, 35, 165, 165);
        lblWeapon.setBounds(15, 220, 100, 25);
        this.comboWeapon.setBounds(115, 220, 335, 25);
        lblMaxHP.setBounds(15, 255, 100, 25);
        this.txtMaxHP.setBounds(115, 255, 335, 25);
        lblAtk.setBounds(15, 290, 100, 25);
        this.txtAtk.setBounds(115, 290, 100, 25);
        lblDef.setBounds(250, 290, 30, 25);
        this.txtDef.setBounds(305, 290, 100, 25);
        lblEM.setBounds(15, 325, 100, 25);
        this.txtEM.setBounds(115, 325, 335, 25);
        lblCR.setBounds(15, 360, 100, 25);
        this.txtCR.setBounds(115, 360, 100, 25);
        lblCD.setBounds(250, 355, 30, 35);
        this.txtCD.setBounds(305, 360, 100, 25);
        lblER.setBounds(15, 395, 100, 25);
        this.txtER.setBounds(115, 395, 335, 25);
        lblElemDmg.setBounds(15, 430, 100, 25);
        this.txtElemDmg.setBounds(115, 430, 335, 25);
        lblNA.setBounds(15, 465, 100, 25);
        this.comboNA.setBounds(115, 465, 80, 25);
        lblES.setBounds(15, 500, 100, 25);
        this.comboES.setBounds(115, 500, 80, 25);
        lblEB.setBounds(15, 535, 100, 25);
        this.comboEB.setBounds(115, 535, 80, 25);
        btnConfirmEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtConfirmEdit) {
                int row = EditCharacterGUI.this.table.getSelectedRow();
                if (row != -1) {
                    GenshinChar selectedChar = MainMenuGUI.charactersArrayList.get(row);
                    try {
                        selectedChar.setWeapon((String)EditCharacterGUI.this.comboWeapon.getSelectedItem());
                        selectedChar.setMaxHP(Double.parseDouble(EditCharacterGUI.this.txtMaxHP.getText()));
                        selectedChar.setAtk(Double.parseDouble(EditCharacterGUI.this.txtAtk.getText()));
                        selectedChar.setDef(Double.parseDouble(EditCharacterGUI.this.txtDef.getText()));
                        selectedChar.setElemMastery(Double.parseDouble(EditCharacterGUI.this.txtEM.getText()));
                        selectedChar.setCritRate(Double.parseDouble(EditCharacterGUI.this.txtCR.getText()));
                        selectedChar.setCritDmg(Double.parseDouble(EditCharacterGUI.this.txtCD.getText()));
                        selectedChar.setErgRecharge(Double.parseDouble(EditCharacterGUI.this.txtER.getText()));
                        selectedChar.setElemDmg(Double.parseDouble(EditCharacterGUI.this.txtElemDmg.getText()));
                        double selectedCharNAnew = Double.parseDouble((String)Objects.<Object>requireNonNull(EditCharacterGUI.this.comboNA.getSelectedItem()));
                        selectedChar.setNA_lvl(selectedCharNAnew);
                        double selectedCharESnew = Double.parseDouble((String)Objects.<Object>requireNonNull(EditCharacterGUI.this.comboES.getSelectedItem()));
                        selectedChar.setES_lvl(selectedCharESnew);
                        double selectedCharEBnew = Double.parseDouble((String)Objects.<Object>requireNonNull(EditCharacterGUI.this.comboEB.getSelectedItem()));
                        selectedChar.setEB_lvl(selectedCharEBnew);
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null, "ERROR: " + String.valueOf(exc) + ". Please try again.", "Error", 0);
                    }
                    MainMenuGUI.chosenCharWeapon.setText("Weapon: " + selectedChar.getWeapon());
                    MainMenuGUI.chosenCharMaxHP.setText("Max HP: " + selectedChar.getMaxHP());
                    MainMenuGUI.chosenCharAtk.setText("ATK: " + selectedChar.getAtk());
                    MainMenuGUI.chosenCharDef.setText("DEF: " + selectedChar.getDef());
                    MainMenuGUI.chosenCharEM.setText("EM: " + selectedChar.getElemMastery());
                    MainMenuGUI.chosenCharCR.setText("CR: " + selectedChar.getCritRate() + "%");
                    MainMenuGUI.chosenCharCD.setText("CD: " + selectedChar.getCritDmg() + "%");
                    MainMenuGUI.chosenCharER.setText("ER: " + selectedChar.getErgRecharge() + "%");
                    MainMenuGUI.chosenCharElemDmg.setText("Elemental DMG+: " + selectedChar.getElemDmg() + "%");
                    MainMenuGUI.chosenCharNA.setText("Normal ATK LVL: " + selectedChar.getNA_lvl());
                    MainMenuGUI.chosenCharES.setText("E LVL: " + selectedChar.getES_lvl());
                    MainMenuGUI.chosenCharEB.setText("Q LVL: " + selectedChar.getEB_lvl());
                    EditCharacterGUI.this.table.setValueAt(selectedChar.getWeapon(), row, 1);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getMaxHP()), row, 3);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getAtk()), row, 4);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getDef()), row, 5);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getElemMastery()), row, 6);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getCritRate()), row, 7);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getCritDmg()), row, 8);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getErgRecharge()), row, 9);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getElemDmg()), row, 10);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getNA_lvl()), row, 11);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getES_lvl()), row, 12);
                    EditCharacterGUI.this.table.setValueAt(Double.toString(selectedChar.getEB_lvl()), row, 13);
                }
                EditCharacterGUI.this.editDialog.dispose();
                EditCharacterGUI.this.updateStatus(MainMenuGUI.charactersArrayList.get(row));
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtCancel) {
                EditCharacterGUI.this.editDialog.dispose();
            }
        });
    }

    public void createWeaponsComboBox(GenshinChar selectedChar) {
        String selectedCharWeaponType = selectedChar.getWeaponType();
        switch (selectedCharWeaponType) {
            case "BW":
                this.comboWeapon = new JComboBox<>(this.bows);
                break;
            case "CT":
                this.comboWeapon = new JComboBox<>(this.catalysts);
                break;
            case "CL":
                this.comboWeapon = new JComboBox<>(this.claymores);
                break;
            case "PL":
                this.comboWeapon = new JComboBox<>(this.polearms);
                break;
            case "SW":
                this.comboWeapon = new JComboBox<>(this.swords);
                break;
        }
    }

    public void prefillFields(GenshinChar selectedChar) {
        String selectedCharImageName = "resources/" + selectedChar.getImageFileName();
        JLabel selectedCharImage = new JLabel(new ImageIcon(selectedCharImageName));
        this.lblEditCharacterImage.setIcon(selectedCharImage.getIcon());
        this.comboWeapon.setSelectedItem(selectedChar.getWeapon());
        this.txtMaxHP.setText(Double.toString(selectedChar.getMaxHP()));
        this.txtAtk.setText(Double.toString(selectedChar.getAtk()));
        this.txtDef.setText(Double.toString(selectedChar.getDef()));
        this.txtEM.setText(Double.toString(selectedChar.getElemMastery()));
        this.txtCR.setText(Double.toString(selectedChar.getCritRate()));
        this.txtCD.setText(Double.toString(selectedChar.getCritDmg()));
        this.txtER.setText(Double.toString(selectedChar.getErgRecharge()));
        this.txtElemDmg.setText(Double.toString(selectedChar.getElemDmg()));
        int selectedCharNA = (int)selectedChar.getNA_lvl();
        String selectedCharNAstr = String.valueOf(selectedCharNA);
        for (String x : this.NAComboItems) {
            if (selectedCharNAstr.equals(x)) {
                this.comboNA.setSelectedItem(x);
                break;
            }
        }
        int selectedCharES = (int)selectedChar.getES_lvl();
        String selectedCharESstr = String.valueOf(selectedCharES);
        for (String x : this.ESEBComboItems) {
            if (selectedCharESstr.equals(x))
                this.comboES.setSelectedItem(x);
        }
        int selectedCharEB = (int)selectedChar.getEB_lvl();
        String selectedCharEBstr = String.valueOf(selectedCharEB);
        for (String x : this.ESEBComboItems) {
            if (selectedCharEBstr.equals(x))
                this.comboEB.setSelectedItem(x);
        }
    }

    public void updateStatus(GenshinChar selectedChar) {
        MainMenuGUI.lblProgramStatus.setText("Stats for " + selectedChar.getCharacterName() + " edited successfully.");
    }

    public void setDialogVisible(boolean isVisible) {
        this.editDialog.setVisible(isVisible);
    }

    public void setModal(boolean isModal) {
        this.editDialog.setModal(isModal);
    }
}
