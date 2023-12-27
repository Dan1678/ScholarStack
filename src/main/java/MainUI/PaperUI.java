package MainUI;

import GroupContent.Comment;
import GroupContent.Paper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaperUI extends JPanel {

    private Paper paper;
    private JLabel title;
    private JCheckBox checkBox;
    private JButton addTagBtn;
    private JButton seeCommentsBtn;
    private GridBagConstraints gbc;

    private ButtonClickListener listener;


    public PaperUI(Paper paper) {
        this.paper = paper;
        //set up panel

        //ensure the added papers have a fixed height
        int paperHeight = 40;
        setPreferredSize(new Dimension(100, paperHeight));
        setMaximumSize(new Dimension(100000, paperHeight));
        setMinimumSize(new Dimension(0, paperHeight));

        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new GridBagLayout());

        addPanelComponents();

        //Temp exmaple
        paper.addComment(new Comment("This is a comment 1"));
        paper.addComment(new Comment("This is a comment 2"));

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
        title.setText(paper.getName());
        gbc.gridx = 1;
        gbc.weightx = 1; // This allows the label to expand and shrink - this line is from ChatGPT
        add(title, gbc);
        gbc.weightx = 0;

        addTagBtn = new JButton("Tag Paper");
        gbc.gridx = 2;
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
