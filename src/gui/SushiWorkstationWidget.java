package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.*;

import sushi.*;
import sushi.Nigiri.NigiriType;
import sushi.Plate.Color;
import sushi.Sashimi.SashimiType;

public class SushiWorkstationWidget extends JPanel implements ActionListener {

	private List<WorkstationListener> listeners;
	private JComboBox<Plate.Color> color;
	private JSlider price;
	private int price_start = 500;
	private int price_end = 1500;
	private int ingredient_start = 0;
	private int ingredient_end = 100;
	private JPanel slider_panel;
	private JSlider avocado;
	private JSlider crab;
	private JSlider eel;
	private JSlider rice;
	private JSlider salmon;
	private JSlider seaweed;
	private JSlider shrimp;
	private JSlider tuna;
	private JButton make_roll;
	private JButton sashimi;
	private JComboBox<SashimiType> sashimi_select;
	private JButton nigiri;
	private JComboBox<NigiriType> nigiri_select;

	private JSlider[] sliders;

	public SushiWorkstationWidget() {

		listeners = new ArrayList<WorkstationListener>();

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(350, 560));

		// Create panel for first 2 components
		JPanel top_subpanel = new JPanel();
		top_subpanel.setLayout(new BorderLayout());

		// Panel for the first component
		JPanel sub1 = new JPanel();

		// first component: selecting kind of plate
		Plate.Color[] colors = { Color.BLUE, Color.GOLD, Color.GREEN, Color.RED };
		color = new JComboBox<Color>(colors);
		sub1.add(new JLabel("Plate Color: "));
		sub1.add(color);

		top_subpanel.add(sub1, BorderLayout.NORTH);

		// second component: setting price if plate is gold
		price = new JSlider(price_start, price_end);
		Hashtable<Integer, JLabel> price_labels = new Hashtable<Integer, JLabel>();
		price_labels.put(500, new JLabel("5.00"));
		price_labels.put(1500, new JLabel("15.00"));
		price.setLabelTable(price_labels);
		price.setPaintLabels(true);
		price.setPaintTicks(true);
		price.setMajorTickSpacing(200);
		price.setPaintLabels(true);
		top_subpanel.add(new JLabel("Gold Price: "));
		top_subpanel.add(price, BorderLayout.SOUTH);

		add(top_subpanel, BorderLayout.NORTH);

		// creating panels for ingredient sliders
		slider_panel = new JPanel();

		/*
		 * Labeling ingredients and combining them with amount sliderCombining
		 * these in a panel
		 */

		JPanel sub2 = new JPanel();
		avocado = new JSlider(ingredient_start, ingredient_end,
				ingredient_start);
		sub2.add(new JLabel("Avocado: "));
		sub2.add(avocado);
		slider_panel.add(sub2);

		JPanel sub3 = new JPanel();
		crab = new JSlider(ingredient_start, ingredient_end, ingredient_start);
		sub3.add(new JLabel("Crab: "));
		sub3.add(crab);
		slider_panel.add(sub3);

		JPanel sub4 = new JPanel();
		eel = new JSlider(ingredient_start, ingredient_end, ingredient_start);
		sub4.add(new JLabel("Eel: "));
		sub4.add(eel);
		slider_panel.add(sub4);

		JPanel sub5 = new JPanel();
		rice = new JSlider(ingredient_start, ingredient_end, ingredient_start);
		sub5.add(new JLabel("Rice: "));
		sub5.add(rice);
		slider_panel.add(sub5);

		JPanel sub6 = new JPanel();
		salmon = new JSlider(ingredient_start, ingredient_end, ingredient_start);
		sub6.add(new JLabel("Salmon: "));
		sub6.add(salmon);
		slider_panel.add(sub6);

		JPanel sub7 = new JPanel();
		seaweed = new JSlider(ingredient_start, ingredient_end,
				ingredient_start);
		sub7.add(new JLabel("Seaweed: "));
		sub7.add(seaweed);
		slider_panel.add(sub7);

		JPanel sub8 = new JPanel();
		shrimp = new JSlider(ingredient_start, ingredient_end, ingredient_start);
		sub8.add(new JLabel("Shrimp: "));
		sub8.add(shrimp);
		slider_panel.add(sub8);

		JPanel sub9 = new JPanel();
		tuna = new JSlider(ingredient_start, ingredient_end, ingredient_start);
		sub9.add(new JLabel("Tuna: "));
		sub9.add(tuna);
		slider_panel.add(sub9);

		add(slider_panel, BorderLayout.CENTER);

		// creating a panel for the final 3 components
		JPanel bottom_subpanel = new JPanel();
		bottom_subpanel.setLayout(new BorderLayout());

		// button to create roll
		make_roll = new JButton("Make Roll");
		bottom_subpanel.add(make_roll, BorderLayout.NORTH);

		make_roll.addActionListener(this);

		// panel for selecting and making sashimi
		JPanel sub10 = new JPanel();
		sub10.add(new JLabel("Sashimi Type: "));

		// selecting sashimi
		SashimiType[] sashimi_types = { SashimiType.CRAB, SashimiType.EEL,
				SashimiType.SALMON, SashimiType.SHRIMP, SashimiType.TUNA };
		sashimi_select = new JComboBox<SashimiType>(sashimi_types);
		sub10.add(sashimi_select);

		// making sashimi
		sashimi = new JButton("Make Sashimi");
		sub10.add(sashimi);

		sashimi.addActionListener(this);

		bottom_subpanel.add(sub10, BorderLayout.CENTER);

		// panel for selecting and making nigiri
		JPanel sub11 = new JPanel();
		sub11.add(new JLabel("Nigiri Type: "));

		// selecting nigiri
		Nigiri.NigiriType[] nigiri_types = { NigiriType.CRAB, NigiriType.EEL,
				NigiriType.SALMON, NigiriType.SHRIMP, NigiriType.TUNA };
		nigiri_select = new JComboBox<NigiriType>(nigiri_types);
		sub11.add(nigiri_select);

		// making nigiri
		nigiri = new JButton("Make Nigiri");
		sub11.add(nigiri);

		nigiri.addActionListener(this);

		bottom_subpanel.add(sub11, BorderLayout.SOUTH);

		add(bottom_subpanel, BorderLayout.SOUTH);

		// array of ingredient sliders
		sliders = new JSlider[9];
		sliders[0] = price;
		sliders[1] = avocado;
		sliders[2] = crab;
		sliders[3] = eel;
		sliders[4] = rice;
		sliders[5] = salmon;
		sliders[6] = seaweed;
		sliders[7] = shrimp;
		sliders[8] = tuna;
	}

	@Override
	public void actionPerformed(ActionEvent a) {

		// creating roll plate
		if (a.getSource().equals(make_roll)) {
			try {
				Plate new_plate = makePlate(makeSushi(makeRoll()));
				publishPlateToListeners(new_plate);
			} catch (Exception e) {
				// pass
			}
		}

		// creating sashimi plate
		if (a.getSource().equals(sashimi)) {
			try {
				Plate new_plate = makePlate(makeSashimi());
				publishPlateToListeners(new_plate);
			} catch (Exception e) {
				// pass
			}
		}

		// creating nigiri plate
		if (a.getSource().equals(nigiri)) {
			try {
				Plate new_plate = makePlate(makeNigiri());
				publishPlateToListeners(new_plate);
			} catch (Exception e) {
				// pass
			}
		}
	}

	// Helper methods

	public Ingredient[] makeRoll() throws Exception {

		int contents_length = 0;
		ArrayList<Ingredient> flex = new ArrayList<Ingredient>();

		for (int i = 1; i < 9; i++) {
			if (sliders[i].getValue() > 0) {
				if (sliders[i].equals(avocado)) {
					Ingredient new_avocado = new Avocado(
							sliders[i].getValue() / 100.00);
					flex.add(new_avocado);
					contents_length++;
				}
				if (sliders[i].equals(crab)) {
					Ingredient new_crab = new Crab(
							sliders[i].getValue() / 100.00);
					flex.add(new_crab);
					contents_length++;
				}
				if (sliders[i].equals(eel)) {
					Ingredient new_eel = new Eel(sliders[i].getValue() / 100.00);
					flex.add(new_eel);
					contents_length++;
				}
				if (sliders[i].equals(rice)) {
					Ingredient new_rice = new Rice(
							sliders[i].getValue() / 100.00);
					flex.add(new_rice);
					contents_length++;
				}
				if (sliders[i].equals(salmon)) {
					Ingredient new_salmon = new Salmon(
							sliders[i].getValue() / 100.00);
					flex.add(new_salmon);
					contents_length++;
				}
				if (sliders[i].equals(seaweed)) {
					Ingredient new_seaweed = new Seaweed(
							sliders[i].getValue() / 100.00);
					flex.add(new_seaweed);
					contents_length++;
				}
				if (sliders[i].equals(shrimp)) {
					Ingredient new_shrimp = new Shrimp(
							sliders[i].getValue() / 100.00);
					flex.add(new_shrimp);
					contents_length++;
				}
				if (sliders[i].equals(tuna)) {
					Ingredient new_tuna = new Tuna(
							sliders[i].getValue() / 100.00);
					flex.add(new_tuna);
					contents_length++;
				}
			}
		}
		if (flex.size() == 0) {
			throw new Exception();
		} else {
			Ingredient[] contents = flex
					.toArray(new Ingredient[contents_length]);
			return contents;
		}
	}

	public Sushi makeSashimi() throws PlatePriceException {
		Sashimi new_sashimi = new Sashimi(
				(SashimiType) sashimi_select.getSelectedItem());
		return new_sashimi;
	}

	public Sushi makeNigiri() throws PlatePriceException {
		Nigiri new_nigiri = new Nigiri(
				(NigiriType) nigiri_select.getSelectedItem());
		return new_nigiri;
	}

	public Sushi makeSushi(Ingredient[] contents) {
		Sushi sushi = new Roll(contents);
		return sushi;
	}

	public Plate makePlate(Sushi contents) throws PlatePriceException {

		Color current = (Color) color.getSelectedItem();

		switch (current) {
		case GOLD:
			GoldPlate gold_plate = new GoldPlate(contents,
					price.getValue() / 100.00);
			return gold_plate;
		case BLUE:
			BluePlate blue_plate = new BluePlate(contents);
			return blue_plate;
		case GREEN:
			GreenPlate green_plate = new GreenPlate(contents);
			return green_plate;
		case RED:
			RedPlate red_plate = new RedPlate(contents);
			return red_plate;
		default:
			return null;
		}
	}

	public void addWorkstationListener(WorkstationListener l) {
		listeners.add(l);
	}

	public void removeWorkstationListener(WorkstationListener l) {
		listeners.remove(l);
	}

	private void publishPlateToListeners(Plate p) {
		for (WorkstationListener l : listeners) {
			l.handleMadePlate(p);
		}
	}
}
