package com.gdssoft.cqjt.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {
	public static void main(String[] args) {

		Str zlj = new Str("丁晓宇", 21);
		Str dxy = new Str("赵四", 22);
		Str cjc = new Str("张三", 21);
		Str lgc = new Str("刘武", 19);

		List<Str> StrList = new ArrayList<Str>();
		StrList.add(zlj);
		StrList.add(dxy);
		StrList.add(cjc);
		StrList.add(lgc);
		
		Test test = new Test();
		test.sortIntMethod(StrList);

		
	}
	
	@SuppressWarnings("unchecked")
	public void sortIntMethod(List list){  
		Collections.sort(list, new Comparator(){        
			@Override        
			public int compare(Object o1, Object o2) {            
				Str stu1=(Str)o1;            
				Str stu2=(Str)o2;            
				if(stu1.getA()>stu2.getA()){                
					return 1;            
					}else if(stu1.getA()==stu2.getA()){ 
						return 0;    
						}else{       
							return -1;     
							}        }    
			});  
		System.out.println("/////////////排序之后///////////////");  
		for(int i=0;i<list.size();i++){    
			Str st=(Str)list.get(i);    
			System.out.println("st.age="+st.getA()+",st.name="+st.getB());  
			}
		}
	
}


