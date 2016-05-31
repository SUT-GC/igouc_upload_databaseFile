package datebasefileupload;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/*
 * 注意： 	服务器的文件是database
 * 				骑牛云的文件是datebase
 */
public class UploadDumpFile {
	
	private  final String ACCESS_KEY = "LJcxRfv5nTPm92JSUrLlP72BY7xPWjIq8Sh-m4Kf";
	private  final String SECRET_KEY ="147EU_I4oHeCCF5el0dBt7zoKpXI9EQmgp_cS9ze";
	
	private  final String REGEX_BOOKMARK = "bookmark_database_[0-9]+.sql";
	private  final String REGEX_MYBLOG = "myblog_database_[0-9]+.sql";
	
	private  final String BUCKET_NAME="datebase";
	private  final String ROOTPATH = "/root/";
	private  final String FILEROOTPATH=ROOTPATH+"datebase/";

	private  final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	private  UploadManager uploadManager;
	private  FindFile findFile;
	
	public UploadDumpFile() {
		try {
			uploadManager = new UploadManager();
			findFile.initTargetFile(FILEROOTPATH);
			findFile.initPattern(REGEX_BOOKMARK, REGEX_MYBLOG);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private  String getUploadToken(){
		return auth.uploadToken(BUCKET_NAME);
	}
	
	public  void upload(){
		
		try {
			String bookmarkFileName = findFile.getBookmarkDumpFileName();
			String myblogFileName = findFile.getMyblogDumpFileName();
			
			if(bookmarkFileName == ""){
				throw new Exception("bookmark 数据库备份文件不存在");
			}
			if(myblogFileName == ""){
				throw new Exception("myblog 数据库备份文件不存在");
			}
			
			Response resBookmark =  uploadManager.put(FILEROOTPATH+bookmarkFileName, bookmarkFileName, getUploadToken());
			Response resMyblog =  uploadManager.put(FILEROOTPATH+myblogFileName, myblogFileName, getUploadToken());
			
			System.out.println("上传bookmark返回的信息: "+resBookmark.bodyString());
			System.out.println("上传myblog返回的信息: "+resMyblog.bodyString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
