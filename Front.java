import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.io.*;
import javax.swing.JFileChooser;

public class Front {
	private JComboBox<String> comboBox;
	protected static JFrame frame;
	private JTextField searchTerm;
	private Search srch;
	private JButton btnSelectAudioFile;
	private JButton btnSelectTextFile;
	private File aFile;
	private File tFile;
	private Player player;
	private JButton btnPlay;
	private JButton btnPause;
	private JButton btnStop;
	private JButton btnDelete;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Front window = new Front();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Front() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 511, 281);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		Blisten lstnr = new Blisten();
		
		btnSelectAudioFile = new JButton("Select Audio File");
		btnSelectAudioFile.addActionListener(lstnr);
		GridBagConstraints gbc_btnSelectAudioFile = new GridBagConstraints();
		gbc_btnSelectAudioFile.fill = GridBagConstraints.VERTICAL;
		gbc_btnSelectAudioFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectAudioFile.gridx = 0;
		gbc_btnSelectAudioFile.gridy = 4;
		frame.getContentPane().add(btnSelectAudioFile, gbc_btnSelectAudioFile);
		
		searchTerm = new JTextField();
		searchTerm.setColumns(10);
		GridBagConstraints gbc_searchTerm = new GridBagConstraints();
		gbc_searchTerm.insets = new Insets(0, 0, 5, 5);
		gbc_searchTerm.fill = GridBagConstraints.BOTH;
		gbc_searchTerm.gridx = 1;
		gbc_searchTerm.gridy = 4;
		frame.getContentPane().add(searchTerm, gbc_searchTerm);
		
		JButton btnNewButton = new JButton("Search");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 4;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		Listen listen = new Listen();
		btnNewButton.addActionListener(listen);

		btnSelectTextFile = new JButton("Select Text File");
		GridBagConstraints gbc_btnSelectTextFile = new GridBagConstraints();
		gbc_btnSelectTextFile.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectTextFile.gridx = 0;
		gbc_btnSelectTextFile.gridy = 5;
		frame.getContentPane().add(btnSelectTextFile, gbc_btnSelectTextFile);
		btnSelectTextFile.addActionListener(lstnr);
		
		JLabel lblregex = new JLabel("(Regex)");
		GridBagConstraints gbc_lblregex = new GridBagConstraints();
		gbc_lblregex.insets = new Insets(0, 0, 5, 5);
		gbc_lblregex.gridx = 1;
		gbc_lblregex.gridy = 5;
		frame.getContentPane().add(lblregex, gbc_lblregex);
		
		btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 5;
		frame.getContentPane().add(btnDelete, gbc_btnDelete);
		
		JLabel lblMatches = new JLabel("Matches:");
		GridBagConstraints gbc_lblMatches = new GridBagConstraints();
		gbc_lblMatches.anchor = GridBagConstraints.EAST;
		gbc_lblMatches.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatches.gridx = 0;
		gbc_lblMatches.gridy = 6;
		frame.getContentPane().add(lblMatches, gbc_lblMatches);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 6;
		frame.getContentPane().add(comboBox, gbc_comboBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 1;
		gbc_horizontalStrut.gridy = 7;
		frame.getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		
		JProgressBar progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 9;
		frame.getContentPane().add(progressBar, gbc_progressBar);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 10;
		frame.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		controlListener clstn = new controlListener();
		
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(clstn);
		panel.add(btnPlay);
		
		btnPause = new JButton("Pause");
		btnPause.addActionListener(clstn);
		panel.add(btnPause);
		
		btnStop = new JButton("Stop");
		btnStop.addActionListener(clstn);
		panel.add(btnStop);
	}
	private class FileLister{
		private String[] listOfFiles(){	
			File aFile = new File(System.getProperty("user.dir"));
			FilenameFilter ff = new FilenameFilter(){
				public boolean accept(File dir, String name){
					return name.endsWith(".wav");
				}
			};
			String[] retval  = aFile.list(ff);
			return retval;
		}
	}
	
	private class Listen implements ActionListener{
			public void actionPerformed(ActionEvent e) throws NullPointerException{
				srch = new Search(searchTerm.getText(), tFile);
				try{
					srch.doSearch();
				}catch(IOException f){
					System.out.println(f);
				}
				comboBox.removeAllItems();
				for(int i = 0; i < srch.getStringResults().length; i++){
					comboBox.addItem(srch.getTimeStampResults()[i] + " : " + srch.getStringResults()[i]);
				}
				srch.clear();
			}
	}
	private class Blisten implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser mChoose = new JFileChooser();
			int fVal = mChoose.showOpenDialog(frame);
			if(fVal == JFileChooser.APPROVE_OPTION){
				File rFile = mChoose.getSelectedFile();
				if (e.getSource() == btnSelectAudioFile){
					player = new Player(rFile);
				}else if(e.getSource() == btnSelectTextFile){
					tFile = rFile;
				}
			}
		}
	}
	private class controlListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(player != null){
				if(e.getSource() == btnPlay){
					player.play();
				}else if(e.getSource() == btnPause){
					player.pause();
				}else if(e.getSource() == btnStop){
					player.stop();
				}
			}else{
				JOptionPane.showMessageDialog(frame, "No Audio File Selected");
			}
			
		}
	}
}
