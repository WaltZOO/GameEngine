package view;

public Class View {
    
    public static void main(String[] args) {
        Model model = new Model(20);
        
        while(true) {
            System.out.println(model.toString());
            model.update();
        }
    }
}
