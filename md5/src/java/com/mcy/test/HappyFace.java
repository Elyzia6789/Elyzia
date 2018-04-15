package com.mcy.test;

import javax.swing.*;
import java.awt.*;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author Âí´ºÓê(cy.ma@zuche.com)
 * Date:2018/04/14
 * @Version 1.0
 */
public class HappyFace extends JApplet {

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.drawOval(100,50,200,200);
        g.fillOval(155,100,10,20);
        g.fillOval(230,100,10,20);
        g.drawArc(150,160,100,50,180,180);

    }
}
