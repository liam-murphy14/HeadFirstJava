package com.rmiservice;

import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer{

    private static final long serialVersionUID = -6816116100429371551L;
    private HashMap<String, Service> serviceList;

    public ServiceServerImpl() throws RemoteException{
        setUpServices();
    }

    private void setUpServices(){
        serviceList = new HashMap<String, Service>();
        serviceList.put("Dice Rolling Service", new DiceService());
        serviceList.put("Day of the Week Service", new DayOfTheWeekService());
        serviceList.put("Visual Music Service", new MiniMusicService());
    }

    public String[] getServiceList(){
        System.out.println("in remote");
        Object[] obs = serviceList.keySet().toArray();
        String[] ss = new String[obs.length];
        for(int i = 0; i < obs.length; i++){
            ss[i] = (String) (obs[i]);
        }
        return ss;
    }

    public Service getService(String serviceKey){
        Service serv = serviceList.get(serviceKey);
        return serv;
    }

    public static void main(String[] args){
        try{
            Naming.rebind("ServiceServer", new ServiceServerImpl());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Remote service is running");
    }
}