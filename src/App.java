import javax.swing.*;
import javax.sound.sampled.*;
import java.io.File;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements ActionListener{
    static JButton botao1;
    static JButton botao2;
    static JFrame frame;
    static boolean iniciar = false;
    static int frame_atual = 1;
    public static void main(String[] args) throws Exception {
        frame = new JFrame();
        Font fonte = new Font("serif", Font.TYPE1_FONT, 10);
        frame.setResizable(false);
        frame.setSize(350, 350);
        App app = new App();
        botao1 = new JButton();
        botao1.addActionListener(app);
        botao1.setBounds(115, 60, 120, 70);
        botao2 = new JButton();
        botao2.addActionListener(app);
        botao2.setBounds(115, 160, 120, 70);
        botao1.setText("É o Gabriel?");
        // botao1.setFont(fonte);
        botao2.setText("Não é o Gabriel?");
        botao2.setFont(fonte);
        frame.add(botao1);
        frame.add(botao2);
        frame.setTitle("Gabriel Program");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==botao1){
            JLabel video = new JLabel();
            video.setOpaque(true);
            video.setBounds(50, 100, 256, 144);
            frame.getContentPane().removeAll();
            frame.add(video);
            String audiopath = "src/audio/audio.wav";
            PlayMusic(audiopath);
            Timer timer = new Timer(100, new ActionListener() {
                public void actionPerformed(ActionEvent v){ 
                    if (frame_atual == 295){
                        System.exit(0);
                    }
                    String frame_f = String.format("%03d", frame_atual);
                    String frame_a = "src/frames/" + frame_f + ".png";
                    System.out.println(frame_a);
                    ImageIcon videoframe = new ImageIcon(frame_a);
                    video.setIcon(videoframe);
                    
                    frame.repaint();
                    frame.revalidate();
                    frame_atual++;
                }
            });
            timer.start();
        }
        else if (e.getSource()==botao2){
            System.exit(0);
        }
    }

    private void PlayMusic(String audiopath) {
        try {
            File musicpath = new File(audiopath);
            if (musicpath.exists()){
                AudioInputStream audioinput = AudioSystem.getAudioInputStream(musicpath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioinput);
                clip.start();
            }
            else{
                System.out.println("Musica não encontrada!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }   
}
