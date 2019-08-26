package hpt.has.shinhan.thread;

import java.util.ArrayList;
import java.util.List;

public class DivideList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> mainList = new ArrayList();
		for(int i = 0; i < 250001; i++)
		{
			mainList.add(i+1);
		}
		List<List<Integer>> distributeList = distributeList2(mainList, 5);
		int j = 1;
		for (List<Integer> list : distributeList) {
			System.out.print("List " + j + " (size = " + list.size() + "): ");
			for(int i : list)
			{
				System.out.print(i + " ");
			}
			System.out.println();
			j++;
		}
	}

	public static <M> List< List<M> > distributeList(List<M> mainList, int subSize)
	{
		List< List<M> > listArray = new ArrayList<>();
		try {
			int iteratorMainList = 0;
			int divineNumber = mainList.size() / subSize;
			if(mainList.size() > 1)
			{
				for(int i = 0; i < subSize - 1; i++)
				{
					List<M> subListArray = new ArrayList<>();
					for(int j = 0; j < divineNumber; j++)
					{
						subListArray.add( mainList.get(iteratorMainList++) );
					}
					listArray.add(subListArray);
				}
				
				List<M> subListArray = new ArrayList<>();
				for(int j = iteratorMainList; j < mainList.size(); j++)
				{
					subListArray.add( mainList.get(iteratorMainList++) );
				}
				listArray.add(subListArray);
			} 
			else {
				listArray.add(mainList);
			} 
		}
		catch(Exception e) {
			System.out.println("hpt.has.shinhan.thread.DivideList.distributeList.Error distributeList: " + e);
		}			
		return listArray;
	}
	
	public static <M> List< List<M> > distributeList1(List<M> mainList, int subSize)
	{
		List< List<M> > listArray = new ArrayList<>();
		try {
			int divineNumber = mainList.size() / subSize;
			int from = 0;
			int to = from + divineNumber + 1;
			if(mainList.size() > 1)
			{
				for(int i = 0; i < subSize; i++)
				{
					List<M> subListArray = mainList.subList(from, to);
					from = to;
					to = to + divineNumber + 1;
					if(to >= mainList.size())
						to = mainList.size();
					listArray.add(subListArray);
				}				
			} 
			else { 
				listArray.add(mainList);
			}
		}
		catch(Exception e) {
			System.out.println("hpt.has.shinhan.thread.DivideList.distributeList.Error distributeList: " + e);
		}			
		return listArray;
	}
	
	public static <M> List< List<M> > distributeList2(List<M> mainList, int subSize)
	{
		List< List<M> > listArray = new ArrayList<>();
		try {
			int divineNumber = mainList.size() / subSize;
			int from = 0;
			int to = from + divineNumber;
			if(mainList.size() > 1)
			{
				for(int i = 0; i < subSize; i++)
				{
					List<M> subListArray = mainList.subList(from, to);
					from = to;
					to = to + divineNumber;
					if(i + 1 == subSize - 1) 
					{
						to = mainList.size();
					}						
					listArray.add(subListArray);
				}
				
			} 
			else 
			{
				listArray.add(mainList);			
			}
		}
		catch(Exception e) {
			System.out.println("hpt.has.shinhan.thread.DivideList.distributeList.Error distributeList: " + e);
		}			
		return listArray;
	}
}
