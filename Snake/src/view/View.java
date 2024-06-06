package view;

import model.Model;

public class View {
    public static void main(String[] args) {
        Model model = new Model(10);
        model.display();
        
        while(true) {
            model.update();
            model.display();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}