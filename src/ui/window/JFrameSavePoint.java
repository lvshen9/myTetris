package ui.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.GameControl;
import util.FrameUtil;

public class JFrameSavePoint extends JFrame{
	private JButton btnOk=null;
	private JLabel lbPoint=null;
	private JTextField txName=null;
	private JLabel errMsg=null;
	private GameControl gameControl=null;
	
	
	
	public JFrameSavePoint(GameControl gameControl){
		this.gameControl=gameControl;
		this.setTitle("�����¼");
		this.setSize(256,128);
		FrameUtil.setFrameCenter(this,-380);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.createCom();
		this.createAction();
		
/*		//TODO ������
		this.setDefaultCloseOperation(3);
		this.setVisible(true);*/
	}
	/**
	 * ��ʾ���ڿ�
	 */
	public void show(int point){
		this.lbPoint.setText("���ĵ÷�:"+point);
		this.setVisible(true);
	}
	/**
	 * �����¼�����
	 */
	private void createAction() {
		this.btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=txName.getText();
				if(name.length()>16||name==null||"".equals(name)){
					errMsg.setText("�������벻�Ϸ�");
				}else{
					setVisible(false);
					gameControl.savePoint(name);
				}
			}
		});
	}
	
	/**
	 * ��ʼ���ؼ�
	 */
	private void createCom(){
		//�����������
		JPanel north=new JPanel(new FlowLayout(FlowLayout.LEFT));
		//������������
		this.lbPoint=new JLabel();
		//��ӷ������������
		north.add(lbPoint);
		//����������Ϣ�ؼ�
		this.errMsg=new JLabel();
		this.errMsg.setForeground(Color.RED);
		//��Ӵ�����Ϣ���������
		north.add(this.errMsg);
		//���������ӵ������
		this.add(north, BorderLayout.NORTH);
		//�����в���壬��ʽ����
		JPanel center=new JPanel(new FlowLayout(FlowLayout.LEFT));
		//�����ı���
		this.txName=new JTextField(10);
		//��������
		center.add(new JLabel("��������"));
		//�ı�����ӵ��в����
		center.add(this.txName);
		//�в������ӵ������
		this.add(center, BorderLayout.CENTER);
		
		//�����ϲ����(��ʽ����)
		JPanel south=new JPanel(new FlowLayout(FlowLayout.CENTER));
		//����ȷ����ť
		this.btnOk=new JButton("ȷ��");
		//��ť��ӵ��ϲ����
		south.add(btnOk);
		//�ϲ������ӵ������
		this.add(south,BorderLayout.SOUTH);
		
		
	}
}
