package roughTask;

import java.io.File;

public class Sample
{

	public static void main(String[] args) 
	{
		File fileObj = new File("SIT6ObjectRepository.xlsx");
		String dirPath = fileObj.getAbsolutePath();
		System.out.println(dirPath);
	}
}
