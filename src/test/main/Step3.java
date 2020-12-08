package test.main;
class RubiksCube{
	//Constructor
	public RubiksCube(char[][][] ary) {
		printAry(initAry(ary));
	}
	// 배열을 매개변수로 받아 초기화하는 함수
	public char[][][] initAry(char[][][] ary) {
		ary=new char[6][][];
		ary[0]= new char[][]{{'B','B','B'},{'B','B','B'},{'B','B','B'}};
		ary[1]= new char[][]{{'W','W','W'},{'W','W','W'},{'W','W','W'}};
		ary[2]= new char[][]{{'O','O','O'},{'O','O','O'},{'O','O','O'}};
		ary[3]= new char[][]{{'G','G','G'},{'G','G','G'},{'G','G','G'}};
		ary[4]= new char[][]{{'Y','Y','Y'},{'Y','Y','Y'},{'Y','Y','Y'}};
		ary[5]= new char[][]{{'R','R','R'},{'R','R','R'},{'R','R','R'}};
		return ary;
	}
	// 출력형태에 맞게 배열을 출력해주는 함수
	public void printAry(char[][][] ary) {
		int i=0;
		for(int j=0; j<ary[i].length; j++) {
			for(int k=0; k<ary[i][j].length; k++)
			{
				System.out.print(k==0?"\t"+ary[i][j][k]+" ":ary[i][j][k]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
		i++;
		for(int j=0; j<ary[i].length; j++) {
			for(int k=0; k<ary[i][j].length; k++) {
				System.out.print(ary[i][j][k]+" ");
			}
			for(int k=0; k<ary[i+1][j].length; k++) {
				System.out.print(k==0?"\t"+ary[i+1][j][k]+" ":ary[i+1][j][k]+" ");
			}
			for(int k=0; k<ary[i+2][j].length; k++) {
				System.out.print(k==0?"\t"+ary[i+2][j][k]+" ":ary[i+2][j][k]+" ");
			}
			for(int k=0; k<ary[i+3][j].length; k++) {
				System.out.print(k==0?"\t"+ary[i+3][j][k]+" ":ary[i+3][j][k]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
		i+=4;
		for(int j=0; j<ary[i].length; j++) {
			for(int k=0; k<ary[i][j].length; k++)
			{
				System.out.print(k==0?"\t"+ary[i][j][k]+" ":ary[i][j][k]+" ");
			}
			System.out.println("");
		}
	}
}

public class Step3 {
	public static void main(String[] args) {
		char[][][] ary=null;
		RubiksCube cube=new RubiksCube(ary);
	}
}
