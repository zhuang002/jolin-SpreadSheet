import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static int[][] matrix = new int[10][9];
	static HashMap<String, String> expressions = new HashMap<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readIn();
		
		for (int i=0;i<10;i++) {
			for (int j=0;j<9;j++) {
				if (matrix[i][j]==-1) {
					Integer a = evaluate(convertCoord(i,j));
					if (a==null)
						matrix[i][j]=-1;
					else
						matrix[i][j]=a;
				}
			}
		}
		
		for (int i=0;i<10;i++) {
			for (int j=0;j<9;j++) {
				if (matrix[i][j]>=0) {
					System.out.print(matrix[i][j]+" ");
				}
				else {
					System.out.print("*");
				}
			}
			System.out.println();
		}

	}
	
	private static Integer evaluate(String sCoord) {
		// TODO Auto-generated method stub
		if (!expressions.containsKey(sCoord)) {
			int[] coord = backConvertCoord(sCoord);
			return matrix[coord[0]][coord[1]];
		}
		String expression = expressions.get(sCoord);
		if (expression==null) {
			return -1;
		}
		Integer a=parseInteger(expression);
		if (a!=null) return a;
		
		expressions.put(sCoord, null);
		
		String[] cells = expression.split("\\+");
		Integer sum = 0;
		for (String cell:cells) {
			a = parseInteger(cell);
			if (a!=null) {
				sum+=a;
			} else {
				a = evaluate(cell);
				if (a!=null) {
					sum+=a;
				} 
				else {
					expressions.put(sCoord, null);
					return null;
				}
			}
		}
		expressions.put(sCoord, ""+sum);
		return sum;
	}

	private static int[] backConvertCoord(String sCoord) {
		// TODO Auto-generated method stub
		int[] ret = new int[2];
		ret[0]= sCoord.charAt(0)-'A';
		ret[1]= Integer.parseInt(sCoord.substring(1))-1;
		return ret;
	}

	private static String convertCoord(int i, int j) {
		// TODO Auto-generated method stub
		return ""+((char)('A'+i))+""+(j+1);
	}

	private static void readIn() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		for (int i=0;i<10;i++) {
			for (int j=0;j<9;j++) {
				String s = sc.next();
				Integer a = parseInteger(s);
				if (a==null) {
					matrix[i][j] = -1;
					expressions.put(convertCoord(i,j), s);
				} else {
					matrix[i][j] = a;
				}
			}
		}
	}

	private static Integer parseInteger(String s) {
		// TODO Auto-generated method stub
		try {
			Integer a=Integer.parseInt(s);
			if (a>=0) return a;
			return null;
		} catch(Exception e) {
			return null;
		}
	}

}
