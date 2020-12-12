package test.main;

import java.util.Scanner;

// ť��(������ü)�� ������ ���� ENUM �������� ǥ��
interface Face{
	int UP=0; int LEFT=1; int FRONT=2; int RIGHT=3; int BACK=4; int DOWN=5;
}
class RubiksCube implements Face{
	// Constructor
	// �迭 �ʱ�ȭ �� �ʱ���� ���
	public RubiksCube(char[][][] ary) {
		printAry(initAry(ary));
	}
	// �迭�� �Ű������� �޾� �ʱ�ȭ�ϴ� �Լ�
	public char[][][] initAry(char[][][] ary) {
		ary[UP]= new char[][]{{'B','B','B'},{'B','B','B'},{'B','B','B'}};
		ary[LEFT]= new char[][]{{'W','W','W'},{'W','W','W'},{'W','W','W'}};
		ary[FRONT]= new char[][]{{'O','O','O'},{'O','O','O'},{'O','O','O'}};
		ary[RIGHT]= new char[][]{{'G','G','G'},{'G','G','G'},{'G','G','G'}};
		ary[BACK]= new char[][]{{'Y','Y','Y'},{'Y','Y','Y'},{'Y','Y','Y'}};
		ary[DOWN]= new char[][]{{'R','R','R'},{'R','R','R'},{'R','R','R'}};
		return ary;
	}
	// ������¿� �°� �迭�� ������ִ� �Լ�
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
	// U<->D ��Ī(U=D', U'=D) // L<->R ��Ī(L=R', L'=R) // F<->B ��Ī (F=B', F'=B)
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
		// ���� for������ �����Ͽ� �������� ���ƺ���
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
		// ���� for������ �����Ͽ� �������� ���ƺ���
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
		//�����Է¹ޱ�
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Cube> ");
		String input=keyboard.nextLine();
		// �Է¹��� ������ ���ڿ��� ���� String�� �迭 size�� ������ max:10���� ����
		String[] commandAry=new String[10];
		int cnt=0;
		for(Character c:input.toCharArray()) {
			// ���̾� '��ȣ�� ������ ������ commandAry�� ��ģ��.(ex U+'=U')
			if(c=='\'') {
				commandAry[cnt-1]+="'";
				// ���̾� '2�� ������ ������ commandAry�� ��ģ��.(ex U+2=U2)
			} else if(c=='2'){
				commandAry[cnt-1]+="2";
			}
			// �Է¹��� ���ڿ��� �ϳ��� �ɰ��� String �迭�� �ִ´�.
			else {
				commandAry[cnt++]=c.toString();
			}
		}
		cube.cubeMove(ary, commandAry);
	}
}
