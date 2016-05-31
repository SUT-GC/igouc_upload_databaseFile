package datebasefileupload;

import java.io.File;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFile {

	private static Pattern patternBookmark;
	private static Pattern patternMyblog;
	private static File targetFile;
	private static String[] childrenOfTargetFile;
	

	public static File getTargetFile() {
		return targetFile;
	}

	public static String[] getChildrenOfTargetFile() {
		return childrenOfTargetFile;
	}

	
	public static void initPattern(String regexBookmark, String regexMyblog){
		patternBookmark = Pattern.compile(regexBookmark);
		patternMyblog = Pattern.compile(regexMyblog);
	}
	
	public static void initTargetFile(String filename) throws Exception{
		targetFile = new File(filename);
		if(targetFile.exists() && targetFile.isDirectory()){
			childrenOfTargetFile =targetFile.list();
		}else{
			throw new Exception("文件目录不存在或者文件不是目录") ;
		}
	}
	
	public static String getBookmarkDumpFileName() throws Exception{
		String filename = "";
		if(childrenOfTargetFile == null || childrenOfTargetFile.length == 0){
			throw new Exception("目标文件夹为空");
		}
		for(String nowFileName : childrenOfTargetFile){
			Matcher matcher = patternBookmark.matcher(nowFileName);
			if(matcher.find()){
				filename = matcher.group();
				break;
			}
		}
		return filename;
	}
	
	public static String getMyblogDumpFileName() throws Exception{
		String filename = "";
		if(childrenOfTargetFile == null || childrenOfTargetFile.length == 0){
			throw new Exception("目标文件夹为空");
		}
		for(String nowFileName : childrenOfTargetFile){
			Matcher matcher = patternMyblog.matcher(nowFileName);
			if(matcher.find()){
				filename = matcher.group();
				break;
			}
		}
		return filename;
	}
}
