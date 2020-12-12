package test.main;

import java.util.Arrays;
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
	public boolean cubeMove(char[][][] ary,String[] commandAry) {
		int start=0;
		int end=ary[0].length-1;
		for(String command :commandAry) {
			if(command!=null) {
				switch(command) {
				case "U": leftMove(ary,start);break;
				case "U'": rightMove(ary,start);break;
				case "D": rightMove(ary,end);break;
				case "D'": leftMove(ary,end);break;
				case "L": downMove(ary,start);break;
				case "L'": upMove(ary,start);break;
				case "R": upMove(ary,end);break;
				case "R'": downMove(ary,end);break;
				case "F": fMove(ary,end);break;
				case "F'":f_Move(ary,end);break;
				case "B": fMove(ary,start);break;
				case "B'":f_Move(ary,start);break;
				}
				System.out.println(command);
				printAry(ary);
				// 큐브의 모든면이 맞았는지 체크하는 함수 호출
				// 모든면이 맞았다면 false를 return 하여 메인메소드를 중지시킨다.
				if(checkCube(ary)) {
					System.out.println("축하합니다. 큐브가 다 맞춰졌습니다.");
					return false;
				}
			} else {break;}
		}
		return true;
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
	}
	public void fMove(char[][][] ary, int startRow) {
		char[] temp=new char[3];
		// 90도회전을 위한 숫자 배열의 index(startRow)가 0이 전달되면  2가 되고
		// index가 2가 전달되면 0이된다.
		int rotation=Math.abs(startRow-2);
		for(int i=0; i<3; i++) {
			temp[i]=ary[LEFT][i][startRow];
			ary[LEFT][i][startRow]=ary[DOWN][rotation][i];
			ary[DOWN][rotation][i]=ary[RIGHT][i][rotation];
			ary[RIGHT][i][rotation]=ary[UP][startRow][i];
			ary[UP][startRow][i]=temp[i];
		}
	}
	public void f_Move(char[][][] ary, int startRow) {
		char[] temp=new char[3];
		// 90도회전을 위한 숫자 배열의 index(startRow)가 0이 전달되면  2가 되고
		// index가 2가 전달되면 0이된다.
		int rotation=Math.abs(startRow-2);
		for(int i=0; i<3; i++) {
			temp[i]=ary[UP][startRow][i];
			ary[UP][startRow][i]=ary[RIGHT][i][rotation];
			ary[RIGHT][i][rotation]=ary[DOWN][rotation][i];
			ary[DOWN][rotation][i]=ary[LEFT][i][startRow];
			ary[LEFT][i][startRow]=temp[i];
		}
	}
	// 모든면이 맞았는지 확인하는 함수
	public boolean checkCube(char[][][] ary) {
		char[][] tempAry=new char[3][3];
		for(int i=0; i<ary.length; i++) {
			for(int j=0; j<tempAry.length; j++) {
				for(int k=0; k<tempAry.length; k++) {
					tempAry[j][k]=ary[i][0][0];
				}
			}
			if(!Arrays.deepEquals(tempAry, ary[i])) {
				return false;
			}
		}
		return true;
	}
}

public class Step3 {
	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		char[][][] ary=new char[6][][];
		RubiksCube cube=new RubiksCube(ary);
		boolean isContinue=true;
		int cnt=0;
		//동작입력받기
		while(isContinue) {
			Scanner keyboard=new Scanner(System.in);
			System.out.print("Cube> ");
			String input=keyboard.nextLine();
			// Q가 나오면 프로그램을 종료한다.
			if(input.equals("Q")) {
				System.out.println("조작갯수: "+cnt);
				break;
			}
			else {
				// 입력받은 동작의 문자열을 담을 String형 배열 size는 임의의 max:20으로 결정
				String[] commandAry=new String[20];
				cnt=0;
				for(Character c:input.toCharArray()) {
					// 뒤이어 '기호가 들어오면 기존에 commandAry에 합친다.(ex U+'=U')
					if(c=='\'') {
						commandAry[cnt-1]+="'";
						// 뒤이어 2가 들어오면 기존에 이전 명령어를 commandAry에 하나더 추가한다.
					} else if(c=='2'){
						commandAry[cnt]=commandAry[cnt-1];
						cnt++;
					}
					// 입력받은 문자열을 하나씩 쪼개서 String 배열에 넣는다.
					else {
						commandAry[cnt++]=c.toString();
					}
				}
				isContinue=cube.cubeMove(ary, commandAry);
			}
		}
		// 경과시간을 출력한다.
		long endTime=System.currentTimeMillis();
		System.out.println("경과시간: " + (endTime-startTime)/1000 +"초");
	}
}
