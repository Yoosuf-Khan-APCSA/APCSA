	public class zero{
	public static void main (String [] args){
		Double num = Prompt.getDouble("");
		System.out.printf("%s%n",TruncatedZero(num));
		}
	public static String TruncatedZero(double num){
		String str = num+"";
		for (int i = str.length()-1; i>str.indexOf('.');i--){
			if(str.indexOf('.')+1==i){}
			else{
				if(str.charAt(i)=='0')
				str= str.substring(0,i);
				else i=0;
			}
		}
		return str;
	}
}
