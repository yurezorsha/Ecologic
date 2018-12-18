package Forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

import Calculations.Company;
import Calculations.Element;
import Calculations.SumElem;
import Calculations.XY;
import DataFromExcel.CatalogOfElements;
import Models.ModelResultConcentrationTable;
import Models.ModelTable;
import Models.ModelTableUz;
import Models.ModelTableX;

public class FormReport extends JFrame {
	private Company c;
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	private ArrayList<SumElem> sumelem = CatalogOfElements.getInstance().sumelem;
	private HashMap<XY, LinkedList<Element>> map;
	private LinkedList<XY> lst = new LinkedList();
	private JTable table;
	private ModelTable model;

	public FormReport(Company c) throws HeadlessException {
		this.c = c;
		setResizable(false);
		setTitle("Отчет по уровню загрязнения");
		HashMap<XY, LinkedList<Element>> map = c.getMap();
		Iterator<Map.Entry<XY, LinkedList<Element>>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<XY, LinkedList<Element>> entry = iter.next();
			lst.add(entry.getKey());
		}
		initFrame();
	}

	private void initFrame() {
		setLayout(new FlowLayout());
		setSize(1000, 750);
		
		JPanel p1 = new JPanel();
		p1.setBounds(10, 10, 950, 300);
		p1.setLayout(new GridLayout(0,1));
		createTable(p1);
		
		JPanel p2 = new JPanel();
		p2.setBounds(10, 320, 450, 100);
		p2.setLayout(new GridLayout(0,1));
		createTableUz(p2, getStringsUz(sumelem));
		
		JPanel p4 = new JPanel();
		p4.setBounds(10, 450, 950, 300);
		p4.setLayout(new GridLayout(0, 1));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 720, 200, 30);
		comboBox.setModel(new DefaultComboBoxModel(lst.toArray()));
		comboBox.setSelectedIndex(0);

		model = new ModelTable(c.getMap().get(lst.get(0)), lst.get(0));
		table = new JTable();
		table.setModel(model);
		table.setVisible(true);
		comboBox.addActionListener(new ActionListener() {///////////////// choose POINT
			@Override
			public void actionPerformed(ActionEvent arg0) {
				XY n = (XY) comboBox.getSelectedItem();

				model = new ModelTable(c.getMap().get(n), n);
				table.setModel(model);
			}
		});
		
		p4.add(table);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(10, 750, 400, 400);
		js.setVisible(true);
		p4.add(js);

		JPanel p5 = new JPanel();
		p5.setBounds(10, 420, 900, 30);
		p5.setLayout(new FlowLayout());
		
		JLabel point=new JLabel(" Координаты источника выброса: X= "+c.getXy().getX()+" Y= " + c.getXy().getY() );
		JLabel points=new JLabel(" Координаты проверяемой точки: ");
		p5.add(point);
		p5.add(points);
		p5.add(comboBox);
		
		
		
		JPanel p3 = new JPanel();
		p3.setBounds(470, 320, 490, 100);
		p3.setLayout(new GridLayout(0, 1));
		createTableX(p3);
		
		JPanel p0 = new JPanel();
		p0.setPreferredSize(new Dimension(1000, 800));
		p0.setBounds(0, 0, 960, 800);
		p0.setLayout(null);
		p0.add(p1);
		p0.add(p2);
		p0.add(p3);
		p0.add(p4);
		p0.add(p5);

		JScrollPane scrollBar = new JScrollPane(p0, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollBar.setPreferredSize(new Dimension(1000, 700));
		add(scrollBar);

	}

	//sum uz list
	public ArrayList<String> getStringsUz(ArrayList<SumElem> sumel) {
		ArrayList<String> lst = new ArrayList<String>();
		for (SumElem el : sumel) {
			if (c.checkElem(el.getSumelem()) != null) {
				lst.add(getUz(el.getSumelem()));
			}
		}
		return lst;

	}

	public String getUz(ArrayList<String> names) {
		String str = "";
		String str2 = "УЗсумм ( ";
		String str0 = "";
		double sumuz = 0;
		if (!names.isEmpty()) {

			ArrayList<String> lst = names;
			for (String code : lst) {
				Element e = this.c.getElementByCode(code);

				sumuz += e.getUz();
				str2 += e.getCode() + " ";
			}
			if (sumuz > 1)
				str0 = " >1";
			str += str2 + " ) = " + sumuz + str0 + "\n";

		} else {
			str = "";
		}
		return str;
	}

	//table sum UZ
	public void createTableUz(JPanel p, ArrayList<String> s) {
		
		ModelTableUz model = new ModelTableUz(s);
		JTable table=new JTable();
		table.setModel(model);
		p.add(table);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(10, 400, 200, 200);
		js.setVisible(true);
		p.add(js);
		table.setVisible(true);
		

	}

	//table X
	public void createTableX(JPanel p) {
		ModelTableX model = new ModelTableX(c);
		JTable table=new JTable();
		table.setModel(model);
		p.add(table);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(470, 320, 500, 100);
		js.setVisible(true);
		p.add(js);
		table.setVisible(true);
		

	}

	//table concentration
	public void createTable(JPanel p1) {
		LinkedList<Element> elements = c.getElements();
		ModelResultConcentrationTable model = new ModelResultConcentrationTable(elements);
		JTable table=new JTable();
		table.setModel(model);
		p1.add(table);
		JScrollPane js = new JScrollPane(table);
		js.setBounds(10, 10, 400, 400);
		js.setVisible(true);
		p1.add(js);
		table.setVisible(true);
		
	}

}
