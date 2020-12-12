package test.main;

import java.util.Scanner;

// 큐브(정육면체)의 각각의 면을 ENUM 형식으로 표현
interface Face{
	int UP=0; int LEFT=1; int FRONT=2; int RIGHT=3; int BACK=4; int DOWN=5;
}
class RubiksCube implements Face{
	// Constructor
	// 배열 초기화 및 초기상태 출력
	public RubiksCube(char[][][] ary) {
		printAry(initAry(ary));
	}
	// 배열을 매개변수로 받아 초기화하는 함수
	public char[][][] initAry(char[][][] ary) {
		ary[UP]= new char[][]{{'B','B','B'},{'B','B','B'},{'B','B','B'}};
		ary[LEFT]= new char[][]{{'W','W','W'},{'W','W','W'},{'W','W','W'}};
		ary[FRONT]= new char[][]{{'O','O','O'},{'O','O','O'},{'O','O','O'}};
		ary[RIGHT]= new char[][]{{'G','G','G'},{'G','G','G'},{'G','G','G'}};
		ary[BACK]= new char[][]{{'Y','Y','Y'},{'Y','Y','Y'},{'Y','Y','Y'}};
		ary[DOWN]= new char[][]{{'R','R','R'},{'R','R','R'},{'R','R','R'}};
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
	// U<->D 대칭(U=D', U'=D) // L<->R 대칭(L=R', L'=R) // F<->B 대칭 (F=B', F'=B)
	public void cubeMove(char[][][] ary,String[] commandAry) {
		int start=0;
		int end=ary[0].length-1;
		for(String command :commandAry) {
			if(command!=null) {
				System.out.println(command);
				switch(command) {
				case "U": leftMove(ary,start);break;
				case "U'": rightMove(ary,start);break;
				case "D": rightMove(ary,end);break;
				case "D'": leftMove(ary,end);break;
				case "L": downMove(ary,start);break;
				case "L'": upMove(ary,start);break;
				case "R": upMove(ary,end);break;
				case "R'": downMove(ary,end);break;
				case "F": fMove(ary,start);break;
				case "F'":f_Move(ary,start);break;
				case "B":
				case "B'":
				case "Q":
				}
			}
		}
	}
	public void leftMove(char[][][] ary,int startRow) {
//		for(int i=LEFT;i<BACK;i++) {
//			for(int j=0;j<ary[i][startRow].length;j++) {
//				if(i==LEFT) {
//					temp[j]=ary[i][startRow][j];
//				}
//				ary[i][startRow][j]=ary[i+1][startRow][j];
//			}
//		}
//		for(int i=0; i<3; i++) {
//			ary[BACK][startRow][i]=temp[i];
//		}
		// 이중 for문보다 간결하여 가독성이 좋아보임
		char[] temp=new char[3];
		for(int i=0; i<3; i++) {
			temp[i]=ary[LEFT][startRow][i];
			ary[LEFT][startRow][i]=ary[FRONT][startRow][i];
			ary[FRONT][startRow][i]=ary[RIGHT][startRow][i];
			ary[RIGHT][startRow][i]=ary[BACK][startRow][i];
			ary[BACK][startRow][i]=temp[i];
		}
		printAry(ary);
	}
	public void rightMove(char[][][] ary, int startRow) {
//		char[] temp=new char[3];
//		for(int i=BACK; i>LEFT; i--) {
//			for(int j=0; j<3; j++) {
//				if(i==BACK) {
//					temp[j]=ary[i][startRow][j];
//				}
//				ary[i][startRow][j]=ary[i-1][startRow][j];
//			}
//		}
//		for(int i=0; i<3; i++) {
//			ary[LEFT][startRow][i]=temp[i];
//		}
		// 이중 for문보다 간결하여 가독성이 좋아보임
		char[] temp=new char[3];
		for(int i=0; i<3; i++) {
			temp[i]=ary[BACK][startRow][i];
			ary[BACK][startRow][i]=ary[RIGHT][startRow][i];
			ary[RIGHT][startRow][i]=ary[FRONT][startRow][i];
			ary[FRONT][startRow][i]=ary[LEFT][startRow][i];
			ary[LEFT][startRow][i]=temp[i];
		}
		printAry(ary);
	}
	public void downMove(char[][][] ary, int startRow) {
		char[] temp=new char[3];
		for(int i=0; i<3; i++) {
			temp[i]=ary[BACK][i][startRow];
			ary[BACK][i][startRow]=ary[DOWN][i][startRow];
			ary[DOWN][i][startRow]=ary[FRONT][i][startRow];
			ary[FRONT][i][startRow]=ary[UP][i][startRow];
			ary[UP][i][startRow]=temp[i];
		}
		printAry(ary);
	}
	public void upMove(char[][][] ary, int startRow) {
		char[] temp=new char[3];
		for(int i=0; i<3; i++) {
			temp[i]=ary[UP][i][startRow];
			ary[UP][i][startRow]=ary[FRONT][i][startRow];
			ary[FRONT][i][startRow]=ary[DOWN][i][startRow];
			ary[DOWN][i][startRow]=ary[BACK][i][startRow];
			ary[BACK][i][startRow]=temp[i];
		}
		printAry(ary);
	}
	public void fMove(char[][][] ary, int startRow) {
		char[] temp=new char[3];
		for(int i=0; i<3; i++) {
			temp[i]=ary[LEFT][i][2];
			ary[LEFT][i][2]=ary[DOWN][0][i];
			ary[DOWN][0][i]=ary[RIGHT][i][0];
			ary[RIGHT][i][0]=ary[UP][2][i];
			ary[UP][2][i]=temp[i];
		}
		printAry(ary);
		
	}
	public void f_Move(char[][][] ary, int startRow) {
		char[] temp=new char[3];
		for(int i=0; i<3; i++) {
			temp[i]=ary[UP][2][i];
			ary[UP][2][i]=ary[RIGHT][i][0];
			ary[RIGHT][i][0]=ary[DOWN][0][i];
			ary[DOWN][0][i]=ary[LEFT][i][2];
			ary[LEFT][i][2]=temp[i];
		}
		printAry(ary);
	}
}

public class Step3 {
	public static void main(String[] args) {
		char[][][] ary=new char[6][][];
		RubiksCube cube=new RubiksCube(ary);
		//동작입력받기
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Cube> ");
		String input=keyboard.nextLine();
		// 입력받은 동작의 문자열을 담을 String형 배열 size는 임의의 max:10으로 결정
		String[] commandAry=new String[10];
		int cnt=0;
		for(Character c:input.toCharArray()) {
			// 뒤이어 '기호가 들어오면 기존에 commandAry에 합친다.(ex U+'=U')
			if(c=='\'') {
				commandAry[cnt-1]+="'";
				// 뒤이어 '2가 들어오면 기존에 commandAry에 합친다.(ex U+2=U2)
			} else if(c=='2'){
				commandAry[cnt-1]+="2";
			}
			// 입력받은 문자열을 하나씩 쪼개서 String 배열에 넣는다.
			else {
				commandAry[cnt++]=c.toString();
			}
		}
		cube.cubeMove(ary, commandAry);
	}
}
