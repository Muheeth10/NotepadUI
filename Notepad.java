import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Notepad {
    JFrame fr = new JFrame("Notepad");

    public Notepad(){
        fr.setSize(1200,700);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JTextArea txt = new JTextArea(25,25);
        txt.setFont(new Font("cambria", Font.PLAIN,20));
        txt.setBackground(new Color(255, 245, 245));
        JScrollPane scrollText = new JScrollPane(txt);
        scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        fr.getContentPane().add(scrollText);
        fr.add(txt,BorderLayout.CENTER);

    // adding Jtree

        JTextArea tree = new JTextArea(5,10);
        tree.setFont(new Font("cambria", Font.PLAIN,15));
        tree.setBackground(new Color(178, 204, 214));
        fr.add(tree,BorderLayout.WEST);

//
//        JFileChooser file = new JFileChooser();
//        File fl = file.getCurrentDirectory();
//        DefaultMutableTreeNode fileroot = new DefaultMutableTreeNode(fl);
//        System.out.println("class PATH"+fl);
//        JTree jT = new JTree(fileroot);
//
//        tree.setText(jT);






        JMenuBar jMB = new JMenuBar();
        fr.setJMenuBar(jMB);

        JMenu File = new JMenu(" File ");
        JMenu Edit = new JMenu(" Edit ");
        jMB.add(File);
        jMB.add(Edit);

        JMenuItem Open = new JMenuItem("Open");
        JMenuItem Saveas = new JMenuItem("Save As");
        JMenuItem Close = new JMenuItem("Close");
        File.add(Open);
        File.add(Saveas);
        File.add(Close);

        File.setFont(new Font("cambria", Font.BOLD,20));
        Edit.setFont(new Font("cambria",Font.BOLD,20));

        Close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
            }

        });

        Open.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e){
                JFileChooser jChoose = new JFileChooser();
                int val = jChoose.showOpenDialog(fr);
                File fl=null;
                if(val == JFileChooser.APPROVE_OPTION){
                    fl = jChoose.getSelectedFile();
                    txt.setText("");
                }
                try{
                    @SuppressWarnings("resourse")
                    BufferedReader l = new BufferedReader(new FileReader(fl));
                    String line = l.readLine();
                    while(line!=null) {
                        txt.append(line + "\n");
                        line = l.readLine();
                    }
                }
                catch (FileNotFoundException nfe){
                   nfe.printStackTrace();
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
           }
        });

        Saveas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser jChooser = new JFileChooser();
                int val = jChooser.showSaveDialog(fr);
                if(val == JFileChooser.APPROVE_OPTION){
                    try {
                        FileWriter fW = new FileWriter(jChooser.getSelectedFile().getAbsolutePath(),true);
                        txt.write(fW);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });



    }



}
