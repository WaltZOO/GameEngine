package view;

import model.Model;

public class View {
    public static void main(String[] args) {
        Model model = new Model(20);
        
        while(true) {
            model.update();
            model.display();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}