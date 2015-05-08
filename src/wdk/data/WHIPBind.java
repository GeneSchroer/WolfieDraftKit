/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.text.DecimalFormat;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Work
 */
public class WHIPBind extends DoubleBinding{

    IntegerProperty totalBB;
    IntegerProperty totalHP;
    DoubleProperty totalIP;
    
         WHIPBind(IntegerProperty totalBB, IntegerProperty totalHP, DoubleProperty totalIP)
            {
                this.totalBB = totalBB;
                this.totalHP = totalHP;
                this.totalIP = totalIP;
                super.bind(this.totalBB, this.totalHP, this.totalIP);
            }
            protected double computeValue() {
                if(totalIP.getValue() == 0)
                    return 0;
                Double w = Double.parseDouble(DecimalFormat.getInstance().format((totalBB.getValue() + totalHP.getValue())/totalIP.getValue()));
                return w;
            }
        }

    
    

