package com.scrum.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import jxl.read.biff.BiffException;

import com.scrum.tools.GccTools;
import com.scrum.tools.ExcelTools;
import com.scrum.utils.FileUtils;

/**
 * �����ڣ�������ڣ�
 * 
 * @author wwt
 *
 */
public class MainWindow {

	private static JFrame window = new JFrame("Program Judge");
	private static JMenuBar jMenuBar = new JMenuBar();
	private static JScrollPane problemsList = new JScrollPane();
	private static JScrollPane problemDetail = new JScrollPane();
	private static JTextArea editBox = new JTextArea();
	private static JPanel resultBox = new JPanel();
	// ������ʾ��Ŀ�б������
	private static JList<String> list = new JList<String>();
	// ������ʾ��Ŀ����ı�ǩ
	private static JLabel detailLabel = new JLabel();
	// ������ʾ���н��������
	private static JLabel resultLabel = new JLabel();
	// �洢�������н��������
	private static String[] feedbacks = new String[2];

	private static MyActionListener myActionListener = new MyActionListener();
	private static MyWindowListener myWindowListener = new MyWindowListener();
	private static MyListListener myListListener = new MyListListener();
	// ��Ŀ�б�
	private static List<Problem> problems = new ArrayList<Problem>();
	// �û�ѡ�еĽ�Ҫ������ļ�
	private static String selectedFile = null;
	// ��Ļ���
	private static final int SCREEN_WIDTH;
	private static final int SCREEN_HEIGHT;
	// ���ڴ�С��λ��
	private static final int WINDOW_WIDTH;
	private static final int WINDOW_HEIGHT;
	private static final int WINDOW_X;
	private static final int WINDOW_Y;
	// �˵����Ĵ�С��λ��
	private static final int MENUBAR_WIDTH;
	private static final int MENUBAR_HEIGHT;
	private static final int MENUBAR_X;
	private static final int MENUBAR_Y;
	// ��Ŀ�б��С��λ��
	private static final int LIST_WIDTH;
	private static final int LIST_HEIGHT;
	private static final int LIST_X;
	private static final int LIST_Y;
	// ��Ŀ�������С��λ��
	private static final int DETAIL_WIDTH;
	private static final int DETAIL_HEIGHT;
	private static final int DETAIL_X;
	private static final int DETAIL_Y;
	// �༭���С��λ��
	private static final int EDITBOX_WIDTH;
	private static final int EDITBOX_HEIGHT;
	private static final int EDITBOX_X;
	private static final int EDITBOX_Y;
	// �����Ĵ�С��λ��
	private static final int RESULT_WIDTH;
	private static final int RESULT_HEIGHT;
	private static final int RESULT_X;
	private static final int RESULT_Y;
	// ������ʱ����·��
	private static final String DES_PATH;

	/**
	 * ��ȡ��������ģ��Ĵ�С�����
	 */
	static {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		SCREEN_WIDTH = dimension.width;
		SCREEN_HEIGHT = dimension.height;

		WINDOW_WIDTH = SCREEN_WIDTH / 2;
		WINDOW_HEIGHT = SCREEN_HEIGHT / 2;
		WINDOW_X = SCREEN_WIDTH / 4;
		WINDOW_Y = SCREEN_HEIGHT / 4;

		MENUBAR_WIDTH = WINDOW_WIDTH;
		MENUBAR_HEIGHT = WINDOW_HEIGHT / 16;
		MENUBAR_X = 0;
		MENUBAR_Y = 0;

		LIST_WIDTH = WINDOW_WIDTH / 4;
		LIST_HEIGHT = (WINDOW_HEIGHT - MENUBAR_HEIGHT) / 2;
		LIST_X = 0;
		LIST_Y = MENUBAR_HEIGHT;

		DETAIL_WIDTH = LIST_WIDTH;
		DETAIL_HEIGHT = LIST_HEIGHT;
		DETAIL_X = 0;
		DETAIL_Y = MENUBAR_HEIGHT + LIST_HEIGHT;

		EDITBOX_WIDTH = WINDOW_WIDTH - LIST_WIDTH;
		EDITBOX_HEIGHT = (int) ((WINDOW_HEIGHT - MENUBAR_HEIGHT) * (2 / 3.0));
		EDITBOX_X = LIST_WIDTH;
		EDITBOX_Y = MENUBAR_HEIGHT;

		RESULT_WIDTH = EDITBOX_WIDTH;
		RESULT_HEIGHT = LIST_HEIGHT + DETAIL_HEIGHT - EDITBOX_HEIGHT;
		RESULT_X = LIST_WIDTH;
		RESULT_Y = MENUBAR_HEIGHT + EDITBOX_HEIGHT;

		DES_PATH = System.getProperty("user.dir") + "/temp.c";
	}

	public static void main(String args[]) {

		// ��ʼ������
		initView();
	}

	/**
	 * ����¼�������
	 * 
	 * @author wwt
	 *
	 */
	private static class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			switch (event.getActionCommand()) {
			case "Compile":
				feedbacks = GccTools.complie();
				break;
			case "Run":
				feedbacks = GccTools.run();
				StringBuilder sb = new StringBuilder();
				sb.append(feedbacks[0]);
				sb.append(feedbacks[1]);
				resultLabel.setText(sb.toString());
				break;
			case "Save":
				try {
					FileUtils.writeToFile(DES_PATH, editBox.getText()
							.toString());
				} catch (IOException e) {
					System.err.println("���򱣴�ʧ��");
				}
				break;
			case "Import":
				showDialog(window, "ѡ��Ҫ��������", FileDialog.LOAD);
				updateProblemsList();
				break;
			default:
				break;
			}
		}

	}// end of MyActionListener

	/**
	 * ������Ŀ�б�ĵ���¼���ѡ����Ŀ������ʾ����
	 * 
	 * @author wwt
	 *
	 */
	private static class MyListListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			Problem problem = problems.get(list.getSelectedIndex());
			StringBuilder details = new StringBuilder();
			details.append("<html><p>Problem:<br>" + problem.getName()
					+ "</p><br>");
			details.append("<p>Description:<br>" + problem.getDescription()
					+ "</p><br>");
			details.append("<p>Input:<br>" + problem.getInput() + "</p><br>");
			details.append("<p>Output:<br>" + problem.getOutput()
					+ "</p></html>");
			detailLabel.setText(details.toString());
		}
	}

	/**
	 * ���ڼ�����
	 * 
	 * @author wwt
	 *
	 */
	private static class MyWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	/**
	 * ��ʼ��UI����
	 */
	private static void initView() {
		window.getContentPane().setBackground(Color.WHITE);
		window.setLayout(null);
		window.addWindowListener(myWindowListener);

		// �˵���
		// ������&���С���ť
		JMenu compile$run = new JMenu("Compile&Run");
		JMenuItem compile = new JMenuItem("Compile");
		JMenuItem run = new JMenuItem("Run");
		compile$run.add(compile);
		compile$run.addSeparator();
		compile$run.add(run);
		jMenuBar.add(compile$run);
		compile.addActionListener(myActionListener);
		run.addActionListener(myActionListener);
		// �����桱��ť
		JMenu saveOption = new JMenu("Save");
		JMenuItem save = new JMenuItem("Save");
		saveOption.add(save);
		jMenuBar.add(saveOption);
		save.addActionListener(myActionListener);
		// ������&������ť��
		JMenu in$out = new JMenu("Import&Export");
		JMenuItem in = new JMenuItem("Import");
		JMenuItem out = new JMenuItem("Export");
		in$out.add(in);
		in$out.addSeparator();
		in$out.add(out);
		jMenuBar.add(in$out);
		in.addActionListener(myActionListener);
		out.addActionListener(myActionListener);
		// ��ʾ�˵�
		jMenuBar.setBounds(MENUBAR_X, MENUBAR_Y, MENUBAR_WIDTH, MENUBAR_HEIGHT);
		window.add(jMenuBar);

		// ��Ŀ�б�
		problemsList.setBounds(LIST_X, LIST_Y, LIST_WIDTH, LIST_HEIGHT);
		problemsList.setBackground(Color.WHITE);
		problemsList.setBorder(BorderFactory.createTitledBorder("My Problems"));
		list.setFixedCellHeight(LIST_HEIGHT / 10);
		list.setFixedCellWidth(LIST_WIDTH);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(myListListener);
		problemsList.getViewport().add(list);
		window.add(problemsList);

		// ��Ŀ�����
		problemDetail
				.setBounds(DETAIL_X, DETAIL_Y, DETAIL_WIDTH, DETAIL_HEIGHT);
		problemDetail.setBackground(Color.WHITE);
		problemDetail.setBorder(BorderFactory
				.createTitledBorder("Problem Detail"));
		detailLabel.setOpaque(true);
		detailLabel.setBackground(Color.WHITE);
		problemDetail.getViewport().add(detailLabel);
		window.add(problemDetail);

		// ����༭��
		editBox.setBounds(EDITBOX_X, EDITBOX_Y, EDITBOX_WIDTH, EDITBOX_HEIGHT);
		editBox.setBackground(Color.WHITE);
		editBox.setBorder(BorderFactory.createTitledBorder("EditBox"));
		window.add(editBox);

		// �����ʾ��
		resultBox.setLayout(new FlowLayout(FlowLayout.LEFT));
		resultBox.setBounds(RESULT_X, RESULT_Y, RESULT_WIDTH, RESULT_HEIGHT);
		resultBox.setBackground(Color.WHITE);
		resultBox.setBorder(BorderFactory.createTitledBorder("Console"));
		resultBox.add(resultLabel);
		window.add(resultBox);

		window.setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
		window.setVisible(true);
	}// end of initView

	/**
	 * ��ʾ���뵼���ļ��ĶԻ���
	 * 
	 * @param parent�Ի���ĸ�����
	 * @param title�Ի���ı���
	 * @param mode�Ի���򿪵�ģʽ
	 */
	private static void showDialog(Frame parent, String title, int mode) {
		FileDialog fileDialog = new FileDialog(parent, title, mode);
		fileDialog.setVisible(true);
		selectedFile = fileDialog.getDirectory() + fileDialog.getFile();
	}

	/**
	 * ����Excel��ȡ������Ŀ��������ʾ����Ļ����Ŀ�б���
	 */
	private static void updateProblemsList() {
		try {
			problems = ExcelTools.getDataFromExcel(selectedFile);
		} catch (BiffException | IOException e) {
			System.out.println("��ȡ�ļ�ʧ�ܣ������ļ��ĸ�ʽ�����ݸ�ʽ�Ƿ���ȷ");
		}
		// ÿ�ε������������������ģ��
		ListModel<String> myDataModel = new ListModel<String>() {
			@Override
			public int getSize() {
				return problems.size();
			}

			@Override
			public String getElementAt(int index) {
				return problems.get(index).getName();
			}

			@Override
			public void addListDataListener(ListDataListener l) {

			}

			@Override
			public void removeListDataListener(ListDataListener l) {

			}
		};
		list.setModel(myDataModel);
	}

}
