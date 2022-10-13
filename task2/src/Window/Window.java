package Window;

import javax.swing.*;

public class Window {


    public final int tileSize = 64;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    JFrame window = new JFrame();

    public void createAll(){
        createWindow();
        createButtons();

    }

    public void createButtons(){

        window.add(button("Button1", tileSize*2,tileSize,tileSize,tileSize*3));
        window.add(button("Button2", tileSize*2,tileSize,tileSize,tileSize*5));
        window.add(button("Button3", tileSize*2,tileSize,tileSize,tileSize*7));

    }
    public static JButton button(String name, int size1, int size2, int loc1, int loc2) {
        JButton l = new JButton(name);
        l.setSize(size1, size2);
        l.setLocation(loc1, loc2);
        return l;
    }


    public void createWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Gym");
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setSize(screenWidth,screenHeight);
    }
}
