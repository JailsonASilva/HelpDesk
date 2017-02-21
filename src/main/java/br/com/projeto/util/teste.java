package br.com.projeto.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class teste {

	public static void main(String[] args) {

		Calendar data48 = Calendar.getInstance();
		data48.add(Calendar.DAY_OF_MONTH, -2);


		String dataFormatada = new SimpleDateFormat("yyyy/MM/dd").format(data48.getTime());

		dataFormatada = "'" + dataFormatada + "'";
		
		System.out.println(dataFormatada);
	}

}
