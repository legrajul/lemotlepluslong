import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class LongestWord {
	private JFrame frame;
	private JButton consonant_button;
	private JButton vowel_button;
	private JButton start_button;
	private JButton reinit_button;
	private LongestWordModel model;
	private JPanel sequence_panel;
	private Timer timer;
	private JLabel left_time;
	
	public static final int CHAR_SIZE = 300;
	public static final int TIME = 120;
	
	public LongestWord() {
		createModel();
		createView();
		placeComponents();
		createController();
		updateView();
	}

	private void placeComponents() {
		sequence_panel = new JPanel();
		{
			for (char c : model.getSequence()) {
				JLabel l = new JLabel(String.valueOf(c));
				l.setFont(new Font("Inconsolata", Font.PLAIN, CHAR_SIZE));
				sequence_panel.add(l);
			}
		}
		frame.add(sequence_panel, BorderLayout.CENTER);

		JPanel p = new JPanel(new GridLayout(0, 1));
		{
			p.add(consonant_button);
			p.add(vowel_button);
			p.add(start_button);
			p.add(reinit_button);
		}
		frame.add(p, BorderLayout.EAST);
		
		p = new JPanel(); {
			p.add(left_time);
		}
		frame.add(p, BorderLayout.SOUTH);
	}

	private void createView() {
		frame = new JFrame("Le mot le plus long");
		consonant_button = new JButton("Consonne");
		vowel_button = new JButton("Voyelle");
		start_button = new JButton("GO!");
		reinit_button = new JButton("Recommencer");
		left_time = new JLabel("");
		left_time.setText(String.format("%02d:%02d", ((int) TIME/60), ((int) TIME % 60)));
		left_time.setFont(new Font("Inconsolata", Font.PLAIN, CHAR_SIZE));
	}

	private void createModel() {
		model = new LongestWordModel(10);
	}

	
	private void updateView() {
		JPanel p = new JPanel();
		sequence_panel.removeAll();
		{
			for (char c : model.getSequence()) {
				JLabel l = new JLabel(String.valueOf(c));
				l.setFont(new Font("Inconsolata", Font.PLAIN, CHAR_SIZE));
				
				sequence_panel.add(l);
			}
		}
		if (model.isReady()) {
			consonant_button.setEnabled(false);
			vowel_button.setEnabled(false);
			start_button.setEnabled(true);
			reinit_button.setEnabled(true);
		} else {
			consonant_button.setEnabled(true);
			vowel_button.setEnabled(true);
			start_button.setEnabled(false);
			reinit_button.setEnabled(false);
		}
		
		frame.pack();
	}
	
	private void createController() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		consonant_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.generateConsonant();
				updateView();
			}
		});

		vowel_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.generateVowel();
				updateView();
			}
		});
		
		reinit_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.reinit();
				left_time.setText(String.format("%02d:%02d", ((int) TIME/60), ((int) TIME % 60)));
				updateView();
			}
		});
		
		start_button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer = new Timer(1000, new ActionListener() {
					private int i = 120;
					@Override
					public void actionPerformed(ActionEvent e) {
						i--;
						left_time.setText(String.format("%02d:%02d", ((int) i/60), ((int) i % 60)));
						if (i == 0) {
							timer.stop();
						}
					}
				});
				start_button.setEnabled(false);
				timer.start();
				
			}
		});
	}

	public void display() {
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new LongestWord().display();
			}
		});
	}
}
