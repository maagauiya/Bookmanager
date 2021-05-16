package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Add implements ActionListener {
    String connectionUrl="jdbc:postgresql://localhost:5432/Bookmanager";
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement=null;
    JFrame frame=new JFrame();
    JButton button=new JButton("Main");
    JButton button2=new JButton("Submit");
    JLabel title=new JLabel("You can add books here");
    JLabel label1=new JLabel("Name:");
    JLabel label2=new JLabel("Author:");
    JLabel label3=new JLabel("Notes:");
    private JTextField name=new JTextField("",2);
    private JTextField author=new JTextField("",2);
    private JTextField notes=new JTextField("",2);

    public Add(){
        frame.setTitle("Bookmanager");
        button.setBounds(10,10 ,70,20);
        button.setFocusable(false);
        button.addActionListener(this);
        frame.add(button);

        title.setBounds(50,25,500,40);
        title.setFont(new Font(null,Font.PLAIN,20));
        frame.add(title);

        label1.setBounds(15,60,100,40);
        label1.setFont(new Font(null,Font.PLAIN,17));
        frame.add(label1);
        name.setBounds(100,70,200,25);
        frame.add(name);

        label2.setBounds(15,100,100,40);
        label2.setFont(new Font(null,Font.PLAIN,17));
        frame.add(label2);
        author.setBounds(100,110,200,25);
        frame.add(author);

        label3.setBounds(15,140,100,40);
        label3.setFont(new Font(null,Font.PLAIN,17));
        frame.add(label3);
        notes.setBounds(100,150,200,25);
        frame.add(notes);

        button2.setBounds(100,190 ,200,40);
        button2.setFocusable(false);
        button2.addActionListener(this);
        frame.add(button2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        ImageIcon image=new ImageIcon("logobook.png");
        frame.setIconImage(image.getImage());
    }



    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==button){
                frame.dispose();
                Mainframe mainframe=new Mainframe();
            }else if(e.getSource()==button2){
                String output="Successfully";
                int id=0;
                try {
                    boolean state=false;
                    String date="null";
                     String namel=name.getText();
                    String authorl=author.getText();
                    String notesl=notes.getText();
                    Class.forName("org.postgresql.Driver");
                    connection= DriverManager.getConnection(connectionUrl,"postgres","magauiyainc");
                    statement=connection.createStatement();
                    resultSet=statement.executeQuery("select * from book");
                    while(resultSet.next()){
                        id=resultSet.getInt("id");
                    }
                    resultSet=statement.executeQuery("insert into book(id,name,author,notes,state,date) values (" + ++id + ",'" + namel + "','" + authorl + "','" + notesl + "',"+state+","+date+")");
                    JOptionPane.showMessageDialog(null,output,"Output",JOptionPane.PLAIN_MESSAGE);
                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
    }
}
