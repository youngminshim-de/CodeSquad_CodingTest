# Step-2 (평면큐브 구현하기)

## public char[][] initAry(char[][] ary)
> A~Z 까지 Char형 난수를 발생시켜 3X3배열을 초기화하는 함수
## public void printAry(char[][] ary)
> 3X3배열을 출력해주는 함수
## public void cubeMove(char[][] ary, String[] actionStr)
> 배열과, 실행할 동작들을 매개변수를 전달받아 실행하는 함수
> String 배열의 형태로 넘어온 동작들을 반복문을 통해 동작하나씩 실행할 수 있게 한다.
> Switch문을 통해 각 각의 동작들에 대해 알맞은 함수를 호출해준다.(U,U',R,R',L,L',B,B,')
> U-B // L-R 은 대칭구조이다.
> 즉, U=B' U'=B 와 같은 움직임을 가진다.(단, 이동시키는 ROW가 다르다. U는 0번째 ROW// B는 마지막 ROW)
> 따라서 실질적으로 동작이 일어나는 Move함수를 호출할때 각 case별로 움직일 Column이나 Row의 값을 매개변수로 전달해준다.
<pre>
<code>
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
				}
				System.out.println(s);
				printAry(ary);
			}
			else break;
		}
	}
</code>
</pre>

## public char[][] leftMove(char[][] ary, int startRow)
> 배열의 원소들을 왼쪽으로 한칸 씩 이동시킨다. (같은 row의 column 이동)
## public char[][] rightMove(char[][] ary, int startRow)
> 배열의 원소들을 오른쪽으로 한칸 씩 이동시킨다. (같은 row의 column 이동)
## public char[][] upMove(char[][] ary, int startRow)
> 배열의 원소들을 위쪽으로 한칸 씩 이동시킨다. (같은 column의 row 이동)
## public char[][] downMove(char[][] ary, int startRow)
> 배열의 원소들을 아래쪽으로 한칸 씩 이동시킨다. (같은 column의 row 이동)
> 네 함수 모두 원리는 step-1에서 구현한 함수와 같다.
