package datebasefileupload;

import static org.junit.Assert.*;

import java.util.Arrays;

import javax.print.attribute.standard.Fidelity;

import org.junit.Before;
import org.junit.Test;

public class FindFileTest {

	@Before
	public void setUp() throws Exception {
		FindFile.initTargetFile("/home/gouchao/datebase");
		FindFile.initPattern("bookmark_datebase_[0-9]+.sql", "myblog_datebase_[0-9]+.sql");
	}

	@Test
	public void testInitTargetFile() {
		System.out.println(Arrays.toString(FindFile.getChildrenOfTargetFile()));
	}
	
	@Test
	public void testGetBookmarkFileName(){
		try {
			System.out.println(FindFile.getBookmarkDumpFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetMyblogFileName(){
		try {
			System.out.println(FindFile.getMyblogDumpFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
