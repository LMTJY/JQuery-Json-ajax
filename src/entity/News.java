package entity;

public class News {
	private int nid;
	private String ntitle;
	private int tid;
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public News(int nid, String ntitle, int tid) {
		super();
		this.nid = nid;
		this.ntitle = ntitle;
		this.tid = tid;
	}
	public News() {
		super();
	}
	
}
