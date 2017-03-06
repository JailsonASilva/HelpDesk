package br.com.projeto.util;

import java.awt.Toolkit;

public class teste {


	 public static void main(String[] args) {
		    Toolkit tk = Toolkit.getDefaultToolkit();
		    java.awt.Dimension d = tk.getScreenSize();
		    
		    System.out.println("Screen width = " + d.width);
		    System.out.println("Screen height = " + d.height);
		  }

}
