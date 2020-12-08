package test.main;

import java.util.Random;
import java.util.Scanner;

class PlaneCube{
	// Constructor : �迭 �ʱ�ȭ �� �ʱ���� ���
	public PlaneCube(char[][] ary) {
		printAry(initAry(ary));
	}
	// Random���� A~Z������ char�� �߻����� �迭�� �ʱ�ȭ�ϴ� �Լ�
	public char[][] initAry(char[][] ary) {
		
		for(int i=0; i<ary.length; i++) {
			for(int j=0; j<ary.length; j++) {
				Random ran=new Random();
				// ASCII CODE A(65) ~ Z(90) ������ �����߻�
				ary[i][j]=(char)(ran.nextInt(26)+65);
			}
		}
		return ary;
	}
	// �迭�� ������ִ� �Լ�
	public void printAry(char[][] ary) {
		for(int i=0; i<ary.length; i++) {
			for(int j=0; j<ary.length; j++) {
				System.out.print(ary[i][j]+" ");
			}
			System.out.println(" ");
		}
	}
	// �迭 ary�� ActionList�� �Ű������� �޾� cube�� �̵���Ű�� �Լ�
	public void cubeMove(char[][] ary, String[] actionStr) {
		int start=0; // ���� �m�� Ȥ�� ���� ������ ǥ���ϱ� ���� ����
		int end=ary.length-1; // ���� �Ʒ��� Ȥ�� ���� �������� ǥ���ϱ� ���� ����
		
		for(String s:actionStr) {
			if(s!=null) {
				switch(s) {
				case "U": leftMove(ary,start);break;
				case "U'": rightMove(ary,start);break;
				case "R": upMove(ary,end);break;
				case "R'": downMove(ary,end);break;
				case "L": downMove(ary,start);break;
				case "L'": upMove(ary,start);break;
				case "B": rightMove(ary,end);break;
				case "B'": leftMove(ary,end);break;
				case "Q": System.out.println("Bye~");break;
				}
				if(s.equals("Q")) {
					break;
				}
				System.out.println(s);
				printAry(ary);
			}
			else break;
		}
	}
	// �������� 1ĭ �̵���Ű�� �Լ�
	public char[][] leftMove(char[][] ary, int startRow) {
		char temp=ary[startRow][0];
		for(int i=0; i<ary.length-1; i++) {
			ary[startRow][i]=ary[startRow][i+1];
		}
		ary[startRow][ary.length-1]=temp;
		return ary;
	}
	// ���������� 1ĭ �̵���Ű�� �Լ�
	public char[][] rightMove(char[][] ary, int startRow) {
		char temp=ary[startRow][ary.length-1];
		for(int i=ary.length-1; i>0; i--) {
			ary[startRow][i]=ary[startRow][i-1];
		}
		ary[startRow][0]=temp;
		return ary;
	}
	// �������� 1ĭ �̵���Ű�� �Լ�
	public char[][] upMove(char[][] ary, int startColumn) {
		char temp=ary[0][startColumn];
		for(int j=0; j<ary.length-1; j++) {
			ary[j][startColumn]=ary[j+1][startColumn];
		}
		ary[ary.length-1][startColumn]=temp;
		return ary;
	}
	// �Ʒ������� 1ĭ �̵���Ű�� �Լ�
	public char[][] downMove(char[][] ary, int startColumn) {
		char temp=ary[ary.length-1][startColumn];
		for(int j=ary.length-1; j>0; j--) {
			ary[j][startColumn]=ary[j-1][startColumn];
		}
		ary[0][startColumn]=temp;
		return ary;
	}
}

public class Step2 {

	public static void main(String[] args) {
		
		char[][] ary=new char[3][3];
		// �迭�� ����� �Ű������� �ϴ� PlaneCube�� Constructor
		PlaneCube cube=new PlaneCube(ary);
		
		//�����Է¹ޱ�
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Cube> ");
		String input=keyboard.nextLine();
		// �Է¹��� ������ ���ڿ��� ���� String�� �迭 size�� ������ max:10���� ����
		String[] actionStr=new String[10];
		int cnt=0;
		for(Character c:input.toCharArray()) {
			// ���̾� '��ȣ�� ������ ������ actionStr�� ��ģ��.(ex U+'=U')
			if(c=='\'') {
				actionStr[cnt-1]+="'";
			} // �Է¹��� ���ڿ��� �ϳ��� �ɰ��� String �迭�� �ִ´�.
			else {
				actionStr[cnt++]=c.toString();
			}
		}
		cube.cubeMove(ary, actionStr);
	}
}
