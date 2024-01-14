package MainUI;

import GroupContent.Comment;
import GroupContent.Paper;
import GroupContent.Tag;
import Managers.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaperUI extends JPanel {

    private Paper paper;
    private JLabel title, userUploaded;
    private JCheckBox checkBox;
    private JButton addTagBtn;
    private JButton seeCommentsBtn;
    private GridBagConstraints gbc;

    private ButtonClickListener listener;


    public PaperUI(Paper paper) {
        this.paper = paper;
        //set up panel

        //ensure the added papers have a fixed height
        int paperHeight = 70;//was 40
        setPreferredSize(new Dimension(100, paperHeight));
        setMaximumSize(new Dimension(100000, paperHeight));
        setMinimumSize(new Dimension(0, paperHeight));

        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new GridBagLayout());

        addPanelComponents();


    }
    public Paper getPaper() {
        return paper;
    }


    private void addPanelComponents() {
        // set up constraints for grid-bag layout
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        //set up components in panel
        checkBox = new JCheckBox();
        add(checkBox);
        add(checkBox, gbc);

        title = new JLabel();
        //try html wrap
//

        String fullTitle = "<html><body style='width: 450px; text-align: center;'>" +
                "<span style='font-size: 10px; font-weight: bold;'>" + paper.getName() + "</span>" +
                "<br><span style='font-size: 8px; color: gray;'>Uploaded by: " + paper.getUser(paper.getName()) + "</span>" +
                "</body></html>";
        JLabel title = new JLabel(fullTitle);

        title.setToolTipText(paper.getName() + " Uploaded by: " + paper.getUser(paper.getName())); // Set tooltip to display the full text on hover


        add(title, gbc);
//        userUploaded = new JLabel();
//        userUploaded.setText(paper.getUser(paper.getName()));
//        title.setText(paper.getName()+ "  Uploaded by: "+paper.getUser(paper.getName()));

        gbc.gridx = 1;
        gbc.weightx = 1; // This allows the label to expand and shrink - this line is from ChatGPT
        add(title, gbc);
        //add(userUploaded, gbc);
        //add(, gbc);
        gbc.weightx = 0;

        addTagBtn = new JButton("Tag Paper");
        gbc.gridx = 2;
        addTagBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = { "Tag", "Subtag" };
                int optionChosen = JOptionPane.showOptionDialog(null, "Choose an option:",
                                                                "Tag or Subtag", JOptionPane.DEFAULT_OPTION,
                                                                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (optionChosen == 0) {
                    String tagString = JOptionPane.showInputDialog("Enter tag:");
                    if (listener != null && tagString != null) {
                        System.out.println("if executed");

                        String tableName = "\"Tags\"";
                        String columns = "\"TagName\", \"ParentTagID\"";
                        String values = String.format("'%s', NULL", tagString);

                        DatabaseManager.insertRecord(tableName, columns, values);

                        String cols = "paperid, tagid";
                        int TagID = DatabaseManager.getId("\"Tags\"", tagString, "\"TagName\"" );
                        int paperID = DatabaseManager.getPaperId("papers4", paper.getName());

                        System.out.println("tag id: "+TagID+" PaperID: "+paperID);

                        String values2 = String.format("%d, %d", TagID, paperID);
                        DatabaseManager.insertRecord("tagpaperlink2", cols, values2);

                        Tag tag = new Tag(tagString);
                        paper.addTags(tag);
                        System.out.println(paper.getTags());
                    }
                    else {
                        System.out.println("Tag addition cancelled");
                    }
                } else if (optionChosen == 1) {
                    String subtagString = JOptionPane.showInputDialog("Enter subtag:");
                    Tag subtag = new Tag(subtagString);
                    if (listener != null) {
                        String[] tagOptions = paper.getTagsStringList().toArray(new String[0]);
                        int tagOptionChosen = JOptionPane.showOptionDialog(null, "Choose an option:",
                                "Tag or Subtag", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, tagOptions, tagOptions[0]);
                        for(Tag chosenTag:paper.getTags()){
                            if(tagOptionChosen == paper.getTags().indexOf(chosenTag)){
                                int TagParentID = DatabaseManager.getId("\"Tags\"", chosenTag.toString(), "\"TagName\"");


                                String tableName = "\"Tags\"";
                                String cols = "\"TagName\", \"ParentTagID\"";
                                String vals = String.format("'%s', %d", subtagString, TagParentID);


                                DatabaseManager.insertRecord(tableName, cols, vals);

                                chosenTag.addSubTag(subtag);
                            }
                            System.out.println(chosenTag.getSubTags());
                        }
                    }

                } else {
                    System.out.println("Dialog closed or unexpected option selected");
                }
            }
        });
        add(addTagBtn, gbc);

        seeCommentsBtn = new JButton("See Comments");
        gbc.gridx = 3;
        seeCommentsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.onButtonClicked(paper);
                }
            }
        });
        add(seeCommentsBtn, gbc);

        //add some space to the end of the line
        JPanel blankspace = new JPanel();
        blankspace.setSize(10, 10);
        gbc.gridx = 4;
        add(blankspace, gbc);

    }
    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public void setButtonClickListener(ButtonClickListener listener) {
        this.listener = listener;
    }


}
