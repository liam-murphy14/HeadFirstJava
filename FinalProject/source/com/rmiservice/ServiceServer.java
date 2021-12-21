package com.rmiservice;

import java.rmi.*;

public interface ServiceServer extends Remote{
    String[] getServiceList() throws RemoteException;
    Service getService(String ServiceKey) throws RemoteException;
}