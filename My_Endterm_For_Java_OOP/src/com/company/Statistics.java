package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;

public class Statistics implements ActionListener {
    String connectionUrl="jdbc:postgresql://localhost:5432/Bookmanager";
    Connection connection=null;
    ResultSet resultSet=null;
    Statement statement=null;
     JFrame frame=new JFrame();
    String[] month={"01","02","03","04","05","06","07","08","09","10","11","12"};
    String[] years={"2015","2016","2017","2018","2019","2020","2021","2022","2023"};
    private JButton button=new JButton("Main");
    private JLabel filter=new JLabel("Filter");
    private JButton goal=new JButton("Goal");
    private JLabel id=new JLabel("ID:");
    private JTextField idtext=new JTextField();
    private JButton idbutton=new JButton("Finished");
    private JButton show=new JButton("Show");
    private JRadioButton radio1=new JRadioButton("Finished");
    private JRadioButton radio2=new JRadioButton("Not finished");
    private JLabel changegoal=new JLabel("Change goal");
    private JTextField goaltext=new JTextField();
    private JButton change=new JButton("Change");
    private JLabel bydate=new JLabel("Show by date");
    private JLabel from =new JLabel("From:");
    private JLabel to=new JLabel("To:");
    private JComboBox frommonth =new JComboBox(month);
    private JComboBox fromyear=new JComboBox(years);
    private JComboBox tomonth =new JComboBox(month);
    private JComboBox toyear=new JComboBox(years);
    private JButton showbydate=new JButton("Show");
    public Statistics() {
        frame.setTitle("Bookmanager");
        button.setBounds(10,5 ,70,20);
        button.setFocusable(false);
        button.addActionListener(this);
        frame.add(button);

        goal.setBounds(260,5,70,20);
        goal.setFocusable(false);
        goal.addActionListener(this);
        goal.setFont(new Font(null,Font.BOLD,16));
        frame.add(goal);


        filter.setBounds(135,75,200,30);
        filter.setFont(new Font(null,Font.BOLD,17));
        frame.add(filter);

        radio1.setBounds(40,105,90,30);
        radio1.setFont(new Font(null,Font.BOLD,13));
        frame.add(radio1);
        radio2.setBounds(170,105,130,30);
        radio2.setFont(new Font(null,Font.BOLD,13));
        frame.add(radio2);

        show.setBounds(105,140,100,20);
        show.setFocusable(false);
        show.addActionListener(this);
        show.setFont(new Font(null,Font.BOLD,17));
        frame.add(show);

        id.setBounds(10,185,50,20);
        id.setFont(new Font(null,Font.BOLD,17));
        frame.add(id);
        idtext.setBounds(40,188,130,17);
        frame.add(idtext);
        idbutton.setBounds(190,188,130,17);
        idbutton.setFocusable(false);
        idbutton.addActionListener(this);
        idbutton.setFont(new Font(null,Font.BOLD,17));
        frame.add(idbutton);

        changegoal.setBounds(105,210,130,32);
        changegoal.setFont(new Font(null,Font.BOLD,17));
        frame.add(changegoal);
        goaltext.setBounds(95,255,120,27);
        frame.add(goaltext);
        change.setBounds(95,285,120,20);
        idbutton.setFocusable(false);
        idbutton.addActionListener(this);
        change.setFont(new Font(null,Font.BOLD,14));
        change.setFocusable(false);
        change.addActionListener(this);
        frame.add(change);

        bydate.setBounds(95,320,120,20);
        bydate.setFont(new Font(null,Font.BOLD,17));
        frame.add(bydate);
        from.setBounds(10,345,50,20);
        from.setFont(new Font(null,Font.BOLD,17));
        frame.add(from);
        frommonth.addActionListener(this);
        frommonth.setBounds(65,347,50,20);
        frame.add(frommonth);
        fromyear.addActionListener(this);
        fromyear.setBounds(130,347,60,20);
        frame.add(fromyear);

        to.setBounds(10,375,50,20);
        to.setFont(new Font(null,Font.BOLD,17));
        frame.add(to);
        tomonth.addActionListener(this);
        tomonth.setBounds(65,375,50,20);
        frame.add(tomonth);
        toyear.addActionListener(this);
        toyear.setBounds(130,375,60,20);
        frame.add(toyear);
        showbydate.setBounds(200,360,70,20);
        showbydate.setFocusable(false);
        showbydate.addActionListener(this);
        frame.add(showbydate);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350,500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
        ImageIcon image=new ImageIcon("logobook.png");
        frame.setIconImage(image.getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
             if(e.getSource()==button){
                 frame.dispose();
                 Mainframe mainframe=new Mainframe();
             }
             else if(e.getSource()==show){
                     if(radio1.isSelected()){
                         try {
                             String output="";
                             Class.forName("org.postgresql.Driver");
                             connection= DriverManager.getConnection(connectionUrl,"postgres","magauiyainc");
                             statement=connection.createStatement();
                             resultSet=statement.executeQuery("select * from book where state=true");
                             while(resultSet.next()){
                                 output+=resultSet.getInt("id")+" | "+resultSet.getString("name")+" | "+resultSet.getString("author")+resultSet.getString("notes")+" | "+resultSet.getString("date")+"\n";
                             }
                             JOptionPane.showMessageDialog(null,output,"Finished books",JOptionPane.PLAIN_MESSAGE);
                         }
                         catch (Exception ex){
                             System.out.println(ex);
                         }
                     }
                     else if(radio2.isSelected()){
                     try {
                         String output="";
                         Class.forName("org.postgresql.Driver");
                         connection= DriverManager.getConnection(connectionUrl,"postgres","magauiyainc");
                         statement=connection.createStatement();
                         resultSet=statement.executeQuery("select * from book where state=false");
                         while(resultSet.next()){

                             output+=resultSet.getInt("id")+" | "+resultSet.getString("name")+" | "+resultSet.getString("author")+resultSet.getString("notes")+" | "+resultSet.getString("date")+"\n";
                         }
                         JOptionPane.showMessageDialog(null,output,"Not finished books",JOptionPane.PLAIN_MESSAGE);
                     }
                     catch (Exception ex){
                         System.out.println(ex);
                     }
                 }
             }
             else if(e.getSource()==idbutton){
                 try {
                     Class.forName("org.postgresql.Driver");
                     connection= DriverManager.getConnection(connectionUrl,"postgres","magauiyainc");
                     statement=connection.createStatement();
                     resultSet=statement.executeQuery("update book set state =true,date="+"'"+new Date()+"'" +" where id="+idtext.getText()+";");
                 }
                 catch (Exception ex){
                     System.out.println(ex);
                 }
             }
             else if(e.getSource()==change){
                 try {
                     Class.forName("org.postgresql.Driver");
                     connection= DriverManager.getConnection(connectionUrl,"postgres","magauiyainc");
                     statement=connection.createStatement();
                     resultSet=statement.executeQuery("update statistics set goal="+goaltext.getText()+";");
                 }
                 catch (Exception ex){
                     System.out.println(ex);
                 }
             }
             else if(e.getSource()==goal){
                 int goal=0,now=0;
                 try {
                     Class.forName("org.postgresql.Driver");
                     connection= DriverManager.getConnection(connectionUrl,"postgres","magauiyainc");
                     statement=connection.createStatement();
                     resultSet=statement.executeQuery("select * from book where state=true");
                     while (resultSet.next()){
                         now++;
                     }
                     resultSet=statement.executeQuery("select * from statistics;");
                     while (resultSet.next()){
                         goal=resultSet.getInt("goal");
                     }

                 }
                 catch (Exception ex){
                     System.out.println(ex);
                 }
                 JOptionPane.showMessageDialog(null,now+"/"+goal,"Finished",JOptionPane.PLAIN_MESSAGE);
             }
             else if(e.getSource()==showbydate){
                 try {
                     String output="";
                     Class.forName("org.postgresql.Driver");
                     connection= DriverManager.getConnection(connectionUrl,"postgres","magauiyainc");
                     statement=connection.createStatement();
                     resultSet=statement.executeQuery("select * from book where date between "+"' "+fromyear.getSelectedItem()+"-"+frommonth.getSelectedItem()+"-01' and "+"' "+toyear.getSelectedItem()+"-"+tomonth.getSelectedItem()+"-31';");
                     while(resultSet.next()){

                         output+=resultSet.getInt("id")+" | "+resultSet.getString("name")+" | "+resultSet.getString("author")+resultSet.getString("notes")+" | "+resultSet.getString("date")+"\n";
                     }

                     JOptionPane.showMessageDialog(null,output,"Finished books",JOptionPane.PLAIN_MESSAGE);
                 }
                 catch (Exception ex){
                     System.out.println(ex);
                 }
             }

    }
}
