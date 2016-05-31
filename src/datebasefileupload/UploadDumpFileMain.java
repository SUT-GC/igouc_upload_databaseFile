package datebasefileupload;

public class UploadDumpFileMain {

	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						System.out.println("开始上传.....");
						UploadDumpFile uploadDumpFile = new UploadDumpFile();
						uploadDumpFile.upload();
						System.out.println("上传完成......");
						this.sleep(1000*60*60*24);
					} catch (InterruptedException e) {
						System.out.println("上传失败！");
						e.printStackTrace();
						break;
					}
					
				}
			}
		}.start();
	}

}
