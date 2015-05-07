/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.data;

import java.util.Comparator;

/**
 *
 * @author Work
 * @param <Player>
 */
public class PlayerComparator implements Comparator<Player>{

   @Override
            public int compare(Player p1, Player p2) {
                String n1 = p1.getLastName();
                String n2 = p2.getLastName();
                if (n1.compareTo(n2) < 0)
                    return -1;
                else                 if (n1.compareTo(n2) > 0)
                      return 1;
                else{
                    n1 = p1.getFirstName();
                    n2 = p2.getFirstName();
                    if (n1.compareTo(n2) < 0)
                        return -1;
                    else if (n1.compareTo(n2) > 0)
                        return 1;
                    else return 0;
                }
            }

    
}
