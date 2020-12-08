package test.main;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Step1 {

	public static void main(String[] args) {
		// ���ڿ� �Է¹ޱ�
		Scanner keyboard=new Scanner(System.in);
		String input=keyboard.nextLine();
		// �Է¹��� ���ڿ��� " "�� �����Ͽ� ���� String �迭�� ���
		String[] separateInput=new String[3];
		int cnt=0;
		StringTokenizer token=new StringTokenizer(input);
		while(token.hasMoreTokens()) {
			separateInput[cnt++]=token.nextToken();
		}
		
		char[] result=sepWord(separateInput);
		//������
		for(char c:result) {
			System.out.print(c);
		}
	} 
	public static char[] sepWord(String[] str) {
		char[] result=str[0].toCharArray();
		
		if(str[2]=="l" || str[2]=="L" && Integer.parseInt(str[1]) >0) {
			return leftMove(result,Integer.parseInt(str[1]));
		}
		else if(str[2]=="r" || str[2]=="R" && Integer.parseInt(str[1])>0){
			return rightMove(result,Integer.parseInt(str[1]));
		}
		// �������� �������� ó��
		else if(str[2]=="l" || str[2]=="L" && Integer.parseInt(str[1])<0) {
			return rightMove(result,Math.abs(Integer.parseInt(str[1])));
		}
		else {
			return leftMove(result,Math.abs(Integer.parseInt(str[1])));
		}
	}
	public static char[] rightMove(char[] result, int num) {
		//���������� ��ĭ�̵�
		for(int i=0; i<num; i++) {
			char temp=result[result.length-1];
			for(int j=result.length-1; j>0; j--) {
				result[j]=result[j-1];
			}
			result[0]=temp;
		}
		return result;
	}
	public static char[] leftMove(char[] result, int num) {
		//�������� ��ĭ�̵�
		for(int i=0; i<num; i++) {
			char temp=result[0];
			for(int j=0; j<result.length-1; j++) {
				result[j]=result[j+1];
			}
			result[result.length-1]=temp;
		}
		return result;
	}
}
