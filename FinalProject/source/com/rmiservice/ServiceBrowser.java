package com.rmiservice;

import java.awt.*;
import javax.swing.*;
import java.rmi.*;
import java.awt.event.*;

public class ServiceBrowser{

    private JPanel mainPanel;
    private JComboBox<String> serviceList;
    private ServiceServer server;

    public void buildGui(){
        JFrame frame = new JFrame("RMI Browser");
        mainPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        String[] services = getServiceList();
        serviceList = new JComboBox<String>(services);

        frame.getContentPane().add(BorderLayout.NORTH, serviceList);

        serviceList.addActionListener(new MyListListener());

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void loadService(String serviceSelection){
        try{
            Service svc = server.getService(serviceSelection);

            mainPanel.removeAll();
            mainPanel.add(svc.getGuiPanel());
            mainPanel.validate();
            mainPanel.repaint();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String[] getServiceList(){
        Object o = null;
        String[] servs = null;

        try{
            o = Naming.lookup("rmi://127.0.0.1/ServiceServer");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        server = (ServiceServer) o;

        try{
            servs = server.getServiceList();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return servs;
    }

    private class MyListListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String selection = (String) serviceList.getSelectedItem();
            loadService(selection);
        }
    }

    public static void main(String[] args){
        new ServiceBrowser().buildGui();
    }
}