
import javax.swing.*;
import java.awt.*;

/**
 * Description:
 * All Rights Reserved
 *
 * @Author 马春雨(cy.ma@zuche.com)
 * Date:2018/04/08
 * @Version 1.0
 */
public class Polygon7 extends JApplet implements Runnable  {
    private int x[] = {4,4,46,46,139,139};
    private int y[] = {-1,-158,-140,-28,26,76};
    private Thread timer = null;
    private int degree = 0;
    private Image buffer;

//    public void init(){
//       start();
//    }


    private Polygon poly = new Polygon(x,y,x.length);
    public void paint(Graphics gg){
        buffer = createImage(1000,600); //创建缓冲画像 double buffer
        Graphics2D g= (Graphics2D)buffer.getGraphics(); //提取缓冲画笔 g
        super.paint(g); //清屏
        g.translate(200,200); //坐标轴平移
        g.rotate(Math.PI/180 * degree++); //坐标轴顺时针旋转
        g.setColor(Color.green);
        for(int j =0;j<3;j++){
            g.fillPolygon(poly);
            g.rotate(Math.PI/180*120); // 坐标轴顺时针旋转
        }
        g.drawString("进入paint",30,30);
        g.drawImage(buffer,0,0,this); //将缓冲影像显示于屏幕


    }
    public void start(){
        if(timer == null){
            timer = new Thread(this);
            System.out.println("进入start");
            timer.start();
        }
    }
    public void stop(){

        timer = null;
    }

    public void run(){
        while (timer != null){
            try{
                Thread.sleep(200);
            }catch (InterruptedException e){
                repaint();
            }
            timer = null;
        }
    }
    public void update(Graphics g){ //必须的，旨在防止闪烁

        paint(g);
    }

//    public static void main(String[] args) {
//        Polygon7 polygon7 = new Polygon7();
//        polygon7.start();
//    }
}

//<applet code=Polygon7.class width=300 height=200 ></applet>
