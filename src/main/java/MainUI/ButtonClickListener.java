package MainUI;


import GroupContent.Paper;
//create interface to improve functionality and reduce the amount of repeated code

public interface ButtonClickListener {
    void onButtonClicked(Paper paper);
}