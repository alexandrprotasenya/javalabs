package by.framework.lab2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CalculatePanel extends JPanel {
	
	private ButtonGroup memButtons = new ButtonGroup();
	private Box memRadioBox = Box.createHorizontalBox();
	private Box resBox = Box.createHorizontalBox();
	
	private Box formulaBox = Box.createHorizontalBox();
	private Box formulaWithImageBox = Box.createVerticalBox();
	private ButtonGroup radioButtons = new ButtonGroup();
	
	// Buttons
	private JButton calcButton = new JButton("Calculate");
	private JButton cleanInputsButton = new JButton("Clean inputs");
	private JButton memPlus = new JButton("M+");
	private JButton memClean= new JButton("MC");
	
	// Inputs and Inputs Labels
	private JTextField inputX = new JTextField("0", 10);
	private JTextField inputY = new JTextField("0", 10);
	private JTextField inputZ = new JTextField("0", 10);
	private JLabel labelX = new JLabel("X:");
	private JLabel labelY = new JLabel("Y:");
	private JLabel labelZ = new JLabel("Z:");
	private JTextField resultField  = new JTextField("", 10);
	private JLabel resultLabel = new JLabel("Result:");
	
	private Box hboxVars = Box.createHorizontalBox();
	
	private static int formulaId = 1;
	private static int memId = 1;
	
	private static ImagePanel formulaImage = new ImagePanel();
	
	// Mems
	private static double mem1 = 0;
	private static double mem2 = 0;
	private static double mem3 = 0;
	private JLabel mem1View = new JLabel("0");
	private JLabel mem2View = new JLabel("0");
	private JLabel mem3View = new JLabel("0");
	
	public CalculatePanel() throws IOException {
		super();
		formulaWithImageBox.add(formulaBox);
		formulaWithImageBox.add(formulaImage);
		
		inputX.setMaximumSize(inputX.getPreferredSize());
		inputY.setMaximumSize(inputY.getPreferredSize());
		inputZ.setMaximumSize(inputZ.getPreferredSize());
		
		hboxVars.setBorder(BorderFactory.createLineBorder(Color.RED));
		hboxVars.add(Box.createHorizontalGlue());
		hboxVars.add(labelX);
		hboxVars.add(Box.createHorizontalStrut(10));
		hboxVars.add(inputX);
		hboxVars.add(Box.createHorizontalStrut(20));
		hboxVars.add(labelY);
		hboxVars.add(Box.createHorizontalStrut(10));
		hboxVars.add(inputY);
		hboxVars.add(Box.createHorizontalStrut(20));
		hboxVars.add(labelZ);
		hboxVars.add(Box.createHorizontalStrut(10));
		hboxVars.add(inputZ);
		hboxVars.add(Box.createHorizontalGlue());
		
		resultField.setMaximumSize(new Dimension(100000, 50));
		resultField.setEditable(false);
		resBox.add(Box.createHorizontalGlue());
		resBox.add(resultLabel);
		resBox.add(Box.createHorizontalStrut(10));
		resBox.add(resultField);
		resBox.add(Box.createHorizontalGlue());
		
		Box inputs = Box.createVerticalBox();
		inputs.add(hboxVars);
		inputs.add(resBox);
		
		addRadioButton("Формула 1", 1);
		addRadioButton("Формула 2", 2);
		radioButtons.setSelected(
				radioButtons.getElements().nextElement().getModel(), true);
		formulaImage.updateImage(formulaId);
		
		JRadioButton addMemButton1 = new JRadioButton("M1");
		addMemButton1.addActionListener(new AddMem(1));
		JRadioButton addMemButton2 = new JRadioButton("M2");
		addMemButton2.addActionListener(new AddMem(2));
		JRadioButton addMemButton3 = new JRadioButton("M3");
		addMemButton3.addActionListener(new AddMem(3));
		memButtons.add(addMemButton1);
		memButtons.add(addMemButton2);
		memButtons.add(addMemButton3);
		memRadioBox.add(Box.createHorizontalGlue());
		memRadioBox.add(addMemButton1);
		memRadioBox.add(addMemButton2);
		memRadioBox.add(addMemButton3);
		memRadioBox.add(Box.createHorizontalGlue());
		memButtons.setSelected(
				memButtons.getElements().nextElement().getModel(), true);
		
		Box inputsBox = Box.createHorizontalBox();
		inputsBox.add(calcButton);
		inputsBox.add(cleanInputsButton);
		
		Box memBox = Box.createHorizontalBox();
		memBox.add(memPlus);
		memBox.add(memClean);
		
		Box buttonsBox = Box.createVerticalBox();
		buttonsBox.add(inputsBox);
		buttonsBox.add(memBox);
		
		Box mem1VL = Box.createHorizontalBox();
		mem1VL.setAlignmentX(RIGHT_ALIGNMENT);
		mem1VL.add(new JLabel("Mem 1:"));
		mem1VL.add(Box.createHorizontalStrut(10));
		mem1VL.add(mem1View);
		mem1VL.add(Box.createHorizontalGlue());
		
		Box mem2VL = Box.createHorizontalBox();
		mem2VL.setAlignmentX(RIGHT_ALIGNMENT);
		mem2VL.add(new JLabel("Mem 2:"));
		mem2VL.add(Box.createHorizontalStrut(10));
		mem2VL.add(mem2View);
		mem2VL.add(Box.createHorizontalGlue());
		
		Box mem3VL = Box.createHorizontalBox();
		mem3VL.setAlignmentX(RIGHT_ALIGNMENT);
		mem3VL.add(new JLabel("Mem 3:"));
		mem3VL.add(Box.createHorizontalStrut(10));
		mem3VL.add(mem3View);
		mem3VL.add(Box.createHorizontalGlue());
		
		Box memViewBox = Box.createVerticalBox();
		memViewBox.add(memRadioBox);
		memViewBox.add(mem1VL);
		memViewBox.add(mem2VL);
		memViewBox.add(mem3VL);
		
		Box box = Box.createHorizontalBox();
		box.add(buttonsBox);
		box.add(Box.createHorizontalStrut(100));
		box.add(memViewBox);
		
		Box box1 = Box.createVerticalBox();
		box1.add(inputs);
		box1.add(box);
		
		add(box1);
		add(formulaWithImageBox);
		
		cleanInputsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				inputX.setText("0");
				inputY.setText("0");
				inputZ.setText("0");
			}
		});
		
		memPlus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					double plus = Double.parseDouble(resultField.getText());
					switch(memId) {
					case 1: 
						mem1 += plus;
						mem1View.setText("" + mem1);
						resultField.setText("" + mem1);
						break;
					case 2:
						mem2 += plus;
						mem2View.setText("" + mem2);
						resultField.setText("" + mem2);
						break;
					case 3: 
						mem3 += plus;
						mem3View.setText("" + mem3);
						resultField.setText("" + mem3);
						break;
					default: break;
					}
				}  catch(NumberFormatException e) {
				}
			}
		});
		
		memClean.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(memId) {
				case 1: 
					mem1 = 0;
					mem1View.setText("" + mem1);
					
					break;
				case 2:
					mem2 = 0;
					mem2View.setText("" + mem2);
					
					break;
				case 3: 
					mem3 = 0;
					mem3View.setText("" + mem3);
					
					break;
				default: break;
				}
			}
		});
		
		calcButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					double x = Double.parseDouble(inputX.getText());
					double y = Double.parseDouble(inputY.getText());
					double z = Double.parseDouble(inputZ.getText());
					double res = 0;
					switch(formulaId) {
					case 1: 
						res = Calculate.calculete1(x, y, z);
						break;
					case 2:
						res = Calculate.calculete2(x, y, z);
						break;
					default: break;
					}
					resultField.setText("" + res);
				} catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(CalculatePanel.this,
							"Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
							JOptionPane.WARNING_MESSAGE);

				}
			}
		});
	}

	private void addRadioButton(String name, final int formulaId) {
		JRadioButton button = new JRadioButton(name);
		formulaBox.add(button);
		radioButtons.add(button);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CalculatePanel.formulaId = formulaId;
				try {
					formulaImage.updateImage(formulaId);
				} catch (IOException e1) {
				}
			}
		});
	}
	
	private class AddMem implements ActionListener {
		
		private final int mMemId;
		
		public AddMem(int memId) {
			mMemId = memId;
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			CalculatePanel.memId = mMemId;
		}
	}
	
}
