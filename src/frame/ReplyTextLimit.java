package frame;
import javax.swing.text.*;
import javax.swing.*;

public class ReplyTextLimit extends PlainDocument{

   //PlainDocument = 텍스트 삽입의 결과로서 문서 구조를 갱신합니다.
   private int limit; 
   public ReplyTextLimit(int limit){
      super();
      this.limit = limit; //가져다 쓰기위해 사용
   }
   
   //메소드 재정의
   public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
      
	   
	   //AttributeSe 인터페이스  - 읽기 전용의 불변의 인터페이스입니다
	   //offs - 개시 오프셋(offset)>= 0
	   //str - 삽입하는 캐릭터 라인. null나 하늘의 캐릭터 라인의 경우는 아무것도 하지 않는다
	   //attr -  속성 세트(2개의 속성 세트가 동일한지 어떤지를 판정합니다. )
	   
      if(str == null)
         return;
      
      if(getLength() * str.length() <= limit){
         super.insertString(offset, str, attr);
      }
   }

}