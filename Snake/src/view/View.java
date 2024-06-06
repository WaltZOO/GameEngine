package view;

import model.Model;

public class View {
    public static void main(String[] args) {
        Model model = new Model(3);
        
        while(true) {
            model.update();
            model.display();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}