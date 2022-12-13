import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import java.awt.GridLayout;
import java.awt.Font;



class VirtualTextBox extends JFrame implements ActionListener{
    JPanel display;
    JPanel keyboard;
    JLabel text;
    JScrollPane scrollPane;
    JButton buttons[]=new JButton[40];
    JTextArea textArea;
    boolean ul=false;
    VirtualTextBox(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        display=new JPanel();
        display.setLayout(null);
        display.setBounds(5, 5, 870, 200);
        display.setBackground(Color.CYAN);
        textArea= new JTextArea();
        textArea.setBounds(7,20,850,170);
        textArea.setFont(new Font("Sans Serif",Font.BOLD,25));
        textArea.setSelectedTextColor(Color.blue);
        textArea.setLineWrap(true);
      
        scrollPane=new JScrollPane(textArea);
        //scrollPane.setPreferredSize(new Dimension(850,170));
        scrollPane.setBounds(850,7,20,200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        display.add(scrollPane);
        display.add(textArea);
        text=new JLabel("Note Pad");
        text.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        text.setBounds(7,7,860,15);
        text.setFont(new Font("MV Boli",Font.BOLD,15));
        display.add(text);

        keyboard=new JPanel();
        keyboard.setLayout(new GridLayout(5,8,2,2));
        keyboard.setBackground(Color.orange);
        keyboard.setBounds(5,205, 870, 350);

       
     
        for(int i=0;i<40;i++){
            buttons[i]=new JButton();
            buttons[i].setBackground(Color.red);
            buttons[i].setFocusable(false);
            keyboard.add(buttons[i]);
            buttons[i].addActionListener(this);

        }
        for(int i=0;i<10;i++){
            String str=Integer.toString(i);
            buttons[i].setText(str);
        }

      
         for(int i=10;i<36;i++){
             char letter=(char)(i-10+97);
             buttons[i].setText(Character.toString(letter));
                    
         }
            
    
        
       
        buttons[37].setText("Capslock");
        buttons[36].setText("Space");
        buttons[38].setText("Delete");

        this.add(keyboard);
        this.add(display);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        for(int i=0;i<40;i++){
            String ltr="";
            
            if(e.getSource()==buttons[i]){
                if(buttons[i].getText().equals("Space")){
                    ltr=textArea.getText();
                    textArea.setText(ltr+" ");
                    helpingVerb();
                }
                else if(buttons[i].getText().equals("Capslock")){
                    //ul=false;
                    UpLow();
                }
                else if(buttons[i].getText().equals("Delete")){
                   textArea.setText("");

                }
              else{
                ltr=buttons[i].getText();
                ltr=textArea.getText()+ltr;
                 textArea.setText(ltr);
              }
            }
        }
        
    }
    private void helpingVerb() {
        String verb;
        verb=textArea.getText();
        String[] words=verb.split(" ");
        //System.out.println(verb.length());
        
        for(String w:words){
            if(w.equals("is")||w.equals("was")||w.equals("has")){
                String searchedWord=w;
                int pos1=textArea.getText().indexOf(searchedWord);
                int pos2=pos1+searchedWord.length();
                try{
                    textArea.getHighlighter().addHighlight(pos1, pos2, new DefaultHighlighter.DefaultHighlightPainter(Color.red));
                }
                catch(BadLocationException e){
                    e.printStackTrace();
                }
            }
        }
       
    }
    private void UpLow() {
        if(ul){
            for(int i=10;i<36;i++){
                char letter=(char)(i-10+97);
                buttons[i].setText(Character.toString(letter));
                
            }
            buttons[37].setBackground(Color.red);
            ul=false;
        }
        else{
            for(int i=10;i<36;i++){
                char letter=(char)(i-10+65);
                buttons[i].setText(Character.toString(letter));
                
            }
            buttons[37].setBackground(Color.green);
            ul=true;
        }
    }
}

class VirtualKeyBoard{
    public static void main(String args[]){
        new VirtualTextBox();
    }
}