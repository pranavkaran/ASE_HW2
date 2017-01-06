package test;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.*;
import edu.SmallSet;

public class UnitTests {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testSetOfSingleParam() {
		try {
			int[] ear = new int[]{1,2,3,4,5,6};
		    int ux = 0;
		    int sz = 6; 
		    SmallSet s1 = new SmallSet(ear, ux, sz);
		    SmallSet s2 = new SmallSet(ear, ux, sz);
			
		    Class c = Class.forName("edu.SmallSet");
		    Object obj = s1;
		    Method method = c.getDeclaredMethod("setOf", new Class[]{SmallSet.class});
		    method.setAccessible(true);
		    
		    assertTrue(s2.nitems() == ((SmallSet) method.invoke(obj, s1)).nitems());
		} catch (IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Unit Test SetOfSingleParam: Passed!!");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testSetOfMultipleParam() {
		try {
			int[] ear = new int[]{1,2,3,4,5,6};
		    int ux = 0;
		    int sz = 6; 
		    SmallSet s1 = new SmallSet(ear, ux, sz);
		    SmallSet s2 = new SmallSet(ear, ux, sz);
			Class c = Class.forName("edu.SmallSet");
		    Object obj = s1;
		    Method method = c.getDeclaredMethod("setOf", new Class[]{int[].class, int.class, int.class});
		    method.setAccessible(true);
		    
		    assertTrue(s2.nitems() == ((SmallSet) method.invoke(obj, ear, ux, sz)).nitems());
		} catch (IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Unit Test SetOfMultipleParam: Passed!!");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testIndexOf() {
		try {
			int[] ear = new int[]{1,2,3,4,5,6};
		    int ux = 0;
		    int sz = 6; 
		    SmallSet s = new SmallSet(ear, ux, sz);
		    
		    Class c = Class.forName("edu.SmallSet");
		    Object obj = s; 
		    Method method = c.getDeclaredMethod("indexOf", new Class[]{int.class});
		    method.setAccessible(true);
		    assertTrue((int)(method.invoke(obj, 2)) == 2);
		} catch (IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Unit Test IndexOf: Passed!!");
	}
	
	@Test
	public void testIsIn() {
		int[] ear = new int[]{1,2,3,4,5,6};
		int ux = 0;
		int sz = 6; 
		SmallSet s = new SmallSet(ear, ux, sz);
			
		assertTrue(s.isin(3));
		System.out.println("Unit Test IsIn: Passed!!");
	}
	
	@Test
	public void testCompact() {
		int[] ear1 = new int[]{1,1,2,2,5,6};
		int ux1 = 0;
		int sz1 = 6; 
		SmallSet s1 = new SmallSet(ear1, ux1, sz1);
		int[] ear2 = new int[]{1,2,5,6};
		int ux2 = 0;
		int sz2 = 4;
		SmallSet s2 = new SmallSet(ear2, ux2, sz2);
		s1.compact();
		s2.compact();
			
		assertTrue(s1.nitems() == s2.nitems());
		System.out.println("Unit Test Compact: Passed!!");
	}
	
	@Test
	public void testInsert() {
		int[] ear = new int[]{1,1,2,2,5,6};
		int ux = 0;
		int sz = 6; 
		SmallSet s = new SmallSet(ear, ux, sz);
		s.insert(7);
		
		assertTrue(s.isin(7));
		System.out.println("Unit Test Insert: Passed!!");
	}
	
	@Test
	public void testDelete() {
		int[] ear = new int[]{1,1,2,2,5,6};
		int ux = 0;
		int sz = 6; 
		SmallSet s = new SmallSet(ear, ux, sz);
		s.delete(6);
		
		assertTrue(!s.isin(6));
		System.out.println("Unit Test Delete: Passed!!");
	}
	
	@Test
	public void testNitems() {
		int[] ear = new int[]{1,1,2,2,5,6};
		int ux = 0;
		int sz = 6; 
		SmallSet s = new SmallSet(ear, ux, sz);
		
		assertTrue(s.nitems() == 4);
		System.out.println("Unit Test Nitems: Passed!!");
	}
	
	@Test
	public void testUnion() {
		int[] ear1 = new int[]{1,2,3,4,5,6};
		int ux1 = 0;
		int sz1 = 6; 
		SmallSet s1 = new SmallSet(ear1, ux1, sz1);
		int len1 = s1.nitems();
		int[] ear2 = new int[]{7,8,9};
		int ux2 = 0;
		int sz2 = 3;
		SmallSet s2 = new SmallSet(ear2, ux2, sz2);
		int len2 = s2.nitems();
		s1.union(s2);
		
		assertTrue(len1 + len2 == s1.nitems());
		System.out.println("Unit Test Union: Passed!!");
	}
	
	@Test
	public void testDiff() {
		int[] ear1 = new int[]{1,2,3,4,5,6};
		int ux1 = 0;
		int sz1 = 6; 
		SmallSet s1 = new SmallSet(ear1, ux1, sz1);
		int len1 = s1.nitems();
		int[] ear2 = new int[]{4,5,6};
		int ux2 = 0;
		int sz2 = 3;
		SmallSet s2 = new SmallSet(ear2, ux2, sz2);
		int len2 = s2.nitems();
		s1.diff(s2);
		
		assertTrue(len1 - len2 == s1.nitems());
		System.out.println("Unit Test Diff: Passed!!");
	}
}
