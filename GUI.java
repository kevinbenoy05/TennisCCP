import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUI {
    private static Queue history;
    private static JFrame frame;
    private static JPanel panel;
    private static JButton submit, back;
    private static JTextField address, lat, lo;
    private static JLabel addressLabel, coordinatesLabel, welcome, instructions;
    private static JTextArea results;
    private static final Font  f1  = new Font(Font.MONOSPACED, Font.BOLD,  15);
    private static String resultsString = "The tennis court you searched will be playable in: ";
    public static void main(String[] args) {
        history = new Queue();
        /*Set up main frame*/
        frame = new JFrame("Tennis Court Condition Predictor");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        /*Component Configuration*/
        panel = new JPanel();
        panel.setLayout(null); //https://stackoverflow.com/questions/12119327/placing-a-jlabel-at-a-specific-x-y-coordinate-on-a-jpanel
        panel.setBackground(new Color(92, 148, 237));
        frame.add(panel);
        frame.setVisible(true);
        panel.setFont(f1);
        addressLabel = new JLabel("Address:");
        coordinatesLabel = new JLabel("Coordinates:");
        address = new JTextField();
        lat = new JTextField();
        lo = new JTextField();
        results = new JTextArea();
        welcome  = new JLabel();
        instructions = new JLabel();
        //Labels
        addressLabel.setBounds(10, 170, 50, 25);
        coordinatesLabel.setBounds(10, 210, 75, 25);
        //Text Feilds/Areas
        welcome.setBounds(35, 20, 500, 75);
        welcome.setText("Tennis Court Condition Predictor");
        welcome.setFont(new Font("Arial", Font.PLAIN, 30));
        instructions.setBounds(35, 35, 400, 200);
        instructions.setText("<html> Instructions: Enter the address or coordinates of the tennis court you are searching for. \n It will return a short and long estimate of how long it will take for it to be playable. \nEnter the values, and click submit! </html>"); //https://stackoverflow.com/questions/2420742/make-a-jlabel-wrap-its-text-by-setting-a-max-width
        address.setBounds(70, 170, 300, 25);
        lat.setBounds(100, 210, 75, 25);
        results.setBounds(100, 170, 300, 25);
        results.setText(resultsString);
        results.setEditable(false);
        lat.setText("Latitude");
        lo.setBounds(200, 210, 75, 25);
        lo.setText("Longitude");
        //Buttons
        submit = new JButton("Submit");
        submit.setBounds(190, 300, 100, 50);
        submit.setFont(f1);
        back = new JButton("Go Back");
        back.setBounds(200, 300, 100, 50);
        back.setVisible(false);
        panel.add(welcome);
        panel.add(lo);
        panel.add(instructions);
        panel.add(addressLabel);
        panel.add(coordinatesLabel);
        panel.add(address);
        panel.add(lat);
        panel.add(submit);
        panel.add(back);
        panel.add(results);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String addressString;
                Double latittude, longitude;
                if(!address.getText().isEmpty()){
                    addressString = address.getText();
                    history.add(addressString);
                    //PERFORM CALCULATION HERE
                }
                else if(isNumber(lat.getText()) && isNumber(lo.getText())){
                    latittude = Double.parseDouble(lat.getText());
                    longitude = Double.parseDouble(lo.getText());
                    //PERFORM CALCULATION HERE
                }
                else{
                    address.setText("");
                    lat.setText("");
                    lo.setText("");
                    homeScreen();
                }
                
            }
        });
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                homeScreen();
            }
        });
    }
    public static void homeScreen(){
        welcome.setVisible(true);
        address.setVisible(true);
        instructions.setVisible(true);
        lat.setVisible(true);
        lo.setVisible(true);
        addressLabel.setVisible(true);
        coordinatesLabel.setVisible(true);
        submit.setVisible(true);
        back.setVisible(false);
        results.setVisible(false);
    }
    public static void resultsScreen(){
        welcome.setVisible(false);
        address.setVisible(false);
        instructions.setVisible(false);
        lat.setVisible(false);
        lo.setVisible(false);
        addressLabel.setVisible(false);
        coordinatesLabel.setVisible(false);
        submit.setVisible(false);
        back.setVisible(true);
        results.setVisible(true);
    }
    public static boolean isNumber(String string){ //https://stackabuse.com/java-check-if-string-is-a-number/
        if(string == null || string.equals("")){
            return false;
        }
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
     }
}
