package Presentation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AppUI extends JFrame{
	
	private JPanel orderStatusPanel;
	private JComboBox<String> drinkSelection;
	private JPanel condimentsPanel;
	private JComboBox<String> locationSelection;

	public AppUI(MobileOrderingApp app){
		
		this.setTitle("Mobile Ordering App");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//drinks section
		this.drinkSelection = new JComboBox<String>();
		drinkSelection.setMaximumSize(drinkSelection.getPreferredSize());
		JPanel drinkPanel = new JPanel();
		drinkPanel.add(drinkSelection);
		
		
		//condiments section
		this.condimentsPanel = new JPanel();
		this.condimentsPanel.setLayout(new BoxLayout(condimentsPanel, BoxLayout.Y_AXIS));
		this.condimentsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//locations section
		this.locationSelection = new JComboBox<String>();
		locationSelection.setMaximumSize(locationSelection.getPreferredSize());
		JPanel locationPanel = new JPanel();
		locationPanel.add(locationSelection);
		
		//order button
		JButton orderButton = new JButton("Order");
		orderButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent event) {
				
				ArrayList<String> condiments = new ArrayList<String>();
				for (Component c : condimentsPanel.getComponents()){
					
					JCheckBox cb = (JCheckBox) c;
					
					if (cb.isSelected()){
						
						condiments.add(cb.getText());
					}
				}
				
				app.createOrder((String) drinkSelection.getSelectedItem(), condiments, (String) locationSelection.getSelectedItem());
			}
		});
		JPanel orderPanel = new JPanel();
		orderPanel.add(orderButton);
		
		//order status section
		this.orderStatusPanel = new JPanel();
		orderStatusPanel.setLayout(new BoxLayout(orderStatusPanel, BoxLayout.Y_AXIS));		
		JScrollPane orderStatusPane = new JScrollPane(orderStatusPanel);
		orderStatusPane.setPreferredSize(new Dimension(orderStatusPane.getWidth(), 100));
		
		JPanel appPanel = new JPanel();
		appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
		appPanel.add(drinkPanel);
		appPanel.add(condimentsPanel);
		appPanel.add(locationPanel);
		appPanel.add(orderPanel);
		
		this.add(appPanel, BorderLayout.NORTH);
		this.add(orderStatusPane, BorderLayout.CENTER);
		
		this.setVisible(true);
	}
	
	public void setOptions(String[] drinks, String[] locations, String[] condiments){
		
		this.drinkSelection.removeAllItems();
		for (String d : drinks){
		
			this.drinkSelection.addItem(d);
		}
		
		this.condimentsPanel.removeAll();
		for (String c : condiments){
			
			this.condimentsPanel.add(new JCheckBox(c));
		}
		
		this.locationSelection.removeAllItems();
		for (String l : locations){
			
			this.locationSelection.addItem(l);
		}
		
		this.revalidate();
	}
	
	public void printLine(String line){
		
		JLabel label = new JLabel();
		label.setText("<html>" + line.replaceAll("\n", "<br/>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;") + "</html>");
		
		this.orderStatusPanel.add(label);
		
		this.revalidate();
	}
	
	public void setLine(String line, String pattern){
		
		for (Component c : this.orderStatusPanel.getComponents()){
			
			JLabel label = (JLabel) c;
			
			if (label.getText().contains(pattern)){
				
				label.setText("<html>" + line.replaceAll("\n", "<br/>").replaceAll("\t", "&nbsp;&nbsp;&nbsp;") + "</html>");
			}
		}
		
		this.revalidate();
	}
}
