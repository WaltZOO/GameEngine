package view;

import model.Model;

public class View {
    public static void main(String[] args) {
        Model model = new Model(20);
        
        while(true) {
            System.out.println(model.toString());
            model.update();
            model.display();
        }
    }
}