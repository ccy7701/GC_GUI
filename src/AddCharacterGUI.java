import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddCharacterGUI extends JPanel {
    private final JTable table;

    private JDialog addDialog;

    private JComboBox<String> comboCharacterClasses;

    private JComboBox<String> comboCharacterToAdd;

    private JComboBox<String> comboWeapon;

    private JTextField txtMaxHP;

    private JTextField txtAtk;

    private JTextField txtDef;

    private JTextField txtEM;

    private JTextField txtCR;

    private JTextField txtCD;

    private JTextField txtER;

    private JTextField txtElemDmg;

    private JLabel lblAddCharacterImage;

    private JComboBox<String> comboNA;

    private JComboBox<String> comboES;

    private JComboBox<String> comboEB;

    String[] NAComboItems = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

    String[] ESEBComboItems = new String[] {
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15" };

    String[] weaponClasses = new String[] { "Bows", "Catalysts", "Claymores", "Polearms", "Swords" };

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

    String[][] characterNames = new String[][] { {
            "Aloy", "Amber", "Collei", "Diona", "Faruzan", "Fischl", "Ganyu", "Gorou", "Kujou Sara", "Tartaglia",
            "Tighnari", "Venti", "Yelan", "Yoimiya", " ", " ", " ", " " }, {
            "Barbara", "Klee", "Lisa", "Mona", "Nahida", "Ningguang", "Sangonomiya Kokomi", "Shikanoin Heizou", "Sucrose", "Wanderer",
            "Yae Miko", "Yanfei", " ", " ", " " }, {
            "Dehya", "Eula", "Diluc", "Arataki Itto", "Beidou", "Chongyun", "Dori", "Noelle", " ", " ",
            " ", " ", " ", " ", " " }, {
            "Candace", "Cyno", "Hu Tao", "Raiden Shogun", "Rosaria", "Shenhe", "Thoma", "Xiangling", "Xiao", "Yaoyao",
            "Yun Jin", "Zhongli", " ", " ", " " }, {
            "Albedo", "Alhaitham", "Bennett", "Jean", "Kaedehara Kazuha", "Kaeya", "Kamisato Ayaka", "Kamisato Ayato", "Keqing", "Kuki Shinobu",
            "Layla", "Nilou", "Qiqi", "Traveller", "Xingqiu" } };

    String[][] characterImgFiles = new String[][] { {
            "Aloy.png", "Amber.png", "Collei.png", "Diona.png", "Faruzan.png", "Fischl.png", "Ganyu.png", "Gorou.png", "KujouSara.png", "Tartaglia.png",
            "Tighnari.png", "Venti.png", "Yelan.png", "Yoimiya.png", " ", " ", " ", " " }, {
            "Barbara.png", "Klee.png", "Lisa.png", "Mona.png", "Nahida.png", "Ningguang.png", "SangonomiyaKokomi.png", "ShikanoinHeizou.png", "Sucrose.png", "Wanderer.png",
            "YaeMiko.png", "Yanfei.png", " ", " ", " " }, {
            "Dehya.png", "Eula.png", "Diluc.png", "AratakiItto.png", "Beidou.png", "Chongyun.png", "Dori.png", "Noelle.png", " ", " ",
            " ", " ", " ", " ", " " }, {
            "Candace.png", "Cyno.png", "HuTao.png", "RaidenShogun.png", "Rosaria.png", "Shenhe.png", "Thoma.png", "Xiangling.png", "Xiao.png", "Yaoyao.png",
            "YunJin.png", "Zhongli.png", " ", " ", " " }, {
            "Albedo.png", "Alhaitham.png", "Bennett.png", "Jean.png", "KaedeharaKazuha.png", "Kaeya.png", "KamisatoAyaka.png", "KamisatoAyato.png", "Keqing.png", "KukiShinobu.png",
            "Layla.png", "Nilou.png", "Qiqi.png", "Traveller.png", "Xingqiu.png" } };

    public AddCharacterGUI(JTable charactersTable) {
        this.table = charactersTable;
        buildGUI();
    }

    public void buildGUI() {
        this.addDialog = new JDialog();
        this.addDialog.setTitle("Add Character");
        this.addDialog.add(this);
        this.addDialog.setSize(new Dimension(485, 700));
        this.addDialog.setLayout((LayoutManager)null);
        this.addDialog.setResizable(false);
        this.addDialog.setDefaultCloseOperation(2);
        JButton btnConfirmAdd = new JButton("Add");
        JButton btnCancel = new JButton("Cancel");
        JLabel lblAddCharacterHeader = new JLabel("Add Character");
        this.lblAddCharacterImage = new JLabel();
        this.lblAddCharacterImage.setIcon(new ImageIcon("resources/noCharSelected.png"));
        JLabel lblCharacter = new JLabel("Character");
        this.comboCharacterClasses = new JComboBox<>(this.weaponClasses);
        this.comboCharacterClasses.setSelectedIndex(-1);
        String[] temp = { " " };
        this.comboCharacterToAdd = new JComboBox<>(temp);
        this.comboCharacterToAdd.setSelectedIndex(-1);
        JLabel lblWeapon = new JLabel("Weapon");
        this.comboWeapon = new JComboBox<>(temp);
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
        this.addDialog.add(btnConfirmAdd);
        this.addDialog.add(btnCancel);
        this.addDialog.add(lblAddCharacterHeader);
        this.addDialog.add(this.lblAddCharacterImage);
        this.addDialog.add(lblCharacter);
        this.addDialog.add(this.comboCharacterClasses);
        this.addDialog.add(this.comboCharacterToAdd);
        this.addDialog.add(lblWeapon);
        this.addDialog.add(this.comboWeapon);
        this.addDialog.add(lblMaxHP);
        this.addDialog.add(this.txtMaxHP);
        this.addDialog.add(lblAtk);
        this.addDialog.add(this.txtAtk);
        this.addDialog.add(lblDef);
        this.addDialog.add(this.txtDef);
        this.addDialog.add(lblEM);
        this.addDialog.add(this.txtEM);
        this.addDialog.add(lblCR);
        this.addDialog.add(this.txtCR);
        this.addDialog.add(lblCD);
        this.addDialog.add(this.txtCD);
        this.addDialog.add(lblER);
        this.addDialog.add(this.txtER);
        this.addDialog.add(lblElemDmg);
        this.addDialog.add(this.txtElemDmg);
        this.addDialog.add(lblNA);
        this.addDialog.add(this.comboNA);
        this.addDialog.add(lblES);
        this.addDialog.add(this.comboES);
        this.addDialog.add(lblEB);
        this.addDialog.add(this.comboEB);
        btnConfirmAdd.setBounds(85, 580, 120, 40);
        btnCancel.setBounds(275, 580, 120, 40);
        lblAddCharacterHeader.setBounds(195, 5, 85, 25);
        this.lblAddCharacterImage.setBounds(150, 20, 165, 165);
        lblCharacter.setBounds(15, 185, 100, 25);
        this.comboCharacterClasses.setBounds(115, 185, 120, 25);
        this.comboCharacterToAdd.setBounds(245, 185, 150, 25);
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
        this.comboCharacterClasses.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtClassSelected) {
                String selectedClass = (String)AddCharacterGUI.this.comboCharacterClasses.getSelectedItem();
                int weaponClassID = AddCharacterGUI.this.filterByWeaponClass(selectedClass);
                AddCharacterGUI.this.loadCharactersAndWeaponsByClass(weaponClassID);
            }
        });
        this.comboCharacterToAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtCharSelected) {
                String selectedCharacter = (String)AddCharacterGUI.this.comboCharacterToAdd.getSelectedItem();
                AddCharacterGUI.this.showConnectedImgFile(selectedCharacter);
            }
        });
        btnConfirmAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtConfirmAdd) {
                GenshinChar newChar = new GenshinChar();
                try {
                    newChar.setCharName((String)AddCharacterGUI.this.comboCharacterToAdd.getSelectedItem());
                    newChar.setWeapon((String)AddCharacterGUI.this.comboWeapon.getSelectedItem());
                    AddCharacterGUI.this.determineWeaponType(newChar);
                    newChar.setMaxHP(Double.parseDouble(AddCharacterGUI.this.txtMaxHP.getText()));
                    newChar.setAtk(Double.parseDouble(AddCharacterGUI.this.txtAtk.getText()));
                    newChar.setDef(Double.parseDouble(AddCharacterGUI.this.txtDef.getText()));
                    newChar.setElemMastery(Double.parseDouble(AddCharacterGUI.this.txtEM.getText()));
                    newChar.setCritRate(Double.parseDouble(AddCharacterGUI.this.txtCR.getText()));
                    newChar.setCritDmg(Double.parseDouble(AddCharacterGUI.this.txtCD.getText()));
                    newChar.setErgRecharge(Double.parseDouble(AddCharacterGUI.this.txtER.getText()));
                    newChar.setElemDmg(Double.parseDouble(AddCharacterGUI.this.txtElemDmg.getText()));
                    double newCharNA = Double.parseDouble((String)Objects.<Object>requireNonNull(AddCharacterGUI.this.comboNA.getSelectedItem()));
                    newChar.setNA_lvl(newCharNA);
                    double newCharES = Double.parseDouble((String)Objects.<Object>requireNonNull(AddCharacterGUI.this.comboES.getSelectedItem()));
                    newChar.setES_lvl(newCharES);
                    double newCharEB = Double.parseDouble((String)Objects.<Object>requireNonNull(AddCharacterGUI.this.comboEB.getSelectedItem()));
                    newChar.setEB_lvl(newCharEB);
                    if (!AddCharacterGUI.this.checkIfCharacterExists(newChar, MainMenuGUI.charactersArrayList)) {
                        MainMenuGUI.charactersArrayList.add(newChar);
                        AddCharacterGUI.this.setConnectedImgFileName(newChar);
                        AddCharacterGUI.this.sortArrayList(newChar, MainMenuGUI.charactersArrayList);
                        AddCharacterGUI.this.updateTable(MainMenuGUI.charactersTable);
                        AddCharacterGUI.this.updateStatus(newChar);
                        AddCharacterGUI.this.addDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Character already exists on the list. Try editing instead.", "Warning", 2);
                    }
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + String.valueOf(exc) + ". \nPlease check your inputs and try again.", "Error", 2);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evtCancel) {
                AddCharacterGUI.this.addDialog.dispose();
            }
        });
    }

    public int filterByWeaponClass(String selectedClass) {
        int weaponClassID = -1;
        switch (selectedClass) {
            case "Bows":
                weaponClassID = 0;
                break;
            case "Catalysts":
                weaponClassID = 1;
                break;
            case "Claymores":
                weaponClassID = 2;
                break;
            case "Polearms":
                weaponClassID = 3;
                break;
            case "Swords":
                weaponClassID = 4;
                break;
        }
        return weaponClassID;
    }

    public void loadCharactersAndWeaponsByClass(int weaponClassID) {
        DefaultComboBoxModel<String> modelCh;
        DefaultComboBoxModel<String> modelWp;
        switch (weaponClassID) {
            case 0:
                modelCh = new DefaultComboBoxModel<>(populateArray(0));
                this.comboCharacterToAdd.setModel(modelCh);
                modelWp = new DefaultComboBoxModel<>(this.bows);
                this.comboWeapon.setModel(modelWp);
                break;
            case 1:
                modelCh = new DefaultComboBoxModel<>(populateArray(1));
                this.comboCharacterToAdd.setModel(modelCh);
                modelWp = new DefaultComboBoxModel<>(this.catalysts);
                this.comboWeapon.setModel(modelWp);
                break;
            case 2:
                modelCh = new DefaultComboBoxModel<>(populateArray(2));
                this.comboCharacterToAdd.setModel(modelCh);
                modelWp = new DefaultComboBoxModel<>(this.claymores);
                this.comboWeapon.setModel(modelWp);
                break;
            case 3:
                modelCh = new DefaultComboBoxModel<>(populateArray(3));
                this.comboCharacterToAdd.setModel(modelCh);
                modelWp = new DefaultComboBoxModel<>(this.polearms);
                this.comboWeapon.setModel(modelWp);
                break;
            case 4:
                modelCh = new DefaultComboBoxModel<>(populateArray(4));
                this.comboCharacterToAdd.setModel(modelCh);
                modelWp = new DefaultComboBoxModel<>(this.swords);
                this.comboWeapon.setModel(modelWp);
                break;
        }
    }

    public String[] populateArray(int row) {
        int columns = (this.characterNames[row]).length;
        for (int i = 0; i < (this.characterNames[row]).length; i++) {
            if (this.characterNames[row][i] == " ")
                columns--;
        }
        String[] tempArray = new String[columns];
        for (int j = 0; j < columns; j++)
            tempArray[j] = this.characterNames[row][j];
        return tempArray;
    }

    public void showConnectedImgFile(String selectedCharacter) {
        for (int i = 0; i < this.characterNames.length; i++) {
            for (int j = 0; j < (this.characterNames[i]).length; j++) {
                if (selectedCharacter.equals(this.characterNames[i][j])) {
                    String tempImageName = "resources/" + this.characterImgFiles[i][j];
                    JLabel tempChosenImg = new JLabel(new ImageIcon(tempImageName));
                    this.lblAddCharacterImage.setIcon(tempChosenImg.getIcon());
                }
            }
        }
    }

    public void determineWeaponType(GenshinChar newChar) {
        int row = -1;
        for (int i = 0; i < this.characterNames.length; i++) {
            for (int j = 0; j < (this.characterNames[i]).length; j++) {
                if (newChar.getCharacterName().equals(this.characterNames[i][j])) {
                    row = i;
                    break;
                }
            }
        }
        switch (row) {
            case 0:
                newChar.setWeaponType("BW");
                break;
            case 1:
                newChar.setWeaponType("CT");
                break;
            case 2:
                newChar.setWeaponType("CL");
                break;
            case 3:
                newChar.setWeaponType("PL");
                break;
            case 4:
                newChar.setWeaponType("SW");
                break;
        }
    }

    public boolean checkIfCharacterExists(GenshinChar newChar, ArrayList<GenshinChar> charactersArrayList) {
        boolean exists = false;
        for (GenshinChar x : charactersArrayList) {
            if (newChar.getCharacterName().equals(x.getCharacterName())) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public void setConnectedImgFileName(GenshinChar newChar) {
        String newCharName = newChar.getCharacterName();
        for (int i = 0; i < this.characterNames.length; i++) {
            for (int j = 0; j < (this.characterNames[i]).length; j++) {
                if (newCharName.equals(this.characterNames[i][j]))
                    newChar.setImageFileName(this.characterImgFiles[i][j]);
            }
        }
    }

    public void sortArrayList(GenshinChar newChar, ArrayList<GenshinChar> charactersArrayList) {
        Collections.sort(charactersArrayList, new Comparator<GenshinChar>() {
            public int compare(GenshinChar obj1, GenshinChar obj2) {
                return obj1.getCharacterName().compareTo(obj2.getCharacterName());
            }
        });
    }

    public void updateTable(JTable charactersTable) {
        DefaultTableModel model = (DefaultTableModel)charactersTable.getModel();
        model.setRowCount(0);
        for (GenshinChar x : MainMenuGUI.charactersArrayList) {
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

    public void updateStatus(GenshinChar newChar) {
        MainMenuGUI.lblProgramStatus.setText("New character " + newChar.getCharacterName() + " added successfully.");
    }

    public void setDialogVisible(boolean isVisible) {
        this.addDialog.setVisible(isVisible);
    }

    public void setModal(boolean isModal) {
        this.addDialog.setModal(isModal);
    }
}
