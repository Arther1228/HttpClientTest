package test;

public class OnlineRequest {

	private  String uid;
	private  String apmac;
	private  String timestamp;
	private  String auth;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getApmac() {
		return apmac;
	}
	public void setApmac(String apmac) {
		this.apmac = apmac;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String toString() {
		return "{\"uid\":\"" + uid + "\",\"apmac\":\""+apmac+"\",\"timestamp\":\""+timestamp+"\",\"authenticator\":\""+auth+"\"}";
	}
}
