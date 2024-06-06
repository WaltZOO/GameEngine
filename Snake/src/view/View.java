package view;

import model.Apple;
import model.Model;

public class View {
    public static void main(String[] args) {
        Model model = new Model(20);
        model.display();
        
        while(true) {
            model.update();
            model.display();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}