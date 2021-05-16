package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Mainframe implements ActionListener {
      JFrame frame=new JFrame();
    private JButton button=new JButton("Add new book");
    private JButton button2=new JButton("See all books");
    private JButton button3=new JButton("Statistics");
    private JButton button4=new JButton("About");
    private JLabel label=new JLabel("Hello, here you can manage and view statistics on how many books you have read");
    public Mainframe()  {
        frame.setTitle("Bookmanager");

        label.setBounds(15,0,500,50);
        label.setFont(new Font(null,Font.PLAIN,12));
        frame.add(label);

        button.setBounds(40,50,100,40);
        button.setFocusable(false);
        button.addActionListener(this);
        frame.add(button);

        button2.setBounds(170,50 ,100,40);
        button2.setFocusable(false);
        button2.addActionListener(this);
        frame.add(button2);

        button3.setBounds(310,50 ,100,40);
        button3.setFocusable(false);
        button3.addActionListener(this);
        frame.add(button3);

        button4.setBounds(170,100 ,100,40);
        button4.setFocusable(false);
        button4.addActionListener(this);
        frame.add(button4);



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,200);
        frame.setLayout(null);
        frame.setVisible(true);
        ImageIcon image=new ImageIcon("logobook.png");
        frame.setIconImage(image.getImage());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String connectionUrl="jdbc:postgresql://localhost:5432/Bookmanager";
        Connection connection=null;
        ResultSet resultSet=null;
        Statement statement=null;
            if(e.getSource()==button){
                frame.dispose();
                Add add=new Add();
            }
            else if(e.getSource()==button4){
                String message="*In add button you can add new books\n*In all button you can see all books\n*In Statistics you can see your statistics ";
                JOptionPane.showMessageDialog(null,message,"About",JOptionPane.PLAIN_MESSAGE);
            }
            else if(e.getSource()==button2){
                try {
                    String output="";
                    Class.forName("org.postgresql.Driver");
                    connection= DriverManager.getConnection(connectionUrl,"postgres","magauiyainc");
                    statement=connection.createStatement();
                    resultSet=statement.executeQuery("select * from book");
                    while(resultSet.next()){
                        output+=resultSet.getInt("id")+" | "+resultSet.getString("name")+" | "+resultSet.getString("author")+" | "+resultSet.getString("notes")+" | "+resultSet.getString("date")+"\n";
                    }
                    JOptionPane.showMessageDialog(null,output,"All books",JOptionPane.PLAIN_MESSAGE);
                }
                catch (Exception ex){
                    System.out.println(ex);
                }
            }
            else if(e.getSource()==button3){
                frame.dispose();
                Statistics statistics=new Statistics();
            }
    }
}
