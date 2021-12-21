package com.rmiservice;

import javax.swing.*;
import java.io.*;

public interface Service extends Serializable{
    JPanel getGuiPanel();
}