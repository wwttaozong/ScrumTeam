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
 * 主窗口（程序入口）
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
	// 真正显示题目列表的容器
	private static JList<String> list = new JList<String>();
	// 真正显示题目详情的标签
	private static JLabel detailLabel = new JLabel();
	// 真正显示运行结果的容器
	private static JLabel resultLabel = new JLabel();
	// 存储编译运行结果及错误
	private static String[] feedbacks = new String[2];

	private static MyActionListener myActionListener = new MyActionListener();
	private static MyWindowListener myWindowListener = new MyWindowListener();
	private static MyListListener myListListener = new MyListListener();
	// 题目列表
	private static List<Problem> problems = new ArrayList<Problem>();
	// 用户选中的将要导入的文件
	private static String selectedFile = null;
	// 屏幕宽高
	private static final int SCREEN_WIDTH;
	private static final int SCREEN_HEIGHT;
	// 窗口大小及位置
	private static final int WINDOW_WIDTH;
	private static final int WINDOW_HEIGHT;
	private static final int WINDOW_X;
	private static final int WINDOW_Y;
	// 菜单栏的大小及位置
	private static final int MENUBAR_WIDTH;
	private static final int MENUBAR_HEIGHT;
	private static final int MENUBAR_X;
	private static final int MENUBAR_Y;
	// 题目列表大小及位置
	private static final int LIST_WIDTH;
	private static final int LIST_HEIGHT;
	private static final int LIST_X;
	private static final int LIST_Y;
	// 题目描述框大小及位置
	private static final int DETAIL_WIDTH;
	private static final int DETAIL_HEIGHT;
	private static final int DETAIL_X;
	private static final int DETAIL_Y;
	// 编辑框大小及位置
	private static final int EDITBOX_WIDTH;
	private static final int EDITBOX_HEIGHT;
	private static final int EDITBOX_X;
	private static final int EDITBOX_Y;
	// 结果框的大小及位置
	private static final int RESULT_WIDTH;
	private static final int RESULT_HEIGHT;
	private static final int RESULT_X;
	private static final int RESULT_Y;
	// 程序临时保存路径
	private static final String DES_PATH;

	/**
	 * 获取各个窗体模块的大小及宽高
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

		// 初始化界面
		initView();
	}

	/**
	 * 点击事件监听器
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
					System.err.println("程序保存失败");
				}
				break;
			case "Import":
				showDialog(window, "选择要导入的题库", FileDialog.LOAD);
				updateProblemsList();
				break;
			default:
				break;
			}
		}

	}// end of MyActionListener

	/**
	 * 监听题目列表的点击事件将选中题目详情显示出来
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
	 * 窗口监听器
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
	 * 初始化UI界面
	 */
	private static void initView() {
		window.getContentPane().setBackground(Color.WHITE);
		window.setLayout(null);
		window.addWindowListener(myWindowListener);

		// 菜单栏
		// “编译&运行”按钮
		JMenu compile$run = new JMenu("Compile&Run");
		JMenuItem compile = new JMenuItem("Compile");
		JMenuItem run = new JMenuItem("Run");
		compile$run.add(compile);
		compile$run.addSeparator();
		compile$run.add(run);
		jMenuBar.add(compile$run);
		compile.addActionListener(myActionListener);
		run.addActionListener(myActionListener);
		// “保存”按钮
		JMenu saveOption = new JMenu("Save");
		JMenuItem save = new JMenuItem("Save");
		saveOption.add(save);
		jMenuBar.add(saveOption);
		save.addActionListener(myActionListener);
		// “导入&导出按钮”
		JMenu in$out = new JMenu("Import&Export");
		JMenuItem in = new JMenuItem("Import");
		JMenuItem out = new JMenuItem("Export");
		in$out.add(in);
		in$out.addSeparator();
		in$out.add(out);
		jMenuBar.add(in$out);
		in.addActionListener(myActionListener);
		out.addActionListener(myActionListener);
		// 显示菜单
		jMenuBar.setBounds(MENUBAR_X, MENUBAR_Y, MENUBAR_WIDTH, MENUBAR_HEIGHT);
		window.add(jMenuBar);

		// 题目列表
		problemsList.setBounds(LIST_X, LIST_Y, LIST_WIDTH, LIST_HEIGHT);
		problemsList.setBackground(Color.WHITE);
		problemsList.setBorder(BorderFactory.createTitledBorder("My Problems"));
		list.setFixedCellHeight(LIST_HEIGHT / 10);
		list.setFixedCellWidth(LIST_WIDTH);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(myListListener);
		problemsList.getViewport().add(list);
		window.add(problemsList);

		// 题目详情框
		problemDetail
				.setBounds(DETAIL_X, DETAIL_Y, DETAIL_WIDTH, DETAIL_HEIGHT);
		problemDetail.setBackground(Color.WHITE);
		problemDetail.setBorder(BorderFactory
				.createTitledBorder("Problem Detail"));
		detailLabel.setOpaque(true);
		detailLabel.setBackground(Color.WHITE);
		problemDetail.getViewport().add(detailLabel);
		window.add(problemDetail);

		// 代码编辑框
		editBox.setBounds(EDITBOX_X, EDITBOX_Y, EDITBOX_WIDTH, EDITBOX_HEIGHT);
		editBox.setBackground(Color.WHITE);
		editBox.setBorder(BorderFactory.createTitledBorder("EditBox"));
		window.add(editBox);

		// 结果显示框
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
	 * 显示导入导出文件的对话框
	 * 
	 * @param parent对话框的父窗口
	 * @param title对话框的标题
	 * @param mode对话框打开的模式
	 */
	private static void showDialog(Frame parent, String title, int mode) {
		FileDialog fileDialog = new FileDialog(parent, title, mode);
		fileDialog.setVisible(true);
		selectedFile = fileDialog.getDirectory() + fileDialog.getFile();
	}

	/**
	 * 将从Excel获取到的题目的数据显示到屏幕的题目列表上
	 */
	private static void updateProblemsList() {
		try {
			problems = ExcelTools.getDataFromExcel(selectedFile);
		} catch (BiffException | IOException e) {
			System.out.println("读取文件失败，请检查文件的格式及数据格式是否正确");
		}
		// 每次导入题库重新设置数据模型
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
