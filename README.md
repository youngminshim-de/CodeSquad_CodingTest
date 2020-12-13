# Step-3 (루빅스 큐브 구현하기)
< 전체적인 구조는 Step-2 평면큐브와 동일함
< 다만 3차원 배열(char [6][3][3]) 을 이용하여 정육면체를 표현하였다.
< 또한 interface를 이용하여 가독성을 좋게하기 위해 static final int로 표현하였다.
<pre>
<code>
// 큐브(정육면체)의 각각의 면을 ENUM 형식으로 표현
interface Face{
	int UP=0; int LEFT=1; int FRONT=2; int RIGHT=3; int BACK=4; int DOWN=5;
}
</pre>
</code>
## public char[][][] initAry(char[][][] ary)
< 3차원배열을 초기화하는 함수
< 3차원 배열이다 보니 for문을 이용하는 것보다 직접 값을 넣어주는 것이 가독성이 좋다고 판단함
<pre>
<code>
	public char[][][] initAry(char[][][] ary) {
		ary[UP]= new char[][]{{'B','B','B'},{'B','B','B'},{'B','B','B'}};
		ary[LEFT]= new char[][]{{'W','W','W'},{'W','W','W'},{'W','W','W'}};
		ary[FRONT]= new char[][]{{'O','O','O'},{'O','O','O'},{'O','O','O'}};
		ary[RIGHT]= new char[][]{{'G','G','G'},{'G','G','G'},{'G','G','G'}};
		ary[BACK]= new char[][]{{'Y','Y','Y'},{'Y','Y','Y'},{'Y','Y','Y'}};
		ary[DOWN]= new char[][]{{'R','R','R'},{'R','R','R'},{'R','R','R'}};
		return ary;
	}
</code>
</pre>
## public void printAry(char[][][] ary)
< 문제에서 요구하는 출력형태로 큐브를 출력하기위한 함수
## public void cubeMove(char[][][] ary, String[] commandAry)
< 각 각의 action에 대해 step-2와 마찬가지로 switch문을 통해 처리하도록 코드를 구현하였다.
< 각 각의 동작에 대해 큐브의 모든면이 다 맞았는지 체크하는 함수 checkCube(ary)를 호출하여
< 큐브의 모든면이 맞았을 시, 동작을 즉시 중단하도록 코드를 구현하였다.
<pre>
<code>
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
</code>
</pre>
## public void leftMove(char[][][] ary, int startRow)
< 3차원배열의 특성상 row의 swap의 경우 모든 column에 대해 진행할 필요 없이 참조값만 전달해 주면
< 되므로 주소값을 통해 swap하였다.
< 왼쪽면<-앞면<-오른쪽면<-뒷면 순으로 코드를 작성하였다. (Action U&D)
<pre>
<code>
		char[] temp=new char[3];
		temp=ary[LEFT][startRow];
		ary[LEFT][startRow]=ary[FRONT][startRow];
		ary[FRONT][startRow]=ary[RIGHT][startRow];
		ary[RIGHT][startRow]=ary[BACK][startRow];
		ary[BACK][startRow]=temp;
</code>
</pre>
## public void rightMove(char[][][] ary, int startRow)
< leftMove 함수와 동일하며, 방향만 반대이다.
## public void upMove(char[][][] ary, int startRow)
< column의 이동을 표현하였다. (Action L&R)
< UP<-FRONT<-DOWN<-BACK
<pre>
<code>
		char[] temp=new char[3];
		for(int i=0; i<3; i++) {
			temp[i]=ary[UP][i][startRow];
			ary[UP][i][startRow]=ary[FRONT][i][startRow];
			ary[FRONT][i][startRow]=ary[DOWN][i][startRow];
			ary[DOWN][i][startRow]=ary[BACK][i][startRow];
			ary[BACK][i][startRow]=temp[i];
		}
</code>
</pre>
## public void downMove(char[][][] ary, int startRow)
< upMove 함수와 동일하며, 방향만 반대이다.
<
< row와 column 간의 이동도 일어나므로 새로 추가한 코드이다. (즉 90도 회전을 위한 함수)
## public void fMove(char[][][] ary, int startRow)
< LEFT(column)<-DOWN(row)<-RIGHT(column)<-UP(row) (Action F&B)
<pre>
<code>
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
</code>
</pre>
## public void f_Move(char[][][] ary, int startRow)
< f_move 함수와 동일하며, 방향만 반대이다.
## public boolean checkCube(char[][][] ary)
< 모든 큐브의 면이 다 맞았는지 확인하는 함수 (추가구현)
< 3X3 임시 배열을 만들어 현재 큐브의 한면에 들어있는 값 하나로 다 채운다.
< 모든면이 다 맞았다면 현재 큐브의 한면에 어떤 값을 복사하더라도 3X3 임시배열과 같아야 하므로
< 이 같은 작업을 모든면에 대해(6번) 반복한다.
< 비교는 Arrays.deepEquals 함수를 이용하여 중첩 for문의 사용을 줄이도록 노력하였다.
< 모든면이 맞다면 true를, 그렇지 않다면 false를 return한다.
<pre>
<code>
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
</code>
</pre>
