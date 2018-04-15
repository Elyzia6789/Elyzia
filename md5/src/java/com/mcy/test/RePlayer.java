package com.mcy.test;

import com.ibm.media.bean.multiplayer.MultiPlayerBean;
import com.sun.org.apache.xml.internal.resolver.helpers.FileURL;

import javax.media.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;

/**
 * Description: 使用jmf编写一个媒体播发器
 * All Rights Reserved
 *
 * @Author Elyzia
 * Date:2018/04/02
 * @Version 1.0
 */

// 运行时总是提示 cannot find a player，不知道什么原因，Manager.class及其相关类都是在java1.3中，不能下载，所以不能调试
//  所以放弃这个类
public class RePlayer {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MultiPlayer("Open Source");
                System.out.println("create a new MediaPlayer");
            }
        });
    }


}
class MultiPlayer extends JFrame implements ControllerListener,ItemListener{
    private Player player;
    private String currentDirectory;
    private String selectFile;
    private String currentFile;
    private String url;
    private InputDialog dialog;
    private Component vc,cc,gcc;

    private boolean loop = false;
    private boolean first = true;
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 450;

    public MultiPlayer(String title){
        super(title);

        //todo 不知道什么时候 调用该方法
        addWindowFocusListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                dispose();
                System.out.println("windowClosing");
            }
            public void windowClosed(WindowEvent event){
                if(player != null)
                    player.close();
                System.out.println("windowClosed");
                System.exit(0);
            }
        });
        setupMenu();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);

    }

    public void setupMenu(){
        Menu fileMenu = new Menu("File");
        MenuItem openItem = new MenuItem("Open");
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                FileDialog fd = new FileDialog(new Frame(),"Open Media File",FileDialog.LOAD);
                fd.setDirectory(currentDirectory);
                fd.show();
                if(fd.getFile() == null)
                    return;
                selectFile = fd.getFile();
                currentDirectory = fd.getDirectory();
                currentFile = currentDirectory + selectFile;
                File tt = new File(currentFile);

                //add new item into list menu when openning a local media file
                MenuItem menuItem = new MenuItem(selectFile);
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        url = "file:" + selectFile;
                        System.out.println("Open File:" + url);
                        setPlayer(url);
                    }
                });

                MenuBar menuBar = getMenuBar();
                Menu listMenu = menuBar.getMenu(3);
                listMenu.add(menuItem);
                setPlayer(currentFile);

            }
        });

        fileMenu.add(openItem);
        fileMenu.addSeparator();

        //loop play mode
        CheckboxMenuItem loopItem = new CheckboxMenuItem("Loop",false);
        loopItem.addItemListener(this);
        fileMenu.add(loopItem);
        fileMenu.addSeparator();

        //quit player
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                return;
            }
        });
        fileMenu.add(quitItem);

        //URL input menu
        Menu urlMenu = new Menu("URL");
        MenuItem urlItem = new MenuItem("Input");
        urlItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dialog == null)
                    dialog = new InputDialog(MultiPlayer.this);
                url = dialog.getUrl();
                System.out.println("open url:" + url);
                setPlayer(url);

                //add a new item into list menu when openning a url
                MenuItem menuItem = new MenuItem(url);
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setPlayer(url);
                    }
                });
                MenuBar menuBar = new MenuBar();
                Menu listMenu = menuBar.getMenu(3);
                listMenu.add(menuItem);
            }
        });

        urlMenu.add(urlItem);

        //Player control menu
        Menu controlMenu = new Menu("Control");
        MenuItem playItem = new MenuItem("Play");
        playItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player != null)
                    player.start();
                return;
            }
        });
        controlMenu.add(playItem);
        controlMenu.addSeparator();

        MenuItem pauseItem = new MenuItem("Pause");
        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player != null)
                    player.stop();
                return;
            }
        });
        controlMenu.add(pauseItem);
        controlMenu.addSeparator();

        MenuItem stopItem = new MenuItem("Stop");
        stopItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(player != null){
                    player.stop();
                    player.setMediaTime(new Time(0));
                }
                return;
            }
        });
        controlMenu.add(stopItem);

        //Player list menu
        Menu listMenu = new Menu("List");
        MenuBar menuBar = new MenuBar();
        menuBar.add(fileMenu);
        menuBar.add(urlMenu);
        menuBar.add(controlMenu);
        menuBar.add(listMenu);
        setMenuBar(menuBar);
    }
    public void setPlayer(String url){
        if(player != null)
            player.close();
        try{
            File ff = new File(url);
            URL tt = ff.toURI().toURL();
            player = Manager.createPlayer(tt);

        }catch (java.io.IOException e2){
            System.out.println(e2);
            return;
        }catch (javax.media.NoPlayerException e2){
            System.out.println(e2);
            System.out.println("Can't find the player!");
            return;
        }catch (Exception e){
            System.out.println(e);
        }
        if(player == null) {
            System.out.println("Can't create the player!");
            return;

        }
        setTitle(url);
        player.addControllerListener(MultiPlayer.this);
        player.prefetch();

    }

    public void controllerUpdate(ControllerEvent e){
        Container tainer = getContentPane();
        if(e instanceof ControllerEvent) {
            if (vc != null) {
                remove(vc);
                vc = null;
            }
            if(cc != null){
                remove(cc);
                cc = null;
            }
            return;
        }
        if(e instanceof EndOfMediaEvent){
            player.setMediaTime(new Time(0));
            if(loop){
                player.start();
            }
            return;

        }
        if(e instanceof PrefetchCompleteEvent){
            player.start();
            return;
        }
        if(e instanceof RealizeCompleteEvent){
            vc = player.getVisualComponent();
            if(vc != null){
                pack();
                return;
            }else {
                setSize(300,75);
                setVisible(true);
            }

        }
    }

    public void itemStateChanged(ItemEvent e){
            loop = !loop;
    }

    public void update(Graphics g){
        paint(g);
    }
    public void paint(Graphics g){
        super.paint(g);
        if(first){
            int width = getSize().width;
            int height = getSize().height;
            g.setColor(Color.magenta);
            g.fillRect(0,0,width,height);

            Font font =  new Font("DialofInput",Font.BOLD,18);
            g.setFont(font);

            FontMetrics fontMetrics = g.getFontMetrics();
            int swidth = fontMetrics.stringWidth("Repro Player");

            g.setColor(Color.white);
            g.drawString("Repro Player",(width - swidth)/2,(height+getInsets().top)/2);
        }
    }
}

class InputDialog extends JDialog{
    private String urlValue;
    private JTextField inputField;
    private static final int DIALOG_WIDTH = 250;
    private static final int DIALOG_HEIGHT = 150;

    public InputDialog(JFrame owner){
        super(owner,"Input Dialog",true);

        //todo 前面定义了这个变量，这里还可以使用final,这是第一次见这种用法
        final JTextField inputField = new JTextField();
        add(inputField,BorderLayout.NORTH);

        JButton ok = new JButton("ok");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                urlValue = inputField.getText().toString();
                inputField.setVisible(false);
            }
        });

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel,BorderLayout.SOUTH);
        setSize(DIALOG_WIDTH,DIALOG_HEIGHT);
        setVisible(true);

    }

    public String getUrl(){
        return urlValue;
    }
}
