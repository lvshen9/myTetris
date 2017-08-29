package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;


public class CircleButton extends JButton{  
	   
	 public CircleButton(String label){  
	  super(label);  
	  Dimension size=getPreferredSize();//��ȡ��ť����Ѵ�С  
	    
	  //������ť�Ĵ�С,ʹ֮���һ������  
	  size.width=size.height=Math.max(size.width,size.height);  
	  setPreferredSize(size);  
	  //ʹjbutton��������,������ʾ���α���,���������ǻ�һ��Բ�ı���  
	  setContentAreaFilled(false);  
	 }  
	   
	 //��ͼ�İ�ť�ı����ͱ�ǩ  
	 protected void paintComponent(Graphics g){  
	  if(getModel().isArmed()){  
	   //getModel������������ģ��ButtonModel  
	   //�����갴�°�ť����buttonModel��armed����Ϊ��  
	   g.setColor(Color.LIGHT_GRAY);  
	  }else{  
	   //�����¼���Ĭ�ϵı���ɫ��ʾ��ť  
	   g.setColor(getBackground());  
	  }  
	  //fillOval������һ�����ε�������Բ,������������Բ  
	  //������Ϊ������ʱ,��������Բ����Բ  
	  g.fillOval(0,0,getSize().width-1,getSize().height-1);  
	    
	  //���ø����paintComponent����ť�ı�ǩ�ͽ������ڵ�С����  
	  super.paintComponents(g);  
	 }  
	 //�ü򵥵Ļ��䵱��ť�ı߽�  
	 protected void paintBorder(Graphics g){  
	  g.setColor(getForeground());  
	  //drawOval���������ε�������Բ,�������,ֻ����һ���߽�  
	  g.drawOval(0,0,getSize().width-1,getSize().height-1);  
	 }  
	   
	 Shape shape;//���ڱ��水ť����״,����������������ť�¼�  
	   
	 //�ж�����Ƿ���ڰ�ť��  
	   
	 public boolean contains(int x,int y){  
	  //�����ť�߿�,λ�÷����ı�,�����һ���µ���״����  
	  if((shape==null)||(!shape.getBounds().equals(getBounds()))){  
	   //������Բ�Ͷ���  
	   shape=new Ellipse2D.Float(0,0,getWidth(),getHeight());  
	  }  
	  //�ж�����x,y�����Ƿ����ڰ�ť��״��  
	  return shape.contains(x,y);  
	 }  
}