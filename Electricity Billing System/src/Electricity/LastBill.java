
package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class LastBill extends JFrame implements ActionListener {
    
    JLabel l1;
    JTextArea t1, t2;
    JButton b1;
    JPanel p;
    
    LastBill(){
        
        setSize(500, 900);
        setLayout(new BorderLayout());
        
        p = new JPanel();
        
        l1 = new JLabel("Generate Bill");
        p.add(l1);
        
        t2 = new JTextArea(50, 15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif", Font.ITALIC, 18));
        p.add(t2);
        
        b1 = new JButton("Generate Bill");
        b1.addActionListener(this);
        
        add(p, "North");
        add(jsp, "Center");
        add(b1, "South");
        
        setLocation(350, 40);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = " + t2.getSelectedText());
            if(rs.next()){
                t1.append("\n    Customer Name:"+rs.getString("name"));
                t1.append("\n    Meter Number:  "+rs.getString("meter"));
                t1.append("\n    Address:            "+rs.getString("address"));
                t1.append("\n    State:                 "+rs.getString("state"));
                t1.append("\n    City:                   "+rs.getString("city"));
                t1.append("\n    Email:                "+rs.getString("email"));
                t1.append("\n    Phone Number  "+rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------");
                t1.append("\n");
            }
            t1.append("Details of the last Bills\n\n\n");
            rs = c.s.executeQuery("select * from bill where meter="+t2.getSelectedText());
            
            while(rs.next()){
                t1.append("       "+ rs.getString("month") + "           " +rs.getString("amount") + "\n");
            }
        }catch(Exception e){}
    }
    
    public static void main(String [] args){
        new LastBill().setVisible(true);
    }
}
