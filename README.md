# Step-1 (단어 밀어내기 구현하기)

*public static char[] sepWord(String[] str)
 StringTokenizer 객체를 이용하여 공백으로 구분되는 문자, 이동할방향, 이동횟수를 각각 전달받는다.
 String[0]=문자열
 String[1]=이동할 방향
 String[2]=이동횟수
 
 각각 L(l), R(r) 이 들어왔을 경우를 처리해주고, 또한 이동횟수가 -(음수)일 경우 해당방향의 역방향으로
 문자열을 이동시키도록 Move 함수를 호출한다.

// 오른쪽으로 단어를 밀어내는 함수
*public static char[] rightMove(char[] result, int num)
  String[0]에 있는 문자열을 char[] 형 배열로 바꾸어 문자단위로 처리하며
  맨 오른쪽에 있는 수부터 swap 해 나간다. 
  한칸씩 움직이므로 매개변수인 num만큼 반복문을 돌려 원하는 이동횟수만큼 이동시킨다.

// 왼쪽으로 단어를 밀어내는 함수
*public static char[] leftMove(char[] result, int num)
 RightMove함수와 동일한 형태이며, 맨 왼쪽에 있는 수부터 swap 해 나간다.
