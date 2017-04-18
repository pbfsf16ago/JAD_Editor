package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class FormAdd extends JFrame {

	private JPanel frm_add;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormAdd frame = new FormAdd(TestProfil.getTestProfil());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormAdd(Profil p) {
		setResizable(false);
		setTitle("Eintrag hinzuf\u00FCgen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 512);
		frm_add = new JPanel();
		frm_add.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(frm_add);
		frm_add.setLayout(null);
		
		JComboBox cbo_ou = new JComboBox();
		cbo_ou.setBounds(14, 384, 256, 32);
		frm_add.add(cbo_ou);
		
		JButton btn_grp = new JButton("Gruppen...");
		btn_grp.setBounds(286, 384, 192, 32);
		frm_add.add(btn_grp);
		
		JButton btn_add = new JButton("OK");
		btn_add.setBounds(286, 432, 192, 32);
		frm_add.add(btn_add);
		
		
		JPanel pnl_add = new JPanel();
		pnl_add.setLayout(new GridLayout(0,2,10,10));

		JLabel[] addLabel = new JLabel[p.getProfGenInfo().size()];
		JTextField[] addText = new JTextField[p.getProfGenInfo().size()];
		
		for (int i =0;i<p.getProfGenInfo().size();i++)
		{
			String zwischen = p.getProfGenInfo().get(i);
			addLabel[i] = new JLabel(zwischen.substring(0, zwischen.indexOf(',')));
			pnl_add.add(addLabel[i]);
			
			addText[i] = new JTextField();
			pnl_add.add(addText[i]);
			addText[i].setColumns(1);
		}
		
		JScrollPane scp_add = new JScrollPane(pnl_add);
		scp_add.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scp_add.setBounds(12, 13, 466, 358);
		frm_add.add(scp_add);
		
		btn_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<String> aL = new ArrayList<>();
				for (int i = 0;i<addText.length;i++)
				{
					aL.add(addText[i].getText());
				}
				User user = new User(aL,"Gruppe","OU");
				System.out.println(user.getUserGenInfo().toString());
				p.addALUser(user);
			}
		});
		
		
	}
}
