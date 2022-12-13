package controller;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AllWindowLis extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        int ans = JOptionPane.showConfirmDialog(null,
                "确定要退出？","",JOptionPane.YES_NO_OPTION);
        if(ans == 0){
            System.exit(0);
        }
    }
}
