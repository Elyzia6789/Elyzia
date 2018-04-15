
import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2018/4/8.
 */
public class HelloJApplet extends JApplet {
    private String hw_text;
    public void init(){
        hw_text = "hello JApplet";
    }
    public void paint(Graphics g){
        g.drawString(hw_text,30,30);
    }
}

// <applet code=HelloJApplet.class width=300 height=200  ></applet>
