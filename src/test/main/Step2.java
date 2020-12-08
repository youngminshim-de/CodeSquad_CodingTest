package test.main;

import java.util.Random;
import java.util.Scanner;

class PlaneCube{
	// Constructor : 배열 초기화 및 초기상태 출력
	public PlaneCube(char[][] ary) {
		printAry(initAry(ary));
	}
	// Random으로 A~Z까지의 char을 발생시켜 배열을 초기화하는 함수
	public char[][] initAry(char[][] ary) {
		
		for(int i=0; i<ary.length; i++) {
			for(int j=0; j<ary.length; j++) {
				Random ran=new Random();
				// ASCII CODE A(65) ~ Z(90) 까지의 난수발생
				ary[i][j]=(char)(ran.nextInt(26)+65);
			}
		}
		return ary;
	}
	// 배열을 출력해주는 함수
	public void printAry(char[][] ary) {
		for(int i=0; i<ary.length; i++) {
			for(int j=0; j<ary.length; j++) {
				System.out.print(ary[i][j]+" ");
			}
			System.out.println(" ");
		}
	}
	// 배열 ary와 ActionList를 매개변수로 받아 cube를 이동시키는 함수
	public void cubeMove(char[][] ary, String[] actionStr) {
		int start=0; // 가장 웟쪽 혹은 가장 왼쪽을 표현하기 위한 변수
		int end=ary.length-1; // 가장 아래쪽 혹은 가장 오른쪽을 표현하기 위한 변수
		
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
	// 왼쪽으로 1칸 이동시키는 함수
	public char[][] leftMove(char[][] ary, int startRow) {
		char temp=ary[startRow][0];
		for(int i=0; i<ary.length-1; i++) {
			ary[startRow][i]=ary[startRow][i+1];
		}
		ary[startRow][ary.length-1]=temp;
		return ary;
	}
	// 오른쪽으로 1칸 이동시키는 함수
	public char[][] rightMove(char[][] ary, int startRow) {
		char temp=ary[startRow][ary.length-1];
		for(int i=ary.length-1; i>0; i--) {
			ary[startRow][i]=ary[startRow][i-1];
		}
		ary[startRow][0]=temp;
		return ary;
	}
	// 위쪽으로 1칸 이동시키는 함수
	public char[][] upMove(char[][] ary, int startColumn) {
		char temp=ary[0][startColumn];
		for(int j=0; j<ary.length-1; j++) {
			ary[j][startColumn]=ary[j+1][startColumn];
		}
		ary[ary.length-1][startColumn]=temp;
		return ary;
	}
	// 아래쪽으로 1칸 이동시키는 함수
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
		// 배열의 사이즈를 매개변수로 하는 PlaneCube의 Constructor
		PlaneCube cube=new PlaneCube(ary);
		
		//동작입력받기
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Cube> ");
		String input=keyboard.nextLine();
		// 입력받은 동작의 문자열을 담을 String형 배열 size는 임의의 max:10으로 결정
		String[] actionStr=new String[10];
		int cnt=0;
		for(Character c:input.toCharArray()) {
			// 뒤이어 '기호가 들어오면 기존에 actionStr에 합친다.(ex U+'=U')
			if(c=='\'') {
				actionStr[cnt-1]+="'";
			} // 입력받은 문자열을 하나씩 쪼개서 String 배열에 넣는다.
			else {
				actionStr[cnt++]=c.toString();
			}
		}
		cube.cubeMove(ary, actionStr);
	}
}
